package com.example.daoImplementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.conexion.Conexion;
import com.example.dao.UsuarioDao;
import com.example.modelos.Usuario;

public class UsuarioDaoImplementacion implements UsuarioDao {
    
    public Usuario create(Usuario u){
        Conexion conexion = new Conexion();
        if (conexion!=null) {
            try {
                String sql = "INSERT INTO " + 
                        "usuario(id, username, password, email, nombre, apellido, telefono) "+
                        "VALUES (?,?,?,?,?)";

                PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
                
                ps.setInt(1, u.getId());
                ps.setString(2, u.getUsername());
                ps.setString(3, u.getPassword());
                ps.setString(4, u.getEmail());
                ps.setString(5, u.getNombre());
                ps.setString(5, u.getApellido());
                ps.setInt(6, u.getTelefono());

                if (ps.executeUpdate()>0) {
                    System.out.println("Insertado correctamente.");
                } else {
                    System.out.println("Imposible insertar.");
                }

                conexion.getConnection().close();
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
            
        }
        return u;

    };

    public List<Usuario> findAll(){
        List<Usuario> lUsuarios = new ArrayList<Usuario>();

        try {
            Conexion conexion = new Conexion();
            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id"), 
                    rs.getString("username"), 
                    rs.getString("password"), 
                    rs.getString("nombre"), 
                    rs.getString("apellido"), 
                    rs.getString("email"), 
                    rs.getInt("telefono"));
                lUsuarios.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lUsuarios;

    };

    public List<Usuario> findByNombre(String nombre){
        Usuario u=null;

        try {
            Conexion conexion = new Conexion();
            String sql = "SELECT * FROM usuario WHERE email=?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new Usuario(
                    rs.getInt("id"), 
                    rs.getString("username"), 
                    rs.getString("password"), 
                    rs.getString("nombre"), 
                    rs.getString("apellido"), 
                    rs.getString("email"), 
                    rs.getInt("telefono"));                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    };

    public Usuario findById(int id){
        Usuario u=null;

        try {
            Conexion conexion = new Conexion();
            String sql = "SELECT * FROM usuario WHERE email=?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new Usuario(
                    rs.getInt("id"), 
                    rs.getString("username"), 
                    rs.getString("password"), 
                    rs.getString("nombre"), 
                    rs.getString("apellido"), 
                    rs.getString("email"), 
                    rs.getInt("telefono"));                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    };

    public Usuario findByUsername(String username){
        Usuario u=null;

        try {
            Conexion conexion = new Conexion();
            String sql = "SELECT * FROM usuario WHERE email=?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new Usuario(
                    rs.getInt("id"), 
                    rs.getString("username"), 
                    rs.getString("password"), 
                    rs.getString("nombre"), 
                    rs.getString("apellido"), 
                    rs.getString("email"), 
                    rs.getInt("telefono"));                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;

    };

    public Usuario findByEmail(String email){
        Usuario u=null;

        try {
            Conexion conexion = new Conexion();
            String sql = "SELECT * FROM usuario WHERE email=?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new Usuario(
                    rs.getInt("id"), 
                    rs.getString("username"), 
                    rs.getString("password"), 
                    rs.getString("nombre"), 
                    rs.getString("apellido"), 
                    rs.getString("email"), 
                    rs.getInt("telefono"));                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    };


    public Usuario update(int idOldUser, Usuario usuario){
        String sql = "UPDATE `usuario` SET " +
        "`id`       = ?,"+
        "`username` = ?,"+
        "`password` = ?,"+
        "`email`    = ?,"+
        "`nombre`   = ?,"+
        "`apellido` = ?,"+
        "`telefono` = ? "+
        "WHERE `id` = ? ";

        try {
            Conexion conexion = new Conexion();
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setInt   (1, usuario.getId());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getNombre());
            ps.setString(6, usuario.getApellido());
            ps.setInt   (7, usuario.getTelefono());
            ps.setInt   (8, idOldUser);

            if (ps.executeUpdate()==0) {
                this.create(usuario);                
            }

        } catch (Exception e) {
            // modo devel
            e.printStackTrace();
        }
        return this.findByUsername(usuario.getUsername());

    };
    public Usuario update(Usuario idOldUser, Usuario usuario){
        if (usuario.equals(idOldUser)) {
            return usuario;
        }
        this.update(idOldUser.getId(), usuario);//Llamo al de arriba llamando al id de OldUser
    };

    public Usuario delete(int idUsuario){
        try{
         String sql = "DELETE FROM usuario WHERE id = ?";
         Usuario usu = this.findById(idUsuario);
         if (usu != null) {
            Conexion cone = new Conexion();
            PreparedStatement ps = cone.getConnection().prepareStatement(sql);

            if (ps.executeUpdate() == 0) {
                return null;
            }
         }
        }catch (Exception e){

        }
    };
    public Usuario delete(Usuario usuario){
        return this.delete(usuario.getId());
    };
}
