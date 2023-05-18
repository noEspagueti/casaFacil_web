package com.casafacil.project.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Usuario {

    @NotEmpty
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String direccion;
    @NotBlank
    private String distrito;
    @NotBlank
    private String celular;
    @NotBlank
    private Credenciales credenciales;

}
