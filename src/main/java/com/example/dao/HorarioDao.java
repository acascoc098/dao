package com.example.dao;

import java.util.List;

import com.example.modelos.Horario;
import com.example.modelos.Instalacion;



public interface HorarioDao {

    /**
     * Cuando insertamos un Horario, como el ID es un
     * AUTO_INCREMENT (MySQL), el ID lo gestiona la BBDD.
     * @param u El Horario que vamos a añadir a la BBDD.
     * @return El mismo Horario pero con el ID real.
     */
    public Horario create(Horario horario) throws DaoException;

    /**
     * Buscar todos. 
     * @return devuelve una lista con todos los Horarios de la BBDD
     */
    public List<Horario> findAll() throws DaoException;
    
    /**
     * Devuelve el Horario con ID=id de la BBDD y si no está, devuelve null
     * @param id el ID de Horario
     * @return el Horario o NULL si no está
     */
    public Horario findById(int id) throws DaoException;
    
    /**
     * Actualiza, si existe, si no, lo crea
     * si lo crea, el ID ¡¡cambia!! (AUTO_INCREMENT)
     * no puedo cambiar el ID
     * @return newHorario
     * */    
    public Horario update(int idOldHorario, Horario newHorario) throws DaoException;
    public Horario update(Horario oldHorario, Horario newHorario) throws DaoException;
    
    /**
     * Borra una instalación si existe ese ID
     * @param idHorario
     * @return la instalación borrada si existe, null en caso contrario
     */
    public Horario delete(int idHorario) throws DaoException;

    /**
     * Borra una instalación si existe esa instalación
     * @param Horario
     * @return la instalación borrada si existe, null en caso contrario
     */
    public Horario delete(Horario Horario) throws DaoException;

    public List<Horario> findByInstalacion(Instalacion instalacion) throws DaoException;
    public List<Horario> findHorarios(Instalacion instalacion) throws DaoException;
}
