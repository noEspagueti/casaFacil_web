package com.casafacil.project.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Usuario {
    @NotBlank(message = "Se requiere especificar el DNI*")
    private String dniUsuario;
    private String tipoUsuario;
    @NotBlank(message = "Se requiere el nombre*")
    private String nombre;
    @NotBlank(message = "Se requiere el apellido*")
    private String apellido;
    @NotBlank(message = "Se requiere la dirección*")
    private String direccion;
    @NotBlank(message = "Se requiere el distrito*")
    private String distrito;
    @NotBlank(message = "Se requiere un celular*")
    private String celular;
    @NotNull(message = "Se requiere credenciales*")
    private Credenciales credenciales;

}
