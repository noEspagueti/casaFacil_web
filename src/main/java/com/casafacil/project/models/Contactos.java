package com.casafacil.project.models;

import java.util.Date;

import lombok.Data;

@Data
public class Contactos {
    private String dniContacto;
    private String nombres;
    private String apellidos;
    private String correo;
    private String celular;
    private String mensaje;
    private Date fecha;
    private Usuario usuario;
}
