package com.casafacil.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.casafacil.project.models.*;
import com.casafacil.project.models.inmuebles.CasaEntity;
import com.casafacil.project.models.inmuebles.DepartamentoEntity;
import com.casafacil.project.models.inmuebles.TerrenoEntity;
import com.casafacil.project.services.AlmacenServiceImpl;
import com.casafacil.project.services.webServiceImpl;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = { "/", "home" })
public class UsuarioController {

    @Autowired
    private AlmacenServiceImpl almacenService;
    @Autowired
    private webServiceImpl servicioWeb;

    @GetMapping(value = { "/", "/home" })
    public ModelAndView home(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        String urlListaCiudades = "http://localhost:8050/api/publicacion/allCiudad";
        List<String> listaCiudades = (List<String>) servicioWeb.methoGet(urlListaCiudades, new ArrayList<String>());
        return new ModelAndView("index.html")
                .addObject("usuario", user)
                .addObject("galeria", new Galeria())
                .addObject("ciudades", listaCiudades);
    }

    @GetMapping("/login")
    public ModelAndView mostrarLoginUsuario() {
        return new ModelAndView("./views/login.html")
                .addObject("titulo", "Iniciar sesión")
                .addObject("credenciales", new Credenciales());
    }

    // LOGIN
    @PostMapping("/login")
    public ModelAndView validarCredenciales(@Validated Credenciales c, BindingResult bindingResult,
            HttpSession session) {
        if (c.getCorreo() == null || c.getClave() == null || bindingResult.hasErrors()) {
            return new ModelAndView("./views/login")
                    .addObject("titulo", "Iniciar sesión")
                    .addObject("credenciales", c);
        }
        String url = "http://localhost:8050/api/usuarios/" + c.getCorreo().trim();
        Usuario usuarioCredencial = (Usuario) servicioWeb.methoGet(url, new Usuario());
        if (usuarioCredencial == null) {
            return new ModelAndView("./views/login")
                    .addObject("titulo", "Iniciar sesión")
                    .addObject("credenciales", new Credenciales())
                    .addObject("noExisteUsuario", true);
        } else {
            Credenciales credencial = usuarioCredencial.getCredenciales();
            if (!c.getClave().equals(credencial.getClave())) {
                return new ModelAndView("./views/login")
                        .addObject("titulo", "Iniciar sesión")
                        .addObject("credenciales", c)
                        .addObject("noExisteUsuario", true);
            }
            session.setAttribute("usuarioLogueado", usuarioCredencial);
            return new ModelAndView("redirect:/home")
                    .addObject("titulo", "Iniciar sesión")
                    .addObject("usuario", session.getAttribute("usuarioLogueado"));
        }
    }

    // REGISTRAR
    @GetMapping("/registrar")
    public ModelAndView mostrarRegistroUsuario(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario != null) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("views/registrar")
                .addObject("titulo", "Registrar usuario")
                .addObject("usuario", new Usuario());
    }

    @PostMapping("/registrar")
    public ModelAndView registrarUsuario(@Validated Usuario u, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || u.getApellido().isEmpty()
                || u.getCredenciales().getCorreo().isEmpty() || u.getCredenciales().getClave().isEmpty()
                || u.getNombre().isEmpty()
                || u.getDireccion().isEmpty() || u.getDistrito().isEmpty()
                || u.getCelular().isEmpty() || u.getTipoUsuario().isEmpty()) {
            return new ModelAndView("views/registrar")
                    .addObject("titulo", "Registrar usuario")
                    .addObject("usuario", u);
        } else {
            String url = "http://localhost:8050/api/usuarios";
            ResponseEntity response = servicioWeb.consumirApi(url, u);
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
        session.invalidate();
        return new ModelAndView("redirect:/login")
                .addObject("usuario", new Usuario());
    }

    // PUBLICAR
    @GetMapping("/publicar")
    public ModelAndView RegistroPublicacion(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user == null) {
            return new ModelAndView("redirect:/")
                    .addObject("credencial", null)
                    .addObject("usuario", null);
        }
        return new ModelAndView("views/publicacion/master")
                .addObject("titulo", "Publicar Inmueble")
                .addObject("publicacion", new Publicacion())
                .addObject("usuario", user);
    }

    // PUBLICAR METODO POST

    @PostMapping("/publicar")
    public ModelAndView savePublicar(@Validated Publicacion p, BindingResult bindingResult, HttpSession session) {
        String url = "http://localhost:8050/api/publicacion";
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (bindingResult.hasErrors()) {
            return new ModelAndView("views/publicacion/master")
                    .addObject("titulo", "Publicar Inmueble")
                    .addObject("publicacion", p)
                    .addObject("usuario", user);
        }

        CasaEntity casa = null;
        DepartamentoEntity departamento = null;
        TerrenoEntity terreno = null;

        String tipoInmueble = p.getTipoInmueble() != null ? p.getTipoInmueble().trim() : "";

        if ("Casa".equals(tipoInmueble)) {
            casa = new CasaEntity(p.getTipoCasa().getNumeroPisos(), p.getTipoCasa().getNumeroHabitaciones(),
                    p.getTipoCasa().getNumeroBanio(), p.getTipoCasa().getAreaConstruida(),
                    p.getTipoCasa().getAreaTotal());
        } else if ("Departamento".equals(tipoInmueble)) {
            departamento = new DepartamentoEntity(p.getTipoDepartamento().getNumeroHabitaciones(),
                    p.getTipoDepartamento().getNumeroBanio(), p.getTipoDepartamento().getNumeroPisos(),
                    p.getTipoDepartamento().getAreaTotal());
        } else if ("Terreno".equals(tipoInmueble)) {
            terreno = new TerrenoEntity(p.getTerreno().getAreaConstruida(), p.getTerreno().getAreaTotal());
        }
        String nombreImagen = almacenService.almacenarArchivos(p.getImagenPublicacion());
        Publicacion publicacioEntity = new Publicacion(p.getTitulo(), p.getContenido(), p.getPrecio(),
                p.getTipoPublicacion(), p.getTipoInmueble(), nombreImagen, p.getCiudad(), new Date(), p.getDistrito(),
                user, p.getDireccion(), casa, departamento, terreno);

        ResponseEntity response = servicioWeb.consumirApi(url, publicacioEntity);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return new ModelAndView("redirect:/home")
                    .addObject("usuario", user);
        }

        return new ModelAndView("redirect:/");
    }

    // MOSTRAR TODOS LAS PUBLICACIONES DEL USUARIO
    @GetMapping("/publicaciones")
    public ModelAndView showAllPublicaciones(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user != null) {
            return new ModelAndView("views/user/master")
                    .addObject("titulo", "yo")
                    .addObject("usuario", user);

        } else {
            return null;
        }
    }

    @GetMapping("/eliminar/{idPublicacion}")
    public ModelAndView eliminarPublicacion(@PathVariable(value = "idPublicacion") Long id) {
        String url = "http://localhost:8050/api/publicacion/delete/" + id;
        ResponseEntity response = servicioWeb.remmove(url);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return new ModelAndView("redirect:/publicaciones");
        }
        return null;
    }

}
