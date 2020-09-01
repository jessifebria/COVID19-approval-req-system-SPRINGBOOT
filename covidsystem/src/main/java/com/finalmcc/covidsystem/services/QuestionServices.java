/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.services;

import com.finalmcc.covidsystem.entities.Question;
import com.finalmcc.covidsystem.repositories.EmployeeRepository;
import com.finalmcc.covidsystem.repositories.QuestionRepository;
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
public class QuestionServices {

    @Autowired
    QuestionRepository questionrepo;

    @Autowired
    EmployeeRepository emprepo;

    public List<Question> getactive() {
        return questionrepo.findactive();
    }

    public List<Question> getnonactive() {
        return questionrepo.findnonactive();
    }

    public void ubahkenonactive(String user, String kode) {
        questionrepo.ubahkenonaktif(user, Integer.parseInt(kode));
    }

    public void ubahkeactive(String user, String id) {
        questionrepo.ubahkeaktif(user, Integer.parseInt(id));
    }

    public void save(Question question, String id) {
        Question que = new Question(question.getQuestion(), question.getSection(), question.getWeight(), "True", emprepo.findById(id).get());
        questionrepo.save(que);
    }

    public List<Question> getcovidactive() {
        return questionrepo.findcovidactive();
    }

    public List<Question> geturgencyactive() {
        return questionrepo.findurgencyactive();
    }
}
