
package com.casafacil.project.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.casafacil.project.models.*;
import com.casafacil.project.services.webServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Miguel
 */
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
        if (publicacion == null && id == null) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("views/postPublicacion")
                .addObject("titulo", "publicacion")
                .addObject("post", publicacion)
                .addObject("credencial", credenciales)
                .addObject("usuario", user);
    }

}
