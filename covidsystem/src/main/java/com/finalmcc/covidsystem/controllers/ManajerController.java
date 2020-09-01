/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.controllers;

import com.finalmcc.covidsystem.entities.Question;
import com.finalmcc.covidsystem.entities.RequestQuota;
import com.finalmcc.covidsystem.entities.TempAnswer;
import com.finalmcc.covidsystem.entities.TempReq;
import com.finalmcc.covidsystem.entities.Entry;
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
import java.util.List;
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
public class ManajerController {

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

    //HOME
    @RequestMapping("/manajer/{user}")
    public String tohomepage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        if (empservices.getbyid(user).getStatus().equalsIgnoreCase("No Data")) {
            model.addAttribute("nodata", true);
            return "manajer";
        }
        if (entryservices.checkiftomorrowvalid(user)) {
            if (entryservices.ifemppending(user)) {
                if (entryservices.getbyemp(user).getStatus().equalsIgnoreCase("Accept")) {
                    model.addAttribute("accept", true);
                }
                else if (entryservices.getbyemp(user).getStatus().equalsIgnoreCase("Pending")) {
                    model.addAttribute("pending", true);
                }
                else if (entryservices.getbyemp(user).getStatus().equalsIgnoreCase("Reject")) {
                    model.addAttribute("reject", true);
                }
            } else {
                model.addAttribute("besokvalid", true);
            }
        } else {
            model.addAttribute("besokinvalid", true);
        }

