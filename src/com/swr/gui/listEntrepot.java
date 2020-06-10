/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import  com.swr.entities.Entrepot;
import  com.swr.services.Entrepot_Service;

/**
 *
 * @author HP
 */
public class listEntrepot extends Form {
    
     Resources theme;

    public listEntrepot() {
        super("Entrepot list", BoxLayout.y());

        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar().addCommandToRightBar("new", null, (evt) -> {
            new addEntrpot().show();
        });
        this.getToolbar().addCommandToRightBar("stat", null, (evt) -> {
            new StatEntrepot().createPieChartForm("Entrepot", new Entrepot_Service().getStat());
        });
        System.out.println("bbb");
       
        for (Entrepot c : new Entrepot_Service().getAllentrepots()) {
           
            this.add(addItem(c));
        }
    }

      public Container addItem(Entrepot c) {

       
             System.out.println("id"+c.getId());    
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(c.getNom());
        Button btn=new Button(c.getNom());
          String url = "http://localhost/StockflowWEB/web/uploads/images/"+c.getImage();
                ImageViewer imgv;
     Image imge;
     EncodedImage enc;
     enc=EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge=URLImage.createToStorage(enc, url, url);
        imgv=new ImageViewer(imge);
        System.err.println(c.getImage());
        cn2.add(lab).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        cn1.add(BorderLayout.EAST,imgv);
        
        btn.addActionListener(e->{
            
       
          Form  f2=new Form("Details",BoxLayout.y());
          Label  libelle=new Label(c.getNom());
          Label  age=new Label(String.valueOf(c.getAdresse()));
          Label  couleur=new Label(String.valueOf(c.getEtat()));
          Label  etat=new Label(String.valueOf(c.getLargitude()));
          Label  type=new Label(String.valueOf(c.getLongitude()));
          Label  Prix=new Label(String.valueOf(c.getVues()));
          Label  x=new Label(String.valueOf(c.getId()));
          Button btn_supp =new Button("Supprimer");
          Button btn_modifier =new Button("Modifier");

        f2.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            this.show();
        });
            f2.add(x).add(libelle).add(age).add(couleur).add(etat).add(type).add(Prix).add(btn_supp).add(btn_modifier);
           
            f2.show();
             btn_supp.addActionListener(ev
                    -> {
                //id=0
                   if (new Entrepot_Service().removeEntrepot(c.getId())== true) {
                       System.out.println(c.getId());
                                    Dialog.show("Suppression Entrepot", "suppression aves success ", "OK", null);
                                    new listEntrepot().showBack();

                                } else {
                                    Dialog.show("Erreur", "Erreur suppression ", "OK", null);
                                }
         
            }
            );
             btn_modifier.addActionListener(l->{
             
             Form fmodifier = new Form(BoxLayout.y());

                Label modif = new Label("EDIT Entrepot");
            
              
                fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evtx) -> {
                    this.showBack();
                });
                AutoCompleteTextField Libelle = new AutoCompleteTextField(c.getNom());
                Libelle.setMinimumElementsShownInPopup(1);
              
                AutoCompleteTextField Description_mod = new AutoCompleteTextField(c.getAdresse());
                Description_mod.setMinimumElementsShownInPopup(1);
          

                AutoCompleteTextField Prix_mod = new AutoCompleteTextField(String.valueOf(c.getLargitude()));
                Prix_mod.setMinimumElementsShownInPopup(1);
            

                AutoCompleteTextField Age_mod = new AutoCompleteTextField(String.valueOf(c.getLongitude()));
                Age_mod.setMinimumElementsShownInPopup(1);
     

                AutoCompleteTextField etat_mod = new AutoCompleteTextField(String.valueOf(c.getEtat()));
                etat_mod.setMinimumElementsShownInPopup(1);
             

                

   
                Label lib = new Label("NOM");
                fmodifier.add(lib).add(Libelle);

                Label des = new Label("ADRESSE");      
                fmodifier.add(des).add(Description_mod);

                Label ty = new Label("LARGITUDE");
                fmodifier.add(ty).add(Prix_mod);

                Label AGE = new Label("LONGITUDE");
                fmodifier.add(AGE).add(Age_mod);

                Label ETAT = new Label("ETAT");
                fmodifier.add(ETAT).add(etat_mod);

                Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
                PHOTO.setUIID("pass");
                fmodifier.addComponent(PHOTO);

                if (DropTarget.isSupported()) {

                    DropTarget dnd = DropTarget.create((evt) -> {

                        String srcFile = (String) evt.getSource();
                        System.out.println("Src file is " + srcFile);

                        System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                        if (srcFile != null) {
                            try {

                                Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);
                                Button submit = new Button("Submit");
                              
                                String nomImage = srcFile.substring(19, srcFile.length());

                                fmodifier.add(img);

                                fmodifier.add(submit);

                                fmodifier.revalidate();
                                fmodifier.getToolbar().addCommandToLeftBar("Return", null, (evtx) -> {
                                    this.showBack();
                                });

                                submit.addActionListener(lll
                                        -> {
Entrepot ab = new Entrepot(Libelle.getText(), Integer.valueOf(Prix_mod.getText()),Integer.valueOf(Age_mod.getText()),etat_mod.getText() , Description_mod.getText(),nomImage);


                                    if (new Entrepot_Service().ModifierEntrepot(ab, c.getId()) == true) {
                                        Dialog.show("Modifier Entrpot", "edit aves success ", "OK", null);
                                        new listEntrepot().showBack();

                                    } else {
                                        Dialog.show("Erreur", "Erreur Modifier ", "OK", null);
                                    }

                                }
                                );

                            } catch (IOException ex) {
                                Log.e(ex);
                            }

                        }

                    }, Display.GALLERY_IMAGE);
                }

                fmodifier.show();

             
             });
             
              
        
         
        });
        
        cn1.setLeadComponent(btn);
        return cn1;
    }
    
    
    
}
