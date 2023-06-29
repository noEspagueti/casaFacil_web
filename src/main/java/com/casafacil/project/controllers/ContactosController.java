package com.casafacil.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.casafacil.project.models.Contactos;
import com.casafacil.project.models.Usuario;
import com.casafacil.project.services.webServiceImpl;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/contactos")
public class ContactosController {

    @Autowired
    private webServiceImpl webService;

    // MOSTRAR CONTACTOS
    @GetMapping
    public ModelAndView showAllContactos(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        return new ModelAndView("views/user/master")
                .addObject("usuario", user)
                .addObject("titulo", "yo");
    }

}
