package com.casafacil.project.services;

import org.springframework.core.io.UrlResource;
import com.casafacil.project.Exceptions.AlmacenException;
import com.casafacil.project.Exceptions.FileNotFoundException;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Miguel
 */
@Service

public class AlmacenServiceImpl implements AlmacenService {

    @Value("${storage.location}")
    private String storageLocation;

    
    @PostConstruct
    @Override
    public void iniciarAlmacenDeArchivos() {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException e) {
            throw new AlmacenException("No se pudo crear el directorio");
        }
    }

    @Override
    public String almacenarArchivos(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        if (nombreArchivo.equals(null)) {
            throw new AlmacenException("No se puede guardar un archivo sin nombre");
        }
        try {
            InputStream archivoStream = archivo.getInputStream();
            Files.copy(archivoStream, Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new AlmacenException("No se puede almacenar la imágen");
        }
        return nombreArchivo;
    }

    @Override
    public Path cargarArchivos(String nombreArchivo) {
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }

    @Override
    public void eliminarArchivo(String nombreArchivo) {
        Path archivo = cargarArchivos(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {
        try {
            Path archivo = cargarArchivos(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());
            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new FileNotFoundException("No se pudo encontrar el archivo " + nombreArchivo);
            }
        } catch (MalformedURLException exception) {
            throw new AlmacenException("No se pudo encontrar el archivo " + nombreArchivo, exception);
        }
    }

}
