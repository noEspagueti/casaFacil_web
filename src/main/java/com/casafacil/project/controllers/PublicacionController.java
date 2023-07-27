package com.casafacil.project.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.OverridesAttribute.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.casafacil.project.models.*;
import com.casafacil.project.services.webServiceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = "/post")
public class PublicacionController {

    @Autowired
    private webServiceImpl webService;

    @GetMapping("/{idPublicacion}")
    public ModelAndView showPublicacion(@PathVariable("idPublicacion") Long id, HttpSession session) {
        String url = "http://localhost:8050/api/publicacion/find/id/" + id;
        Publicacion publicacion = (Publicacion) webService.methoGet(url, new Publicacion());
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        Usuario usuarioContacto = publicacion.getUsuario();
        session.setAttribute("contacto", usuarioContacto);
        if (publicacion == null || id == null) {
            return new ModelAndView("redirect:/home");
        }
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("views/postPublicacion")
                .addObject("titulo", "publicacion")
                .addObject("post", publicacion)
                .addObject("credencial", credenciales)
                .addObject("contactos", new Contactos())
                .addObject("usuarioContacto", usuarioContacto)
                .addObject("usuario", user);
    }

    @PostMapping("/{idPublicacion}")
    public ModelAndView saveContacto(Contactos c, HttpSession session, @PathVariable("idPublicacion") Long id) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user != null || id != null || c != null) {
            String urlContacto = "http://localhost:8050/api/publicacion/find/id/" + id;
            // Usuario usuarioContacto = (Usuario) session.getAttribute("contacto");
            Publicacion publicacionContacto = (Publicacion) webService.methoGet(urlContacto, new Publicacion());
            Usuario usuarioContacto = publicacionContacto.getUsuario();

            Contactos contacto = c;
            contacto.setDniContacto(user.getDniUsuario());
            contacto.setNombres(user.getNombre());
            contacto.setApellidos(user.getApellido());
            contacto.setCorreo(user.getCredenciales().getCorreo());
            contacto.setCelular(user.getCelular());
            contacto.setFecha(new Date());
            contacto.setUsuario(usuarioContacto);
            String url = "http://localhost:8050/api/contactos";
            ResponseEntity response = webService.consumirApi(url, contacto);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return new ModelAndView("redirect:/home");
            }
        }
        return null;
    }

}
