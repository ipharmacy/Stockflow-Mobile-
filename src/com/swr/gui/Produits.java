/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.swr.entities.SessionUser;
import com.swr.entities.produit;
import com.swr.services.ServiceProduit;
import java.util.ArrayList;
import jdk.nashorn.internal.objects.NativeString;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Dialog;
import java.io.IOException;

/**
 *
 * @author Dhia
 */
public class Produits extends BaseForm{
    Form current; 
    Resources theme= UIManager.initFirstTheme("/theme");
    Database db;
    static Form f;
    
    Produits(Form previous,Resources theme) {
       
        current=this;
        f=this;
          setTitle("Produits"); 
         getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack() ); 
          
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> new AddProduit(theme).show());
        fab.bindFabToContainer(this.getContentPane());
        
       for(produit p:com.swr.services.ServiceProduit.getInstance().getAllproduits(SessionUser.loggedUser.getId()))
        { 
            this.add(setProduit(p));
           
        } 


       getToolbar().addCommandToOverflowMenu("Statistique produits", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new statProd(current,theme).show();
            }
        }); 
       
         getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new SignInForm().show();
            }
        });     
    
           
        
    } 
    
      private Container setProduit(produit p)
    {
        Container cnt=new Container(new BorderLayout());
        Container cnt2= new Container(BoxLayout.y());
        
        Label lbnom=new Label(""+p.getNom());
        Label lbprix=new Label("Prix :"+p.getPrix()+" dt");
        
        Label lbdate=new Label("publié le "+ p.getDate().substring(0,10));
        Label lbcategory=new Label("Categorie :"+p.getIdCategorie().get("nom"));
        Label lbquant=new Label("Quantite :"+p.getQuantite());
        
        
        
        ImageViewer lbimg=new ImageViewer();
        
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("load.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,p.getImage_name(),"http://localhost:8080/StockflowWEB/web/uploads/products/"+p.getImage_name()); 
        lbimg.setImage(uRLImage);
        
       
        Container supedit = new Container(BoxLayout.x());
        Label supp = new Label("supprimer");
        supp.addPointerPressedListener((e)->{
            com.swr.services.ServiceProduit SP = new ServiceProduit();
            if(ServiceProduit.getInstance().deleteProduit(p.getId_produit())){
                Produits refresh = new Produits(this,theme);
                refresh.show();
            }
        });
        
        Label edit = new Label("modifier");
        edit.addPointerPressedListener((e)->{
            EditProduit ep = new EditProduit(theme, p);
            ep.show();
        });
         Label ajouter = new Label("Ajouter au panier");
        ajouter.addPointerPressedListener((e)->{
     
            
              try {
                boolean created = false;
                created = Database.exists("Pann.db");
                
                db = Database.openOrCreate("Pann.db");
                
                if (created == false) {
                    db.execute("create table Pann (id INTEGER PRIMARY KEY AUTOINCREMENT,idproduit INTEGER  ,quantite INTEGER  , nom TEXT )");
                }
                 Cursor result = db.executeQuery("select * from Pann where idproduit = "+p.getId_produit());
                 
                  int i = 0 ;
                  int a =0 ;
                  boolean exist = false;
            
                  
         while (result.next()) {
             Row r = result.getRow();
             
           
           
            if ( (int)Double.parseDouble(r.getString(1)) == p.getId_produit() )
            {
                a = (int)Double.parseDouble(r.getString(2))+1;
                System.out.println( a);
                db.execute("update  Pann set quantite = "+a+" where idproduit ="+p.getId_produit());
                exist =true ;
            }
            
 
               
             
         }
         if (!exist)
             db.execute("insert into Pann (idproduit,quantite,nom) values ('"+p.getId_produit()+"','1','"+p.getNom()+"')");
                 
                  System.out.println(p.getNom());
                 
              Dialog.show("Info", "Produit ajouté au panier!", "ok", null);
            } catch (IOException ex) {
                ex.printStackTrace();
               
            }
        });
        
      
        
        
        
      
        
        
       
        
        supedit.addAll(supp,edit,ajouter);
        cnt2.addAll(lbnom,lbprix,lbdate,lbcategory,lbquant,supedit);
        cnt.add(BorderLayout.WEST,lbimg);
        cnt.add(BorderLayout.CENTER,cnt2);
        /*btn.addActionListener((e)->{
            new EmployeDetailsForm(current,p).show();
        });*/
        
        return cnt;
    }
      
}
    

