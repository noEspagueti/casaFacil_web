/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.Exceptions;

/**
 *
 * @author Miguel
 */
public class FileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileNotFoundException(String mensaje) {
        super(mensaje);
    }

    public FileNotFoundException(String mensaje, Throwable e) {
        super(mensaje, e);
    }
}
