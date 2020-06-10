/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.swr.entities.Posts;
import com.swr.services.ServicePosts;

/**
 *
 * @author Soulah
 */
public class ModifyPost extends Form{
      Form previous;
Resources theme;
    ServicePosts sP=new ServicePosts();
    public ModifyPost(Resources theme,Form previous,Posts p){
          getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previous.showBack() );
    
    
    setTitle("Modify Post");
        setLayout(BoxLayout.y());
        
        TextField tfcontenu = new TextField("","Edit your post");
        Button btnValider = new Button("Modify");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfcontenu.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Posts a = new Posts(p.getId(),tfcontenu.getText());
                        if( ServicePosts.getInstance().EditPost(a))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfcontenu,btnValider);
    }
    
}
