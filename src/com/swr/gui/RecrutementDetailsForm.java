/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.swr.entities.Recrutement;

/**
 *
 * @author chihe
 */
public class RecrutementDetailsForm extends Form{
    Form current;
    
    public RecrutementDetailsForm(Form previous,Recrutement r)
    {
        current=this;
        setTitle(r.getNom());
        setLayout(BoxLayout.y());
        
        Container cnt=new Container(BoxLayout.x());
        Button btnModifier= new Button("Modifier");
        Button btnSupprimer= new Button("Supprimer"); 
        cnt.addAll(btnModifier,btnSupprimer);
        
        Label lbnom=new Label("Nom et Prenom : "+r.getNom()+" "+r.getPrenom());
        Label lbemail=new Label("Email : "+r.getMail());
        Label lbcin=new Label("Cin : "+r.getCin());
        Label ldateNaissance=new Label("Date Naissance:"+r.getDateNaissance());
        Label lbNumTel=new Label("Numero de telephone : "+r.getNumTel()); 
        
        
        
       
        this.addAll(lbnom,lbemail,lbcin,ldateNaissance,lbNumTel,cnt);
        
        
        btnModifier.addActionListener((e)->{
        new ModifierRecrutement(current, r).show();
        });
        btnSupprimer.addActionListener((em)->{
            com.swr.services.ServicesRecrutement.getInstance().deleteRecrutement(r.getId());
        previous.showBack();
        });
        
       getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());

        
    }
    
    
}
