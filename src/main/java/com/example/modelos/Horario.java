package com.example.modelos;

import java.time.LocalTime;
import java.util.Objects;

public class Horario {
    private int id;
    private Instalacion instalacion;
    private LocalTime horaInicio;
    private LocalTime horaFin;


    public Horario() {
    }

    public Horario(int id, Instalacion instalacion, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.instalacion = instalacion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instalacion getInstalacion() {
        return this.instalacion;
    }

    public void setInstalacion(Instalacion instalacion) {
        this.instalacion = instalacion;
    }

    public LocalTime getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Horario id(int id) {
        setId(id);
        return this;
    }

    public Horario instalacion(Instalacion instalacion) {
        setInstalacion(instalacion);
        return this;
    }

    public Horario horaInicio(LocalTime horaInicio) {
        setHoraInicio(horaInicio);
        return this;
    }

    public Horario horaFin(LocalTime horaFin) {
        setHoraFin(horaFin);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Horario)) {
            return false;
        }
        Horario horario = (Horario) o;
        return id == horario.id && Objects.equals(instalacion, horario.instalacion) && Objects.equals(horaInicio, horario.horaInicio) && Objects.equals(horaFin, horario.horaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instalacion, horaInicio, horaFin);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", instalacion='" + getInstalacion() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFin='" + getHoraFin() + "'" +
            "}";
    }
    
}
