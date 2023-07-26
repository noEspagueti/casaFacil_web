package com.casafacil.project.models;

import com.casafacil.project.models.inmuebles.CasaEntity;
import com.casafacil.project.models.inmuebles.DepartamentoEntity;
import com.casafacil.project.models.inmuebles.TerrenoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Publicacion {
    private Long idPublicacion;
    @NotBlank(message = "Se debe ingresar el título*")
    private String titulo;
    @NotBlank(message = "Se debe ingresar el una descripción*")
    private String contenido;
    @NotNull(message = "El precio es obligatorio*")
    private Double precio;
    @NotBlank(message = "Se debe ingresar el tipo de publicación*")
    private String tipoPublicacion;
    @NotBlank(message = "Se debe ingresar el tipo de inmueble*")
    private String tipoInmueble;

    private String rutaImg;
    @NotBlank(message = "Se debe ingresar una ciudad*")
    private String ciudad;
    private Date fecha;
    @NotBlank(message = "Se debe ingresar un distrito*")
    private String distrito;
    private Usuario usuario;

    @Transient
    @JsonIgnore
    private MultipartFile imagenPublicacion;

    @NotBlank(message = "se debe ingresar una dirección")
    private String direccion;

    private CasaEntity tipoCasa;
    private DepartamentoEntity tipoDepartamento;
    private TerrenoEntity terreno;

    public Publicacion() {
    }

    public Publicacion(String titulo, String contenido, Double precio, String tipoPublicacion, String tipoInmueble,
            String rutaImg, String ciudad, Date fecha, String distrito, Usuario usuario,
            String direccion,
            CasaEntity tipoCasa, DepartamentoEntity tipoDepartamento, TerrenoEntity terreno) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.precio = precio;
        this.tipoPublicacion = tipoPublicacion;
        this.tipoInmueble = tipoInmueble;
        this.rutaImg = rutaImg;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.distrito = distrito;
        this.usuario = usuario;
        this.direccion = direccion;
        this.tipoCasa = tipoCasa;
        this.tipoDepartamento = tipoDepartamento;
        this.terreno = terreno;
    }

    public Long getIdPublicacion() {
        return this.idPublicacion;
    }

    public void setIdPublicacion(Long idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoPublicacion() {
        return this.tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getTipoInmueble() {
        return this.tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public String getRutaImg() {
        return this.rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDistrito() {
        return this.distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MultipartFile getImagenPublicacion() {
        return this.imagenPublicacion;
    }

    public void setImagenPublicacion(MultipartFile imagenPublicacion) {
        this.imagenPublicacion = imagenPublicacion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TerrenoEntity getTerreno() {
        return this.terreno;
    }

    public void setTerreno(TerrenoEntity terreno) {
        this.terreno = terreno;
    }

    public CasaEntity getTipoCasa() {
        return this.tipoCasa;
    }

    public void setTipoCasa(CasaEntity tipoCasa) {
        this.tipoCasa = tipoCasa;
    }

    public DepartamentoEntity getTipoDepartamento() {
        return this.tipoDepartamento;
    }

    public void setTipoDepartamento(DepartamentoEntity tipoDepartamento) {
        this.tipoDepartamento = tipoDepartamento;
    }

}
