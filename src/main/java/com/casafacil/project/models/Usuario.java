package com.casafacil.project.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Usuario {
    @NotNull
    @NotEmpty
    private String dniUsuario;
    
    @NotEmpty
    @NotNull
    private String nombre;
    @NotEmpty
    @NotNull
    private String apellido;
    @NotEmpty
    @NotNull
    private String direccion;
    @NotEmpty
    @NotNull
    private String distrito;
    @NotEmpty
    @NotNull
    private String celular;

    @NotNull
    private Credenciales credenciales;
    


}
