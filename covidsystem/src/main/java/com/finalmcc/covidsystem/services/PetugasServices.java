/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.RapidOfficer;
import com.finalmcc.covidsystem.entities.RapidResult;
import com.finalmcc.covidsystem.repositories.EmployeeRepository;
import com.finalmcc.covidsystem.repositories.RapidOfficerRepository;
import com.finalmcc.covidsystem.repositories.RapidResultRepository;
import java.text.ParseException;
import java.util.Collection;
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
public class PetugasServices {

    @Autowired
    RapidOfficerRepository ROrepo;

    @Autowired
    RapidResultRepository RRrepo;

    @Autowired
    EmployeeRepository EMPrepo;

    @Autowired
    DateServices dateservices;

    @Autowired
    RapidResultServices rrservice;

     public List<RapidResult> getactive() {
        return rrservice.getactive();
    }

    public List<RapidResult> getexpired() {
        return rrservice.getexpired();
    }

    public List<RapidResult> getraktif() {
        return rrservice.getraktif();
    }
    
    public RapidOfficer getbyid(String id) {
        return ROrepo.findById(id).get();
    }

    public void ubahkepositif(String id, Integer kode) {
        RRrepo.findById(kode).get().setResult("Positif");
        RRrepo.findById(kode).get().setRapidofficer(ROrepo.findById(id).get());
    }

    public void ubahkenegatif(String id, Integer kode) {
        RRrepo.findById(kode).get().setResult("Negatif");
        RRrepo.findById(kode).get().setRapidofficer(ROrepo.findById(id).get());
    }

    public void ubahkefalse() {
        RRrepo.changetofalse();
    }

    public void saverapid(RapidResult rapidr, String user) throws ParseException {
        RapidResult rapid = new RapidResult(dateservices.datetosql(), rapidr.getResult(), ROrepo.findById(user).get(), rapidr.getEmployee(), "True");
        RRrepo.save(rapid);
    }

    public boolean checkvalid(String emp) {
        if (RRrepo.countemptrue(emp) == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    

}
