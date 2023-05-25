/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miguel
 */
@Data
public class Almacen {
    private String imagenDecoder;
    private String nombreArchivo;

    public Almacen(String imagenDecoder, String nombreArchivo) {
        this.imagenDecoder = imagenDecoder;
        this.nombreArchivo = nombreArchivo;
    }
    
}