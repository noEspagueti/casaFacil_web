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
import com.casafacil.project.models.Credenciales;
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
    public ModelAndView showCntactos(HttpSession session) {
        Credenciales credenciales = (Credenciales) session.getAttribute("credencialUser");
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        String url = "http://localhost:8050/api/contactos/" + user.getDniUsuario();
        List<Contactos> listaContactos = (List<Contactos>) webService.methoGet(url, new ArrayList<Contactos>());
        return new ModelAndView("views/misContactos")
                .addObject("credencial", credenciales)
                .addObject("usuario", user)
                .addObject("misContactos", listaContactos)
                .addObject("titulo", "Mis contactos");
    }

}
