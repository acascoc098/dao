package com.example.conexion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
           
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:33306", prop);

            if (!createDatabase()) {
                System.out.println("--== CONEXION IMPOSIBLE ==--");
            }

            Properties prop = new Properties();
            prop.load(fis);
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

    public static boolean createDatabase() {
        boolean solucion = true;
        try {
            FileInputStream fis = new FileInputStream("db.properties");
            Properties prop = new Properties();
            prop.load(fis);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:33306", prop);

            String sql = "CREATE DATABASE IF NOT EXISTS `reservas`";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
               solucion = ps.executeUpdate()==1;
            }

        } catch (Exception e) {
            solucion = false;
            e.printStackTrace();
        }
        return solucion;
    }

    public static boolean dropDatabase() {
        boolean solucion = true;
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fis);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:33306", prop);

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

    public static void initializeDatabase(String filename, String databasename) {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fis);
            try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:33306/"+databasename, prop)) {
                // Obtener el script SQL desde el archivo
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
                StringBuilder scriptSql = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    // Concatenar las líneas del script
                    scriptSql.append(linea.trim());
                    if (!linea.endsWith(";")) {
                        // Si la línea no termina con ';', agrega un espacio para separar las instrucciones
                        scriptSql.append(" ");
                    } else {
                        // Si la línea termina con ';', ejecuta la instrucción SQL
                        try (Statement statement = conexion.createStatement()) {
                            statement.executeUpdate(scriptSql.toString());
                        }
                        // Reinicia el StringBuilder para la próxima instrucción
                        scriptSql.setLength(0);
                    }
                }
                reader.close();
                System.out.println("Base de datos inicializada correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
