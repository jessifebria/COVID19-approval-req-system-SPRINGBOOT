/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 * @author JESSI
 */
@Entity
@Table(name = "department")
@EntityScan(basePackages = {"com.finalmcc.covidsystem.entities"})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
    , @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id")
    , @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name")
    , @NamedQuery(name = "Department.findByQuota", query = "SELECT d FROM Department d WHERE d.quota = :quota")
    , @NamedQuery(name = "Department.findByCanonline", query = "SELECT d FROM Department d WHERE d.canonline = :canonline")
    , @NamedQuery(name = "Department.findByCode", query = "SELECT d FROM Department d WHERE d.code = :code")})
public class Department implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quota")
    private int quota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Canonline")
    private String canonline;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Code")
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manajer", fetch = FetchType.LAZY)
    private Collection<RequestQuota> requestQuotaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "Id")
    private String id;
    @JoinColumn(name = "Manajer", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee manajer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.LAZY)
    private Collection<Employee> employeeCollection;

    public Department() {
    }

    public Department(String id) {
        this.id = id;
    }

    public Department(String id, String name, int quota, String canonline, String code) {
        this.id = id;
        this.name = name;
        this.quota = quota;
        this.canonline = canonline;
        this.code = code;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Employee getManajer() {
        return manajer;
    }

    public void setManajer(Employee manajer) {
        this.manajer = manajer;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.finalmcc.covidsystem.entities.Department[ id=" + id + " ]";
    }
    @XmlTransient
    public Collection<RequestQuota> getRequestQuotaCollection() {
        return requestQuotaCollection;
    }
    public void setRequestQuotaCollection(Collection<RequestQuota> requestQuotaCollection) {
        this.requestQuotaCollection = requestQuotaCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getCanonline() {
        return canonline;
    }

    public void setCanonline(String canonline) {
        this.canonline = canonline;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
