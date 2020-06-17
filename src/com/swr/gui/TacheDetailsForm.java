/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.Tache;
import com.swr.services.ServicesTache;
import java.util.Date;

/**
 *
 * @author chihe
 */
public class TacheDetailsForm extends Form{
       Resources theme= UIManager.initFirstTheme("/theme");
       Form current;
    public TacheDetailsForm(Form previous,Tache t)
    {
        current=this;
        Style LabelEtat = UIManager.getInstance().getComponentStyle("Label");
        Style LabelIcon = UIManager.getInstance().getComponentStyle("Label");
        int test=0;
        int testt=0;
        setTitle("Tache : "+t.getCommentaire());
        setLayout(BoxLayout.y());
        Label lbcommentaire=new Label("Commentaire : "+t.getCommentaire(),FontImage.createMaterial(FontImage.MATERIAL_ASSIGNMENT,LabelIcon));
        Label lbdateAttribution=new Label("Date Attribution : "+t.getDateAttribution(),FontImage.createMaterial(FontImage.MATERIAL_CALENDAR_TODAY,LabelIcon));
        Label lbdateLimit=new Label("Date Limite : "+t.getDateLimite(),FontImage.createMaterial(FontImage.MATERIAL_CALENDAR_TODAY,LabelIcon));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = new Date();  
        System.out.println(formatter.format(date));  
        
        
        String Str="";

        
        
       if ((t.getEtat()==0)&&(formatter.format(date).compareTo(formatter.format(t.getDateLimite())) > 0))
        {
            Str=Str+"Tache ratée ";
            LabelEtat.setFgColor(ColorUtil.rgb(255,0,0));
            testt=1;
        }
        else if (t.getEtat()==1)
        {
            test=1;
            Str=Str+"Tache effectuee";
            LabelEtat.setFgColor(ColorUtil.rgb(0,128,0));
        }
        else if((t.getEtat()==0)&&(formatter.format(date).compareTo(formatter.format(t.getDateLimite())) < 0))
        {
            Str=Str+"Tache en cours ";
            LabelEtat.setFgColor(ColorUtil.rgb(255,140,0));
        }
        
        Label lbetat=new Label(); 
         
         if (test==0){
             lbetat.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DONUT_LARGE,LabelEtat));
        }else
         {
             lbetat.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DONE_OUTLINE,LabelEtat));
         }
         
        lbetat.setText(Str);
        
        Style buttonIcon = UIManager.getInstance().getComponentStyle("Button");
        Button btnValider=new Button();
        if (t.getEtat()==0 && testt==0 )
        {
            buttonIcon.setFgColor(ColorUtil.rgb(255, 255, 255));
            btnValider.setIcon(FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE,buttonIcon));
            btnValider.setText("Valider");
            btnValider.addActionListener((em)->{
           com.swr.services.ServicesTache.getInstance().validerTache(t.getCommentaire());
            previous.showBack();
            });  
        }
        else if(t.getEtat()==1)
        {
            buttonIcon.setFgColor(ColorUtil.rgb(255, 255, 255));    
            btnValider.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE_FOREVER,buttonIcon));
            btnValider.setText("Supprimer");
            btnValider.addActionListener((em)->{
            com.swr.services.ServicesTache.getInstance().deleteTache(t.getId());
            previous.showBack();
            });            
         }
        else if (t.getEtat()==0 && testt==1)
        {
            btnValider.setText("Supprimer");
            btnValider.addActionListener((em)->{
            com.swr.services.ServicesTache.getInstance().deleteTache(t.getId());
            previous.showBack();
            });         
        }
        Container cnt1=new Container(BoxLayout.x());
        
        Label lbnomEmp=new Label(t.getNomEmploye());
        Label lbprenom=new Label(t.getPrenomEmploye());
        Label lbdateDateLimite=new Label(t.getDateLimite().toString(),FontImage.createMaterial(FontImage.MATERIAL_CALENDAR_TODAY,LabelIcon));
        this.addAll(lbcommentaire,lbetat,lbdateAttribution,lbdateDateLimite,btnValider);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,em->previous.showBack());
    }
    
}
