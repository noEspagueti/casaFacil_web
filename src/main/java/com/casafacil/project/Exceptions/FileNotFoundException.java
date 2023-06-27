package com.casafacil.project.Exceptions;

public class FileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileNotFoundException(String mensaje) {
        super(mensaje);
    }

    public FileNotFoundException(String mensaje, Throwable e) {
        super(mensaje, e);
    }
}
