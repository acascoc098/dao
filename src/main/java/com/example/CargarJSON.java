package com.example;

import com.example.conexion.FactoriaConexion;

public class CargarJSON {
    public static void main(String[] args) {
        // Borrar la BBDD entera porque así
        // borramos todas las tablas y disparadores
        //después procedemos a inicializar la BBDD
        FactoriaConexion.dropDatabase();
        FactoriaConexion.createDatabase();

        
        //Cargar el SQL de borrar la BBDD y cargarla de nuevo
    }
}
