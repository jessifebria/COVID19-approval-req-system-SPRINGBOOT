/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.Department;
import com.finalmcc.covidsystem.repositories.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
public class DepartmentServices {
    
    @Autowired
    DepartmentRepository deptrepo;
    
    public Department getbyid(String id){
        return deptrepo.findById(id).get();
    }
    
    public void ubahquota(String id, Integer quota){
        deptrepo.findById(id).get().setQuota(quota);
    }
    
    public List<Department> getbygroup(String code){
        return deptrepo.getgroupcode(code);
    }
    
}
