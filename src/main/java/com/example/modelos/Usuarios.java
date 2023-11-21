package com.example.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarios {
    List <Usuario> lUsuarios;


    public Usuarios() {
        lUsuarios = new ArrayList<Usuario>();
    }

    public Usuarios(List<Usuario> lUsuarios) {
        this.lUsuarios = lUsuarios;
    }

    public List<Usuario> getLUsuarios() {
        return this.lUsuarios;
    }

    public void setLUsuarios(List<Usuario> lUsuarios) {
        this.lUsuarios = lUsuarios;
    }

    public Usuarios lUsuarios(List<Usuario> lUsuarios) {
        setLUsuarios(lUsuarios);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuarios)) {
            return false;
        }
        Usuarios usuarios = (Usuarios) o;
        return Objects.equals(lUsuarios, usuarios.lUsuarios);
    }


    @Override
    public String toString() {
        return "{" +
            " lUsuarios='" + getLUsuarios() + "'" +
            "}";
    }
    
}
