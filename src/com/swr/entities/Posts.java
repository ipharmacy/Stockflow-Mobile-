/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.entities;
import java.util.Date;


/**
 *
 * @author Soulah
 */
public class Posts {
    int id,iduser;

    public Posts() {
    }

    public Posts(String text) {
      
    }

    public Posts(int id,String desciption) {
        this.id = id;
        this.desciption = desciption;
     
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Posts(String sujet, String desciption, String type) {
        this.sujet = sujet;
        this.desciption = desciption;
        this.type = type;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getSujet() {
        return sujet;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", iduser=" + iduser + ", sujet=" + sujet + ", desciption=" + desciption + ", type=" + type + '}';
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Posts(int id, int iduser, String sujet, String desciption, String type) {
        this.id = id;
        this.iduser = iduser;
        this.sujet = sujet;
        this.desciption = desciption;
        this.type = type;
    }
    
   
    String sujet,desciption,type;
    

}
