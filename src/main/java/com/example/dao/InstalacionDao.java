package com.example.dao;

import java.util.List;

import com.example.modelos.Instalacion;
import com.example.modelos.Usuario;

public class InstalacionDao {
    /**
     * Al insertar como el id es un AUTO_Increment, lo gestiona la BBDD
     * @param u Usuario para añadir a la BBDD
     * @return El mismo usuario pero con el id real
     */
    public Instalacion create(Usuario u);

    /**
     * Buscar todos. 
     * @return devuelve una lista con todos los usuarios de la BBDD
     */
    public List<Instalacion> findAll();
    
    /**
     * Devuelve el usuario con ID=id de la BBDD y si no está, devuelve null
     * @param id el ID de usuario
     * @return el usuario o NULL si no está
     */
    public Instalacion findById(int id);

    /**
     * Devuelve el usuario con USERNAME=username de la BBDD y si no está, devuelve null.
     * El USERNAME es único y no se puede repetir.
     * @param username el nombre de usuario
     * @return el usuario o NULL si lo encuentra
     */
    public Instalacion findByNombre(String nombre);
    
    
    /**
     * Actualiza, si existe, si no, lo crea
     * si lo crea, el ID ¡¡cambia!! (AUTO_INCREMENT)
     * no puedo cambiar el ID
     * @return newUser
     * */    
    public Instalacion update(int idOldInstalacion, Instalacion newInstalacion);
    public Instalacion update(Instalacion oldInstalacion, Instalacion newInstalacion);
    
    public Instalacion delete(int idOldInstalacion);
    public Instalacion delete(Instalacion instalacion);
}
