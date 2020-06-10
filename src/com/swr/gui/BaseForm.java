/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.swr.gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.swr.entities.SessionUser;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    Form instance;
Resources theme;
    
   public BaseForm(){};
    
    public void installSidemenu(Resources res) {
       
        Image selection = res.getImage("line.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        
         // spacer
        
        getToolbar().addComponentToSideMenu(new Label(res.getImage("admin.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Connecté en tant que "+SessionUser.loggedUser.getUsername(), "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        
        Button inboxButton = new Button("Home", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton);
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        getToolbar().addComponentToSideMenu(inbox);
        
       // getToolbar().addCommandToSideMenu("Stat", statsImage, e -> new StatsForm(res).show());
       // getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
         
        getToolbar().addCommandToSideMenu("Employes", null, e -> {new Employees(this,theme).show();});
        getToolbar().addCommandToSideMenu("Produits", null, e -> {new allProducts(this,theme).show();});
        getToolbar().addCommandToSideMenu("Blog", null, e -> {
              new BlogForm(theme,this).show();
            
});
    
        getToolbar().addCommandToSideMenu("Log out", null, e -> {new SignInForm().show();});
        
       
        
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
