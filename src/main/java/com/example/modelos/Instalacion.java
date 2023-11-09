package com.example.modelos;

import java.util.Objects;

public class Instalacion {
    private int id;
    private String nombre;
    private String descripcion;


    public Instalacion() {
    }

    public Instalacion(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instalacion id(int id) {
        setId(id);
        return this;
    }

    public Instalacion nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Instalacion descripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Instalacion)) {
            return false;
        }
        Instalacion instalacion = (Instalacion) o;
        return id == instalacion.id && Objects.equals(nombre, instalacion.nombre) && Objects.equals(descripcion, instalacion.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}