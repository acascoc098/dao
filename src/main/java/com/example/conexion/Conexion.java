package com.example.conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

//import com.mysql.cj.xdevapi.Statement;

/**
 *
 * @author acascoc098
 */
public class Conexion {

    Connection conn;
    Properties prop;

    /**
     * 
     */
    public Conexion() {
        // Vía JDBC
        if (conn == null) {
            try (FileInputStream fis = new FileInputStream("db.properties")) {
                // Class.forName("com.mysql.jdbc.Driver");
                prop = new Properties();
                prop.load(fis);
                this.conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:33306/inventario",
                        prop);
            } catch (SQLException | ClassCastException | IOException e) {
                Logger.getLogger(Conexion.class.getName()).severe(e.getLocalizedMessage());
            }
        }
    }
}