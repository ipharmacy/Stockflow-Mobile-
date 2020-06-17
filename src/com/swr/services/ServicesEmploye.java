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
public class ServicesEmploye {

    public ArrayList<Employe> employes;
    public boolean resultOk;
    public static ServicesEmploye instance;
    private ConnectionRequest req;

    public ServicesEmploye() {
        req = new ConnectionRequest();

    }

    public static ServicesEmploye getInstance() {
        if (instance == null) {
            instance = new ServicesEmploye();
        }
        return instance;
    }

    public boolean addEmploye(Employe e) {
        String url = Statics.BASE_URL + "employe/newEmploye?name=" + e.getNom() + "&prenom=" + e.getPrenom() + "&mail=" + e.getMail() + "&numTel=" + e.getNumTel() + "&cin=" + e.getCin() + "&poste=" + e.getPoste() + "&salaire=" + e.getSalaire()+"&photo="+e.getPhoto();
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

    public boolean uppEmploye(Employe e) {
        String Url = "http://localhost/untitled3/web/app_dev.php/employe/modifyEmploye?id="
                + e.getId()
                + "&name=" + e.getNom()
                + "&prenom=" + e.getPrenom()
                + "&mail=" + e.getMail()
                + "&numTel=" + e.getNumTel()
                + "&cin=" + e.getCin()
                + "&poste=" + e.getPoste()
                + "&salaire=" + e.getSalaire();
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

    public boolean deelEmploye(Employe e) {
        String Url = "http://localhost/untitled3/web/app_dev.php/employe/removeEmploye?id=" +e.getId();
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

    public ArrayList<Employe> parseEmployes(String jsonText) {
        try {
            employes = new ArrayList();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Employe e = new Employe();
                float id = Float.parseFloat(obj.get("id").toString());
                float cin = Float.parseFloat(obj.get("cin").toString());
                float numTel = Float.parseFloat(obj.get("numTel").toString());
                float idUtilisateur = Float.parseFloat(obj.get("idUtilisateur").toString());
                String nom = obj.get("nom").toString();
                String prenom = obj.get("prenom").toString();
                String mail = obj.get("mail").toString();
                String poste = obj.get("poste").toString();
                String photo = obj.get("photo").toString();
                Float salaire = Float.parseFloat(obj.get("salaire").toString());
                LinkedHashMap<String, Object> date = (LinkedHashMap<String, Object>) obj.get("dateRecrutement");
                double t = (double) date.get("timestamp");
                long x = (long) (t * 1000L);
                e.setDateRecrutement(new Date(x));
                LinkedHashMap<String, Object> datea = (LinkedHashMap<String, Object>) obj.get("dateNaissance");
                double p = (double) datea.get("timestamp");
                long y = (long) (t * 1000L);
                e.setDateRecrutement(new Date(y));
                e.setId((int) id);
                e.setConge(0);
                e.setCin((int) cin);
                e.setNumTel((int) numTel);
                e.setIdUtilisateur((int) idUtilisateur);
                e.setNom(nom);
                e.setPrenom(prenom);
                e.setMail(mail);
                e.setPoste(poste);
                e.setPhoto(photo);
                e.setSalaire(salaire);
                employes.add(e);
            }
        } catch (IOException ex) {
        }
        return employes;
    }

    public ArrayList<Employe> getAllEmployes(){
        String url = Statics.BASE_URL + "employe/findAllEmployes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                employes = parseEmployes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return employes;
    }
    
//    
     public ArrayList<Employe> getAllEmployesSortedByLastName(){
        String url = Statics.BASE_URL + "employe/sortLastName";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                employes = parseEmployes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return employes;
    }
    
     
     public ArrayList<Employe> getAllEmployesSortedByStatus(){
        String url = Statics.BASE_URL + "employe/sortstatus";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                employes = parseEmployes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return employes;
    }
    
    
    
    
    
    
    

    public ArrayList<Employe> getEmploye(String Name) {
        String url = Statics.BASE_URL + "employe/returnEmploye?prenom=" + Name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                employes = parseEmployes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        return employes;
    }

 
        public void deleteEmploye(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/untitled3/web/app_dev.php/employe/removeEmploye?id="+id;
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
        
    public boolean updateEmploye(Employe e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/untitled3/web/app_dev.php/tasks/modifyEmploye?id="
                + e.getId()
                + "&name=" + e.getNom()
                + "&prenom=" + e.getPrenom()
                + "&mail=" + e.getMail()
                + "&numTel=" + e.getNumTel()
                + "&cin=" + e.getCin()
                + "&poste=" + e.getPoste()
                + "&salaire=" + e.getSalaire();
        con.setUrl(Url);

        System.out.println("modifié");

        con.addResponseListener((em) -> {
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
        return resultOk;
    }

}
