package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.example.dao.ReservaDao;
import com.example.dao.UsuarioDao;
import com.example.daoImplementacion.ReservaDaoIml;
import com.example.daoImplementacion.UsuarioDaoImplementacion;
import com.example.modelos.LocalDateTimeTypeAdapter;
import com.example.modelos.LocalDateTypeAdapter;
import com.example.modelos.LocalTimeTypeAdapter;
import com.example.modelos.Reserva;
import com.example.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GuardarJSON 
{
    public static void main( String[] args )
    {

        UsuarioDao uDao = new UsuarioDaoImplementacion();
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
