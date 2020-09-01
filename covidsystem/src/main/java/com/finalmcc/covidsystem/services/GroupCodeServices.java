/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.GroupCode;
import com.finalmcc.covidsystem.repositories.GroupCodeRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
@Transactional
public class GroupCodeServices {

    @Autowired
    GroupCodeRepository grouprepo;

    @Autowired
    DateServices dateservices;
    
    public String soondate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(grouprepo.nextgroupdate());
        c.add(Calendar.DATE, 14);
        String output = sdf.format(c.getTime());
        return output;
    }

    public Date soondatetosql() {
        Calendar c = Calendar.getInstance();
        c.setTime(grouprepo.nextgroupdate());
        c.add(Calendar.DATE, 14);
        return c.getTime();
    }

     public Date soondatenowtosql() {
        Calendar c = Calendar.getInstance();
        c.setTime(grouprepo.nextgroupdate());
        c.add(Calendar.DATE, 13);
        return c.getTime();
    }
    
    public Date datenow() {
        return grouprepo.nextgroupdate();

    }

    public String expdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(grouprepo.nextgroupdate());
        c.add(Calendar.DATE, 25);
        String output = sdf.format(c.getTime());
        return output;
    }

    public int count(String group) {
        if (group == "a") {
            return grouprepo.counta() * 2;
        } else if (group == "b") {
            return grouprepo.countb() * 2;
        } else {
            return 0;
        }
    }

    public String gettomorrowgroup() throws ParseException{
        return grouprepo.getdaygroup(dateservices.gettomorrowday());
    }
    
    
    public List<GroupCode> getall() {
        return grouprepo.getall();
    }

    public void setfalse(Date date) {
        grouprepo.setfalse(date);
    }

    public void savegroup(Date start, String day1, String day2, String day3, String day4, String day5) {

        GroupCode dayy = new GroupCode("Monday", start, day1, "True");
        grouprepo.save(dayy);

        GroupCode dayy1 = new GroupCode("Tuesday", start, day2, "True");
        grouprepo.save(dayy1);

        GroupCode dayy2 = new GroupCode("Wednesday", start, day3, "True");
        grouprepo.save(dayy2);

        GroupCode dayy3 = new GroupCode("Thursday", start, day4, "True");
        grouprepo.save(dayy3);

        GroupCode dayy4 = new GroupCode("Friday", start, day5, "True");
        grouprepo.save(dayy4);
    }

}
