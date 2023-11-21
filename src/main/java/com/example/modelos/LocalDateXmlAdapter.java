package com.example.modelos;

import java.text.ParseException;
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class LocalDateXmlAdapter extends XmlAdapter <String, LocalDate>{
    
    @Override
    public String marshal(LocalDate ld) {
        return ld.toString();
    }

    @Override
    public LocalDate unmarshal(String date) throws ParseException{
        return LocalDate.parse(date);
    }
    
}
