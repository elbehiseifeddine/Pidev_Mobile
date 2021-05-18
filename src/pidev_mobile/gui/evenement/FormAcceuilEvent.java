/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.evenement;

import com.codename1.components.SpanButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;


/**
 *
 * @author ASUS
 */
public class FormAcceuilEvent extends BaseForm {
Resources res;
    public FormAcceuilEvent(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        this.res=res;
        getTitleArea().setUIID("blackContainer");
        
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
    setLayout(new BorderLayout());
    Tabs tbs = new Tabs() {
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

tbs.setTabUIID(null);
tbs.addTab("Mes Events", FontImage.MATERIAL_3D_ROTATION, 4, new FormMesEvents(this,res));

tbs.addTab("Evenements", FontImage.MATERIAL_ACCESS_ALARM, 4, new FormEventList(this,res));
tbs.addTab("Mes Participations", FontImage.MATERIAL_ACCOUNT_BOX, 4, new FormListMesParticipationsE(this,res));

tbs.getTabsContainer().setScrollableX(false); 
tbs.addSelectionListener(new SelectionListener() {
            @Override
            public void selectionChanged(int oldSelected, int newSelected) {
                if(newSelected==0){
                    setTitle("Mes Evenement");
                }
                else if(newSelected==1){
                    setTitle("Evenement");
                }
                else if(newSelected==2){
                    setTitle("Participations");
                }
            }
        });

this.add(BorderLayout.CENTER, tbs);



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
tb.addTab("Mes Events", FontImage.MATERIAL_3D_ROTATION, 4, new FormMesEvents(this,res).refreshLayoutover(this));

tb.addTab("Evenements", FontImage.MATERIAL_ACCESS_ALARM, 4, new FormEventList(this,res));
tb.addTab("Mes Participations", FontImage.MATERIAL_ACCOUNT_BOX, 4, new FormListMesParticipationsE(this,res).refreshLayoutover(this));

tb.getTabsContainer().setScrollableX(false); 

this.add(BorderLayout.CENTER, tb);
this.revalidate();
this.show();
    }
    
    
}
