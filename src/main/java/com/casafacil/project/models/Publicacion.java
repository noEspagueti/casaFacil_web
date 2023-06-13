/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Transient;
import java.util.Date;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miguel
 */
@Data
@JsonSerialize
public class Publicacion {
    private Long idPublicacion;
    private String titulo;
    private String contenido;
    private Double precio;
    private String tipoPublicacion;
    private String tipoInmueble;
    private String rutaImg;
    private String ciudad;
    private Date fecha;
    private String distrito;
    private Usuario usuario;
    @Transient
    @JsonIgnore
    private MultipartFile imagenPublicacion;
}
