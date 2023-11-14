package com.example.conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * Patrón Factoría: 
 * 
 */
public class FactoriaConexion {

    private static Connection conn;

    private FactoriaConexion() {
        // Constructor privado para evitar instancias
    }

    public static Connection getConnection() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fis);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:33306", prop);

            if (!createDatabase()) {
                System.out.println("--== CONEXION IMPOSIBLE ==--");
            }

            conn.close();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:33306/reservas", prop);
        } catch (SQLException | ClassCastException | IOException e) {
            Logger.getLogger(FactoriaConexion.class.getName()).severe(e.getLocalizedMessage());
        }
        
        return conn;

    }

    public static void destroy() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }    

    private static boolean createDatabase() {
        boolean solucion = true;
        try {
            String sql = "CREATE DATABASE IF NOT EXISTS `reservas`";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        } catch (Exception e) {
            solucion = false;
            e.printStackTrace();
        }
        return solucion;
    }

    public static boolean dropDatabase() {
        boolean solucion = true;
        try {
            String sql = "DROP DATABASE `reservas`";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        } catch (Exception e) {
            solucion = false;
            e.printStackTrace();
        }
        return solucion;
    }
}
