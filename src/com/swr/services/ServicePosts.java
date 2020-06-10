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
import com.swr.entities.Posts;
import com.swr.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServicePosts {

    public ArrayList<Posts> posts;
    public  String  result="";
    public static ServicePosts instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicePosts() {
         req = new ConnectionRequest();
    }

    public static ServicePosts getInstance() {
        if (instance == null) {
            instance = new ServicePosts();
        }
        return instance;
    }

    public boolean addPost(Posts t) {
        String url = Statics.BASE_URL + "/news?" +"sujet="+ t.getSujet() +"&" +"description=" + t.getDesciption()+"&"+"type="+t.getType();         req.setUrl(url);
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

    public ArrayList<Posts> parsePosts(String jsonText){
        try {
            posts=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Posts t = new Posts();   
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setDesciption(obj.get("description").toString());
                t.setIduser(0);
                t.setSujet(obj.get("sujet").toString());
                t.setType(obj.get("type").toString());
                //float iduser =Float.parseFloat( obj.get("iduser").toString());
                //t.setIduser((int)iduser);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                //t.setName(obj.get("name").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                  posts.add(t);
             
            }
            
            
        } catch (IOException ex) {
            
        }
        return posts;
    }
    
    public ArrayList<Posts> getAllPosts(){
         String url = "http://localhost/stockflowWEB/web/app_dev.php"+"/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }
    
    public String DeletePost(Posts p){
          String url = Statics.BASE_URL + "/deletee?id=" + p.getId();
          
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
                   

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                   result=(String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    public boolean EditPost(Posts p) {
        String url = Statics.BASE_URL + "/editpm?id="+p.getId()
                +"&description=" + p.getDesciption();
              
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   /*  public boolean ViewsPost(Posts p) {
        String url = Statics.BASE_URL + "/viewspm?idp="+p.getIdP();              
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
      public boolean LikesPost(Posts p) {
        String url = Statics.BASE_URL + "/likepm?idp="+p.getIdP();
              
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }*/
}
