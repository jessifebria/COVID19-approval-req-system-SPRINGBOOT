/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.controllers;

import com.finalmcc.covidsystem.entities.RapidResult;
import com.finalmcc.covidsystem.services.DateServices;
import com.finalmcc.covidsystem.services.EmployeeServices;
import com.finalmcc.covidsystem.services.PetugasServices;
import com.finalmcc.covidsystem.services.RapidResultServices;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JESSI
 */
@Controller
public class PetugasController {

    @Autowired
    DateServices dateservices;

    @Autowired
    PetugasServices petugasservices;

    @Autowired
    EmployeeServices empservices;

    @Autowired 
    RapidResultServices rrservices;
    
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    @RequestMapping("/petugas/{user}")
    public String awal(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow());
        model.addAttribute("user", petugasservices.getbyid(user));
        model.addAttribute("rapid", new RapidResult());
        model.addAttribute("countrapid", rrservices.rapidtrue() );
        model.addAttribute("countemp", empservices.countemp());
        if (a == 1) {
            model.addAttribute("idnotfound", true);
            a = 0;
        }
        if (b == 1) {
            model.addAttribute("datasudahada", true);
            b = 0;
        }
        if (c == 1) {
            model.addAttribute("sukses", true);
            c = 0;
        }
        if (d == 1) {
            model.addAttribute("tidaklengkap", true);
            d = 0;
        }
        return "petugas";
    }

    @RequestMapping("/petugas/alldata/{user}")
    public ModelAndView ksm(@PathVariable(name = "user") String user) throws ParseException {
        ModelAndView mav = new ModelAndView("petugastable");
        mav.addObject("date", dateservices.datenow());
        mav.addObject("user", petugasservices.getbyid(user));
        mav.addObject("active", petugasservices.getactive());
        mav.addObject("expired", petugasservices.getexpired());
        return mav;
    }

    @RequestMapping("/petugas/pantau/{user}")
    public String topantau(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow());
        model.addAttribute("user", petugasservices.getbyid(user));
        model.addAttribute("reaktif", petugasservices.getraktif());
        return "petugaspantau";
    }

    @GetMapping("/petugas/ubahpositif/{user}/{kode}")
    public String ubahpositif(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode) {
        petugasservices.ubahkepositif(user, Integer.parseInt(kode));
        return "redirect:/petugas/pantau/" + user;
    }

    @GetMapping("/petugas/ubahnegatif/{user}/{kode}")
    public String ubahnegatif(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode) {
        petugasservices.ubahkenegatif(user, Integer.parseInt(kode));
        return "redirect:/petugas/pantau/" + user;
    }

    @GetMapping("/petugas/ubahfalse/{user}")
    public String ubahfalse(@PathVariable(name = "user") String user) {
        petugasservices.ubahkefalse();
        return "redirect:/petugas/alldata/" + user;
    }

    @PostMapping("/petugas/saverapid/{user}")
    public String saverapid(@PathVariable(name = "user") String user, @ModelAttribute RapidResult rapid, Model model) throws ParseException {
        try {
            empservices.existbyid(rapid.getEmployee().getId());
        } catch (Exception e) {
            a = 1;
            return "redirect:/petugas/" + user;
        }

        if ((rapid.getResult() == null) | (rapid.getEmployee().getId() == null)) {
            d = 1;
            return "redirect:/petugas/" + user;
        }

        if (petugasservices.checkvalid(rapid.getEmployee().getId())) {
            petugasservices.saverapid(rapid, user);
            c = 1;
            return "redirect:/petugas/" + user;
        } else {
            b = 1;
            return "redirect:/petugas/" + user;
        }
    }

}
