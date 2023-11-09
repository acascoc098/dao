package com.example.dao;

import java.util.List;

import com.iesvdc.acceso.inventario.modelos.Usuario;

public class UsuarioDao {
    //el id se autogenera, por eso quiero el objeto nuevo para saber el id real
    /**
     * Al insertar como el id es un AUTO_Increment, lo gestiona la BBDD
     * @param u Usuario para añadir a la BBDD
     * @return El mismo usuario pero con el id real
     */
    public Usuario create(Usuario u);

    /**
     * 
     * @return Lista con todoos los usuarios de la base
     */
    public List<Usuario> findAll();

    /**
     * 
     * @param nombre
     * @return
     */
    public List<Usuario> findByNombre(String nombre);
    /**
     * 
     * @param id
     * @return
     */
    public Usuario findById(int id);
    /**
     * 
     * @param username
     * @return
     */
    public Usuario findByUsername(String username);
    /**
     * Devuelve el usuario con EMAIL = email
     * El email es único y no se puede repetir
     * @param email
     * @return usuario o NULL si lo encuentra
     */
    public Usuario findByEmail(String email);

    /**
     * Actualiza si existe, si no, lo crea
     * Al crearlo el id ¡cambia! (AUTO_INCREMENT)
     * No puedo cambiar el id
     * @return newUser
     **/
    public Usuario update(int idOldUser, Usuario usuario);
    public Usuario update(int idOldUser, Usuario usuario);

    //Borrado por id u objaeto
    public Usuario delete(int idUsuario);
    public Usuario delete(Usuario usuario);
}
