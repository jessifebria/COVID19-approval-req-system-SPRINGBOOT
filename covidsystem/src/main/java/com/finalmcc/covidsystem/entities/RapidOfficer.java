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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "rapid_officer")
@EntityScan(basePackages = {"com.finalmcc.covidsystem.entities"})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RapidOfficer.findAll", query = "SELECT r FROM RapidOfficer r")
    , @NamedQuery(name = "RapidOfficer.findById", query = "SELECT r FROM RapidOfficer r WHERE r.id = :id")
    , @NamedQuery(name = "RapidOfficer.findByName", query = "SELECT r FROM RapidOfficer r WHERE r.name = :name")})
public class RapidOfficer implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "Id")
    private String id;
    @JoinColumn(name = "Id", referencedColumnName = "Username", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Account account;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rapidofficer", fetch = FetchType.LAZY)
    private Collection<RapidResult> rapidResultCollection;

    public RapidOfficer() {
    }

    public RapidOfficer(String id) {
        this.id = id;
    }

    public RapidOfficer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @XmlTransient
    public Collection<RapidResult> getRapidResultCollection() {
        return rapidResultCollection;
    }

    public void setRapidResultCollection(Collection<RapidResult> rapidResultCollection) {
        this.rapidResultCollection = rapidResultCollection;
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
        if (!(object instanceof RapidOfficer)) {
            return false;
        }
        RapidOfficer other = (RapidOfficer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.finalmcc.covidsystem.entities.RapidOfficer[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
