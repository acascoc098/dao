package com.example.daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.conexion.Conexion;
import com.example.conexion.FactoriaConexion;
import com.example.dao.DaoException;
import com.example.dao.UsuarioDao;
import com.example.modelos.Usuario;

public class UsuarioDaoImplementacion implements UsuarioDao {
    
   public Usuario create(Usuario u) throws DaoException{

        try (Connection conexion = FactoriaConexion.getConnection()) {
            String sql = "INSERT INTO " + 
                    //"usuario(id, username, password, email, nombre, apellido, telefono) "+
                    "usuario(username, password, email, nombre, apellido, telefono) "+
                    "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = conexion.prepareStatement(sql);
            
            // ps.setInt   (1, u.getId());
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getNombre());
            ps.setString(5, u.getApellido());
            ps.setInt   (6, u.getTelefono());

            if (ps.executeUpdate()==0) {
                throw new DaoException("Creando usuario: Imposible crear");
            }
        } catch (SQLException e) {
            throw new DaoException("Creando usuario: Imposible crear" + e.getLocalizedMessage());
        }
        
        return this.findById(u.getId());
    }
    
    public List<Usuario> findAll(){
        List<Usuario> lUsuarios = new ArrayList<Usuario>();

        try (Connection conexion = FactoriaConexion.getConnection()){
            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = conexion.prepareStatement(sql);
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
            throw new DaoException(e.getMessage());
        }
        return lUsuarios;
    }

    public List<Usuario> findAll(int pagina, int resultadosPorPagina) {
        List<Usuario> usuarios = new ArrayList<>();
    
        try (Connection conexion = FactoriaConexion.getConnection()) {
            String sql = "SELECT * FROM usuario LIMIT ?, ?";
            int inicio = (pagina - 1) * resultadosPorPagina;
    
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                
                ps.setInt(1, inicio);
                ps.setInt(2, resultadosPorPagina);
    
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Usuario u = new Usuario(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("email"),
                                rs.getInt("telefono"));
                        usuarios.add(u);
                    }
                }
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    
        return usuarios;
    }


    public Usuario findById(int id){
        Usuario u=null;

        try (Connection conexion = FactoriaConexion.getConnection()){            
            String sql = "SELECT * FROM usuario WHERE id=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
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
            throw new DaoException(e.getMessage());
        }
        return u;
    }

    public Usuario findByUsername(String username){
        Usuario u=null;

        try (Connection conexion = FactoriaConexion.getConnection()){
            String sql = "SELECT * FROM usuario WHERE username=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
            throw new DaoException(e.getMessage());
        }
        return u;
    }

    public Usuario findByEmail(String email){
        Usuario u=null;

        try (Connection conexion = FactoriaConexion.getConnection()){
            String sql = "SELECT * FROM usuario WHERE email=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
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
            throw new DaoException(e.getMessage());
        }
        return u;
    }

    public Usuario update(int idOldUser, Usuario newUser){
        // Como es un AUTOINCREMENT no hace falta poner ID
        String sql = "UPDATE `usuario` SET " +
            //"`id`       = ?,"+
            "`username` = ?,"+
            "`password` = ?,"+
            "`email`    = ?,"+
            "`nombre`   = ?,"+
            "`apellido` = ?,"+
            "`telefono` = ? "+
            "WHERE `id` = ? ";

        try (Connection conexion = FactoriaConexion.getConnection()){
            PreparedStatement ps = conexion.prepareStatement(sql);
            // ps.setInt   (1, newUser.getId());
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getEmail());
            ps.setString(4, newUser.getNombre());
            ps.setString(5, newUser.getApellido());
            ps.setInt   (6, newUser.getTelefono());
            //ps.setInt   (8, idOldUser);

            if (ps.executeUpdate()==0) {
                this.create(newUser);                
            }

        } catch (Exception e) {
            // modo devel
            throw new DaoException(e.getMessage());
        }
        return this.findByUsername(newUser.getUsername());
    }

    public Usuario update(Usuario oldUser, Usuario newUser){
        if (newUser.equals(oldUser)) {
            return newUser;
        }
        return this.update(oldUser.getId(), newUser);
    }

    public Usuario delete(int idUsuario){
        String sql = "DELETE FROM `usuario` WHERE (id=?)"; 
        Usuario u = this.findById(idUsuario);
        if (u!=null){
            try (Connection conexion = FactoriaConexion.getConnection()){
                PreparedStatement ps = conexion.prepareStatement(sql);
                if (ps.executeUpdate()==0){
                    return null;
                }
            } catch (Exception e) {
                throw new DaoException(e.getMessage());
            }
        }
        return u;
    }

    public Usuario delete(Usuario usuario){
        return this.delete(usuario.getId());
    }

    public int count(){
        int cuenta = 0;
        try (Connection conexion = FactoriaConexion.getConnection()){
            String sql = "SELECT count(*) FROM usuario";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
                cuenta = rs.getInt(1);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return cuenta;
    }
}
