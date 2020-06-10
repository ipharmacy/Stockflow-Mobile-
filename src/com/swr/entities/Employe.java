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
public class Employe {
    private int id,cin,numTel,idUtilisateur,conge;
    private String nom,prenom,mail,poste,photo;
    private float salaire;
    Date dateRecrutement,dateNaissance;
    
            public Employe(String nom,String prenom,String mail,int numTel,int cin,String poste,float salaire)
            {
                  this.cin = cin;
                  this.numTel = numTel;
                  this.nom = nom;
                  this.prenom = prenom;
                  this.mail = mail;
                  this.poste = poste;
                  this.salaire = salaire;
            }
              public Employe(int id,String nom,String prenom,String mail,int numTel,int cin,String poste,float salaire)
            {
                  this.cin = cin;
                  this.numTel = numTel;
                  this.nom = nom;
                  this.prenom = prenom;
                  this.mail = mail;
                  this.poste = poste;
                  this.salaire = salaire;
            }
    public Employe() {
    }

    public Employe(int id, int cin, int numTel, int idUtilisateur, String nom, String prenom, String mail, String poste, String photo, float salaire, int conge,Date dateRecrutement,Date dateNaissance) {
        this.id = id;
        this.cin = cin;
        this.numTel = numTel;
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.poste = poste;
        this.photo = photo;
        this.dateRecrutement = dateRecrutement;
        this.dateNaissance = dateNaissance;
        this.salaire = salaire;
        this.conge = conge;
    }
    
    public Employe(String nom, String prenom, String mail ,int numTel , int cin , String poste,float salaire,String photo)
    {
        this.cin = cin;
        this.numTel = numTel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.poste = poste;
        this.photo = photo;
        this.salaire = salaire;     
    }

    public Employe(int cin, int numTel, int idUtilisateur, String nom, String prenom, String mail, String poste, String photo, float salaire, int conge,Date dateRecrutement,Date dateNaissance) {
        this.cin = cin;
        this.numTel = numTel;
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.poste = poste;
        this.photo = photo;
       this.dateRecrutement = dateRecrutement;
       this.dateNaissance = dateNaissance;
        this.salaire = salaire;
        this.conge = conge;
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
     * @return the idUtilisateur
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * @param idUtilisateur the idUtilisateur to set
     */
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    /**
     * @return the poste
     */
    public String getPoste() {
        return poste;
    }

    /**
     * @param poste the poste to set
     */
    public void setPoste(String poste) {
        this.poste = poste;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
    /**
     * @return the salaire
     */
    public float getSalaire() {
        return salaire;
    }

    /**
     * @param salaire the salaire to set
     */
    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    /**
     * @return the conge
     */
    public int isConge() {
        return conge;
    }

    /**
     * @param conge the conge to set
     */
    public void setConge(int conge) {
        this.conge = conge;
    }

    @Override
    public String toString() {
       return "Employe{" + "id=" + id + ", cin=" + cin + ", numTel=" + numTel + ", idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", poste=" + poste + ", photo=" + photo + ", dateRecrutement=" + dateRecrutement + ", dateNaissance=" + dateNaissance + ", salaire=" + salaire + ", conge=" + conge + '}';
    }
   
    
    
}
