/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.services;

import com.casafacil.project.models.Publicacion;
import com.casafacil.project.models.Usuario;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel
 */
@Service
public class PublicacionFormularioServiceImpl implements PublicacionFormularioService {

    @Override
    public Publicacion getPublicacionEntity(Publicacion p, Usuario u, String nombreRuta) {
        Publicacion publicacionEntity = new Publicacion();
        publicacionEntity.setTitulo(p.getTitulo());
        publicacionEntity.setContenido(p.getContenido());
        publicacionEntity.setPrecio(p.getPrecio());
        publicacionEntity.setTipoPublicacion(p.getTipoPublicacion());
        publicacionEntity.setTipoInmueble(p.getTipoInmueble());
        publicacionEntity.setCiudad(p.getCiudad());
        publicacionEntity.setRutaImg(nombreRuta);
        publicacionEntity.setUsuario(u);
        publicacionEntity.setDistrito(p.getDistrito());
        publicacionEntity.setFecha(new Date());
        return publicacionEntity;
    }

}
