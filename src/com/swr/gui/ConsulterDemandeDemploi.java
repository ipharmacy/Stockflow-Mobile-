/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.swr.entities.Recrutement;
import java.util.ArrayList;


/**
 *
 * @author chihe
 */
public class ConsulterDemandeDemploi extends Form{
    Form current;
    Recrutement rec=new Recrutement();

    public ConsulterDemandeDemploi(Form previous,String prenom,String nom)
    { 
        current=this;
        setTitle("Vos demandes d'emploi");
         for(Recrutement r:com.swr.services.ServicesRecrutement.getInstance().getAllRecrutements())
        { 
            if (r.getNom().equals(nom) && r.getPrenom().equals(prenom))
            {
                this.add(setRecrutement(r));
            }
        }  
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
    } 
    
      private Container setRecrutement(Recrutement r)
    {
        Style LabelIcon = UIManager.getInstance().getComponentStyle("Label");
        Style LabelEtat = UIManager.getInstance().getComponentStyle("Label");
           int test=0;
        LabelIcon.setFgColor(ColorUtil.rgb(0,0,0));
        Container cnt=new Container(BoxLayout.y());
        Label lbnom=new Label("Nom : "+r.getNom(),FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION,LabelIcon));
        Label lbprenom=new Label("Prenom : "+r.getPrenom(),FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION,LabelIcon));
        Label lbmail=new Label("Email : "+r.getMail(),FontImage.createMaterial(FontImage.MATERIAL_CONTACT_MAIL,LabelIcon));
        Label lbcin=new Label("Cin : "+String.valueOf(r.getCin()),FontImage.createMaterial(FontImage.MATERIAL_PERM_IDENTITY,LabelIcon));
//        Label lbdate=new Label("Date de naissance : "+r.getDateNaissance().toString());
        Label lbnum=new Label("Numero de telephone : "+String.valueOf(r.getNumTel()),FontImage.createMaterial(FontImage.MATERIAL_SETTINGS_PHONE,LabelIcon));
        String str;
        
        if (r.getEtat()==0)
        {
            str="Demande non traitée";
            LabelEtat.setFgColor(ColorUtil.rgb(255,0,0));

        }else
        {
            str="Demande acceptée";
            LabelEtat.setFgColor(ColorUtil.rgb(0,128,0));
            test=1;
        }
         Label lbetat=new Label();
         
         if (test==0){
             lbetat.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DONUT_LARGE,LabelEtat));
        }else
         {
             lbetat.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DONE_OUTLINE,LabelEtat));
         }
         
         lbetat.setText(str);
        
        Button btn=new Button(); 
        cnt.addAll(lbnom,lbprenom,lbmail,lbcin,lbnum,lbetat,btn);
        btn.addActionListener((e)->{
                new RecrutementDetailsForm(current, r).show();
        });
        cnt.setLeadComponent(btn);
             
        return cnt;
    } 
}
