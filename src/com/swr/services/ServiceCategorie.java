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
import com.swr.entities.Categorie;
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
public class ServiceCategorie {

    public ArrayList<Categorie> categories;
    
    public static ServiceCategorie instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategorie() {
         req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

//    public boolean addcategorie(Categorie t) {
//
//    }

    public ArrayList<Categorie> parsecategories(String jsonText){
        try {
            categories=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
              
                categories.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        return categories;
    }
    
    public ArrayList<Categorie> getAllcategories(){
        String url = Statics.BASE_URL+"produit/getAllCategorie";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parsecategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

    public ArrayList<Categorie> getById(int id) {
          String url = Statics.BASE_URL+"produit/getById/"+id;
          System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parsecategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
}
