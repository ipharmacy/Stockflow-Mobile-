/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.swr.entities.Employe;


/**
 *
 * @author chihe
 */
public class sendMailToEmploye extends Form{
    public sendMailToEmploye(Form previous,Employe e)
    {
        
        
        
        setTitle("Soyez Interactive avec les Employes");
        setLayout(BoxLayout.y());
        Label lbEmailEmploye=new Label("L'adresse du destinataire : ");
        TextField EmailEmployee=new TextField(e.getMail(),"",20,TextArea.ANY);
        Label lbEmailSender=new Label("Votre adresse : "); 
        TextField EmailSender=new TextField("","",20,TextArea.ANY); 
        Label lbsujet =new Label("Sujet : ");
        TextField sujet=new TextField("","",20,TextArea.ANY);
        Label lbmessage=new Label("Message : ");
        TextField message=new TextField("","",20,TextArea.ANY);
        Button btnSend=new Button("Envoyer"); 
        this.addAll(lbEmailEmploye,EmailEmployee,lbEmailSender,EmailSender,lbsujet,sujet,lbmessage,message,btnSend);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,em->previous.showBack());


      btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    try{
                    if(new com.swr.services.ServicesRecrutement().SendMailEmploye(sujet.getText(), EmailSender.getText(),EmailEmployee.getText(),message.getText()))
                    Dialog.show("Success","Connection accepted",new Command("ok"));
                    else
                    Dialog.show("ERROR","Server error",new Command("ok"));
                    }catch(NumberFormatException e)
                    {
                        Dialog.show("ERROR","Status must be a number",new Command("ok"));
                    }
            }
        });
            
        
    }
    
    
    
}
