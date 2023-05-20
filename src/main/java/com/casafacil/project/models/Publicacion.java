/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Miguel
 */
@Data
public class Publicacion {

    private Long idPublicacion;
    @NotBlank
    private String titulo;
     @NotBlank
    private String contenido;
    private Double precio;
    private String rutaImg;
    private String tipoPublicacion;
    private String tipoInmueble;
    private Data fecha;
}
