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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.swr.entities.Entrepot;
import utils.DataSource;
import  com.swr.utils.Statics;

/**
 *
 * @author HP
 */
public class Entrepot_Service {

    private ConnectionRequest request;
    private boolean responseResult;
    
    public ArrayList<Entrepot> entrepots;

    public Entrepot_Service() {
        request = DataSource.getInstance().getRequest();
    }
public boolean ModifierEntrepot(Entrepot abonnment,int id) {
     
        String url = Statics.BASE_URL + "/entrepotmobile/editEntrepot?nom=" + abonnment.getNom()+ "&image=" + abonnment.getImage()+ "&adresse=" + abonnment.getAdresse()+ "&largitude=" + abonnment.getLargitude()+ "&longitude=" + abonnment.getLongitude()+ "&etat=" + abonnment.getEtat()+"&id=" +id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
  
    public ArrayList<Entrepot> getAllentrepots() {
        String url = Statics.BASE_URL + "/entrepotmobile/AllEntrepot?id="+14;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    entrepots = parsentrepots(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (IOException ex) {
                }
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return entrepots;
    }

    public ArrayList<Entrepot> parsentrepots(String jsonText) throws IOException {

        entrepots = new ArrayList<>();

        JSONParser jp = new JSONParser();
        Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
        for (Map<String, Object> obj : list) {

            String nom = obj.get("nom").toString();
            String adresse = obj.get("adresse").toString();

            String etat = obj.get("etat").toString();
            String image = obj.get("image").toString();
            int id= (int) Float.parseFloat(obj.get("id").toString());
            int longitude = (int) Float.parseFloat(obj.get("longitude").toString());
            int largitude = (int) Float.parseFloat(obj.get("largitude").toString());
            int rating = (int) Float.parseFloat(obj.get("rating").toString());
            int vues = (int) Float.parseFloat(obj.get("vues").toString());
            int nb_rates = 0;
            entrepots.add(new Entrepot(id,nom, vues, nb_rates, rating, longitude, largitude, adresse, etat, image));

        }

        return entrepots;
    }


     public boolean addEntrepot(Entrepot v) {
     
        String url = Statics.BASE_URL + "/entrepotmobile/AddEntrepot?nom=" + v.getNom()+ "&adresse=" + v.getAdresse()+ "&etat=" + v.getEtat()+ "&image=" + v.getImage()+ "&longitude=" + v.getLongitude()+ "&largitude=" + v.getLargitude();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
     public boolean removeEntrepot(int id) {
     
        String url = Statics.BASE_URL + "/entrepotmobile/removeEntrepot?id=" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
        public ArrayList<Entrepot> parseStat(String jsonText) {
        
        try {
            entrepots = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("nom").toString();     
          
      

            int quantite = (int)Float.parseFloat(obj.get("nombre").toString());
            
    
            Entrepot ab = new Entrepot();
            ab.setVues(quantite);
            ab.setNom(LibC);
            entrepots.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return entrepots;
    }
         public ArrayList<Entrepot> getStat() {
        String url = Statics.BASE_URL + "/entrepotmobile/statEntrepot";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                entrepots = parseStat(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return entrepots;
    }

}
