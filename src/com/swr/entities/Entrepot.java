/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.entities;

/**
 *
 * @author HP
 */
public class Entrepot {
    private int id,vues,nb_rates,rating,idutilisateur,longitude,largitude; 
    private String  adresse  ,etat,image,nom;

    public Entrepot() {
    }

    public Entrepot(int id, int vues, int nb_rates, int rating, int idutilisateur, int longitude, int largitude, String adresse, String etat, String image) {
        this.id = id;
        this.vues = vues;
        this.nb_rates = nb_rates;
        this.rating = rating;
        this.idutilisateur = idutilisateur;
        this.longitude = longitude;
        this.largitude = largitude;
        this.adresse = adresse;
        this.etat = etat;
        this.image = image;
    }

    public String getNom() {
        return nom;
    }
     public Entrepot(int id,String nom,int vues, int nb_rates, int rating, int longitude, int largitude, String adresse, String etat, String image) {
       this.nom=nom;
       this.id=id;
       this.vues = vues;
        this.nb_rates = nb_rates;
        this.rating = rating;
        this.longitude = longitude;
        this.largitude = largitude;
        this.adresse = adresse;
        this.etat = etat;
        this.image = image;
    }

    public Entrepot(String nom,int vues, int nb_rates, int rating, int longitude, int largitude, String adresse, String etat, String image) {
       this.nom=nom;
        this.vues = vues;
        this.nb_rates = nb_rates;
        this.rating = rating;
        this.longitude = longitude;
        this.largitude = largitude;
        this.adresse = adresse;
        this.etat = etat;
        this.image = image;
    }
     public Entrepot(String nom, int longitude, int largitude, String adresse, String etat, String image) {
       this.nom=nom;
  
        this.longitude = longitude;
        this.largitude = largitude;
        this.adresse = adresse;
        this.etat = etat;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getVues() {
        return vues;
    }

    public int getNb_rates() {
        return nb_rates;
    }

    public int getRating() {
        return rating;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLargitude() {
        return largitude;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEtat() {
        return etat;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVues(int vues) {
        this.vues = vues;
    }

    public void setNb_rates(int nb_rates) {
        this.nb_rates = nb_rates;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLargitude(int largitude) {
        this.largitude = largitude;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Entrepot{" + "id=" + id + ", vues=" + vues + ", nb_rates=" + nb_rates + ", rating=" + rating + ", idutilisateur=" + idutilisateur + ", longitude=" + longitude + ", largitude=" + largitude + ", adresse=" + adresse + ", etat=" + etat + ", image=" + image + ", nom=" + nom + '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    
    
    
    
}
