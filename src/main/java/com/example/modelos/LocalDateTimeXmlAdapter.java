package com.example.modelos;

import java.text.ParseException;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class LocalDateTimeXmlAdapter extends XmlAdapter <String, LocalDateTime>{
    
    @Override
    public String marshal(LocalDateTime ld) {
        return ld.toString();
    }

    @Override
    public LocalDateTime unmarshal(String date) throws ParseException{
        return LocalDateTime.parse(date);
    }
    
}
