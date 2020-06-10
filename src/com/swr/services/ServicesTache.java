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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.swr.entities.Employe;
import com.swr.entities.Tache;
import com.swr.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author chihe
 */
public class ServicesTache {
    public ArrayList <Tache> taches;
    public HashMap <String,Integer> tachesfaites;
    public HashMap <String,Integer> tachesnonfaites;

    
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
    
    
       public boolean validerTache(String commentaire)
    {
        String url=Statics.BASE_URL+"/tache/validerTache?commentaire="+commentaire;
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
        
        public ArrayList <Tache> parseTachesEmploye(String jsonText)
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
                float Etat=Float.parseFloat(obj.get("etat").toString());
                float idEmploye=Float.parseFloat(obj.get("idEmploye").toString());
                float idUtilisateur=Float.parseFloat(obj.get("idUtilisateur").toString());
                SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
                String DateAttribution=obj.get("DateAttribution").toString(); 
                String DateLimite=obj.get("DateLimit").toString();
                t.setId((int)id);
                t.setCommentaire(commentaire);
                t.setEtat((int)Etat);
                t.setIdEmploye((int)idEmploye);
                t.setIdUtilisateur((int) idUtilisateur); 
                t.setDateAttribution(formatter6.parse(DateAttribution));
                t.setDateLimite(formatter6.parse(DateLimite));
                taches.add(t);
            }
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
        return taches;
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
                LinkedHashMap<String,Object> employe = (LinkedHashMap<String,Object>) obj.get("idEmploye"); 

                String nomEmp=employe.get("nom").toString();
                String prenomEmp=employe.get("prenom").toString();
                
                t.setNomEmploye(nomEmp);
                t.setPrenomEmploye(prenomEmp);
                
                t.setId((int)id);
                t.setCommentaire(commentaire);
                t.setEtat(0);
                LinkedHashMap<String,Object> Nom = (LinkedHashMap<String,Object>) obj.get("idUtilisateur"); 
                String Utilisateur=Nom.get("username").toString();
                t.setUtilisateur(Utilisateur);
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
    
    
       public ArrayList <Tache> getAllTachesEmploye(String prenom)
    {
        String url=Statics.BASE_URL+"/tache/getEmpTache?prenom="+prenom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                taches=parseTachesEmploye(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return taches;
    } 
    
    
    
   
    
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
             
             
             
    public HashMap <String,Integer> parseTachesFaite(String jsonText)
    {
        try {
            tachesfaites=new HashMap();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list=(List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list)
                
            {  
     int nbJanvier,nbFevrier,nbMars,nbAvril,nbMai,nbJuin,nbJuillet,nbAout,nbSeptembre,nbOctober,nbNovember,nbDecembre=0;
                
                
                        if(obj.get("mois").toString().equals("1"))
                        {
                           nbJanvier=(int) Float.parseFloat(obj.get("nbTaches").toString());
                           tachesfaites.put("janvier",nbJanvier); 
                        }
                
                        if(obj.get("mois").toString().equals("2"))
                        {                       
                            nbFevrier=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("fevrier",nbFevrier); 

                            
                        }
                             if(obj.get("mois").toString().equals("3"))
                        {
                            nbMars=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("mars",nbMars);
                            
                        }
                              if(obj.get("mois").toString().equals("4"))
                        {
                            nbAvril=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("avril",nbAvril);
                        }
                             if(obj.get("mois").toString().equals("5"))
                        {
                            nbMai=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("mai",nbMai);
                        }
                        
                         if(obj.get("mois").toString().equals("6"))
                        {
                            nbJuin=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("juin",nbJuin);
                        }
                          if(obj.get("mois").toString().equals("7"))
                        {
                            nbJuillet=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("juillet",nbJuillet);
                        }
                           if(obj.get("mois").toString().equals("8"))
                        {
                            nbAout=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("aout",nbAout);
                        }
                            if(obj.get("mois").toString().equals("9"))
                        {
                            nbSeptembre=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("september",nbSeptembre);
                        }
                             if(obj.get("mois").toString().equals("10"))
                        {
                            nbOctober=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("october",nbOctober);
                        }
                              if(obj.get("mois").toString().equals("11"))
                        {
                               nbNovember=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesfaites.put("november",nbNovember);
                        }
                               if(obj.get("mois").toString().equals("12"))
                        {
                               nbDecembre=(int) Float.parseFloat(obj.get("nbTaches").toString());
                               tachesfaites.put("december",nbDecembre);
                        }
                        
            }
        } catch (IOException ex) {
        }
        return tachesfaites;
    }
                   
    public HashMap <String,Integer> parseTachesNonFaite(String jsonText)
    {
        try {
            tachesnonfaites=new HashMap<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list=(List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list)
            {
                int nbJanvier,nbFevrier,nbMars,nbAvril,nbMai,nbJuin,nbJuillet,nbAout,nbSeptembre,nbOctober,nbNovember,nbDecembre=0;
                
                
                        if(obj.get("mois").toString().equals("1"))
                        {
                           nbJanvier=(int) Float.parseFloat(obj.get("nbTaches").toString());
                           tachesnonfaites.put("janvier",nbJanvier); 
                        }
                
                        if(obj.get("mois").toString().equals("2"))
                        {                       
                            nbFevrier=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("fevrier",nbFevrier); 

                            
                        }
                             if(obj.get("mois").toString().equals("3"))
                        {
                            nbMars=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("mars",nbMars);
                            
                        }
                              if(obj.get("mois").toString().equals("4"))
                        {
                            nbAvril=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("avril",nbAvril);
                        }
                             if(obj.get("mois").toString().equals("5"))
                        {
                            nbMai=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("mai",nbMai);
                        }
                        
                         if(obj.get("mois").toString().equals("6"))
                        {
                            nbJuin=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("juin",nbJuin);
                        }
                          if(obj.get("mois").toString().equals("7"))
                        {
                            nbJuillet=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("juillet",nbJuillet);
                        }
                           if(obj.get("mois").toString().equals("8"))
                        {
                            nbAout=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("aout",nbAout);
                        }
                            if(obj.get("mois").toString().equals("9"))
                        {
                            nbSeptembre=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("september",nbSeptembre);
                        }
                             if(obj.get("mois").toString().equals("10"))
                        {
                            nbOctober=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("october",nbOctober);
                        }
                              if(obj.get("mois").toString().equals("11"))
                        {
                               nbNovember=(int) Float.parseFloat(obj.get("nbTaches").toString());
                            tachesnonfaites.put("november",nbNovember);
                        }
                               if(obj.get("mois").toString().equals("12"))
                        {
                               nbDecembre=(int) Float.parseFloat(obj.get("nbTaches").toString());
                               tachesnonfaites.put("december",nbDecembre);
                        }
               
            }
        } catch (IOException ex) {
        }
        return tachesnonfaites;
    }
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
    public HashMap <String,Integer> TachesFaites(int idEmploye)
    {
        String url=Statics.BASE_URL+"/employe/statEmpFaite?idEmploye="+idEmploye;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                tachesfaites=parseTachesFaite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tachesfaites;
    } 
          
    
    public HashMap <String,Integer> TachesNonFaites(int idEmploye)
    {
        String url=Statics.BASE_URL+"/employe/statEmpNonFaite?idEmploye="+idEmploye;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                tachesnonfaites=parseTachesNonFaite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tachesnonfaites;
    } 

     
}
