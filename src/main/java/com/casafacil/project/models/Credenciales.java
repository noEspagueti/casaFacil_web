package com.casafacil.project.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Credenciales {
    private Long idCredenciales;
    @NotBlank(message = "El correro es requerido*")
    @Email
    private String correo;
    @NotBlank(message = "La clave es requerida*")
    private String clave;

}
