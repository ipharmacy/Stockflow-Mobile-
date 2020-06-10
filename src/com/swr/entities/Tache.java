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
public class Tache {
    private int id,idUtilisateur,idEmploye,etat;
    private String commentaire;
    private Date dateAttribution,DateLimite;
    private String nomEmploye,prenomEmploye;

    public String getNomEmploye() {
        return nomEmploye;
    }
    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }
    public String getPrenomEmploye() {
        return prenomEmploye;
    }
    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }
    public Tache() {
    }
    public Tache(int id, int idUtilisateur, int idEmploye, String commentaire,Date dateAttribution,Date DateLimite) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idEmploye = idEmploye;
        this.commentaire = commentaire;
       this.dateAttribution = dateAttribution;
        this.DateLimite = DateLimite;
    }

    public Tache(int idUtilisateur, int idEmploye, String commentaire,Date dateAttribution,Date DateLimite) {
        this.idUtilisateur = idUtilisateur;
        this.idEmploye = idEmploye;
        this.commentaire = commentaire;
           this.dateAttribution = dateAttribution;
            this.DateLimite = DateLimite;
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
     * @return the idEmploye
     */
    public int getIdEmploye() {
        return idEmploye;
    }

    /**
     * @param idEmploye the idEmploye to set
     */
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * @return the etat
     */
    public int getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Date getDateAttribution() {
        return dateAttribution;
    }

    public void setDateAttribution(Date dateAttribution) {
        this.dateAttribution = dateAttribution;
    }

    public Date getDateLimite() {
        return DateLimite;
    }

    public void setDateLimite(Date DateLimite) {
        this.DateLimite = DateLimite;
    }

 

    @Override
    public String toString() {
       return "Tache{" + "id=" + id + ", idUtilisateur=" + idUtilisateur + ", idEmploye=" + idEmploye + ", commentaire=" + commentaire + ", dateAttribution=" + dateAttribution + ", DateLimite=" + DateLimite + '}';
    }
    
    
}
