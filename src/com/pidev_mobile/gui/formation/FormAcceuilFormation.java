/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.formation;

import com.codename1.components.ButtonList;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.DefaultLookAndFeel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.pidev_mobile.entities.Formation;
import com.pidev_mobile.myapp.MyApplication;
import com.pidev_mobile.services.FormationService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FormAcceuilFormation extends Form{

    public FormAcceuilFormation() {
        
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
tb.addTab("Mes Formations", FontImage.MATERIAL_3D_ROTATION, 4, new FormMesFormation(this));

tb.addTab("Formations", FontImage.MATERIAL_ACCESS_ALARM, 4, new FormListFormation(this));
tb.addTab("Mes Participations", FontImage.MATERIAL_ACCOUNT_BOX, 4, new FormListMesParticipations(this));

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
tb.addTab("Mes Formations", FontImage.MATERIAL_3D_ROTATION, 4, new FormMesFormation(this).refreshLayoutover(this));

tb.addTab("Formations", FontImage.MATERIAL_ACCESS_ALARM, 4, new FormListFormation(this));
tb.addTab("Mes Participations", FontImage.MATERIAL_ACCOUNT_BOX, 4, new FormListMesParticipations(this).refreshLayoutover(this));

tb.getTabsContainer().setScrollableX(false); 

this.add(BorderLayout.CENTER, tb);
this.revalidate();
this.show();
    }
}
