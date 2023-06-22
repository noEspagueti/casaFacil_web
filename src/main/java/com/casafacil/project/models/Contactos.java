package com.casafacil.project.models;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Contactos {
    private String dniContacto;
    private String nombres;
    private String apellidos;
    private String correo;
    private String celular;
    @NotBlank
    private String mensaje;
    private Date fecha;
    private Usuario usuario;
}
