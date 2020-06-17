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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.Employe;

/**
 *
 * @author chihe
 */
public class EmployesSortedByLastName extends BaseForm{
    Form current; 
    Resources theme= UIManager.initFirstTheme("/theme");;   

    public EmployesSortedByLastName(Form previous)
    {
        current=this;
        for(Employe e:com.swr.services.ServicesEmploye.getInstance().getAllEmployesSortedByLastName())
        { 
            this.add(setEmploye(e));
        } 
        
         setTitle("Employes"); 
        getToolbar().addMaterialCommandToLeftBar(""
                 , FontImage.MATERIAL_ARROW_BACK
                 ,e -> previous.showBack() ); 
        
        getToolbar().addCommandToOverflowMenu("Demande d'emploi", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new DemandeDemploi(current).show();   
            }
        }); 
       
         getToolbar().addCommandToOverflowMenu("Consulter Demande d'emploi", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new ConsulterDemandeDemploi(current,"chiheb","mhamdi").show();   
            }
        }); 

        
    }
  
      private Container setEmploye(Employe em)
    {
        Container cnt=new Container();
        Label lbnom=new Label(em.getNom());
        Label lbprenom=new Label(em.getPrenom());
        Label lbposte=new Label(em.getPoste());
        Container cnnt=new Container(BoxLayout.x());
        cnnt.addAll(lbnom,lbprenom);
        Container cnnnt=new Container(BoxLayout.y());
        cnnnt.addAll(cnnt,lbposte);
        ImageViewer lbimg=new ImageViewer();
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("load.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                em.getPhoto()
                , "http://127.0.0.1/untitled3/web/images/"+em.getPhoto());   
        lbimg.setImage(uRLImage);
        
        Button btn=new Button();
        
        cnt.add(FlowLayout.encloseLeftMiddle(lbimg));
        cnt.add(FlowLayout.encloseMiddle(cnnnt));
        
        btn.addActionListener((e)->{
            new EmployeDetailsForm(current,em).show();
        });
        
        
        cnt.setLeadComponent(btn);
        return cnt;
    }
    
    
    
}
