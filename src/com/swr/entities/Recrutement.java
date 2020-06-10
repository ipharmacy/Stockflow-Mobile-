/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.entities;

import java.util.Date;


/**
 *
 * @author chihe
 */
public class Recrutement {
   private int id,cin,numTel,etat;
   private String nom,prenom,mail,dt;
   private Date dateNaissance;

    public Recrutement() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Recrutement(int id, int cin, int numTel, String nom, String prenom, String mail,Date dateNaissance) {
        this.id = id;
        this.cin = cin;
        this.numTel = numTel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.dateNaissance = dateNaissance;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Recrutement(int cin, int numTel, String nom, String prenom, String mail,Date dateNaissance) {
        this.cin = cin;
        this.numTel = numTel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.dateNaissance = dateNaissance;
    }
    
        public Recrutement(int cin, int numTel, String nom, String prenom, String mail,String dt) {
        this.cin = cin;
        this.numTel = numTel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.dt = dt;
    }
    
       public Recrutement(int cin, int numTel, String nom, String prenom, String mail)
         {
        this.cin = cin;
        this.numTel = numTel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.dateNaissance = dateNaissance;
         }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cin
     */
    public int getCin() {
        return cin;
    }

    /**
     * @param cin the cin to set
     */
    public void setCin(int cin) {
        this.cin = cin;
    }

    /**
     * @return the numTel
     */
    public int getNumTel() {
        return numTel;
    }

    /**
     * @param numTel the numTel to set
     */
    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

  
    @Override
   public String toString() {
      return "Recrutement{" + "id=" + id + ", cin=" + cin + ", numTel=" + numTel + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", dateNaissance=" + dateNaissance + '}';
    }
   
   
    
}
