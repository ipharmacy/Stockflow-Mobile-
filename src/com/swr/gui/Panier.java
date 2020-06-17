/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.Categorie;
import com.swr.entities.Employe;
import com.swr.entities.produit;
import com.swr.services.ServiceProduit;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import jdk.nashorn.internal.objects.NativeString;

/**
 *
 * @author Dhia
 */
public class Panier extends BaseForm{
    Form current; 
    Database db;
    Resources theme= UIManager.initFirstTheme("/theme");
     boolean created = false;
    Container cnt2 = new Container(BoxLayout.y());
    Panier(Form previous) {
       
        current=this;

          setTitle("Panier"); 
          
            try {
         getToolbar().addCommandToLeftBar("back", null, e ->{ Produits a =new Produits(current,theme); a.show();});
        
         
          
               
                
             created = Database.exists("Pann.db");
               
                db = Database.openOrCreate("Pann.db");
                
                if (created == false) {
                    db.execute("create table Pann (id INTEGER PRIMARY KEY AUTOINCREMENT,idproduit INTEGER  ,quantite INTEGER  , nom TEXT");
                }
         
         Cursor result = db.executeQuery("select * from Pann");
         
         int i = 0 ;
        
         while (result.next()) {
             Row r = result.getRow();
           final int idproduit = r.getInteger(1) ;
            
             
             Label lnom= new Label("Produit : "+r.getString(3));
             Label lquantite= new Label("Quantite : "+r.getInteger(2)+"");
             
              Label supprimer = new Label("Supprimer du  panier");
              supprimer.addPointerPressedListener((e)->{
                  
            
              try {
               
                boolean created = false;
                created = Database.exists("Pann.db");
                
                db = Database.openOrCreate("Pann.db");
                
                if (created == false) {
                    db.execute("create table Pann (id INTEGER PRIMARY KEY AUTOINCREMENT,idproduit INTEGER  ,quantite INTEGER  , nom TEXT )");
                }
                  db.execute("delete from Pann where idproduit ="+ idproduit);
                    new Panier(current).show();   
            } catch (IOException ex) {
                ex.printStackTrace();
               
            }
        });
              
               cnt2.add(lnom).add(lquantite).add(supprimer);
               
               
             
         }
        final List<Integer> ids =new ArrayList<>();
          final List<Integer> qte =new ArrayList<>();
          Button confirmer = new Button("Confirmer");
         confirmer.addActionListener(es -> {
                    
            
             
             try {
                 final Cursor resulta = db.executeQuery("select * from Pann");
                 
                  
                
                 
                  while (resulta.next()) {
             Row r = resulta.getRow();
           final int idproduit = r.getInteger(1) ;
            ids.add(idproduit);
            qte.add( r.getInteger(2));
            
        
            
               
             
         }
                  
                    String delim = "-";

		StringBuilder sb = new StringBuilder();

		int it = 0;
		while (it < ids.size() - 1) {
			sb.append(ids.get(it));
			sb.append(delim);
			it++;
		}
		sb.append(ids.get(it));

		String resids = sb.toString();
		System.out.println(resids);
                
                
               

		StringBuilder sba = new StringBuilder();

		int ita = 0;
		while (ita < qte.size() - 1) {
			sba.append(qte.get(ita));
			sba.append(delim);
			ita++;
		}
		sba.append(qte.get(ita));

		String resqte = sba.toString();
		System.out.println(resqte);
                
             ServiceProduit s =new ServiceProduit();
             s.ajoutpanier(resids, resqte);
                 
                Dialog.show("Info", "commande terminé!", "ok", null);   
                 
                 
                 
                 
                 
                 
                 
             } catch (IOException ex) {
              
             }
             
             
             
             
             
             
             
                        });
         
         
          
           cnt2.add(confirmer);
          
          add(cnt2);
         
     } catch (IOException ex) {
        ex.printStackTrace();
     }
       
        
      

  
       
           
        
    } 

   
      private Container setProduit(produit p)
    {
        Container cnt=new Container(new BorderLayout());
        Container cnt2= new Container(BoxLayout.y());
        
        Label lbnom=new Label(""+p.getNom());
        Label lbprix=new Label("Prix :"+p.getPrix()+" dt");
        
        Label lbdate=new Label("publié le "+ p.getDate().substring(0,10));
        Label lbcategory=new Label("Categorie :"+p.getIdCategorie().get("nom"));
        
        
        
        ImageViewer lbimg=new ImageViewer();
        
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("load.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,p.getImage_name(),"http://localhost/StockflowWEB/web/uploads/products/"+p.getImage_name()); 
        lbimg.setImage(uRLImage);
        
       
        Container supedit = new Container(BoxLayout.x());
        /*Label supp = new Label("supprimer");
        supp.addPointerPressedListener((e)->{
            com.swr.services.ServiceProduit SP = new ServiceProduit();
            if(ServiceProduit.getInstance().deleteProduit(p.getId_produit())){
                Produits refresh = new Produits(theme);
                refresh.show();
            }
        });
        
        Label edit = new Label("modifier");
        edit.addPointerPressedListener((e)->{
            EditProduit ep = new EditProduit(theme, p);
            ep.show();
        
        });*/
        Label ajouter = new Label("Ajouter au panier");
        ajouter.addPointerPressedListener((e)->{
            com.swr.services.ServiceProduit SP = new ServiceProduit();
            if(ServiceProduit.getInstance().deleteProduit(p.getId_produit())){
                Produits refresh = new Produits(current,theme);
                refresh.show();
            }
        });
        
        
        
        supedit.addAll(ajouter);
        cnt2.addAll(lbnom,lbprix,lbdate,lbcategory,supedit);
        cnt.add(BorderLayout.WEST,lbimg);
        cnt.add(BorderLayout.CENTER,cnt2);
        /*btn.addActionListener((e)->{
            new EmployeDetailsForm(current,p).show();
        });*/
        
        return cnt;
    }
      
}
    

