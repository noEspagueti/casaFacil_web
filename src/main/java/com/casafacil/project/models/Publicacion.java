package com.casafacil.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@JsonSerialize
public class Publicacion {
    private Long idPublicacion;
    @NotBlank(message = "Se debe ingresar el título*")
    private String titulo;
    @NotBlank(message = "Se debe ingresar el una descripción*")
    private String contenido;
    @NotNull(message = "El precio es obligatorio*")
    private Double precio;
    @NotBlank(message = "Se debe ingresar el tipo de publicación*")
    private String tipoPublicacion;
    @NotBlank(message = "Se debe ingresar el tipo de inmueble*")
    private String tipoInmueble;
    private String rutaImg;
    @NotBlank(message = "Se debe ingresar una ciudad*")
    private String ciudad;
    private Date fecha;
    @NotBlank(message = "Se debe ingresar un distrito*")
    private String distrito;
    private Usuario usuario;
    @Transient
    @JsonIgnore
    private MultipartFile imagenPublicacion;
}
