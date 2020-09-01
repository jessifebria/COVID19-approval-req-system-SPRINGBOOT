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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "group_code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupCode.findAll", query = "SELECT g FROM GroupCode g")
    , @NamedQuery(name = "GroupCode.findByDay", query = "SELECT g FROM GroupCode g WHERE g.groupCodePK.day = :day")
    , @NamedQuery(name = "GroupCode.findByStartdate", query = "SELECT g FROM GroupCode g WHERE g.groupCodePK.startdate = :startdate")
    , @NamedQuery(name = "GroupCode.findByIsactive", query = "SELECT g FROM GroupCode g WHERE g.isactive = :isactive")})
public class GroupCode implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Isactive")
    private String isactive;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupCodePK groupCodePK;
    @JoinColumn(name = "Code", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Code code;

    public GroupCode() {
    }

    public GroupCode(GroupCodePK groupCodePK) {
        this.groupCodePK = groupCodePK;
    }

    public GroupCode(GroupCodePK groupCodePK, String isactive) {
        this.groupCodePK = groupCodePK;
        this.isactive = isactive;
    }

    public GroupCode(String day, Date startdate, String code, String isactive) {
        this.groupCodePK = new GroupCodePK(day, startdate);
        this.code = new Code(code);
        this.isactive = isactive;
    }

     public GroupCode(String day, Date startdate) {
        this.groupCodePK = new GroupCodePK(day, startdate);
    }

    public GroupCodePK getGroupCodePK() {
        return groupCodePK;
    }

    public void setGroupCodePK(GroupCodePK groupCodePK) {
        this.groupCodePK = groupCodePK;
    }


    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupCodePK != null ? groupCodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupCode)) {
            return false;
        }
        GroupCode other = (GroupCode) object;
        if ((this.groupCodePK == null && other.groupCodePK != null) || (this.groupCodePK != null && !this.groupCodePK.equals(other.groupCodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.finalmcc.covidsystem.entities.GroupCode[ groupCodePK=" + groupCodePK + " ]";
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
    
}
