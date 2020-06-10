/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

import  com.swr.entities.Entrepot;
import  com.swr.services.Entrepot_Service;

/**
 *
 * @author HP
 */
public class addEntrpot extends Form {

  
         Resources theme;

    public addEntrpot() {
        
        super("add_entrepots", BoxLayout.y());
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new listEntrepot().show();
        });
        TextField Libelle = new TextField("", "Nom", 20, TextArea.ANY);
        TextField Description = new TextField("", "Adresse", 20, TextArea.ANY);
        TextField Couleur = new TextField("", "Largitude", 20, TextArea.ANY);
        TextField etat = new TextField("", "Longitude", 20, TextArea.ANY);
        TextField type = new TextField("", "Etat", 20, TextArea.ANY);
        
       

        this.add("Nom : ").add(Libelle);
        this.add("Adresse : ").add(Description);
        this.add("Largitude : ").add(Couleur);
        this.add("Longitude : ").addAll(etat);
        this.add("Etat : ").add(type);
        

        this.addComponent(new SpanLabel("Drag your photo here"));
        if (DropTarget.isSupported()) {
            
            DropTarget dnd = DropTarget.create((evt) -> {

                String srcFile = (String) evt.getSource();
                System.out.println("Src file is " + srcFile);

                System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                if (srcFile != null) {
                    try {
                        Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);

                        String nomImage = srcFile.substring(19, srcFile.length());

                        this.add(img);
                        Button btn = new Button("Submit");
                        this.add(btn);

                        this.revalidate();

                        btn.addActionListener(l
                                -> {

                            if (Description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Description ", "OK", null);

                            } else if (Libelle.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Libelle ", "OK", null);

                            } else {
                             
                                Entrepot ab = new Entrepot(Libelle.getText(), Integer.valueOf(Couleur.getText()),Integer.valueOf(etat.getText()),type.getText() , Description.getText(),nomImage);

                                if (new Entrepot_Service().addEntrepot(ab) == true) {
                                    Dialog.show("Ajout Entrepot", "ajout aves success ", "OK", null);

                                } else {
                                    Dialog.show("Erreur", "Erreur ajout ", "OK", null);
                                }

                            }

                        }
                        );

                    } catch (IOException ex) {
                      
                    }

                }

            }, Display.GALLERY_IMAGE);
        }
        this.show();
    }
}
