package com.iesvdc.acceso;


import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iesvdc.acceso.dao.ReservaDao;
import com.iesvdc.acceso.dao.UsuarioDao;
import com.iesvdc.acceso.daoimpl.ReservaDaoIml;
import com.iesvdc.acceso.daoimpl.UsuarioDaoImpl;
import com.iesvdc.acceso.modelos.Reserva;
import com.iesvdc.acceso.modelos.Usuario;
import com.iesvdc.acceso.modelos.Usuarios;



public class GuardarJSON 
{
    public static void main( String[] args )
    {

        UsuarioDao uDao = new UsuarioDaoImpl();
        List<Usuario> lUsuarios = uDao.findAll();        

        ReservaDao rDao = new ReservaDaoIml();
        List<Reserva> lReservas = rDao.findAll();

        

        try {
            // Para que salga "bonito"
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .setPrettyPrinting().create();
            // Gson gson = new Gson();
            String salida = gson.toJson(lUsuarios);
            System.out.println(salida);
            salida = gson.toJson(lReservas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
