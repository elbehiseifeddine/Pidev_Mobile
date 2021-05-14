/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.evenement;

import com.codename1.components.SpanButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.pidev_mobile.gui.formation.FormListFormation;
import com.pidev_mobile.gui.formation.FormListMesParticipations;
import com.pidev_mobile.gui.formation.FormMesFormation;

/**
 *
 * @author ASUS
 */
public class FormAcceuilEvent extends Form {

    public FormAcceuilEvent() {
    setLayout(new BorderLayout());
    Tabs tb = new Tabs() {
    @Override
    protected Component createTab(String title, Image icon) { 
        SpanButton custom = new SpanButton(title);
        custom.setIcon(icon);
        custom.setUIID("Container");
        custom.setTextUIID("Tab");
        custom.setIconPosition(BorderLayout.NORTH);
        custom.setIconUIID("Tab");
        return custom;
    }

    @Override
    protected void setTabSelectedIcon(Component tab, Image icon) {
        ((SpanButton)tab).setPressedIcon(icon); 
    }

    protected void selectTab(Component tab) { 
    }

    @Override
    protected void bindTabActionListener(Component tab, ActionListener l) {
        ((SpanButton)tab).addActionListener(l);
    }
};

tb.setTabUIID(null);
tb.addTab("Mes Events", FontImage.MATERIAL_3D_ROTATION, 4, new FormMesEvents(this));

tb.addTab("Evenements", FontImage.MATERIAL_ACCESS_ALARM, 4, new FormEventList(this));
tb.addTab("Mes Participations", FontImage.MATERIAL_ACCOUNT_BOX, 4, new FormListMesParticipationsE(this));

tb.getTabsContainer().setScrollableX(false); 

this.add(BorderLayout.CENTER, tb);



    }
    
    public void refreshLayout(){
        this.removeAll();
          Tabs tb = new Tabs() {
    @Override
    protected Component createTab(String title, Image icon) { 
        SpanButton custom = new SpanButton(title);
        custom.setIcon(icon);
        custom.setUIID("Container");
        custom.setTextUIID("Tab");
        custom.setIconPosition(BorderLayout.NORTH);
        custom.setIconUIID("Tab");
        return custom;
    }

    @Override
    protected void setTabSelectedIcon(Component tab, Image icon) {
        ((SpanButton)tab).setPressedIcon(icon); 
    }

    protected void selectTab(Component tab) { 
    }

    @Override
    protected void bindTabActionListener(Component tab, ActionListener l) {
        ((SpanButton)tab).addActionListener(l);
    }
};

tb.setTabUIID(null);
tb.addTab("Mes Events", FontImage.MATERIAL_3D_ROTATION, 4, new FormMesEvents(this).refreshLayoutover(this));

tb.addTab("Evenements", FontImage.MATERIAL_ACCESS_ALARM, 4, new FormEventList(this));
tb.addTab("Mes Participations", FontImage.MATERIAL_ACCOUNT_BOX, 4, new FormListMesParticipationsE(this).refreshLayoutover(this));

tb.getTabsContainer().setScrollableX(false); 

this.add(BorderLayout.CENTER, tb);
this.revalidate();
this.show();
    }
    
    
}
