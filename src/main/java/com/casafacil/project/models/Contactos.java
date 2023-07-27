package com.casafacil.project.models;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Contactos {
    String dniContacto;
    String nombres;
    String apellidos;
    String correo;
    String celular;
    @NotBlank
    String mensaje = "Hola! Estoy interesado en contactarme sobre el inmueble que encontré en casaFacil :).";
    Date fecha;
    Usuario usuario;
}
