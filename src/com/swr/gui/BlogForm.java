/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.Posts;
import com.swr.services.ServicePosts;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Soulah
 */
public class BlogForm extends BaseForm {
   
    Form instance;
Resources theme;
ArrayList<Posts> posts=ServicePosts.getInstance().getAllPosts();


 
public BlogForm(Resources theme,Form p){
    instance = this ;
     setTitle("Blog");
       getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> p.showBack() );
    
     

      getToolbar().addCommandToOverflowMenu("Add Post", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new AddPost(theme,instance).show();
                
           
            }
        });
      
      
      ArrayList<Map<String, Object>> data = new ArrayList<>();
        for( int i=0;i<ServicePosts.getInstance().getAllPosts().size();i++){
         
            String x =   ServicePosts.getInstance().getAllPosts().get(i).getSujet();
            String x1 =  ServicePosts.getInstance().getAllPosts().get(i).getDesciption();
            int mm = Display.getInstance().convertToPixels(3);

            
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
 
    URLImage uRLImage = URLImage.createToStorage(placeholder,
              ServicePosts.getInstance().getAllPosts().get(i).getType()
                , "http://localhost:8080/stockflowWEB/web/uploads/products/"+ServicePosts.getInstance().getAllPosts().get(i).getType()); 
          

  data.add(createListEntry(x,x1,uRLImage));
        }

DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
MultiList ml = new MultiList(model);

ml.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
           
           new PostDetails(theme,instance, ml.getSelectedIndex()).show();
         // Dialog.show("Alert",  ml.getSelectedItem().toString(), new Command("OK"));

        }
    });


/*
ml.addLongPressListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {
            
            ServicePosts.getInstance().DeletePost(posts.get(ml.getSelectedIndex()));
                 new BlogForm(theme, instance).show();
        }
    } );
*/
      
    add(ml);
    
 /*
 
 
     
        for( int i=0;i<ServicePosts.getInstance().getAllPosts().size();i++){
            SpanLabel sp = new SpanLabel();
        sp.setText(ServicePosts.getInstance().getAllPosts().get(i).toString());
        add(sp);
        }*/
        
}

private Map<String, Object> createListEntry(String x,String x1,Image u) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1",x);
    entry.put("Line2",x1);
    entry.put("Line3",u);
   
    return entry;
}

    
    
    
}