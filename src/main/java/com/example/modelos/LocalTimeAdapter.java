package com.example.modelos;

import java.text.ParseException;
import java.time.LocalTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class LocalTimeAdapter extends XmlAdapter <String, LocalTime>{
    
    @Override
    public String marshal(LocalTime ld) {
        return ld.toString();
    }

    @Override
    public LocalTime unmarshal(String date) throws ParseException{
        return LocalTime.parse(date);
    }
    
}
