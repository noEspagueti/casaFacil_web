package com.casafacil.project.services;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miguel
 */
public interface AlmacenService {

    public String almacenarArchivo(MultipartFile archivo);
    
    public String convertImgToString(MultipartFile archivo);
}
