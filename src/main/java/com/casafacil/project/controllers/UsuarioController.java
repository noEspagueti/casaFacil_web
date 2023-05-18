package com.casafacil.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.casafacil.project.models.Credenciales;

import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "home"})
public class UsuarioController {

    @GetMapping("/customer/account/login")
    public ModelAndView mostrarLoginUsuario() {
        return new ModelAndView("./views/login.html").
                addObject("titulo", "Iniciar sesión").
                addObject("credenciales", new Credenciales());
    }

    @PostMapping("/customer/account/login")
    public ModelAndView validarCredenciales(@Validated Credenciales c, BindingResult bindingResult, HttpSession loginSession) {
        if (c.getCorreo() == null || c.getClave() == null || bindingResult.hasErrors()) {
            return new ModelAndView("./views/login").
                    addObject("titulo", "Iniciar sesión").
                    addObject("credenciales", new Credenciales());
        }
        loginSession.setAttribute("credencial", c);
        return new ModelAndView("index")
                .addObject("credencial", loginSession.getAttribute("credencial"));
    }

    @GetMapping("/customer/account/registrar")
    public ModelAndView mostrarRegistroUsuario() {
        return new ModelAndView("views/registrar")
                .addObject("titulo", "Registrar usuario");
    }

}
