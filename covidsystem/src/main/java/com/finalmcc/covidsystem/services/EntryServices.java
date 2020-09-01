/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.Department;
import com.finalmcc.covidsystem.entities.Entry;
import com.finalmcc.covidsystem.repositories.DepartmentRepository;
import com.finalmcc.covidsystem.repositories.EmployeeRepository;
import com.finalmcc.covidsystem.repositories.EntryRepository;
import com.finalmcc.covidsystem.repositories.GroupCodeRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class EntryServices {

    @Autowired
    EntryRepository entryrepo;

    @Autowired
    DepartmentRepository deptrepo;

    @Autowired
    EmployeeRepository emprepo;

    @Autowired
    DateServices dateservices;

    @Autowired
    GroupCodeRepository gcrepo;

    public List<Entry> entrybydept(String dept) {
        return entryrepo.entrybydept(dept);
    }

    public List<Entry> entrybyemp(String emp) {
        return (List<Entry>) emprepo.findById(emp).get().getEntryCollection();
    }

    public List<Entry> findacc(String emp) {
        return entryrepo.findacc(emp);
    }

    public List<Entry> findrej(String emp) {
        return entryrepo.findrej(emp);
    }

    public boolean checkiftomorrowvalid(String emp) throws ParseException {
        String code = gcrepo.getdaygroup(dateservices.gettomorrowday());
        if (emprepo.findById(emp).get().getCode().getId().equalsIgnoreCase(code)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkifafter5pm() throws ParseException {
        Date date = dateservices.getdatetimaafter5pmtosql();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strdate = dateFormat.format(date);
        if (Integer.parseInt(strdate.substring(11, 13)) >= 17) {
            return true;
        } else {
            return false;
        }

    }
    
    public void saveentrycovid(Entry entry){
        entryrepo.save(entry);
    }
    
    public boolean ifemppending(String emp) throws ParseException{
        if (entryrepo.countemppending(dateservices.gettomorrowdate(),emp)==0) {
            return false;
        }
        else{
            return true;
        }
    }
    
    public Entry getbyemp(String emp) throws ParseException{
        return entryrepo.findbyemp(dateservices.gettomorrowdate(), emp);
    }
    
    public void updateurgency(String emp, String hasil) throws ParseException{
        entryrepo.findbyemp(dateservices.gettomorrowdate(), emp).setUrgencyweight(Integer.parseInt(hasil));
    }
    
    public void updatenote(String emp, String note) throws ParseException{
        entryrepo.findbyemp(dateservices.gettomorrowdate(), emp).setEmployeenote(note);
    }
    
    public void reject(String emp) throws ParseException{
        entryrepo.findbyemp(dateservices.gettomorrowdate(), emp).setStatus("Reject");
    }
    
    public int countaccempbydept(String dept) throws ParseException{
        return entryrepo.countaccempbydept(dept, dateservices.gettomorrowdate());
    }
    
    public List<Entry> findpending() throws ParseException{
        return entryrepo.findpending( dateservices.gettomorrowdate());
    }
    
     public List<Entry> findacctoday() throws ParseException{
        return entryrepo.findacctoday(dateservices.gettomorrowdate());
    }
     
      public List<Entry> findrejtoday() throws ParseException{
        return entryrepo.findrejecttiday(dateservices.gettomorrowdate());
    }
    
    public void rejectbyid(String id){
        entryrepo.findById(Integer.parseInt(id)).get().setStatus("Reject");
    }
    public void acceptbyid(String id){
        entryrepo.findById(Integer.parseInt(id)).get().setStatus("Accept");
    }
    
    
}
