/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JESSI
 */
@Entity
@Table(name = "request_quota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestQuota.findAll", query = "SELECT r FROM RequestQuota r")
    , @NamedQuery(name = "RequestQuota.findById", query = "SELECT r FROM RequestQuota r WHERE r.id = :id")
    , @NamedQuery(name = "RequestQuota.findByQuota", query = "SELECT r FROM RequestQuota r WHERE r.quota = :quota")
    , @NamedQuery(name = "RequestQuota.findByManajernote", query = "SELECT r FROM RequestQuota r WHERE r.manajernote = :manajernote")
    , @NamedQuery(name = "RequestQuota.findByPicnote", query = "SELECT r FROM RequestQuota r WHERE r.picnote = :picnote")
    , @NamedQuery(name = "RequestQuota.findByStatus", query = "SELECT r FROM RequestQuota r WHERE r.status = :status")})
public class RequestQuota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quota")
    private int quota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Manajernote")
    private String manajernote;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "Picnote")
    private String picnote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Status")
    private String status;
    @JoinColumn(name = "Pic", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee pic;
    @JoinColumn(name = "Manajer", referencedColumnName = "Manajer")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department manajer;

    public RequestQuota() {
    }

    public RequestQuota(Integer id) {
        this.id = id;
    }

    public RequestQuota(Integer id, int quota, String manajernote, String picnote, String status) {
        this.id = id;
        this.quota = quota;
        this.manajernote = manajernote;
        this.picnote = picnote;
        this.status = status;
    }

    public RequestQuota(Department manajer, int quota, String manajernote, String status) {
        this.quota = quota;
        this.manajernote = manajernote;
        this.manajer = manajer;
        this.pic = new Employee("E0001");
        this.status = status;
    }

    public RequestQuota(int quota, String manajernote) {
        this.quota = quota;
        this.manajernote = manajernote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getManajernote() {
        return manajernote;
    }

    public void setManajernote(String manajernote) {
        this.manajernote = manajernote;
    }

    public String getPicnote() {
        return picnote;
    }

    public void setPicnote(String picnote) {
        this.picnote = picnote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getPic() {
        return pic;
    }

    public void setPic(Employee pic) {
        this.pic = pic;
    }

    public Department getManajer() {
        return manajer;
    }

    public void setManajer(Department manajer) {
        this.manajer = manajer;
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
        if (!(object instanceof RequestQuota)) {
            return false;
        }
        RequestQuota other = (RequestQuota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.finalmcc.covidsystem.entities.RequestQuota[ id=" + id + " ]";
    }

}
