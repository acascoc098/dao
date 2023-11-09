package com.example.daoImplementacion;

import java.util.List;

import com.example.modelos.Instalacion;
import com.example.modelos.Usuario;

public class InstalacionDaoImple {
    public Instalacion create(Instalacion u){
        
    };

    public List<Instalacion> findAll();
    
    public Instalacion findById(int id);

    public Instalacion findByNombre(String nombre);
     
    public Instalacion update(int idOldInstalacion, Instalacion newInstalacion);
    public Instalacion update(Instalacion oldInstalacion, Instalacion newInstalacion);
    
    public Instalacion delete(int idOldInstalacion);
    public Instalacion delete(Instalacion instalacion);
}
