/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.entities;

import java.util.LinkedHashMap;

/**
 *
 * @author Dhia
 */
public class produit {
    private int id_produit;
    private String nom;
    private int quantite;
    private float prix;
    private String date;
    private String img;
    private int id_entrepot;
    private int idUtilisateur;
    private LinkedHashMap<Object,Object> idCategorie;
    private String image_name;

   
    private int archiver;
    private int nbvue;
    
     public produit() {
    }

    public produit(int id_produit, String nom, int quantite, float prix, String date, String img, int id_entrepot, int idUtilisateur, LinkedHashMap<Object,Object> idCategorie, String image_name, int archiver, int nbvue) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.date = date;
        this.img = img;
        this.id_entrepot = id_entrepot;
        this.idUtilisateur = idUtilisateur;
        this.idCategorie = idCategorie;
        this.image_name = image_name;
        this.archiver = archiver;
        this.nbvue = nbvue;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId_entrepot() {
        return id_entrepot;
    }

    public void setId_entrepot(int id_entrepot) {
        this.id_entrepot = id_entrepot;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public LinkedHashMap<Object,Object> getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(LinkedHashMap<Object,Object> idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getArchiver() {
        return archiver;
    }

    public void setArchiver(int archiver) {
        this.archiver = archiver;
    }

    public int getNbvue() {
        return nbvue;
    }

    public void setNbvue(int nbvue) {
        this.nbvue = nbvue;
    }
     
}
