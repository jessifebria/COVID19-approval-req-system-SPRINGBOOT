/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.RequestQuota;
import com.finalmcc.covidsystem.repositories.DepartmentRepository;
import com.finalmcc.covidsystem.repositories.RequestQuotaRepository;
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
public class RequestQuotaServices {

    @Autowired
    RequestQuotaRepository reqrepo;
    
    @Autowired
    DepartmentRepository deptrepo;
    
    @Autowired
    EmployeeServices empservices;

    public List<RequestQuota> getacc() {
        return reqrepo.findacc();
    }

    public List<RequestQuota> getreject() {
        return reqrepo.findreject();
    }

    public List<RequestQuota> getpending() {
        return reqrepo.findpending();
    }

     public List<RequestQuota> getbydept(String manajer) {
        return reqrepo.findbydept(manajer);
    }
    
    public void acc(String user, String text,Integer kode){
        reqrepo.acc(user, text, kode);
        deptrepo.ubahquota(reqrepo.findById(kode).get().getQuota(), reqrepo.findById(kode).get().getManajer().getId());
    }
    
    public void rej(String user, String text,Integer kode){
        reqrepo.rej(user, text, kode);
    }
    
    public void savereq(Integer quota, String note, String emp ){
        reqrepo.savereq(quota, note, emp);
    }
    
    public int countbydept(String manajer){
        return reqrepo.countactive(manajer);
    }
    
}
