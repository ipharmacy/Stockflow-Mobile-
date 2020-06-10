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
import com.codename1.ui.spinner.Picker;
import com.swr.entities.Employe;

/**
 *
 * @author chihe
 */
public class EmployeModifierForm extends Form{
       Employe emp=new Employe();

    public EmployeModifierForm(Form previous,Employe e)
    {
        setTitle("Modifier les données de : "+e.getNom()+e.getPrenom());
        
                for(Employe em:com.swr.services.ServicesEmploye.getInstance().getAllEmployes())
                 {
                if(em.getPrenom().equals(e.getPrenom()))
                {
                    emp=em;
                }
                }
                System.out.println(emp.getId());
        
        
        
        TextField name=new TextField("","nom",20,TextArea.ANY);
        TextField prenom=new TextField("","prenom",20,TextArea.ANY);
        TextField mail=new TextField("","mail",20,TextArea.ANY);
        TextField cin=new TextField("","cin",20,TextArea.ANY);
        TextField numTel=new TextField("","Numero Telephone",20,TextArea.ANY);
        Label ldate=new Label("Date de naissance");
        Picker datePicker=new Picker();
        TextField Poste=new TextField("","Poste",20,TextArea.ANY);
        TextField salaire=new TextField("","Salaire",20,TextArea.ANY);
        Button btnModif=new Button("Modifier");
        this.addAll(name,prenom,mail,cin,numTel,Poste,salaire,btnModif);
        btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {                        
                    try{
                        emp.setNom(name.getText());
                        emp.setPrenom(prenom.getText());
                        emp.setNumTel(Integer.parseInt(numTel.getText()));
                        emp.setPoste(Poste.getText());
                        emp.setSalaire(Float.parseFloat(salaire.getText()));
                        emp.setMail(mail.getText());
                        emp.setCin(Integer.parseInt(cin.getText()));
                        
                   if(new com.swr.services.ServicesEmploye().uppEmploye(emp))
                    Dialog.show("Success","Connection accepted",new Command("ok"));
                    else
                    Dialog.show("ERROR","Server error",new Command("ok"));
                    }catch(NumberFormatException e)
                    {
                        Dialog.show("ERROR","Status must be a number",new Command("ok"));
                    }
                
            }
        });
        
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,em->previous.showBack());
    }
}
