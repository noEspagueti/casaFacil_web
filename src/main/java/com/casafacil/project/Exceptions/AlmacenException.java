package com.casafacil.project.Exceptions;

public class AlmacenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlmacenException(String mensaje) {
        super(mensaje);
    }

    public AlmacenException(String mensaje, Throwable e) {
        super(mensaje, e);
    }

}