        return "manajer";
    }

    // ANSWER
    int i = -1;
    int hasilcovid = 0;
    int covidfinish = 0;

    @RequestMapping("/manajer/answer/{user}")
    public String toanswerpage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        if (empservices.getbyid(user).getStatus().equalsIgnoreCase("No Data")) {
            model.addAttribute("nodata", true);
            return "manajerpertanyaan";
        }
        if (entryservices.checkiftomorrowvalid(user)) {
            if (entryservices.ifemppending(user)) {
                model.addAttribute("hasilcovid", entryservices.getbyemp(user).getCovidweight());
                if (entryservices.getbyemp(user).getCovidweight() > 3) {
                    entryservices.reject(user);
                    model.addAttribute("tidaklolos", true);
                } else {
                    if (empservices.getbyid(user).getDepartment().getCanonline().equalsIgnoreCase("Yes")) {
                        if (entryservices.getbyemp(user).getUrgencyweight() == null) {
                            model.addAttribute("jawaburgency", true);
                        } else {
                            if (entryservices.getbyemp(user).getEmployeenote() == null) {
                                model.addAttribute("note", true);
                                model.addAttribute("answer", new TempAnswer());

                            } else {
                                model.addAttribute("realfinish", true);
                            }
                        }
                    } else {
                        if (entryservices.getbyemp(user).getEmployeenote() == null) {
                            model.addAttribute("note", true);
                            model.addAttribute("answer", new TempAnswer());

                        } else {
                            model.addAttribute("realfinish", true);
                        }
                    }
                }
            } else {
                if (entryservices.checkifafter5pm()) {
                    model.addAttribute("valid", true);
                } else {
                    model.addAttribute("belumjam5", true);
                }
            }
        } else {
            model.addAttribute("invalid", true);
        }
        return "manajerpertanyaan";
    }

    @RequestMapping("/manajer/answercovid/{user}")
    public String jawabpertanyaan(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("covid", true);
        List<Question> question = new ArrayList<Question>(questionservices.getcovidactive());
        i += 1;
        model.addAttribute("que", question.get(i));
        model.addAttribute("answer", new TempAnswer());
        if (i == question.size() - 1) {
            model.addAttribute("finish", true);
            covidfinish = 1;
        } else {
            model.addAttribute("next", true);
        }
        return "manajerjawab";
    }

    @RequestMapping("/manajer/saveanswercovid/{user}")
    public String submit(@PathVariable(name = "user") String user, TempAnswer temp, Model model) throws ParseException {
        List<Question> question = new ArrayList<Question>(questionservices.getcovidactive());
        if (temp.getJawab().equalsIgnoreCase("yes")) {
            hasilcovid += question.get(i).getWeight();
        }
        if (covidfinish == 1) {
            Entry ent = new Entry(dateservices.getdatetimaafter5pmtosql(), hasilcovid, "Pending", dateservices.gettomorrowdaytosql(), empservices.getbyid(user));
            entryservices.saveentrycovid(ent);
            covidfinish = 0;
            i = -1;
            hasilcovid = 0;
            return "redirect:/manajer/answer/" + user;
        } else {
            return "redirect:/manajer/answercovid/" + user;
        }
    }

    int z = -1;
    int hasilurgency = 0;
    int urgencyfinish = 0;

    @RequestMapping("/manajer/answerurgency/{user}")
    public String jawabpertanyaanurgency(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("urgency", true);
        List<Question> question = new ArrayList<Question>(questionservices.geturgencyactive());
        z += 1;
        System.out.println(z + " , " + question.size());
        model.addAttribute("que", question.get(z));
        model.addAttribute("answer", new TempAnswer());
        if (z == question.size() - 1) {
            model.addAttribute("finish", true);
            urgencyfinish = 1;
        } else {
            model.addAttribute("next", true);
        }
        return "manajerjawab";
    }

    @RequestMapping("/manajer/saveanswerurgency/{user}")
    public String submiturgency(@PathVariable(name = "user") String user, TempAnswer temp, Model model) throws ParseException {
        List<Question> question = new ArrayList<Question>(questionservices.geturgencyactive());
        if (temp.getJawab().equalsIgnoreCase("yes")) {
            hasilurgency += question.get(z).getWeight();
        }
        if (urgencyfinish == 1) {
            entryservices.updateurgency(user, String.valueOf(hasilurgency));
            urgencyfinish = 0;
            z = -1;
            hasilurgency = 0;
            return "redirect:/manajer/answer/" + user;
        } else {
            return "redirect:/manajer/answerurgency/" + user;
        }
    }

    @RequestMapping("/manajer/savenote/{user}")
    public String submitnote(@PathVariable(name = "user") String user, TempAnswer temp, Model model) throws ParseException {
        entryservices.updatenote(user, temp.getJawab());
        return "redirect:/manajer/answer/" + user;
    }

    //HISTORY
    @RequestMapping("/manajer/history/{user}")
    public String tohistorypage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        model.addAttribute("active", entryservices.findacc(user));
        model.addAttribute("reject", entryservices.findrej(user));
        return "manajerriwayat";
    }

    int a = 0;
    int b = 0;
    int c = 0;

    //DEPARTMENT
    @RequestMapping("/manajer/department/{user}")
    public String todepartmentpage(@PathVariable(name = "user") String user, Model model) throws ParseException {
        model.addAttribute("date", dateservices.datenow2());
        model.addAttribute("user", empservices.getbyid(user));
        String iddept = empservices.getbyid(user).getDepartment().getId();
        model.addAttribute("dept", deptservices.getbyid(iddept));
        model.addAttribute("emp", empservices.getbydept(iddept));
        model.addAttribute("entry", entryservices.entrybydept(iddept));
        model.addAttribute("count", empservices.countbydept(iddept));
        model.addAttribute("his", reqservices.getbydept(user));
        model.addAttribute("request", new TempReq());
        if (a == 1) {
            model.addAttribute("tipedatasalah", true);
            a = 0;
        } else if (b == 1) {
            model.addAttribute("lebih", true);
            b = 0;
        } else if (c == 1) {
            model.addAttribute("sukses", true);
            c = 0;
        }
        return "manajerdepartment";
    }

    @PostMapping("/manajer/saverequest/{user}")
    public String saverequest(@PathVariable(name = "user") String user, TempReq temp, Model model) {
        try {
            Integer.parseInt(temp.getQuota());
        } catch (NumberFormatException e) {
            a = 1;
            return "redirect:/manajer/department/" + user;
        }

        if (reqservices.countbydept(user) > 0) {
            b = 1;
            return "redirect:/manajer/department/" + user;
        }
        c = 1;
        reqservices.savereq(Integer.parseInt(temp.getQuota()), temp.getNote(), user);

        return "redirect:/manajer/department/" + user;
    }

}
