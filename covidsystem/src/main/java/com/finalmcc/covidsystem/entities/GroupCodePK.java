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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JESSI
 */
@Embeddable
public class GroupCodePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Day")
    private String day;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;

    public GroupCodePK() {
    }

    public GroupCodePK(String day, Date startdate) {
        this.day = day;
        this.startdate = startdate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (day != null ? day.hashCode() : 0);
        hash += (startdate != null ? startdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupCodePK)) {
            return false;
        }
        GroupCodePK other = (GroupCodePK) object;
        if ((this.day == null && other.day != null) || (this.day != null && !this.day.equals(other.day))) {
            return false;
        }
        if ((this.startdate == null && other.startdate != null) || (this.startdate != null && !this.startdate.equals(other.startdate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.finalmcc.covidsystem.entities.GroupCodePK[ day=" + day + ", startdate=" + startdate + " ]";
    }
    
}
