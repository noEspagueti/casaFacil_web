package com.casafacil.project.models;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Credenciales {

    private Long id;
    @NotBlank
    @Email
    private String correo;
    @NotBlank
    private String clave;
    

}
