/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.evenement;

import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.pidev_mobile.entities.EventLoisir;
import com.pidev_mobile.myapp.MyApplication;
import com.pidev_mobile.services.EventService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FormEventList extends Form {
    private List<EventLoisir> events;

    public FormEventList(Form form) {
        setLayout(BoxLayout.y());
         setTitle("Formations");
         //setUIID("background");
         
         this.getToolbar().setUIID("toolbar");
        //this.setUIID("background");
        final FormEventList fl = this;

         //formations = new ArrayList<>();
        events = EventService.getInstance().getAll("freelancer",1);
        for(int i=0;i<events.size();i++){
            this.add(addEventHolder(events.get(i),form));
        }
         
          
        
       
        
         Style iconStyle = this.getUIManager().getComponentStyle("Title");
    FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
    FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);

    // we place the back and done commands in the toolbar, we need to change UIID of the "Done" command
    // so we can color it in Red
    this.getToolbar().addCommandToLeftBar("", leftArrow, (e) -> Log.p("Back pressed"));
        
             
    }
    
    public Container addEventHolder(EventLoisir E,Form f){
         Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(E.getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
                     Label ldated = new Label(String.valueOf(E.getDateDebut()));
                     Label ldatef = new Label(String.valueOf(E.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormEventDetails fd = new FormEventDetails(E,f);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
    }
    
    
}
