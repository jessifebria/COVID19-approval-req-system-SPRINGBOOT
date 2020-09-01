/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
public class DateServices {

    //UNTUK SEMUA KEC PETUGAS RAPID
    public Date datetosql2() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String datepalsu = "2020/08/16";
        Date date = formatter.parse(datepalsu);

        return date;
    }

    public String datenow2() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        String datepalsu = "16/08/2020";
        Date date = formatter.parse(datepalsu);
        DateFormat format2 = new SimpleDateFormat("EEEE");

        return format2.format(date) + ", " + formatter.format(date);
    }
    
     public Date gettomorrowdaytosql() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(datetosql2());
        c.add(Calendar.DATE, 1);
        
        return c.getTime();
    }
     
    public String gettomorrowdate() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(datetosql2());
        c.add(Calendar.DATE, 1);
        Date date =c.getTime();
        DateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        
        return format2.format(date);
    }
    
    public String gettomorrowday() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(datetosql2());
        c.add(Calendar.DATE, 1);
        Date date =c.getTime();
        DateFormat format2 = new SimpleDateFormat("EEEE",Locale.ENGLISH);
        
        return format2.format(date);
    }
    

    public Date getdatetimebefore5pmtosql() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String datepalsu = "2020/08/16 14:53:50";
        Date date = formatter.parse(datepalsu);

        return date;
    }
    
    
    public Date getdatetimaafter5pmtosql() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String datepalsu = "2020/08/16 18:12:22";
        Date date = formatter.parse(datepalsu);

        return date;
    }

    //UNTUK PETUGAS RAPID
    public Date datetosql() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String datepalsu = "2020/08/15";
        Date date = formatter.parse(datepalsu);

        return date;
    }

    public String datenow() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        String datepalsu = "15/08/2020";
        Date date = formatter.parse(datepalsu);
        DateFormat format2 = new SimpleDateFormat("EEEE");

        return format2.format(date) + ", " + formatter.format(date);
    }

}
