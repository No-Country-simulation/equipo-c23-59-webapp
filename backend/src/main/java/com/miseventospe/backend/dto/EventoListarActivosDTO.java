package com.miseventospe.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miseventospe.backend.enumeraciones.CategoriaEvento;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventoListarActivosDTO {
    private String titulo;
    private String descripcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private String fecha;
    private String hora;
    private String ubicacion;
    private Double latitud;
    private Double longitud;
    private String organizador;
    private CategoriaEvento categoria;
    private String enlace;
    private Double precio;

    public EventoListarActivosDTO(String titulo, String descripcion, Date fecha, String hora, String ubicacion, Double latitud, Double longitud, String organizador, CategoriaEvento categoria, String enlace, Double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.organizador = organizador;
        this.categoria = categoria;
        this.enlace = enlace;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEvento categoria) {
        this.categoria = categoria;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public Object getPrecio() {
        return (precio != null && precio > 0) ? precio : "Gratis";
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }
}
