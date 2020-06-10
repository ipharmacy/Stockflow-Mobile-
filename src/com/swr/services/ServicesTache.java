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
import com.swr.entities.Employe;
import com.swr.entities.Tache;
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
public class ServicesTache {
    public ArrayList <Tache> taches;
    public Employe e;
    public boolean resultOk;
    public static ServicesTache instance;
    private ConnectionRequest req;
    
    
     public ServicesTache()
    {
        req= new ConnectionRequest();
        
    }
     
        public static ServicesTache getInstance()
    {
        if (instance==null)
        {
            instance= new ServicesTache();
        }
       return instance;
    }
        
    public boolean addTache(Tache t,Employe e)
    {
        String url=Statics.BASE_URL+"/tache/newTache?commentaire="+t.getCommentaire()+"&idEmploye="+e.getId();
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
        
        
        
        
        
         public ArrayList <Tache> parseTaches(String jsonText)
    {
        try {
            taches=new ArrayList();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list=(List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list)
            {
                Tache t = new Tache();
                float id =Float.parseFloat(obj.get("id").toString());
                String commentaire=obj.get("commentaire").toString();
                float idUtilisateur=Float.parseFloat(obj.get("idUtilisateur").toString());
                LinkedHashMap<String,Object> employe = (LinkedHashMap<String,Object>) obj.get("idEmploye"); 

                String nomEmp=employe.get("nom").toString();
                String prenomEmp=employe.get("prenom").toString();
                
                t.setNomEmploye(nomEmp);
                t.setPrenomEmploye(prenomEmp);
                
                t.setId((int)id);
                t.setCommentaire(commentaire);
                t.setEtat(0);
                t.setIdUtilisateur((int)idUtilisateur);
                LinkedHashMap<String,Object> date = (LinkedHashMap<String,Object>) obj.get("DateAttribution"); 
                        double k = (double) date.get("timestamp");
                        long x = (long) (k * 1000L);
                        t.setDateAttribution(new Date(x));
                          LinkedHashMap<String,Object> datea= (LinkedHashMap<String,Object>) obj.get("DateLimit"); 
                        double p = (double) datea.get("timestamp");
                        long y= (long) (p * 1000L);
                        t.setDateLimite(new Date(y));
               taches.add(t);
            }
        } catch (IOException ex) {
        }
        return taches;
    }
                  
    public ArrayList <Tache> getAllTaches()
    {
        String url=Statics.BASE_URL+"/tache/allTaches";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                taches=parseTaches(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return taches;
    } 
    
    
    
    
//    
//    public Employe parseEmploye(String jsonText)
//    {
//        e=new Employe();
//        try {
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            List<Map<String,Object>> list=(List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> obj : list)
//            {
//                float id =Float.parseFloat(obj.get("id").toString());
//                float cin=Float.parseFloat(obj.get("cin").toString());
//                float numTel=Float.parseFloat(obj.get("numTel").toString());
//                float idUtilisateur=Float.parseFloat(obj.get("idUtilisateur").toString());
//                String nom=obj.get("nom").toString();
//                String prenom=obj.get("prenom").toString();
//                String mail=obj.get("mail").toString();
//                String poste=obj.get("poste").toString();
//                String photo=obj.get("photo").toString();
//                Float salaire=Float.parseFloat(obj.get("salaire").toString());
//                 LinkedHashMap<String,Object> date = (LinkedHashMap<String,Object>) obj.get("dateRecrutement"); 
//                        double t = (double) date.get("timestamp");
//                        long x = (long) (t * 1000L);
//                        e.setDateRecrutement(new Date(x));
//                          LinkedHashMap<String,Object> datea= (LinkedHashMap<String,Object>) obj.get("dateNaissance"); 
//                        double p = (double) datea.get("timestamp");
//                        long y= (long) (t * 1000L);
//                        e.setDateRecrutement(new Date(y));
//                e.setId((int)id);
//                e.setConge(0);
//                e.setCin((int)cin);
//                e.setNumTel((int)numTel);
//                e.setIdUtilisateur((int)idUtilisateur);
//                e.setNom(nom);
//                e.setPrenom(prenom);
//                e.setMail(mail);
//                e.setPoste(poste);
//                e.setPhoto(photo);
//                e.setSalaire(salaire);
//            }
//        } catch (IOException ex) {
//        }
//        return e;
//    }    
//    
//    
//    
//     public int getEmploye(String prenom)
//    {
//        int idEmploye;
//        String url=Statics.BASE_URL+"/employe/returnEmploye?prenom="+prenom;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                e=parseEmploye(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return e.getId();
//    } 
//   
    
    
             public void deleteTache(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/untitled3/web/app_dev.php/tache/removeTache?id="+id;
        con.setUrl(Url);
        System.out.println("supprimer");
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }  
          

     
}
