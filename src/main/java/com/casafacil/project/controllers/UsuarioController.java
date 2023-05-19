package com.casafacil.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.casafacil.project.models.Credenciales;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = {"/", "home"})
public class UsuarioController {

    private RestTemplate restTemplate;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home(HttpSession loginSession) {
        Credenciales credencial = (Credenciales) loginSession.getAttribute("credencial");
        return new ModelAndView("index")
                .addObject("credencial", credencial);
    }

    @GetMapping("/customer/account/login")
    public ModelAndView mostrarLoginUsuario() {
        return new ModelAndView("./views/login.html").
                addObject("titulo", "Iniciar sesión").
                addObject("credenciales", new Credenciales());
    }

    //LOGIN
    @PostMapping("/customer/account/login")
    public ModelAndView validarCredenciales(@Validated Credenciales c, BindingResult bindingResult, HttpSession loginSession) {
        if (c.getCorreo() == null || c.getClave() == null || bindingResult.hasErrors()) {
            return new ModelAndView("./views/login")
                    .addObject("titulo", "Iniciar sesión")
                    .addObject("credenciales", new Credenciales());
        } else {
            String url = "http://localhost:8050/api/credenciales/" + c.getCorreo().trim();
            try {
                restTemplate = new RestTemplate();
                ResponseEntity<Credenciales> response = restTemplate.getForEntity(url.trim(), Credenciales.class);
                Credenciales credencialApi = response.getBody();
                loginSession.setAttribute("credencial", c);
                return new ModelAndView("redirect:/home")
                        .addObject("credencial", loginSession.getAttribute("credencial"));
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
    
    //REGISTRAR
    @GetMapping("/customer/account/registrar")
    public ModelAndView mostrarRegistroUsuario() {
        return new ModelAndView("views/registrar")
                .addObject("titulo", "Registrar usuario");
    }

    //LOGOUT
    @RequestMapping("/logout")
    @GetMapping
    public ModelAndView logout(HttpSession loginSession) {
        loginSession.removeAttribute("credencial");
        return new ModelAndView("redirect:/")
                .addObject("credencial", null);
    }
    
    //PUBLICAR
    
}
