/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.services;

import com.casafacil.project.models.Publicacion;
import com.casafacil.project.models.PublicacionFormulario;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel
 */
@Service
public class PublicacionFormularioImpl implements PublicacionFormularioService {

    @Override
    public Publicacion getPublicacionEntity(PublicacionFormulario p) {
        Publicacion publicacionEntity = new Publicacion();  
        publicacionEntity.setTitulo(p.getTitulo());
        publicacionEntity.setContenido(p.getContenido());
        publicacionEntity.setPrecio(p.getPrecio());
        publicacionEntity.setTipoPublicacion(p.getTipoPublicacion());
        publicacionEntity.setTipoInmueble(p.getTipoInmueble());
        publicacionEntity.setCiudad(p.getCiudad());
        publicacionEntity.setDistrito(p.getDistrito());
        publicacionEntity.setFecha(new Date());
        return publicacionEntity;
    }

}
