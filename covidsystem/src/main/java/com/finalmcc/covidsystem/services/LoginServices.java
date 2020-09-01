/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
public class LoginServices {
    
    @Autowired 
    AccountRepository accountrepo;
    
    public String isvalid(String username, String pass){
        if (accountrepo.existsById(username)) {
            if ((accountrepo.findById(username).get().getPassword()).equalsIgnoreCase(pass)) {
                return accountrepo.findById(username).get().getRole();
            }
            else{
                return "false";
            }
        }
        else{
            return "false";
        }
    }
    
    
}
