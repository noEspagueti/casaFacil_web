package com.casafacil.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.casafacil.project.models.Galeria;
import com.casafacil.project.models.Publicacion;
import com.casafacil.project.models.Usuario;
import com.casafacil.project.services.webServiceImpl;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/galeria")
public class GaleriaController {

    @Autowired
    private webServiceImpl webService;

    @GetMapping
    public ModelAndView showGaleria(@RequestParam(value = "publicacion") String publicacion,
            @RequestParam(value = "inmueble") String inmueble, @RequestParam(value = "detalles") String ciudad,
            HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        Galeria galeria = new Galeria();
        galeria.setPublicacion(publicacion);
        galeria.setInmueble(inmueble);
        galeria.setDetalles(ciudad);
        List<Publicacion> publicaciones = null;
        String url = "";
        if (galeria.getDetalles() == null || galeria.getDetalles() == "") {
            url = "http://localhost:8050/api/publicacion/galeria/" + publicacion + "/" + inmueble;
        } else {
            url = "http://localhost:8050/api/publicacion/galeria/" + publicacion + "/" + inmueble + "/" + ciudad;
        }
        publicaciones = (List<Publicacion>) webService.methoGet(url, new ArrayList<Publicacion>());
        return new ModelAndView("./views/galeria")
                .addObject("titulo", "Descubre más")
                .addObject("galeria", galeria)
                .addObject("listaPublicacion", publicaciones)
                .addObject("usuario", user);
    }

}
