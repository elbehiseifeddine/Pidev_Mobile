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
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
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
public class FormListFormation extends BaseForm{
private List<Formation> formations;
private Resources res;
    public FormListFormation(FormAcceuilFormation form,Resources res) {
      // form.getToolbar().setTitle("Formations");
         setLayout(BoxLayout.y());
         this.res=res;
         //setUIID("background");
         
     
        //this.setUIID("background");
   

         //formations = new ArrayList<>();
        formations = FormationService.getInstance().getAll("freelancer",1);
        for(int i=0;i<formations.size();i++){
            this.add(addFromationHolder(formations.get(i),form));
        }
         
            /*Container List = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
             System.out.println("index "+index);
                if (index == 0) {
                    formations = FormationService.getInstance().getAll("freelancer",1);
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
                           System.out.println("offfffffffff"+formations.get(i).getLabelle());
                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(formations.get(i).getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(formations.get(i).getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormFormationDetails fd = new FormFormationDetails(e,FormListFormation.this);
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
    
    public Container addFromationHolder(Formation f,FormAcceuilFormation form) {
        Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getLabelle());
                          
                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage(f.getImageF()).scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormFormationDetails fd = new FormFormationDetails(f,form,res);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);

        return holder;

    }
   
    
}
