package com.example.daoImplementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.conexion.Conexion;
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
        List<com.iesvdc.acceso.inventario.modelo.Usuario> usuarios = new ArrayList<com.iesvdc.acceso.inventario.modelo.Usuario>();

        try {
            Conexion con = new Conexion();
            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = con.getConnection.prepareStatement(sql);
            ResultSet rs = ps.execute();

            while (rs.next()) {
                Usuario u = new Usuario(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getInt("telefono"));

                usuarios.add(u);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    };

    public List<Usuario> findByNombre(String nombre);

    public Usuario findById(int id){
        com.iesvdc.acceso.inventario.modelo.Usuario u = null;

        try {
            Conexion con = new Conexion();
            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = con.getConnection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.execute();

            while (rs.next()) {
                u = new Usuario(rs.getInt("id"),
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

    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);
    public Usuario update(int idOldUser, Usuario usuario);
    public Usuario update(int idOldUser, Usuario usuario);
    public Usuario delete(int idUsuario);
    public Usuario delete(Usuario usuario);
}
