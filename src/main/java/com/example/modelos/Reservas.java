package com.example.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservas {
    @XmlElement(name="reserva")
    List<Reserva> lReservas;

    public Reservas() {
        this.lReservas = new ArrayList<Reserva>();
    }

    public Reservas(List<Reserva> lReservas) {
        this.lReservas = lReservas;
    }

    public List<Reserva> getLReservas() {
        return this.lReservas;
    }

    public void setLReservas(List<Reserva> lReservas) {
        this.lReservas = lReservas;
    }

    public Reservas lReservas(List<Reserva> lReservas) {
        setLReservas(lReservas);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reservas)) {
            return false;
        }
        Reservas reservas = (Reservas) o;
        return Objects.equals(lReservas, reservas.lReservas);
    }


    @Override
    public String toString() {
        return "{" +
            " lReservas='" + getLReservas() + "'" +
            "}";
    }
    
}
