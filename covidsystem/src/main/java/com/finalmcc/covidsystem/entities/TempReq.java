/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.entities;

/**
 *
 * @author JESSI
 */
public class TempReq {
    
    private String quota;
    private String note;

     public TempReq() {
    }
    
    public TempReq(String quota, String note) {
        this.quota = quota;
        this.note = note;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
