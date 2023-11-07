package com.example.dao;

import java.util.List;

import com.iesvdc.acceso.inventario.modelos.Usuario;

public class UsuarioDao {
    //el id se autogenera, por eso quiero el objeto nuevo para saber el id real
    public Usuario create(Usuario u);
    public List<Usuario> findAll();
    public List<Usuario> findByNombre(String nombre);
    public Usuario findById(int id);
    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);

    //Actualiza si existe, si no, lo crea
    //Al crearlo el id ¡cambia! (AUTO_INCREMENT)
    //No puedo cambiar el id
    public Usuario update(int idOldUser, Usuario usuario);
    public Usuario update(int idOldUser, Usuario usuario);
    //Borrado por id u objaeto
    public Usuario delete(int idUsuario);
    public Usuario delete(Usuario usuario);
}
