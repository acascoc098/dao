package com.example.dao;

import java.util.List;

import com.example.modelos.Usuario;

public interface UsuarioDao {
    //el id se autogenera, por eso quiero el objeto nuevo para saber el id real
    /**
     * Al insertar como el id es un AUTO_Increment, lo gestiona la BBDD
     * @param u Usuario para añadir a la BBDD
     * @return El mismo usuario pero con el id real
     */
    public Usuario create(Usuario u);

    /**
     * Buscar todos. 
     * @return devuelve una lista con todos los usuarios de la BBDD
     */
    public List<Usuario> findAll();
    
    /**
     * Devuelve el usuario con ID=id de la BBDD y si no está, devuelve null
     * @param id el ID de usuario
     * @return el usuario o NULL si no está
     */
    public Usuario findById(int id);

    /**
     * Devuelve el usuario con USERNAME=username de la BBDD y si no está, devuelve null.
     * El USERNAME es único y no se puede repetir.
     * @param username el nombre de usuario
     * @return el usuario o NULL si lo encuentra
     */
    public Usuario findByUsername(String username);
    
    /**
     * Devuelve el usuario con EMAIL=email de la BBDD y si no está, devuelve null. 
     * El EMAIL es único y no se puede repetir.
     * @param email la dirección de correo del usuario a buscar
     * @return el usuario o NULL si lo encuentra
     */
    public Usuario findByEmail(String email);
    
    /**
     * Actualiza, si existe, si no, lo crea
     * si lo crea, el ID ¡¡cambia!! (AUTO_INCREMENT)
     * no puedo cambiar el ID
     * @return newUser
     * */    
    public Usuario update(int idOldUser, Usuario newUser);
    public Usuario update(Usuario oldUser, Usuario newUser);
    
    public Usuario delete(int idUsuario);
    public Usuario delete(Usuario usuario);

}
