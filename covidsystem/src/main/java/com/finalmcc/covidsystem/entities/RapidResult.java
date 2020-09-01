/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 * @author JESSI
 */
@Entity
@Table(name = "rapid_result")

@EntityScan(basePackages = {"com.finalmcc.covidsystem.entities"})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RapidResult.findAll", query = "SELECT r FROM RapidResult r")
    , @NamedQuery(name = "RapidResult.findById", query = "SELECT r FROM RapidResult r WHERE r.id = :id")
    , @NamedQuery(name = "RapidResult.findByDate", query = "SELECT r FROM RapidResult r WHERE r.date = :date")
    , @NamedQuery(name = "RapidResult.findByResult", query = "SELECT r FROM RapidResult r WHERE r.result = :result")
    , @NamedQuery(name = "RapidResult.findByIsactive", query = "SELECT r FROM RapidResult r WHERE r.isactive = :isactive")})
public class RapidResult implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Result")
    private String result;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Isactive")
    private String isactive;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "Rapidofficer", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RapidOfficer rapidofficer;
    @JoinColumn(name = "Employee", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    public RapidResult() {
    }

    public RapidResult(Date date, String result, RapidOfficer ro, Employee emp, String isactive) {
        this.date = date;
        this.result = result;
        this.rapidofficer=ro;
        this.employee=emp;
        this.isactive = isactive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public RapidOfficer getRapidofficer() {
        return rapidofficer;
    }

    public void setRapidofficer(RapidOfficer rapidofficer) {
        this.rapidofficer = rapidofficer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RapidResult)) {
            return false;
        }
        RapidResult other = (RapidResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.finalmcc.covidsystem.entities.RapidResult[ id=" + id + " ]";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
    
}
