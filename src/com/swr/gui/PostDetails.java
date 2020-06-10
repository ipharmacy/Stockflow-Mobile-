/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
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
public class PostDetails extends BaseForm{
    
    Form instance;
Resources theme;
ArrayList<Posts> posts=ServicePosts.getInstance().getAllPosts();
    public PostDetails(Resources theme,Form previous,int x){
         instance = this ;
        // ServicePosts.getInstance().ViewsPost(posts.get(x));
     setTitle("Blog");
        setLayout(BoxLayout.y());
    

       getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previous.showBack() );
                 ImageViewer img = new ImageViewer();
                 

        int mm = Display.getInstance().convertToPixels(200);

  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 600), false);
 
    URLImage uRLImage = URLImage.createToStorage(placeholder,
                 posts.get(x).getType()
                , "http://127.0.0.1/stockflowWEB/web/images/"+"k.jpg"); 
     
       img.setImage(uRLImage);
         SpanLabel sp = new SpanLabel();
        sp.setText(posts.get(x).getDesciption());
        ShareButton sb=new ShareButton();
        sb.setText("Invitez vos amis");
        sb.setTextToShare("Bonjour, je t’invite à\n" +
        "à partager cette publication avec vos proches  ");
        
        /*Button like=new Button("Like");
        Button Comment=new Button("Comment");*/
        Button dlt=new Button("Delete");
        Button mdy=new Button("Edit");
        
       /*  like.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
             // ServicePosts.getInstance().LikesPost(posts.get(x));
                
             }
         });*/
 
        
        mdy.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
                 new ModifyPost(theme, instance,posts.get(x)).show();

                
             }
         });
 
 
        dlt.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
                 ServicePosts.getInstance().DeletePost(posts.get(x));
                 new BlogForm(theme, instance).show();

                
             }
         });
        
       add(img);
       Container c1=new Container(BoxLayout.x());
       c1.add(dlt).add(mdy);
       add(sp).add(sb).add(c1);
  
    }
}
