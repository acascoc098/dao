package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.example.conexion.Conexion;
import com.example.conexion.FactoriaConexion;
import com.example.dao.DaoException;
import com.example.dao.HorarioDao;
import com.example.dao.InstalacionDao;
import com.example.dao.ReservaDao;
import com.example.dao.UsuarioDao;
import com.example.daoImplementacion.HorarioDaoImpl;
import com.example.daoImplementacion.InstalacionDaoImple;
import com.example.daoImplementacion.ReservaDaoIml;
import com.example.daoImplementacion.UsuarioDaoImplementacion;
import com.example.modelos.Horario;
import com.example.modelos.Reserva;
import com.example.modelos.Reservas;
import com.example.modelos.Usuario;
import com.example.modelos.Usuarios;

public class App {
    public static void main(String[] args) throws Exception {
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:33306/inventario";
            //Connection com = DriverManager.getConnection(jdbcUrl,"root", "zx76wbz7FG89k");

            //Conexion cone = new Conexion();
            Connection con = FactoriaConexion.getConnection();
            if (con != null) {
                System.out.println("Conectado correctamente");

                usuario(con);
                estancia(con);
                
            } else {
                System.out.println("Error de conexión");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        UsuarioDao uDao = new UsuarioDaoImplementacion();
        InstalacionDao iDao = new InstalacionDaoImple();
        HorarioDao horDao = new HorarioDaoImpl();
        ReservaDao rDao = new ReservaDaoIml();
        //List<Usuario> lUsuarios = uDao.findAll();        
        //System.out.println(lUsuarios);

        //System.out.println("Hay "+uDao.count()+ " usuarios");
        //System.out.println("--=== HORARIOS EN LA BASE DE DATOS ===--");
        //System.out.println(horDao.findAll());
        try{
            Usuario u = uDao.findById(3);
            Horario h = horDao.findById(4);
            rDao.create(new Reserva(0, u, h, LocalDateTime.now(), LocalDate.of(2023, 12, 20)));
        }catch(DaoException e){
            e.printStackTrace();
        }

        /*-----------------------------------------------------------*/
        JAXBContext jaxbContext;
        UsuarioDao uDaoo = new UsuarioDaoImplementacion();
        ReservaDao rDaoo = new ReservaDaoIml();

        List<Usuario> lUsuarios = uDaoo.findAll();
        Usuarios usuarios = new Usuarios();
        usuarios.setLUsuarios(lUsuarios);

        Reservas reservas = new Reservas(rDaoo.findAll());
        
        System.out.println(usuarios);

        try {
            //Usuarios
            jaxbContext = JAXBContext.newInstance(usuarios.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(usuarios, new File("usuarios.xml"));

            //Reservas
            jaxbContext = JAXBContext.newInstance(reservas.getClass());
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(usuarios, new File("reservas.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*ReservaDao resDao = new ReservaDaoIml();

        List<Reserva> lReservas = resDao.findAll();
        Reservas reservas = new Reservas();
        reservas.setLReservas(lReservas);
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(reservas.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(reservas, new File("reservas.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static void usuario(Connection com) throws SQLException{
        System.out.println("------------SELECT-----------");
                String sql = "SELECT * FROM usuario";

                PreparedStatement ps = com.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                System.out.println("\tid \t username \t password \t password \t email");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String tipo = rs.getString("tipo");
                    String email = rs.getString("email");

                    System.out.println("\t" + id + "\t" + username + "\t" + password + "\t" + password + "\t" + tipo + "\t" + email);
                }

                System.out.println("-------------INSERT----------");
                sql = "INSERT INTO usuario (?,?,?,?,?)";//Así mejor -> INSERT INTO usuario (id, username, password, tipo, email) VALUES (?,?,?,?,?)
                ps = com.prepareStatement(sql);
                ps.setInt(1, -1);
                ps.setString(2, "Paco");
                ps.setString(3, "contraseñaPaco");
                ps.setString(4, "ADMIN");
                ps.setString(5, "pacopepe@gmail.com");

                if (ps.executeUpdate() > 0){
                    System.out.println("INSERT REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL INSERTAR");
                }

                System.out.println("--------------DELETE------------");
                sql = "DELETE FROM usuario WHERE username = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "Paco");

                if (ps.executeUpdate() > 0){
                    System.out.println("DELETE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL BORRAR");
                }

                System.out.println("--------------UPDATE------------");
                sql = "UPDATE FROM usuario SET password = ? WHERE username = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "nuevacontra122");
                ps.setString(2, "pablo12");

                if (ps.executeUpdate() > 0){
                    System.out.println("UPDATE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL ACTUALIZAR");
                }

                com.close();
    }

    public static void estancia(Connection com) throws SQLException{
        System.out.println("------------SELECT-----------");
                String sql = "SELECT * FROM estancia";

                PreparedStatement ps = com.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                System.out.println("\tid \t nombre \t desripcion");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");

                    System.out.println("\t" + id + "\t" + nombre + "\t" + descripcion);
                }

                System.out.println("-------------INSERT----------");
                sql = "INSERT INTO estancia (id, nombre, descripcion) VALUES (?,?,?)";
                ps = com.prepareStatement(sql);
                ps.setInt(1, 1);
                ps.setString(2, "Hotel");
                ps.setString(3, "Descripción simple");

                if (ps.executeUpdate() > 0){
                    System.out.println("INSERT REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL INSERTAR");
                }

                System.out.println("--------------DELETE------------");
                sql = "DELETE FROM usuario WHERE username = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "Hotel");

                if (ps.executeUpdate() > 0){
                    System.out.println("DELETE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL BORRAR");
                }

                System.out.println("--------------UPDATE------------");
                sql = "UPDATE FROM usuario SET descripcion = ? WHERE nombre = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "Cambio de descripcion");
                ps.setString(2, "Hotel");

                if (ps.executeUpdate() > 0){
                    System.out.println("UPDATE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL ACTUALIZAR");
                }

                com.close();
    }
}
