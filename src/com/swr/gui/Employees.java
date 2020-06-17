/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import static com.codename1.ui.layouts.BorderLayout.WEST;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.Employe;
import com.swr.entities.Tache;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author chihe
 */
public class Employees extends BaseForm { 
    Form current; 
    Resources theme= UIManager.initFirstTheme("/theme");
    String role=SignInForm.getUser().getRoles();
    public Employees(Form f,Resources r)
    {
            current=this;
            setTitle("Employes"); 
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e -> f.showBack() ); 
            String username=SignInForm.u.getUsername();
            if(role.equals("ROLE_USER"))
            {
                System.out.println("UTILISATEUR CONNECTE : "+SignInForm.getUser().getUsername());
                 for(Employe e:com.swr.services.ServicesEmploye.getInstance().getAllEmployes())
                 { 
                         this.add(setEmploye(e));
                 } 
          
            getToolbar().addCommandToOverflowMenu("Trier selon Prenom", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new EmployesSortedByLastName(current).show();   
            }
             }); 
            
             getToolbar().addCommandToOverflowMenu("Trier selon Poste", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new EmployesSortedByStatus(current).show();   
            }
             }); 
      
            getToolbar().addCommandToOverflowMenu("Demande d'emploi", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new DemandeDemploi(current).show();   
            }
            }); 
       
            getToolbar().addCommandToOverflowMenu("Consulter Demande d'emploi", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new ConsulterDemandeDemploi(current,username,username).show();   
            }
             }); 
         
            }
            else if(role.equals("ROLE_EMPLOYE"))
            {
                  for(Tache t:com.swr.services.ServicesTache.getInstance().getAllTachesEmploye(username))
            {
                    this.add(setTache(t));
            }
                  System.out.println(" Taches faites ");
            for (Map.Entry<String, Integer> entry : com.swr.services.ServicesTache.getInstance().TachesFaites(30).entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		}System.out.println(" Taches non faites ");
            for (Map.Entry<String, Integer> entry : com.swr.services.ServicesTache.getInstance().TachesNonFaites(30).entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	
                  
                  
            getToolbar().addCommandToOverflowMenu("Statistique des Taches", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt){
             new Statistics(current,username).show();   
            }
             });  
            }
           
        
    } 
    
      private Container setEmploye(Employe em)
    {
        Container cnt=new Container();
        Label lbnom=new Label(em.getNom());
        Label lbprenom=new Label(em.getPrenom());
        Label lbposte=new Label(em.getPoste());
        Container cnnt=new Container(BoxLayout.x());
        cnnt.addAll(lbnom,lbprenom);
        Container cnnnt=new Container(BoxLayout.y());
        cnnnt.addAll(cnnt,lbposte);
        ImageViewer lbimg=new ImageViewer();
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("load.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                em.getPhoto()
                , "http://127.0.0.1/untitled3/web/images/"+em.getPhoto());   
        lbimg.setImage(uRLImage);
        
        Button btn=new Button();
        
        cnt.add(FlowLayout.encloseLeftMiddle(lbimg));
        cnt.add(FlowLayout.encloseMiddle(cnnnt));
        
        btn.addActionListener((e)->{
            new EmployeDetailsForm(current,em).show();
        });
        
        
        cnt.setLeadComponent(btn);
        return cnt;
    }
      
      
        
      private Container setTache(Tache t)
    {
       Style LabelEtat = UIManager.getInstance().getComponentStyle("Label");
        Style LabelIcon = UIManager.getInstance().getComponentStyle("Label");

        Container cnt=new Container(BoxLayout.y());        
        Label lbcommentaire=new Label("Commentaire : "+t.getCommentaire(),FontImage.createMaterial(FontImage.MATERIAL_ASSIGNMENT,LabelIcon));
        Label lbdateAttribution=new Label("Date Attribution : "+t.getDateAttribution(),FontImage.createMaterial(FontImage.MATERIAL_CALENDAR_TODAY,LabelIcon));
        Label lbdateLimit=new Label("Date Limite : "+t.getDateLimite(),FontImage.createMaterial(FontImage.MATERIAL_CALENDAR_TODAY,LabelIcon));
        int test=0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = new Date();  
        System.out.println(formatter.format(date));  
        formatter.format(date);
        String Str="";
        System.out.println((formatter.format(date).compareTo(formatter.format(t.getDateLimite()))));
        
        if ((t.getEtat()==0)&&(formatter.format(date).compareTo(formatter.format(t.getDateLimite())) > 0))
        {
            Str=Str+"Tache ratée ";
            LabelEtat.setFgColor(ColorUtil.rgb(255,0,0));
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
        
         System.out.println(Str);
         
         Label lbetat=new Label(); 
         
         if (test==0){
             lbetat.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DONUT_LARGE,LabelEtat));
        }else
         {
             lbetat.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DONE_OUTLINE,LabelEtat));
         }
         
        lbetat.setText(Str);
        Button btn=new Button();
        cnt.addAll(lbcommentaire,lbetat,lbdateAttribution,lbdateLimit);
        
        btn.addActionListener((e)->{
         new TacheDetailsForm(current,t).show();
        });
        
        cnt.setLeadComponent(btn);
        return cnt;
    }
    
      
      
    
    
    
    
}
