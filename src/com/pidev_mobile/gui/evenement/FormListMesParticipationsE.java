/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.evenement;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.pidev_mobile.entities.EventLoisir;
import com.pidev_mobile.entities.Formation;
import com.pidev_mobile.gui.formation.FormAcceuilFormation;
import com.pidev_mobile.gui.formation.FormListMesParticipations;
import com.pidev_mobile.gui.formation.FormMaParticipationFDetails;
import com.pidev_mobile.myapp.MyApplication;
import com.pidev_mobile.services.EventService;
import com.pidev_mobile.services.FormationService;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FormListMesParticipationsE extends Form {
private List<EventLoisir> events;
    public FormListMesParticipationsE(FormAcceuilEvent form) {
     setLayout(BoxLayout.y());
         setTitle("Mes Participation");
         //setUIID("background");
         
         this.getToolbar().setUIID("toolbar");
         events = EventService.getInstance().getParticipation("freelancer",1);
         for(int i=0;i<events.size();i++){
             this.add(addpartholderE(events.get(i), form));
         }
       
        
         Style iconStyle = this.getUIManager().getComponentStyle("Title");
    FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
    FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);

    // we place the back and done commands in the toolbar, we need to change UIID of the "Done" command
    // so we can color it in Red
    this.getToolbar().addCommandToLeftBar("", leftArrow, (e) -> Log.p("Back pressed"));
    
       /* this.getStyle().setBgColor(0xFFFFFF);
         this.getStyle().setBgTransparency(100);*/
    
    
}
    
     public FormListMesParticipationsE refreshLayoutover(FormAcceuilEvent form) {
         
        this.removeAll();
        
         //setUIID("background");
         
       
        //this.setUIID("background");
        events = EventService.getInstance().getParticipation("freelancer",1);
         for(int i=0;i<events.size();i++){
             this.add(addpartholderE(events.get(i), form));
         }
        

        this.revalidate();
        ToastBar.showMessage("participation annulée", FontImage.MATERIAL_INFO);
        return this;
        
    }
     
     public Container addpartholderE(EventLoisir f,FormAcceuilEvent form){
          Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                         FormMaParticipationEDetails fd = new FormMaParticipationEDetails(f,form);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
     }

    
    
}