/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.Employee;
import com.finalmcc.covidsystem.repositories.EmployeeRepository;
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
public class EmployeeServices {
    
    @Autowired
    EmployeeRepository emprepo;
    
    public boolean existbyid(String id){
        return emprepo.existsById(id);
    }
    
    public Employee getbyid(String id){
        return emprepo.findById(id).get();
    }
    
    public List<Employee> getbydept(String id){
        return emprepo.empdept(id);
    }
    
    public int countbydept(String id){
        return emprepo.countdept(id);
    }
    
    public int countemp(){
        return emprepo.countemp();
    }
    
    
            
}
