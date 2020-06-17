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
import com.codename1.ui.events.ActionListener;
import com.swr.entities.produit;
import com.swr.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceProduit {

    public ArrayList<produit> produits;
    public ArrayList<String> nomCategorie;
    public ArrayList<Double> nbProduit;
    
    
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public boolean addproduit(produit t,int idc) {
        String url = Statics.BASE_URL + "produit/addProduitMobile/"+ t.getNom() +"/" + t.getQuantite()+"/" + t.getPrix()+"/" + t.getIdUtilisateur()+"/" + idc +"/" + t.getImage_name();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

        public boolean Editproduit(produit t,int idc) {
        String url = Statics.BASE_URL + "produit/EditProduitMobile/"+ t.getId_produit() +"/"+ t.getNom() +"/" + t.getQuantite()+"/" + t.getPrix()+"/" + t.getIdUtilisateur()+"/" + idc +"/" + t.getImage_name();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public ArrayList<produit> parseproduits(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                produit t = new produit();
                float prix = Float.parseFloat(obj.get("prix").toString());
                t.setPrix((int)prix);
                t.setNom(obj.get("nom").toString());
                 float idP = Float.parseFloat(obj.get("idProduit").toString());
                t.setId_produit((int)idP);
                float nb = Float.parseFloat(obj.get("nbvue").toString());
                t.setNbvue((int)nb);
                float qt = Float.parseFloat(obj.get("quantite").toString());
                t.setQuantite((int)qt);
                float idU = Float.parseFloat(obj.get("idUtilisateur").toString());
                t.setIdUtilisateur((int)idU);
                t.setImage_name(obj.get("imageName").toString());
                t.setDate(obj.get("date").toString());
                LinkedHashMap<Object,Object> lhmcat = (LinkedHashMap<Object,Object>)obj.get("idCategorie");
                t.setIdCategorie(lhmcat);
              
                produits.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return produits;
    }
      public ArrayList<String> parseproduitsST(String jsonText){
        try {
            nomCategorie=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
         
            
                nomCategorie.add(obj.get("nom").toString());
            }
            
            
        } catch (IOException ex) {
            
        }
        return nomCategorie;
    }
          public ArrayList<Double> parseproduitsINT(String jsonText){
        try {
            nbProduit=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               
           
                 float idc = Float.parseFloat(obj.get("somme").toString());
             
              
                nbProduit.add((double)idc);
            }
            
            
        } catch (IOException ex) {
            
        }
        return nbProduit;
    }
    
    public ArrayList<produit> getAllproduits(int idu){
        String url = Statics.BASE_URL+"produit/GetAllProduit/"+idu;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseproduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    public ArrayList<produit> getTopProduits(){
        String url = Statics.BASE_URL+"produit/getTopProductMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseproduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    public ArrayList<produit> getAllproduit(){
        String url = Statics.BASE_URL+"produit/GetAllProduits";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseproduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    
        public boolean deleteProduit(int id) {

        String url = Statics.BASE_URL + "produit/deleteProduitMobile/" + id ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       public ArrayList<String> getNomStatproduits(int id){
        String url = Statics.BASE_URL+"produit/recupererStat2Mobile"+"/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nomCategorie = parseproduitsST(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nomCategorie;
    }
        public ArrayList<Double> getNbStatproduits(int id){
        String url = Statics.BASE_URL+"produit/recupererStat2Mobile"+"/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nbProduit = parseproduitsINT(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nbProduit;
    }
         public void ajoutpanier(String a ,String b) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost:8080/StockflowWEB/web/app_dev.php/commande/gerer_Commande_mobile?ids="+a+"&qte="+b;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }


}
