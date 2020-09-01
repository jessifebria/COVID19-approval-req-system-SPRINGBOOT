/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.RapidResult;
import com.finalmcc.covidsystem.repositories.RapidResultRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
public class RapidResultServices {
     @Autowired
    RapidResultRepository RRrepo;
     
     
    public List<RapidResult> getactive() {
        return RRrepo.findactive();
    }

    public List<RapidResult> getexpired() {
        return RRrepo.findexpired();
    }

    public List<RapidResult> getraktif() {
        return RRrepo.findreaktif();
    }

     public List<RapidResult> getpositif() {
        return RRrepo.findpositif();
    }
     
     public List<RapidResult> hispositif() {
        return RRrepo.hispositif();
    }

     public int rapidtrue(){
         return RRrepo.countrapidtrue();
     }
}
