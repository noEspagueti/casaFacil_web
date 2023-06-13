/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.casafacil.project.models.*;
import com.casafacil.project.services.webServiceImpl;
import java.util.List;
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
    public ModelAndView showPublicacion(@PathVariable("idPublicacion") Long id) {
        Publicacion publicacion = (Publicacion) webService.methoGet("http://localhost:8050/api/publicacion/find/" + id, new Publicacion());
        if (publicacion == null && id == null) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("views/postPublicacion")
                .addObject("post", publicacion);
    }

}
