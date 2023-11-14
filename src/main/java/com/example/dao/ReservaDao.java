package com.example.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.modelos.Instalacion;
import com.example.modelos.Reserva;
import com.example.modelos.Usuario;


public interface ReservaDao {
    /**
     * Cuando insertamos una Reserva, como el ID es un
     * AUTO_INCREMENT (MySQL), el ID lo gestiona la BBDD.
     * @param u El Reserva que vamos a añadir a la BBDD.
     * @return El mismo Reserva pero con el ID real.
     */
    public Reserva create(Reserva horario) throws DaoException;

    /**
     * Buscar todos. 
     * @return devuelve una lista con todas las Reservas de la BBDD
     */
    public List<Reserva> findAll() throws DaoException;
    
    /**
     * Devuelve la Reserva con ID=id de la BBDD y si no está, devuelve null
     * @param id el ID de Reserva
     * @return la Reserva o NULL si no está
     */
    public Reserva findById(int id) throws DaoException;
    
    /**
     * Actualiza, si existe, si no, lo crea
     * si lo crea, el ID ¡¡cambia!! (AUTO_INCREMENT)
     * no puedo cambiar el ID
     * @return newReserva
     * */    
    public Reserva update(int idOldReserva, Reserva newReserva) throws DaoException;
    public Reserva update(Reserva oldReserva, Reserva newReserva) throws DaoException;
    
    /**
     * Borra una instalación si existe ese ID
     * @param idReserva
     * @return la instalación borrada si existe, null en caso contrario
     */
    public Reserva delete(int idReserva) throws DaoException;

    /**
     * Borra una instalación si existe esa instalación
     * @param Reserva
     * @return la instalación borrada si existe, null en caso contrario
     */
    public Reserva delete(Reserva Reserva) throws DaoException;

    public List<Reserva> findByInstalacion(Instalacion instalacion) throws DaoException;
    public List<Reserva> findByInstalacionAndFecha(Instalacion instalacion, LocalDate fecha) throws DaoException;
    public List<Reserva> findByUsuario(Usuario usuario) throws DaoException;
    public List<Reserva> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha) throws DaoException;

}
