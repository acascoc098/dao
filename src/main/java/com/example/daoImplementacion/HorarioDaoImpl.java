package com.example.daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.example.conexion.FactoriaConexion;
import com.example.dao.DaoException;
import com.example.dao.HorarioDao;
import com.example.modelos.Horario;
import com.example.modelos.Instalacion;



public class HorarioDaoImpl implements HorarioDao{
    
    public Horario create(Horario horario){
        String sql = "INSER INTO `horario` " + 
                    "(hora_inicio, hora_fin, instalacion) "+
                    "VALUES (?, ?, ?)";
        Horario hor = null;
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTime(1, Time.valueOf(horario.getHoraInicio()));
            ps.setTime(2, Time.valueOf(horario.getHoraFin()));
            ps.setInt(3, horario.getInstalacion().getId());
            if (ps.executeUpdate()>0) {
                hor = horario;
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return hor;
    }

    public List<Horario> findAll() {
        String sql =  "SELECT h.id AS `idHorario`, h.hora_inicio, h.hora_fin, " +
                    "i.id AS `idInstalacion`, i.nombre, i.descripcion " +
                    "FROM horario h, instalacion i " +
                    "WHERE h.instalacion = i.id";
        List<Horario> lHorarios = new ArrayList<Horario>();
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Horario horario = new Horario(
                    rs.getInt("idHorario"),
                    new Instalacion(
                        rs.getInt("idInstalacion"), 
                        rs.getString("nombre"), 
                        rs.getString("descripcion")),
                    rs.getTime("hora_inicio").toLocalTime(),
                    rs.getTime("hora_fin").toLocalTime()
                );
                lHorarios.add(horario);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lHorarios;
    }
    
    public Horario findById(int id) {
        String sql =  "SELECT h.id AS `idHorario`, h.hora_inicio, h.hora_fin, " +
                    "i.id AS `idInstalacion`, i.nombre, i.descripcion " +
                    "FROM horario h, instalacion i " +
                    "WHERE h.instalacion = i.id AND h.id=?";
        
        Horario horario = null;

        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                horario = new Horario(
                    rs.getInt("idHorario"),
                    new Instalacion(
                        rs.getInt("idInstalacion"), 
                        rs.getString("nombre"), 
                        rs.getString("descripcion")),
                    rs.getTime("hora_inicio").toLocalTime(),
                    rs.getTime("hora_fin").toLocalTime()
                );                
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return horario;
    }
     
    public Horario update(int idOldHorario, Horario newHorario){
        
        String sql = "UPDATE `horario` SET "+
            "`hora_inicio`  = ? ,"+
            "`hora_fin`     = ? ," +
            "`instalacion`  = ?  " +
            " WHERE `id`    = ? ";

        Horario horario = this.findById(idOldHorario);

        if (horario!=null){
            try (Connection conn = FactoriaConexion.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setTime(1, Time.valueOf(newHorario.getHoraInicio()));
                ps.setTime(2, Time.valueOf(newHorario.getHoraFin()));
                ps.setInt(3, newHorario.getInstalacion().getId());
                ps.setInt(4, idOldHorario);
                if (ps.executeUpdate()==0) 
                    throw new DaoException("Horario no actualizado");
            } catch (Exception e) {
                throw new DaoException("Error al actualizar horario: "+e.getMessage());
            }
        }
        return horario;
    }
    
    public Horario update(Horario oldHorario, Horario newHorario){
        return this.update(oldHorario.getId(), newHorario);
    }
    
    public Horario delete(int idHorario){

        String sql = "DELETE FROM `horario` WHERE `id` = ? ";

        Horario horario = this.findById(idHorario);

        if (horario!=null){
            try (Connection conn = FactoriaConexion.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, idHorario);
                if (ps.executeUpdate()==0) {
                    throw new DaoException("Horario no eliminado");
                }
            } catch (Exception e) {
                throw new DaoException("Error al actualizar horario: "+e.getMessage());
            }
        }
        return horario;
    }

    public Horario delete(Horario horario){
        return this.delete(horario.getId());
    }

    public List<Horario> findByInstalacion(Instalacion instalacion){
        String sql =  "SELECT h.id AS `idHorario`, h.hora_inicio, h.hora_fin, " +
                    "i.id AS `idInstalacion`, i.nombre, i.descripcion " +
                    "FROM horario h, instalacion i" +
                    "WHERE h.instalacion = i.id AND i.id = ?";
        List<Horario> lHorarios = new ArrayList<Horario>();
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, instalacion.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Horario horario = new Horario(
                    rs.getInt("idHorario"),
                    new Instalacion(
                        rs.getInt("idInstalacion"), 
                        rs.getString("nombre"), 
                        rs.getString("descripcion")),
                    rs.getTime("hora_inicio").toLocalTime(),
                    rs.getTime("hora_fin").toLocalTime()
                );
                lHorarios.add(horario);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lHorarios;
    }
    
    public List<Horario> findHorarios(Instalacion instalacion){
        return this.findByInstalacion(instalacion);
    }
    
}
