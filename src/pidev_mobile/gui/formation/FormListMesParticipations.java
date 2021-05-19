/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.formation;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.List;
import pidev_mobile.MyApplication;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Formation;
import pidev_mobile.services.FormationService;

/**
 *
 * @author ASUS
 */
public class FormListMesParticipations extends BaseForm {
private List<Formation> formations;
private Resources res;
    public FormListMesParticipations(FormAcceuilFormation form,Resources res) {
        this.res=res;
     // form.getToolbar().setTitle("Mes Participation");
         setLayout(BoxLayout.y());
        
         //setUIID("background");
         
         
         formations = FormationService.getInstance().getParticipation("freelancer",22);
         for(int i=0;i<formations.size();i++){
             this.add(addpartholder(formations.get(i), form));
         }
        //this.setUIID("background");
        /*final FormListMesParticipations fl = this;

         formations = new ArrayList<>();
            Container List = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    formations = FormationService.getInstance().getParticipation("freelancer",1);
                }
                  if (index + amount > formations.size()) {
                    amount = formations.size() - index;
                }
                if (amount <= 0) {
                    return null;
                }
                Component[] more = new Component[amount];
                for (int i = 0; i < amount; i++) {
                    Formation e = formations.get(i);
                    Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(formations.get(i).getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(formations.get(i).getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(formations.get(i).getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                         FormMaParticipationFDetails fd = new FormMaParticipationFDetails(e,FormListMesParticipations.this);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                  //  holder.setUIID("background");
                    more[i] = holder;
                }
                return more;

            }

        };
        List.setScrollableY(false);
      // List.setUIID("background");
     
        this.add(List);*/
        
        
    
       /* this.getStyle().setBgColor(0xFFFFFF);
         this.getStyle().setBgTransparency(100);*/
    
    
}
    
     public FormListMesParticipations refreshLayoutover(FormAcceuilFormation form) {
         
        this.removeAll();
        
         //setUIID("background");
         
       
        //this.setUIID("background");
        formations = FormationService.getInstance().getParticipation("freelancer",22);
         for(int i=0;i<formations.size();i++){
             this.add(addpartholder(formations.get(i), form));
         }
        

        this.revalidate();
        ToastBar.showMessage("participation annulée", FontImage.MATERIAL_INFO);
        return this;
        
    }
     
     public Container addpartholder(Formation f,FormAcceuilFormation form){
          Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage(f.getImageF()).scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                         FormMaParticipationFDetails fd = new FormMaParticipationFDetails(f,form,res);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
     }


    }
    


