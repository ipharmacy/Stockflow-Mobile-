/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
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
import com.swr.entities.Recrutement;


/**
 *
 * @author chihe
 */
public class ConsulterDemandeDemploi extends Form{
    Form current;
   Recrutement rec=new Recrutement();

    public ConsulterDemandeDemploi(Form previous)
    { 
        current=this;
        setTitle("Votre demande d'emploi");
        Label lbnom=new Label("Nom : ");
        TextField name=new TextField("","",20,TextArea.ANY);
        Label lbprenom=new Label("Prenom :");
        TextField prenom=new TextField("","",20,TextArea.ANY); 
        Button btnRecherche=new Button("Rechercher");
        
        btnRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
              
                    try{
                     for(Recrutement r:com.swr.services.ServicesRecrutement.getInstance().getAllRecrutements())
                     {
                         if ((r.getNom().equals(name.getText()))&&(r.getPrenom().equals(prenom.getText())))
                         {
//                             current.add(setRecrutement(r));
                               rec=r;
                         }
                     } 
                     if (rec==null)
                     {
                           Dialog.show("Demande Inexistante","Saisis des données valides",new Command("ok"));
                     }
                     else {
                         new RecrutementTrouve(current, rec).show();
                     }
                    }catch(NumberFormatException e)
                    {
                        Dialog.show("ERROR","Status must be a number",new Command("ok"));
                    }
            }
        });
        

  
        this.addAll(lbnom,name,lbprenom,prenom,btnRecherche);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
    }

    
       
    private Container setRecrutement(Recrutement r)
    {
        Container cnt=new Container(BoxLayout.y());
        Label lbnom=new Label("Nom : "+r.getNom());
        Label lbprenom=new Label("Prenom : "+r.getPrenom());
        Label lbmail=new Label("Email : "+r.getMail());
        Label lbcin=new Label("Cin : "+String.valueOf(r.getCin()));
//        Label lbdate=new Label("Date de naissance : "+r.getDateNaissance().toString());
        Label lbnum=new Label("Numero de telephone : "+String.valueOf(r.getNumTel()));
        String str;
        
        if (r.getEtat()==0)
        {
            str="Demande non traitée";
        }else
        {
            str="Demande acceptée";
        }
        
        Label lbetat=new Label(str);
        Button btn=new Button(); 

        
        
        cnt.addAll(lbnom,lbprenom,lbmail,lbcin,lbnum,lbetat,btn);
        
        btn.addActionListener((e)->{
                new RecrutementDetailsForm(current, r).show();
        });
        cnt.setLeadComponent(btn);
             
        return cnt;
    }




    
}
