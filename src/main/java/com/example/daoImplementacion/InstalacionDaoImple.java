package com.example.daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.conexion.FactoriaConexion;
import com.example.dao.DaoException;
import com.example.dao.InstalacionDao;
import com.example.modelos.Instalacion;

public class InstalacionDaoImple implements InstalacionDao {
    public Instalacion create(Instalacion i){

        try (Connection conexion = FactoriaConexion.getConnection()) {
            String sql = "INSERT INTO " + 
                    "instalacion(nombre, descripcion) "+
                    "VALUES (?,?)";

            PreparedStatement ps = conexion.prepareStatement(sql);
            // Como el ID es un AUTOINCREMENT se puede omitir
            // ps.setInt   (1, i.getId());
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getDescripcion());

            if (ps.executeUpdate()>0) {
                System.out.println("Insertado correctamente.");
            } else {
                System.out.println("Imposible insertar.");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        
        return this.findByNombre(i.getNombre());
    }
    
    public List<Instalacion> findAll(){
        List<Instalacion> lInstalaciones = new ArrayList<Instalacion>();

        try (Connection conexion = FactoriaConexion.getConnection()){
            String sql = "SELECT * FROM instalacion";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Instalacion i = new Instalacion(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("descripcion"));
                lInstalaciones.add(i);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lInstalaciones;
    }

    public Instalacion findById(int id){
        Instalacion i=null;

        try (Connection conexion = FactoriaConexion.getConnection()){            
            String sql = "SELECT * FROM instalacion WHERE id=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i = new Instalacion(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("descripcion"));                
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return i;
    }

    public Instalacion findByNombre(String nombre){
        Instalacion i=null;

        try (Connection conexion = FactoriaConexion.getConnection()){
            String sql = "SELECT * FROM instalacion WHERE nombre=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i = new Instalacion(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("descripcion"));                
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return i;
    }


    public Instalacion update(int idOldInstalacion, Instalacion newInstalacion){
        
        String sql = "UPDATE `instalacion` SET " +
                        // "`id`       = ?,"    +
                        "`nombre`   = ?,"    +
                        "`descripcion` = ? " +
                        "WHERE `id` = ? ";

        try (Connection conexion = FactoriaConexion.getConnection()){
            PreparedStatement ps = conexion.prepareStatement(sql);
            // ps.setInt   (1, newInstalacion.getId());
            ps.setString(1, newInstalacion.getNombre());
            ps.setString(2, newInstalacion.getDescripcion());
            ps.setInt   (3, idOldInstalacion);

            if (ps.executeUpdate()==0) {
                this.create(newInstalacion);                
            }

        } catch (Exception e) {
            // modo devel
            throw new DaoException(e.getMessage());
        }
        return this.findByNombre(newInstalacion.getNombre());
    }

    public Instalacion update(Instalacion oldInstalacion, Instalacion newInstalacion){
        if (newInstalacion.equals(oldInstalacion)) {
            return newInstalacion;
        }
        return this.update(oldInstalacion.getId(), newInstalacion);
    }

    public Instalacion delete(int idInstalacion){
        String sql = "DELETE FROM `instalacion` WHERE (id=?)"; 
        Instalacion i = this.findById(idInstalacion);
        if (i!=null){
            try (Connection conexion = FactoriaConexion.getConnection()){
                PreparedStatement ps = conexion.prepareStatement(sql);
                if (ps.executeUpdate()==0){
                    return null;
                }
            } catch (Exception e) {
                throw new DaoException(e.getMessage());
            }
        }
        return i;
    }

    public Instalacion delete(Instalacion instalacion){
        return this.delete(instalacion.getId());
    }

    public int count(){
        int cuenta = 0;
        try (Connection conexion = FactoriaConexion.getConnection()){
            String sql = "SELECT count(*) FROM instalacion";
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
