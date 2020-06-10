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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.Employe;

/**
 *
 * @author chihe
 */
public class EmployeDetailsForm extends Form{
        Resources theme= UIManager.initFirstTheme("/theme");
        Form current;

    public EmployeDetailsForm(Form previous,Employe e)
    {
        current=this;
        setTitle("Employe : "+e.getNom()+" "+e.getPrenom());
        setLayout(BoxLayout.y());
        System.out.println(e.getPhoto());
        
        ImageViewer lbimg=new ImageViewer();
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("load.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                e.getPhoto()
                , "http://127.0.0.1/untitled3/web/images/"+e.getPhoto());   
        lbimg.setImage(uRLImage);
        Label lnumTel=new Label("Telephone :"+e.getNumTel()); 
        Label lposte=new Label("Poste :"+e.getPoste()); 
        Label lemail=new Label("Email :"+e.getMail());
        Button btnContacter= new Button("Contacter");
        Container cnt=new Container(BoxLayout.x());
        cnt.addAll(btnContacter);
        this.addAll(lbimg,lnumTel,lposte,lemail,cnt);
        btnContacter.addActionListener((em)->{
            new sendMailToEmploye(current,e).show();
        });
        
    

        

        
        

    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,em->previous.showBack());
        
    }
    
}
