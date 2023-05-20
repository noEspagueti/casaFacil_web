package com.casafacil.project.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Usuario {

    @NotEmpty
    @NotBlank
    @NotNull
    private String nombre;
    @NotEmpty
    @NotBlank
    @NotNull
    private String apellido;
    @NotEmpty
    @NotBlank
    @NotNull
    private String direccion;
    @NotEmpty
    @NotBlank
    @NotNull
    private String distrito;
    @NotEmpty
    @NotBlank
    @NotNull
    private String celular;
 
    @NotNull
    private Credenciales credenciales;

}
