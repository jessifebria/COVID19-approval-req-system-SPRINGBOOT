/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.controllers;

import com.finalmcc.covidsystem.entities.Account;
import com.finalmcc.covidsystem.services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author JESSI
 */
@Controller
public class LoginController {
    
    @Autowired
    LoginServices loginservices;
    
    @GetMapping("")
    public String index(){
        return "index";
    }
    
    @GetMapping("/index")
    public String index2(){
        return "index";
    }
    
    @GetMapping("/login")
    public String gologin(Model model){
        model.addAttribute("account", new Account());
        return "login";
    }
    
    @RequestMapping("/check")
    public String check(@ModelAttribute Account account, Model model){
        String user = account.getUsername();
        String pass = account.getPassword();
        String role= loginservices.isvalid(user,pass);
        if (role.equalsIgnoreCase("RO")) {
           return "redirect:/petugas/"+user;
        }
        else if(role.equalsIgnoreCase("admin")){
            return "redirect:/admin/"+ user;
        }
        else if (role.equalsIgnoreCase("KD")) {
           return "redirect:/manajer/"+user;
        }
        else if (role.equalsIgnoreCase("EMP")) {
           return "redirect:/employee/" +user;
        }
        else{
            model.addAttribute("error", true);
            return "login";
        }
    }
    
    
    
}
