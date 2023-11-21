package com.example.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Reserva {
    private int id; 
    private Usuario usuario;
    private Horario horario;
     @XmlElement(name="fecha_hora_reserva")
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime fechaHoraHecha; // fecha y hora cuando hicimos la reserva
    @XmlElement(name="fecha_reserva")
    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    private LocalDate fecha; // fecha cuando tenemos reservada la instalación



    public Reserva() {
    }

    public Reserva(int id, Usuario usuario, Horario horario, LocalDateTime fechaHoraHecha, LocalDate fecha) {
        this.id = id;
        this.usuario = usuario;
        this.horario = horario;
        this.fechaHoraHecha = fechaHoraHecha;
        this.fecha = fecha;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Horario getHorario() {
        return this.horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public LocalDateTime getFechaHoraHecha() {
        return this.fechaHoraHecha;
    }

    public void setFechaHoraHecha(LocalDateTime fechaHoraHecha) {
        this.fechaHoraHecha = fechaHoraHecha;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Reserva id(int id) {
        setId(id);
        return this;
    }

    public Reserva usuario(Usuario usuario) {
        setUsuario(usuario);
        return this;
    }

    public Reserva horario(Horario horario) {
        setHorario(horario);
        return this;
    }

    public Reserva fechaHoraHecha(LocalDateTime fechaHoraHecha) {
        setFechaHoraHecha(fechaHoraHecha);
        return this;
    }

    public Reserva fecha(LocalDate fecha) {
        setFecha(fecha);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reserva)) {
            return false;
        }
        Reserva reserva = (Reserva) o;
        return id == reserva.id && Objects.equals(usuario, reserva.usuario) && Objects.equals(horario, reserva.horario) && Objects.equals(fechaHoraHecha, reserva.fechaHoraHecha) && Objects.equals(fecha, reserva.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, horario, fechaHoraHecha, fecha);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", horario='" + getHorario() + "'" +
            ", fechaHoraHecha='" + getFechaHoraHecha() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }

    
}
