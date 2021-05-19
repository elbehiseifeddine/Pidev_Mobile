/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.evenement;

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
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.List;
import pidev_mobile.MyApplication;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.EventLoisir;
import pidev_mobile.services.EventService;

/**
 *
 * @author ASUS
 */
public class FormEventList extends BaseForm{
    private List<EventLoisir> events;
private Resources res;
    public FormEventList(Form form,Resources res) {
        setLayout(BoxLayout.y());
         this.res=res;
         //setUIID("background");
         
        

         //formations = new ArrayList<>();
        events = EventService.getInstance().getAll("freelancer",1);
        for(int i=0;i<events.size();i++){
            this.add(addEventHolder(events.get(i),form));
        }
         
          
        
       
        
     
        
             
    }
    
    public Container addEventHolder(EventLoisir E,Form f){
         Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(E.getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage(E.getImageE()).scaled(300, 400));
                     Label ldated = new Label(String.valueOf(E.getDateDebut()));
                     Label ldatef = new Label(String.valueOf(E.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormEventDetails fd = new FormEventDetails(E,f,res);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
    }
    
    
}
