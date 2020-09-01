/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.controllers;

import com.finalmcc.covidsystem.entities.Department;
import com.finalmcc.covidsystem.entities.Question;
import com.finalmcc.covidsystem.entities.TempGroup;
import com.finalmcc.covidsystem.services.DateServices;
import com.finalmcc.covidsystem.services.DepartmentServices;
import com.finalmcc.covidsystem.services.EmployeeServices;
import com.finalmcc.covidsystem.services.EntryServices;
import com.finalmcc.covidsystem.services.GroupCodeServices;
import com.finalmcc.covidsystem.services.QuestionServices;
import com.finalmcc.covidsystem.services.RapidResultServices;
import com.finalmcc.covidsystem.services.RequestQuotaServices;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author JESSI
 */
@Controller
public class AdminController {

    @Autowired
    DateServices dateservices;

    @Autowired
    EmployeeServices empservices;

    @Autowired
    RapidResultServices rrservices;

    @Autowired
    DepartmentServices deptservices;

    @Autowired
    EntryServices entryservices;

    @Autowired
    QuestionServices questionservices;

    @Autowired
    GroupCodeServices groupservices;

    @Autowired
    RequestQuotaServices reqservices;

    @RequestMapping("/admin/{user}")
    public String tohomepage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        String group = groupservices.gettomorrowgroup();
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("group", group);
        List<Department> dept = new ArrayList<Department>(deptservices.getbygroup(group));
        model.addAttribute("dept1", dept.get(0));
        model.addAttribute("emp1", entryservices.countaccempbydept(dept.get(0).getId()));
        model.addAttribute("dept2", dept.get(1));
        model.addAttribute("emp2", entryservices.countaccempbydept(dept.get(1).getId()));
        model.addAttribute("dept3", dept.get(2));
        model.addAttribute("emp3", entryservices.countaccempbydept(dept.get(2).getId()));
        model.addAttribute("listentry", entryservices.findpending());
        model.addAttribute("acctoday", entryservices.findacctoday());
        model.addAttribute("rejecttoday", entryservices.findrejtoday());
        return "admin";
    }
    
    @GetMapping("/admin/rejectentry/{user}/{kode}")
    public String rejectentry(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode) {
        entryservices.rejectbyid(kode);
        return "redirect:/admin/" + user;
    }

    @GetMapping("/admin/acceptentry/{user}/{kode}")
    public String accentry(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode) {
        entryservices.acceptbyid(kode);
        return "redirect:/admin/" + user;
    }
    

    //INFO KARYAWAN POSITIF 
    @RequestMapping("/admin/info/{user}")
    public String toinfo(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("positif", rrservices.getpositif());
        model.addAttribute("his", rrservices.hispositif());
        return "admininfo";
    }

    //DEPARTMENT
    @RequestMapping("/admin/department/{user}")
    public String todepartment(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        return "admindepartment";
    }

    @RequestMapping("/admin/detaildepartment/{user}/{iddept}")
    public String todetaildepartment(@PathVariable(name = "user") String user, @PathVariable(name = "iddept") String iddept, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("dept", deptservices.getbyid(iddept));
        model.addAttribute("emp", empservices.getbydept(iddept));
        model.addAttribute("entry", entryservices.entrybydept(iddept));
        return "admindetaildepartment";
    }

    //PERTANYAAN
    @RequestMapping("/admin/pertanyaan/{user}")
    public String topertanyaan(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("active", questionservices.getactive());
        model.addAttribute("nonactive", questionservices.getnonactive());
        model.addAttribute("question", new Question());
        return "adminpertanyaan";
    }

    @GetMapping("/admin/tononactive/{user}/{kode}")
    public String ubahnonactive(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode) {
        questionservices.ubahkenonactive(user, kode);
        return "redirect:/admin/pertanyaan/" + user;
    }

    @GetMapping("/admin/toactive/{user}/{kode}")
    public String ubahactive(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode) {
        questionservices.ubahkeactive(user, kode);
        return "redirect:/admin/pertanyaan/" + user;
    }

    @PostMapping("admin/savequestion/{user}")
    public String savequestion(@PathVariable(name = "user") String user, Question question) {
        questionservices.save(question, user);
        return "redirect:/admin/pertanyaan/" + user;
    }

    //GROUP
    int a = 0;
    int b = 0;
    int c = 0;

    @RequestMapping("/admin/group/{user}")
    public String togroup(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("soondate", groupservices.soondate());
        model.addAttribute("expdate", groupservices.expdate());
        model.addAttribute("counta", String.valueOf(groupservices.count("a")));
        model.addAttribute("countb", String.valueOf(groupservices.count("b")));
        model.addAttribute("hist", groupservices.getall());
        model.addAttribute("group", new TempGroup());
        if (a == 1) {
            model.addAttribute("tidaklengkap", true);
            a = 0;
        } else if (b == 1) {
            model.addAttribute("belumsaatnya", true);
            b = 0;
        } else if (c == 1) {
            model.addAttribute("sukses", true);
            c = 0;
        }
        return "admingroup";
    }

    @PostMapping("admin/group/save/{user}")
    public String savegroup(@PathVariable(name = "user") String user, TempGroup group) throws ParseException {
        if (groupservices.soondatenowtosql().compareTo(dateservices.datetosql2()) != 0) {
            b = 1;
        } else if ((group.getDay1() == null) || (group.getDay2() == null) || (group.getDay3() == null) || (group.getDay4() == null) || (group.getDay5() == null)) {
            a = 1;
        } else {
            c = 1;
            Date now = groupservices.datenow();
            groupservices.savegroup(groupservices.soondatetosql(), group.getDay1(), group.getDay2(), group.getDay3(), group.getDay4(), group.getDay5());
            groupservices.setfalse(now);
        }
        return "redirect:/admin/group/" + user;
    }

    //REQUEST KUOTA
    @RequestMapping("/admin/request/{user}")
    public String torequest(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("his", reqservices.getacc());
        model.addAttribute("hisreject", reqservices.getreject());
        model.addAttribute("hispending", reqservices.getpending());
        return "adminrequest";
    }

    @GetMapping("/admin/accept/{user}/{kode}/{text}")
    public String accreq(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode, @PathVariable(name = "text") String text) {
        reqservices.acc(user, text, Integer.parseInt(kode));
        return "redirect:/admin/request/" + user;
    }

    @GetMapping("/admin/reject/{user}/{kode}/{text}")
    public String rejreq(@PathVariable(name = "user") String user, @PathVariable(name = "kode") String kode, @PathVariable(name = "text") String text) {
        reqservices.rej(user, text, Integer.parseInt(kode));
        return "redirect:/admin/request/" + user;
    }
}
