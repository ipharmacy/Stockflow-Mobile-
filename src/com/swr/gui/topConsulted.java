/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
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

/**
 *
 * @author Dhia
 */
public class topConsulted extends BaseForm{
    Form current; 
    Resources theme= UIManager.initFirstTheme("/theme");
    
   topConsulted(Form previous,Resources theme) {
       
        current=this;

          setTitle("Les plus consultés"); 
          getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack() );
          
        /*FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> new AddProduit(theme).show());
        fab.bindFabToContainer(this.getContentPane());
        */
       for(produit p:com.swr.services.ServiceProduit.getInstance().getTopProduits())
        { 
            this.add(setProduit(p));
           
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
        Label lbnbvue=new Label("Nombre de vu : "+p.getNbvue());
        
        
        ImageViewer lbimg=new ImageViewer();
        
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("load.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,p.getImage_name(),"http://localhost:8080/StockflowWEB/web/uploads/products/"+p.getImage_name()); 
        lbimg.setImage(uRLImage);
        
       
        Container supedit = new Container(BoxLayout.x());
        
        
      
        cnt2.addAll(lbnom,lbprix,lbdate,lbcategory,lbnbvue,supedit);
        cnt.add(BorderLayout.WEST,lbimg);
        cnt.add(BorderLayout.CENTER,cnt2);
        /*btn.addActionListener((e)->{
            new EmployeDetailsForm(current,p).show();
        });*/
        
        return cnt;
    }
      
}
    

