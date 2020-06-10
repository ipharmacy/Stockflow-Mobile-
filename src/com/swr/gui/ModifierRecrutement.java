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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.swr.entities.Recrutement;

/**
 *
 * @author chihe
 */
public class ModifierRecrutement extends Form{
 Recrutement Rec=new Recrutement();
            public ModifierRecrutement(Form previous,Recrutement r)
        {
                 setTitle("Modifier Recrutement"); 
                    setLayout(BoxLayout.y());
       
            for(Recrutement re:com.swr.services.ServicesRecrutement.getInstance().getAllRecrutements())
                 {
                if(re.getCin()==r.getCin())
                {
                 Rec=re;   
                }
                }
                System.out.println(Rec.getId());
                
        TextField name=new TextField(r.getNom(),"nom",20,TextArea.ANY);
        TextField prenom=new TextField(r.getPrenom(),"prenom",20,TextArea.ANY);
        TextField mail=new TextField(r.getMail(),"mail",20,TextArea.ANY);
        TextField cin=new TextField(""+r.getCin(),"cin",20,TextArea.ANY);
        TextField numTel=new TextField(""+r.getNumTel(),"Numero Telephone",20,TextArea.ANY);
        Picker datePicker=new Picker(); 
        Button btnModif=new Button("Modifier");
        this.addAll(name,prenom,mail,cin,datePicker,numTel,btnModif);
        
         btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {                        
                    try{
                        String Dateee=removeSpecificChars(datePicker.getText(),"/");
                        Rec.setNom(name.getText());
                        Rec.setPrenom(prenom.getText());
                        Rec.setNumTel(Integer.parseInt(numTel.getText()));
                        Rec.setMail(mail.getText());
                        Rec.setCin(Integer.parseInt(cin.getText()));
                        Rec.setEtat(r.getEtat());
                        Rec.setDt(Dateee); 
                   if(new com.swr.services.ServicesRecrutement().uppRecrutement(Rec))
                    Dialog.show("Success","Connection accepted",new Command("ok"));
                    else
                    Dialog.show("ERROR","Server error",new Command("ok"));
                    }catch(NumberFormatException e)
                    {
                        Dialog.show("ERROR","Status must be a number",new Command("ok"));
                    }
                
            }
        });
                               
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
