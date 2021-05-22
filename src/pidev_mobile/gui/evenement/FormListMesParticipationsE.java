/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.evenement;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.io.Preferences;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.List;
import pidev_mobile.MyApplication;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.EventLoisir;
import pidev_mobile.services.EventService;

/**
 *
 * @author ASUS
 */
public class FormListMesParticipationsE extends BaseForm {
private List<EventLoisir> events;
private Resources res;
    public FormListMesParticipationsE(FormAcceuilEvent form,Resources res) {
     setLayout(BoxLayout.y());
     this.res=res;
         
         //setUIID("background");
         
       
         events = EventService.getInstance().getParticipation("freelancer",(int) Math.round(Preferences.get("id", 1.1)));
         for(int i=0;i<events.size();i++){
             this.add(addpartholderE(events.get(i), form));
         }
       
        
      
       /* this.getStyle().setBgColor(0xFFFFFF);
         this.getStyle().setBgTransparency(100);*/
    
    
}
    
     public FormListMesParticipationsE refreshLayoutover(FormAcceuilEvent form) {
         
        this.removeAll();
        
         //setUIID("background");
         
       
        //this.setUIID("background");
        events = EventService.getInstance().getParticipation("freelancer",(int) Math.round(Preferences.get("id", 1.1)));
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

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage(f.getImageE()).scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                         FormMaParticipationEDetails fd = new FormMaParticipationEDetails(f,form,res);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
     }

    
    
}
