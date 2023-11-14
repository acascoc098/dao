package com.example.dao;

import java.util.List;

import com.example.modelos.Instalacion;
import com.example.modelos.Usuario;

public interface InstalacionDao {
    /**
     * Cuando insertamos un Instalacion, como el ID es un
     * AUTO_INCREMENT (MySQL), el ID lo gestiona la BBDD.
     * @param u El Instalacion que vamos a añadir a la BBDD.
     * @return El mismo Instalacion pero con el ID real.
     */
    public Instalacion create(Instalacion u) throws DaoException;

    /**
     * Buscar todos. 
     * @return devuelve una lista con todos los Instalacions de la BBDD
     */
    public List<Instalacion> findAll() throws DaoException;
    
    /**
     * Devuelve el Instalacion con ID=id de la BBDD y si no está, devuelve null
     * @param id el ID de Instalacion
     * @return el Instalacion o NULL si no está
     */
    public Instalacion findById(int id) throws DaoException;

    /**
     * Devuelve el Instalacion con NAME=name de la BBDD y si no está, devuelve null.
     * El NAME es único y no se puede repetir.
     * @param nombre el nombre de Instalacion
     * @return el Instalacion o NULL si lo encuentra
     */
    public Instalacion findByNombre(String nombre) throws DaoException;
        
    
    /**
     * Actualiza, si existe, si no, lo crea
     * si lo crea, el ID ¡¡cambia!! (AUTO_INCREMENT)
     * no puedo cambiar el ID
     * @return newInstalacion
     * */    
    public Instalacion update(int idOldInstalacion, Instalacion newInstalacion) throws DaoException;
    public Instalacion update(Instalacion oldInstalacion, Instalacion newInstalacion) throws DaoException;
    
    /**
     * Borra una instalación si existe ese ID
     * @param idInstalacion
     * @return la instalación borrada si existe, null en caso contrario
     */
    public Instalacion delete(int idInstalacion) throws DaoException;

    /**
     * Borra una instalación si existe esa instalación
     * @param Instalacion
     * @return la instalación borrada si existe, null en caso contrario
     */
    public Instalacion delete(Instalacion Instalacion) throws DaoException;

    public int count()  throws DaoException;
}
