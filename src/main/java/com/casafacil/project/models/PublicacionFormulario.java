/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.models;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miguel
 */
@Data
public class PublicacionFormulario {
    @NotEmpty
    private String titulo;
    @NotEmpty
    private String contenido;
    @NotNull
    private String precio;
    @NotEmpty
    private String tipoPublicacion;
    @NotEmpty
    private String tipoInmueble;
    @NotEmpty
    private String ciudad;
    @NotEmpty
    private String distrito;
    @Transient
    private MultipartFile imagenPublicacion;

}
