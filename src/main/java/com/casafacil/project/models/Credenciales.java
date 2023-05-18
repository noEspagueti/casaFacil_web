package com.casafacil.project.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Credenciales {

    private Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Email
    private String correo;
    @NotNull
    @NotEmpty
    @NotBlank
    private String clave;

}
