package com.casafacil.project.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.nio.file.Path;

/**
 *
 * @author Miguel
 */
public interface AlmacenService {

//    public String almacenarArchivo(MultipartFile archivo);
//    
//    public String convertImgToString(MultipartFile archivo);
    public void iniciarAlmacenDeArchivos();

    public String almacenarArchivos(MultipartFile archivo);

    public Path cargarArchivos(String nombreArchivo);

    public void eliminarArchivo(String nombreArchivo);

    public Resource cargarComoRecurso(String nombreArchivo);
}
