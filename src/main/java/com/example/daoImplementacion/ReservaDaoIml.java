package com.example.daoImplementacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.conexion.FactoriaConexion;
import com.example.dao.DaoException;
import com.example.dao.ReservaDao;
import com.example.modelos.Horario;
import com.example.modelos.Instalacion;
import com.example.modelos.Reserva;
import com.example.modelos.Usuario;



public class ReservaDaoIml implements ReservaDao {
    
    public Reserva create(Reserva reserva){
        String sql = "INSER INTO `reserva` " + 
                    "(usuario, horario, fecha) "+
                    "VALUES (?, ?, ?)";
        Reserva res = null;
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reserva.getUsuario().getId());
            ps.setInt(2, reserva.getHorario().getId());
            ps.setDate(3, Date.valueOf(reserva.getFecha()));
            if (ps.executeUpdate()>0) {
                res = reserva;
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return res;
    }

    public List<Reserva> findAll() {
        // Las reservas tienen Usuarios y Horarios dentro y a su vez Horario tiene Instalación dentro
        String sql =  "SELECT r.id as idReserva, r.fecha, r.fecha_hora_hecha, " + 
                "u.id as idUsuario, u.username, u.nombre as nombreUsuario, u.apellido , " + 
                "h.id as idHorario, h.hora_inicio, h.hora_fin, " + 
                "i.id as idInstalacion, i.nombre as nombreInstalacion, i.descripcion " + 
                "FROM reserva r, usuario u, horario h, instalacion i " + 
                "WHERE i.id = h.instalacion AND h.id = r.horario AND u.id = r.usuario" ;

        List<Reserva> lReservas = new ArrayList<Reserva>();
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reserva reserva = new Reserva(
                    rs.getInt("idReserva"),
                    new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("username"),
                        null,
                        rs.getString("nombreUsuario"),
                        rs.getString("apellido"),
                        null,
                        -1),
                    new Horario(
                        rs.getInt("idHorario"),
                        new Instalacion(
                            rs.getInt("idInstalacion"),
                            rs.getString("nombreInstalacion"),
                            rs.getString("descripcion")
                        ),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime()
                    ),
                    rs.getTimestamp("fecha_hora_hecha").toLocalDateTime(),
                    rs.getDate("fecha").toLocalDate()
                );
                lReservas.add(reserva);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lReservas;
    }
    
    public Reserva findById(int id) {
        // Las reservas tienen Usuarios y Horarios dentro y a su vez Horario tiene Instalación dentro
        String sql =  "SELECT r.id as idReserva, r.fecha, r.fecha_hora_hecha, " + 
                "u.id as idUsuario, u.username, u.nombre as nombreUsuario, u.apellido , " + 
                "h.id as idHorario, h.hora_inicio, h.hora_fin, " + 
                "i.id as idInstalacion, i.nombre as nombreInstalacion, i.descripcion " + 
                "FROM reserva r, usuario u, horario h, instalacion i " + 
                "WHERE i.id = h.instalacion AND h.id = r.horario AND u.id = r.usuario and r.id=? " ;

        Reserva reserva = null; 
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                reserva = new Reserva(
                    rs.getInt("idReserva"),
                    new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("username"),
                        null,
                        rs.getString("nombreUsuario"),
                        rs.getString("apellido"),
                        null,
                        -1),
                    new Horario(
                        rs.getInt("idHorario"),
                        new Instalacion(
                            rs.getInt("idInstalacion"),
                            rs.getString("nombreInstalacion"),
                            rs.getString("descripcion")
                        ),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime()
                    ),
                    rs.getTimestamp("fecha_hora_hecha").toLocalDateTime(),
                    rs.getDate("fecha").toLocalDate()
                ); 
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return reserva;
    }
     
    public Reserva update(int idOldReserva, Reserva newReserva){
        // el ID nunca se debe actualizar
        String sql = "UPDATE `reserva` SET " + 
                        // "`id`       = ?, " +
                        "`usuario`  = ?, " +
                        "`horario`  = ?, " +
                        "`fecha`    = ?, " +
                        "`fecha_hora_hecha` = ?"+
                        "WHERE `id` = ?";

        Reserva reserva = this.findById(idOldReserva);

        if (reserva!=null){
            try (Connection conn = FactoriaConexion.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, newReserva.getUsuario().getId());
                ps.setInt(2, newReserva.getHorario().getId());
                ps.setDate(3, Date.valueOf(newReserva.getFecha()));
                ps.setTimestamp(4, Timestamp.valueOf(newReserva.getFechaHoraHecha()) );

                if (ps.executeUpdate()==0) 
                    throw new DaoException("Reserva no actualizado");
            } catch (Exception e) {
                throw new DaoException("Error al actualizar reserva: "+e.getMessage());
            }
        }
        return reserva;
    }
    
    public Reserva update(Reserva oldReserva, Reserva newReserva){
        return this.update(oldReserva.getId(), newReserva);
    }
    
    public Reserva delete(int idReserva){

        String sql = "DELETE FROM `reserva` WHERE `id` = ? ";

        Reserva reserva = this.findById(idReserva);

        if (reserva!=null){
            try (Connection conn = FactoriaConexion.getConnection()) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, idReserva);
                if (ps.executeUpdate()==0) {
                    throw new DaoException("Reserva no eliminado");
                }
            } catch (Exception e) {
                throw new DaoException("Error al actualizar reserva: "+e.getMessage());
            }
        }
        return reserva;
    }

    public Reserva delete(Reserva reserva){
        return this.delete(reserva.getId());
    }

     
    public List<Reserva> findByInstalacion(Instalacion instalacion) throws DaoException{
        // Las reservas tienen Usuarios y Horarios dentro y a su vez Horario tiene Instalación dentro
        String sql =  "SELECT r.id as idReserva, r.fecha, r.fecha_hora_hecha, " + 
                "u.id as idUsuario, u.username, u.nombre as nombreUsuario, u.apellido , " + 
                "h.id as idHorario, h.hora_inicio, h.hora_fin, " + 
                "i.id as idInstalacion, i.nombre as nombreInstalacion, i.descripcion " + 
                "FROM reserva r, usuario u, horario h, instalacion i " + 
                "WHERE h.instalacion = ? AND h.id = r.horario AND u.id = r.usuario " ;

        List<Reserva> lReservas = new ArrayList<Reserva>();
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, instalacion.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reserva reserva = new Reserva(
                    rs.getInt("idReserva"),
                    new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("username"),
                        null,
                        rs.getString("nombreUsuario"),
                        rs.getString("apellido"),
                        null,
                        -1),
                    new Horario(
                        rs.getInt("idHorario"),
                        new Instalacion(
                            rs.getInt("idInstalacion"),
                            rs.getString("nombreInstalacion"),
                            rs.getString("descripcion")
                        ),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime()
                    ),
                    rs.getTimestamp("fecha_hora_hecha").toLocalDateTime(),
                    rs.getDate("fecha").toLocalDate()
                );
                lReservas.add(reserva);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lReservas;
    }

    public List<Reserva> findByInstalacionAndFecha(Instalacion instalacion, LocalDate fecha) throws DaoException {
        // Las reservas tienen Usuarios y Horarios dentro y a su vez Horario tiene Instalación dentro
        String sql =  "SELECT r.id as idReserva, r.fecha, r.fecha_hora_hecha, " + 
                "u.id as idUsuario, u.username, u.nombre as nombreUsuario, u.apellido , " + 
                "h.id as idHorario, h.hora_inicio, h.hora_fin, " + 
                "i.id as idInstalacion, i.nombre as nombreInstalacion, i.descripcion "  + 
                "FROM reserva r, usuario u, horario h, instalacion i " + 
                "WHERE h.instalacion = ? AND h.id = r.horario AND r.usuario = u.id AND r.fecha = ? " ;

        List<Reserva> lReservas = new ArrayList<Reserva>();
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, instalacion.getId());
            ps.setDate(2, Date.valueOf(fecha) );
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reserva reserva = new Reserva(
                    rs.getInt("idReserva"),
                    new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("username"),
                        null,
                        rs.getString("nombreUsuario"),
                        rs.getString("apellido"),
                        null,
                        -1),
                    new Horario(
                        rs.getInt("idHorario"),
                        new Instalacion(
                            rs.getInt("idInstalacion"),
                            rs.getString("nombreInstalacion"),
                            rs.getString("descripcion")
                        ),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime()
                    ),
                    rs.getTimestamp("fecha_hora_hecha").toLocalDateTime(),
                    rs.getDate("fecha").toLocalDate()
                );
                lReservas.add(reserva);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lReservas;
    }

    public List<Reserva> findByUsuario(Usuario usuario) throws DaoException {
        // Las reservas tienen Usuarios y Horarios dentro y a su vez Horario tiene Instalación dentro
        String sql =  "SELECT r.id as idReserva, r.fecha, r.fecha_hora_hecha, " + 
                "u.id as idUsuario, u.username, u.nombre as nombreUsuario, u.apellido , " + 
                "h.id as idHorario, h.hora_inicio, h.hora_fin, " + 
                "i.id as idInstalacion, i.nombre as nombreInstalacion, i.descripcion " + 
                "FROM reserva r, usuario u, horario h, instalacion i " + 
                "WHERE i.id = h.instalacion AND h.id = r.horario AND r.usuario = ? " ;

        List<Reserva> lReservas = new ArrayList<Reserva>();
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setInt(1, usuario.getId());
            while(rs.next()){
                Reserva reserva = new Reserva(
                    rs.getInt("idReserva"),
                    new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("username"),
                        null,
                        rs.getString("nombreUsuario"),
                        rs.getString("apellido"),
                        null,
                        -1),
                    new Horario(
                        rs.getInt("idHorario"),
                        new Instalacion(
                            rs.getInt("idInstalacion"),
                            rs.getString("nombreInstalacion"),
                            rs.getString("descripcion")
                        ),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime()
                    ),
                    rs.getTimestamp("fecha_hora_hecha").toLocalDateTime(),
                    rs.getDate("fecha").toLocalDate()
                );
                lReservas.add(reserva);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lReservas;
    }
    
    public List<Reserva> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha) throws DaoException {
        // Las reservas tienen Usuarios y Horarios dentro y a su vez Horario tiene Instalación dentro
        String sql =  "SELECT r.id as idReserva, r.fecha, r.fecha_hora_hecha, " + 
                "u.id as idUsuario, u.username, u.nombre as nombreUsuario, u.apellido , " + 
                "h.id as idHorario, h.hora_inicio, h.hora_fin, " + 
                "i.id as idInstalacion, i.nombre as nombreInstalacion, i.descripcion " + 
                "FROM reserva r, usuario u, horario h, instalacion i " + 
                "WHERE i.id = h.instalacion AND h.id = r.horario AND r.usuario = ? AND r.fecha=? " ;

        List<Reserva> lReservas = new ArrayList<Reserva>();
        try (Connection conn = FactoriaConexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setInt(1, usuario.getId());
            ps.setDate(2, Date.valueOf(fecha));
            while(rs.next()){
                Reserva reserva = new Reserva(
                    rs.getInt("idReserva"),
                    new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("username"),
                        null,
                        rs.getString("nombreUsuario"),
                        rs.getString("apellido"),
                        null,
                        -1),
                    new Horario(
                        rs.getInt("idHorario"),
                        new Instalacion(
                            rs.getInt("idInstalacion"),
                            rs.getString("nombreInstalacion"),
                            rs.getString("descripcion")
                        ),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime()
                    ),
                    rs.getTimestamp("fecha_hora_hecha").toLocalDateTime(),
                    rs.getDate("fecha").toLocalDate()
                );
                lReservas.add(reserva);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return lReservas;
    }
   
}
