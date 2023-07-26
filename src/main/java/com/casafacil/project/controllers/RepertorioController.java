package com.casafacil.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.casafacil.project.models.Contactos;
import com.casafacil.project.models.Publicacion;
import com.casafacil.project.models.Usuario;
import com.casafacil.project.services.webServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/repertorio")
public class RepertorioController {

    @Autowired
    private webServiceImpl servicioWeb;

    // MOSTRAR TODOS LAS PUBLICACIONES DEL USUARIO
    @GetMapping("/publicaciones")
    public ModelAndView showAllPublicacionesApi(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user != null) {
            String urlGetPublicaciones = ("http://localhost:8050/api/publicacion/" + user.getDniUsuario()).trim();
            List<Publicacion> listaPublicacionUsuario = (List<Publicacion>) servicioWeb.methoGet(urlGetPublicaciones,
                    new ArrayList<Publicacion>());
            return new ModelAndView("views/misPublicaciones")
                    .addObject("titulo", "yo")
                    .addObject("usuario", user)
                    .addObject("publicacionUsuario", listaPublicacionUsuario);
        } else {
            return null;
        }
    }

    // MOSTRAR TODOS LOS CONTACTOS
    @GetMapping("/contactos")
    public ModelAndView showAllContactosApi(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        String url = "http://localhost:8050/api/contactos/" + user.getDniUsuario();
        List<Contactos> listaContactos = (List<Contactos>) servicioWeb.methoGet(url, new ArrayList<Contactos>());
        return new ModelAndView("views/misContactos")
                .addObject("usuario", user)
                .addObject("misContactos", listaContactos)
                .addObject("titulo", "yo");
    }

    @GetMapping("/Casa")
    public ModelAndView showCasaFormulario(HttpSession session) {
        return new ModelAndView("views/publicacion/casaFormulario")
                .addObject("publicacion", new Publicacion());
    }

    @GetMapping("/Departamento")
    public ModelAndView showDepartamentoFormulario(HttpSession session) {
        return new ModelAndView("views/publicacion/departamentoFormulario")
                .addObject("publicacion", new Publicacion());
    }

    @GetMapping("/Terreno")
    public ModelAndView showTerrenoFormulario(HttpSession session) {
        return new ModelAndView("views/publicacion/terrenoFormulario")
                .addObject("publicacion", new Publicacion());
    }

}
