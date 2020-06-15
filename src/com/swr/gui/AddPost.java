/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
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
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Soulah
 */
public class AddPost extends BaseForm {
       InboxForm previous = new InboxForm();
Resources theme;
private String im ;

    ServicePosts sP=new ServicePosts();
    public AddPost(Resources theme,Form previous){
          getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previous.showBack() );
    
    
    setTitle("Add a new Post");
        setLayout(BoxLayout.y());
        
        TextField tfcontenu = new TextField("","Subject of the post ");
        TextField tfcontenu1 = new TextField("","Description");
         //TextField tfcontenu2 = new TextField("","URL IMAGE");
         Button ib= new Button("upload an image");
        Button btnValider = new Button("Add a Post");
        
        
       ib.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ev) {
                   
                    

                   try {
                        
                          
                     String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost:8080/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want

                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    im =fileNameInServer ;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                }
                }
            });
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfcontenu.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Posts p = new Posts(tfcontenu.getText(),tfcontenu1.getText(),im);
                        if( ServicePosts.getInstance().addPost(p))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfcontenu,tfcontenu1,btnValider,ib);
    }
    
}
