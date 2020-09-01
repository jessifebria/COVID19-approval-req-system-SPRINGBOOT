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

/**
 *
 * @author JESSI
 */
@Entity
@Table(name = "entry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entry.findAll", query = "SELECT e FROM Entry e")
    , @NamedQuery(name = "Entry.findById", query = "SELECT e FROM Entry e WHERE e.id = :id")
    , @NamedQuery(name = "Entry.findByDatetime", query = "SELECT e FROM Entry e WHERE e.datetime = :datetime")
    , @NamedQuery(name = "Entry.findByCovidweight", query = "SELECT e FROM Entry e WHERE e.covidweight = :covidweight")
    , @NamedQuery(name = "Entry.findByUrgencyweight", query = "SELECT e FROM Entry e WHERE e.urgencyweight = :urgencyweight")
    , @NamedQuery(name = "Entry.findByEmployeenote", query = "SELECT e FROM Entry e WHERE e.employeenote = :employeenote")
    , @NamedQuery(name = "Entry.findByStatus", query = "SELECT e FROM Entry e WHERE e.status = :status")
    , @NamedQuery(name = "Entry.findByTomorrowdate", query = "SELECT e FROM Entry e WHERE e.tomorrowdate = :tomorrowdate")})
public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Covidweight")
    private int covidweight;
    @Column(name = "Urgencyweight")
    private Integer urgencyweight;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "Employeenote")
    private String employeenote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tomorrowdate")
    @Temporal(TemporalType.DATE)
    private Date tomorrowdate;
    @JoinColumn(name = "Employee", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    public Entry() {
    }

    public Entry(Integer id) {
        this.id = id;
    }

    public Entry(Integer id, Date datetime, int covidweight, String employeenote, String status, Date tomorrowdate) {
        this.id = id;
        this.datetime = datetime;
        this.covidweight = covidweight;
        this.employeenote = employeenote;
        this.status = status;
        this.tomorrowdate = tomorrowdate;
    }

    
    public Entry(Date datetime, int covidweight, String status, Date tomorrowdate, Employee emp) {
        this.datetime = datetime;
        this.covidweight = covidweight;
        this.status = status;
        this.employee=emp;
        this.tomorrowdate = tomorrowdate;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getCovidweight() {
        return covidweight;
    }

    public void setCovidweight(int covidweight) {
        this.covidweight = covidweight;
    }

    public Integer getUrgencyweight() {
        return urgencyweight;
    }

    public void setUrgencyweight(Integer urgencyweight) {
        this.urgencyweight = urgencyweight;
    }

    public String getEmployeenote() {
        return employeenote;
    }

    public void setEmployeenote(String employeenote) {
        this.employeenote = employeenote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTomorrowdate() {
        return tomorrowdate;
    }

    public void setTomorrowdate(Date tomorrowdate) {
        this.tomorrowdate = tomorrowdate;
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
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry other = (Entry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.finalmcc.covidsystem.entities.Entry[ id=" + id + " ]";
    }
    
}
