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
import com.codename1.ui.spinner.Picker;
import com.swr.entities.Recrutement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author chihe
 */
public class DemandeDemploi extends Form{
    
    public DemandeDemploi(Form previous)
    {
        setTitle("Demande d'emploi"); 
        setLayout(BoxLayout.y());
        Label lbname=new Label("Nom :");
        TextField name=new TextField("","",20,TextArea.ANY);
        Label lbprenom=new Label("Prenom : "); 
        TextField prenom=new TextField("","",20,TextArea.ANY);
        Label lbmail=new Label("Email :");
        TextField mail=new TextField("","",20,TextArea.ANY);
        Label lbcin=new Label("Cin : ");
        TextField cin=new TextField("","",20,TextArea.ANY); 
        Label ldate=new Label("Date de naissance : ");
        Picker datePicker=new Picker(); 
        Label lbnumTel=new Label("Numero de telephone : ");
        TextField numTel=new TextField("","",20,TextArea.ANY); 
        Button btnAjout=new Button("Valider");
        SimpleDateFormat formatter6=new SimpleDateFormat("dd/MM/yyyy"); 
        
           btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
              
                    try{
                    String Dateee=removeSpecificChars(datePicker.getText(),"/");
                    System.out.println(Dateee); 
                    Date date6=datePicker.getDate();
                    Recrutement r = new Recrutement(Integer.parseInt(cin.getText()),Integer.parseInt(numTel.getText()),name.getText(),prenom.getText(),mail.getText(),Dateee);
                    if(new com.swr.services.ServicesRecrutement().addRecrutement(r))
                    Dialog.show("Success","Connection accepted",new Command("ok"));
                    else
                    Dialog.show("ERROR","Server error",new Command("ok"));
                    }catch(NumberFormatException e)
                    {
                        Dialog.show("ERROR","Status must be a number",new Command("ok"));
                    }
            }
        });
        
        this.addAll(lbname,name,lbprenom,prenom,lbmail,mail,lbcin,cin,ldate,datePicker,lbnumTel,numTel,btnAjout);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
        
    }
    
     public static String removeSpecificChars(String originalstring ,String removecharacterstring)
    {
        char[] orgchararray=originalstring.toCharArray();
        char[] removechararray=removecharacterstring.toCharArray();
        int start,end=0;
        
        //tempBoolean automatically initialized to false ,size 128 assumes ASCII
        boolean[]  tempBoolean = new boolean[128];
        
        //Set flags for the character to be removed
        for(start=0;start < removechararray.length;++start)
        {
            tempBoolean[removechararray[start]]=true;
        }
        
        //loop through all characters ,copying only if they are flagged to false
        for(start=0;start < orgchararray.length;++start)
        {
            if(!tempBoolean[orgchararray[start]])
            {
                orgchararray[end++]=orgchararray[start];
            }
        }
        
        
        return new String(orgchararray,0,end);
    }

    
    
    
}
