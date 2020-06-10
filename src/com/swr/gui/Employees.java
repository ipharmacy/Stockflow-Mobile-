/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.Employe;

/**
 *
 * @author chihe
 */
public class Employees extends BaseForm { 
    Form current; 
    Resources theme= UIManager.initFirstTheme("/theme");;   
    
    public Employees(Form f,Resources r)
    {
        current=this;
          setTitle("Employes"); 
          getToolbar().addMaterialCommandToLeftBar(""
                 , FontImage.MATERIAL_ARROW_BACK
                 ,e -> f.showBack() ); 
          
       for(Employe e:com.swr.services.ServicesEmploye.getInstance().getAllEmployes())
        { 
            this.add(setEmploye(e));
        } 

  
       getToolbar().addCommandToOverflowMenu("Demande d'emploi", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new DemandeDemploi(current).show();   
            }
        }); 
       
         getToolbar().addCommandToOverflowMenu("Consulter Demande d'emploi", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new ConsulterDemandeDemploi(current).show();   
            }
        }); 
      
           
        
    } 
    
      private Container setEmploye(Employe em)
    {
        Container cnt=new Container(BoxLayout.x());
        Label lbnom=new Label(em.getNom());
        ImageViewer lbimg=new ImageViewer();
    
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("load.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                em.getPhoto()
                , "http://127.0.0.1/untitled3/web/images/"+em.getPhoto());   
        lbimg.setImage(uRLImage);
        
        Button btn=new Button();
        
        cnt.addAll(lbimg,lbnom,btn);
        
        btn.addActionListener((e)->{
            new EmployeDetailsForm(current,em).show();
        });
        
        
        cnt.setLeadComponent(btn);
        return cnt;
    }
    
    
    
    
}
