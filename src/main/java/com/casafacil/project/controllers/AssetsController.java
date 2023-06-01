/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.controllers;

import com.casafacil.project.services.AlmacenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Miguel
 */
@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Autowired
    private AlmacenServiceImpl almacenService;

    @GetMapping("/{filename:.+}")
    public Resource getImage(@PathVariable("filename") String filename) {
        return almacenService.cargarComoRecurso(filename);
    }

}
