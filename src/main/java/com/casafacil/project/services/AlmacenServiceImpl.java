/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miguel
 */
@Service

public class AlmacenServiceImpl implements AlmacenService {

    @Override
    public String almacenarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        return nombreArchivo;
    }

    @Override
    public String convertImgToString(MultipartFile archivo) {
        String textoImg = "";
        try {
            byte[] archivoByte = archivo.getBytes();
            textoImg = Base64.getEncoder().encodeToString(archivoByte);
        } catch (IOException ex) {
            return "";
        }
        return textoImg;
    }

}
