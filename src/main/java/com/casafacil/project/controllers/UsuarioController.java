package com.casafacil.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.casafacil.project.models.Credenciales;
import com.casafacil.project.models.Publicacion;
import com.casafacil.project.models.PublicacionFormulario;
import com.casafacil.project.models.Usuario;
import com.casafacil.project.services.AlmacenServiceImpl;
import com.casafacil.project.services.PublicacionFormularioImpl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = {"/", "home"})
public class UsuarioController {

    @Autowired
    private AlmacenServiceImpl almacenService;
    @Autowired
    private PublicacionFormularioImpl publicacionFormularioService;
    private RestTemplate restTemplate;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home(HttpSession session) {
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        return new ModelAndView("index")
                .addObject("credencial", credenciales)
                .addObject("usuario", user);
    }

    @GetMapping("/customer/account/login")
    public ModelAndView mostrarLoginUsuario() {
        return new ModelAndView("./views/login.html").
                addObject("titulo", "Iniciar sesión").
                addObject("credenciales", new Credenciales());
    }

    //LOGIN
    @PostMapping("/customer/account/login")
    public ModelAndView validarCredenciales(@Validated Credenciales c, BindingResult bindingResult, HttpSession session) {
        if (c.getCorreo() == null || c.getClave() == null || bindingResult.hasErrors()) {
            return new ModelAndView("./views/login")
                    .addObject("titulo", "Iniciar sesión")
                    .addObject("credenciales", c);
        } else {
            String url = "http://localhost:8050/api/usuarios/" + c.getCorreo().trim();
            try {
                restTemplate = new RestTemplate();
                ResponseEntity<Usuario> response = restTemplate.getForEntity(url.trim(), Usuario.class);
                Usuario usuarioCredencial = response.getBody();
                Credenciales credencial = usuarioCredencial.getCredenciales();
                session.setAttribute("credencialUser", credencial);
                session.setAttribute("usuarioLogueado", usuarioCredencial);
                return new ModelAndView("redirect:/home")
                        .addObject("credencial", session.getAttribute("credencialUser"))
                        .addObject("usuario", session.getAttribute("usuarioLogueado"));
            } catch (HttpClientErrorException.NotFound notFound) {
                String getMensaje = notFound.getMessage();
                return new ModelAndView("./views/login")
                        .addObject("titulo", "Iniciar sesión")
                        .addObject("credenciales", new Credenciales())
                        .addObject("noExisteUsuario", true)
                        .addObject("error", getMensaje);
            }
        }
    }

    //REGISTRAR
    @GetMapping("/customer/account/registrar")
    public ModelAndView mostrarRegistroUsuario() {
        return new ModelAndView("views/registrar")
                .addObject("titulo", "Registrar usuario")
                .addObject("usuario", new Usuario());
    }

    @PostMapping("/customer/account/registrar")
    public ModelAndView registrarUsuario(@Validated Usuario u, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || u.getApellido().isEmpty()
                || u.getCredenciales().getCorreo().isEmpty() || u.getCredenciales().getClave().isEmpty() || u.getNombre().isEmpty()
                || u.getDireccion().isEmpty() || u.getDistrito().isEmpty()
                || u.getCelular().isEmpty()) {
            return new ModelAndView("views/registrar")
                    .addObject("titulo", "Registrar usuario")
                    .addObject("usuario", u);
        } else {
            restTemplate = new RestTemplate();
            String url = "http://localhost:8050/api/usuarios";
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Usuario> entidadUsuario = new HttpEntity(u, header);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entidadUsuario, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return new ModelAndView("redirect:/home");
            }
        }
        return null;
    }

    //LOGOUT
    @RequestMapping("/logout")
    @GetMapping
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("credencialUser");
        session.removeAttribute("usuario");
        return new ModelAndView("redirect:/")
                .addObject("credencial", null)
                .addObject("usuario", null);
    }

    //PUBLICAR
    @GetMapping("publicar/nuevo")
    public ModelAndView RegistroPublicacion(HttpSession session) {
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user == null || credenciales == null) {
            return new ModelAndView("redirect:/")
                    .addObject("credencial", null)
                    .addObject("usuario", null);
        }
        return new ModelAndView("views/publicar")
                .addObject("titulo", "Publicar Inmueble")
                .addObject("publicacion", new PublicacionFormulario())
                .addObject("credencial", credenciales)
                .addObject("usuario", user);
    }

    @PostMapping("publicar/nuevo")
    public ModelAndView publicar(@Validated PublicacionFormulario p, BindingResult bindingResult, HttpSession session) {
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (bindingResult.hasErrors() || p.getImagenPublicacion().isEmpty()) {
            if (p.getImagenPublicacion().isEmpty()) {
                bindingResult.rejectValue("imagenPublicacion", "MultipartNotEmpty");
            }
            return new ModelAndView("views/publicar")
                    .addObject("titulo", "Publicar Inmueble")
                    .addObject("publicacion", p)
                    .addObject("credencial", credenciales)
                    .addObject("usuario", user);
        } else {
            String nombreImagen = almacenService.almacenarArchivo(p.getImagenPublicacion());
            Publicacion publicacionEntity = publicacionFormularioService.getPublicacionEntity(p);
            publicacionEntity.setUsuario(user);
            publicacionEntity.setRutaImg("esteEsElError");

            String url = "http://localhost:8050/api/publicacion";
            restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Publicacion> entidadpublicacion = new HttpEntity(publicacionEntity, header);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entidadpublicacion, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return new ModelAndView("redirect:/home")
                        .addObject("credencial", credenciales)
                        .addObject("usuario", user);
            }
            return null;
        }

    }

}
