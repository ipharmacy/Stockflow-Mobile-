/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.swr.entities.Recrutement;
import com.swr.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chihe
 */
public class ServicesRecrutement {
    public ArrayList <Recrutement> recrutements;
    public boolean resultOk;
    public static ServicesRecrutement instance;
    private ConnectionRequest req;
    
    
     public ServicesRecrutement()
    {
        req= new ConnectionRequest();
        
    }
     
        public static ServicesRecrutement getInstance()
    {
        if (instance==null)
        {
            instance= new ServicesRecrutement();
        }
       return instance;
    }
        
        
        
         public boolean addRecrutement(Recrutement r)
    {
        String url=Statics.BASE_URL+"/recrutement/newRecrutement?nom="+r.getNom()+"&prenom="+r.getPrenom()+"&dateNaissance="+r.getDt()+"&mail="+r.getMail()+"&cin="+r.getCin()+"&numTel="+r.getNumTel();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
    }    
         
         
        
         public ArrayList <Recrutement> parseRecrutements(String jsonText)
    {
        try {
            recrutements=new ArrayList();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list=(List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list)
            {
                Recrutement r = new Recrutement();
                float id =Float.parseFloat(obj.get("id").toString());
                String nom=obj.get("nom").toString();
                String prenom=obj.get("prenom").toString();
                String mail=obj.get("mail").toString();
                float cin=Float.parseFloat(obj.get("cin").toString());
                float numTel=Float.parseFloat(obj.get("numTel").toString());
                float etat=Float.parseFloat(obj.get("etat").toString());
                String dt=obj.get("dateNaissance").toString();
                
                r.setId((int)id);
                r.setNom(nom);
                r.setPrenom(prenom);
                r.setMail(mail);
                r.setCin((int)cin);
                r.setNumTel((int)numTel);
                r.setEtat((int)etat);
                r.setDt(dt);
      
               recrutements.add(r);
            }
        } catch (IOException ex) {
        }
        return recrutements;
    }
         
         
    public ArrayList <Recrutement> getAllRecrutements()
    {
        String url=Statics.BASE_URL+"recrutement/all_recrutement";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                recrutements=parseRecrutements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return recrutements;
    }
            
               
            
            
        public boolean Entretien(Recrutement r) {
        String url = Statics.BASE_URL + "recrutement/envoieMail?mail="+r.getMail();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }   
        
        
        public boolean SendMailEmploye(String sujet,String source,String destination,String message) {
        String url = Statics.BASE_URL + "employe/sendMail?sujet="+sujet+"&source="+source+"&destination="+destination+"&message="+message;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }   
        
        
        
        public boolean uppRecrutement(Recrutement r) {
        String Url = "http://localhost/untitled3/web/app_dev.php/recrutement/modifyRecrutement?id="
                + r.getId()
                + "&nom=" + r.getNom()
                + "&prenom=" + r.getPrenom()
                + "&mail=" + r.getMail()
                + "&numTel=" + r.getNumTel()
                + "&cin=" + r.getCin()
                +"&etat="+r.getEtat()
                +"&dateNaissance="+r.getDt();
        req.setUrl(Url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
        
        
        
        public void deleteRecrutement(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/untitled3/web/app_dev.php/recrutement/removeRecrutement?id="+id;
        con.setUrl(Url);
        System.out.println("supprimer");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }  
        
    
    
    
}
