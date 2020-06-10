/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.services;

//import edu.gestudent.utils.DataBase;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.swr.entities.Posts;
import com.swr.entities.User;
import com.swr.utils.Statics;
import static com.swr.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import java.sql.Connection;
//import java.sql.Statement;
/**
 *
 * @author Ayadi
 */
public class ServicesUsers {

    public static ServicesUsers instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<User> users;

    
    
    public User User = new User();

    private ServicesUsers() {
        req = new ConnectionRequest();
    }

    public static ServicesUsers getInstance() {

        if (instance == null) {
            instance = new ServicesUsers();
        }
        return instance;
    }

 
    
    
    public User  parseUser(String jsonText){
       
                User u = new User();
              try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            
           
            Map<String,Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

           if(UserListJson.isEmpty()==false)
           {
               
                float id = Float.parseFloat(UserListJson.get("id").toString());
                System.out.println(id);
                u.setId((int)id);
               
                u.setUsername(UserListJson.get("username").toString());
               // u.setEmail(UserListJson.get("email").toString());
                u.setPassword(UserListJson.get("password").toString());
               // u.setC_password(UserListJson.get("c_password").toString());
              //  u.setRoles(UserListJson.get("roles").toString());
                users.add(u);
            
            }
            
        } catch (IOException ex) {
            
        }
        return u;
    }

    public User Login(String username,String password) {
        String url =BASE_URL +"User/loginMobile/"+username+"/"+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                User = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }
    public boolean checkLogin(String username, String password) {
        String url = "http://localhost:8080/StockflowWEB/web/app_dev.php/User/loginMobile/"  + username + "/" + password;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>()  {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<User> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User user = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                user.setId((int) id);
                user.setUsername(obj.get("Username").toString());
                user.setRoles(obj.get("Role").toString());
             
                users.add(user);
            }
        } catch (IOException ex) {
        }
        return users;
    }
    public ArrayList<User> getUser(String username, String password) {
        String url = "http://localhost:8080/StockflowWEB/web/app_dev.php/User/loginMobile/"  + username + "/" + password;
        req.setUrl(url);
        // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    

}
