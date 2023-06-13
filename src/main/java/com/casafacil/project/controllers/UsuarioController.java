package com.casafacil.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.casafacil.project.models.*;
import com.casafacil.project.services.AlmacenServiceImpl;
import com.casafacil.project.services.PublicacionFormularioServiceImpl;
import com.casafacil.project.services.webServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping(value = {"/", "home"})
public class UsuarioController {

    @Autowired
    private AlmacenServiceImpl almacenService;
    @Autowired
    private PublicacionFormularioServiceImpl publicacionFormularioService;
    @Autowired
    private webServiceImpl servicioWeb;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home(HttpSession session) {
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        List<Publicacion> listaPublicacion = (List<Publicacion>) servicioWeb.methoGet("http://localhost:8050/api/publicacion/all", new ArrayList<Publicacion>());
        List<String> listaCiudades = (List<String>) servicioWeb.methoGet("http://localhost:8050/api/publicacion/allCiudad", new ArrayList<String>());
        session.setAttribute("publicaciones", listaPublicacion);
        return new ModelAndView("index.html")
                .addObject("credencial", credenciales)
                .addObject("listaPublicacion", listaPublicacion)
                .addObject("usuario", user)
                .addObject("ciudades", listaCiudades);
    }

    @GetMapping("/customer/account/login")
    public ModelAndView mostrarLoginUsuario() {
        return new ModelAndView("./views/login.html").addObject("titulo", "Iniciar sesión").addObject("credenciales",
                new Credenciales());
    }

    // LOGIN
    @PostMapping("/customer/account/login")
    public ModelAndView validarCredenciales(@Validated Credenciales c, BindingResult bindingResult,
            HttpSession session) {
        if (c.getCorreo() == null || c.getClave() == null || bindingResult.hasErrors()) {
            return new ModelAndView("./views/login")
                    .addObject("titulo", "Iniciar sesión")
                    .addObject("credenciales", c);
        } else {
            try {
                String url = "http://localhost:8050/api/usuarios/" + c.getCorreo().trim();
                Usuario usuarioCredencial = (Usuario) servicioWeb.methoGet(url, new Usuario());
                Credenciales credencial = usuarioCredencial.getCredenciales();

                // * {falta validad los credenciales}
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

    // REGISTRAR
    @GetMapping("/customer/account/registrar")
    public ModelAndView mostrarRegistroUsuario() {
        return new ModelAndView("views/registrar")
                .addObject("titulo", "Registrar usuario")
                .addObject("usuario", new Usuario());
    }

    @PostMapping("/customer/account/registrar")
    public ModelAndView registrarUsuario(@Validated Usuario u, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || u.getApellido().isEmpty()
                || u.getCredenciales().getCorreo().isEmpty() || u.getCredenciales().getClave().isEmpty()
                || u.getNombre().isEmpty()
                || u.getDireccion().isEmpty() || u.getDistrito().isEmpty()
                || u.getCelular().isEmpty()) {
            return new ModelAndView("views/registrar")
                    .addObject("titulo", "Registrar usuario")
                    .addObject("usuario", u);
        } else {
            ResponseEntity response = servicioWeb.consumirApi("http://localhost:8050/api/usuarios", u);
            if (response.getStatusCode() == HttpStatus.OK) {
                return new ModelAndView("redirect:/home");
            }
        }
        return null;
    }

    // LOGOUT
    @RequestMapping("/logout")
    @GetMapping
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("credencialUser");
        session.removeAttribute("usuario");
        return new ModelAndView("redirect:/")
                .addObject("credencial", null)
                .addObject("usuario", null);
    }

    // PUBLICAR
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
                .addObject("publicacion", new Publicacion())
                .addObject("credencial", credenciales)
                .addObject("usuario", user);
    }

    @PostMapping("publicar/nuevo")
    public ModelAndView publicar(@Validated Publicacion p, BindingResult bindingResult, HttpSession session) {

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
            String nombreImagen = almacenService.almacenarArchivos(p.getImagenPublicacion());
            Publicacion publicacionEntity = publicacionFormularioService.getPublicacionEntity(p, user, nombreImagen);
            ResponseEntity response = servicioWeb.consumirApi("http://localhost:8050/api/publicacion", publicacionEntity);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return new ModelAndView("redirect:/home")
                        .addObject("credencial", credenciales)
                        .addObject("usuario", user);
            }
            return null;
        }

    }

    //MOSTRAR TODOS LAS PUBLICACIONES DEL USUARIO
    @GetMapping("/publicaciones")
    public ModelAndView showAllPublicaciones(HttpSession session) {
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");

        if (user != null) {
            //Solicitud para obtener toda la lista de publicacion de un usuario
            String urlGetPublicaciones = ("http://localhost:8050/api/publicacion/" + user.getDniUsuario()).trim();
            List<Publicacion> listaPublicacionUsuario = (List<Publicacion>) servicioWeb.methoGet(urlGetPublicaciones, new ArrayList<Publicacion>());
            return new ModelAndView("views/misPublicaciones")
                    .addObject("credencial", credenciales)
                    .addObject("usuario", new Usuario())
                    .addObject("publicacionUsuario", listaPublicacionUsuario);
        }

        return new ModelAndView("views/misPublicaciones")
                .addObject("credencial", credenciales)
                .addObject("usuario", user);
    }

    //MOSTRAR CONTACTOS
    @GetMapping("/contactos")
    public ModelAndView showContactos(HttpSession session) {
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        return new ModelAndView("views/misContactos")
                .addObject("credencial", credenciales)
                .addObject("usuario", user);
    }

}
