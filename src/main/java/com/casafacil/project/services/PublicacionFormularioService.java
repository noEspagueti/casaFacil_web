/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.casafacil.project.services;

import com.casafacil.project.models.Publicacion;
import com.casafacil.project.models.Usuario;

/**
 *
 * @author Miguel
 */
public interface PublicacionFormularioService {
    public Publicacion getPublicacionEntity( Publicacion p, Usuario u);
}
