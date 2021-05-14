/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.formation;

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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.pidev_mobile.entities.Formation;
import com.pidev_mobile.myapp.MyApplication;
import com.pidev_mobile.services.FormationService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FormMesFormation extends Form {
private List<Formation> Listformations;
    public FormMesFormation(FormAcceuilFormation form) {
          setLayout(BoxLayout.y());
         setTitle("Mes Formations");
         //setUIID("background");
         
         this.getToolbar().setUIID("toolbar");
        //this.setUIID("background");
        final FormMesFormation fl = this;

         //Listformations = new ArrayList<>();
         Listformations = FormationService.getInstance().getFParUser("freelancer",1);
         for(int i=0;i<Listformations.size();i++){
             this.add(addMaFormationholder(Listformations.get(i),form));
         }
           /* Container List = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                System.out.println("index mes"+index);
                if (index == 0) {
                    Listformations = FormationService.getInstance().getFParUser("freelancer",1);
                }
                  if (index + amount > Listformations.size()) {
                    amount = Listformations.size() - index;
                }
                if (amount <= 0) {
                    return null;
                }
                Component[] more = new Component[amount];
                for (int i = 0; i < amount; i++) {
                    Formation e = Listformations.get(i);
                    Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(Listformations.get(i).getLabelle());
                    System.out.println("ooooooooo"+Listformations.get(i).getLabelle());
                 

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(Listformations.get(i).getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(Listformations.get(i).getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormMaFormationDetails fd = new FormMaFormationDetails(e,FormMesFormation.this);
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
           FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
fab.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 new FormAjoutFormation(form).show();
                 
              }
          });
fab.bindFabToContainer(this.getContentPane());
         Style iconStyle = this.getUIManager().getComponentStyle("Title");
    FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
    FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);

    // we place the back and done commands in the toolbar, we need to change UIID of the "Done" command
    // so we can color it in Red
    this.getToolbar().addCommandToLeftBar("", leftArrow, (e) -> Log.p("Back pressed"));
    
       /* this.getStyle().setBgColor(0xFFFFFF);
         this.getStyle().setBgTransparency(100);*/
    
    
}

      public FormMesFormation refreshLayoutover(FormAcceuilFormation form) {
          System.out.println("dkhal li methode");
        this.removeAll();
        
         //setUIID("background");
         
       
        //this.setUIID("background");
        Listformations = FormationService.getInstance().getFParUser("freelancer",1);
         for(int i=0;i<Listformations.size();i++){
             this.add(addMaFormationholder(Listformations.get(i), form));
         }

        this.revalidate();
        return this;
        
        
    }
      public Container addMaFormationholder(Formation f,FormAcceuilFormation form){
          Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
                     Label ldated = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateDebut()));
                     Label ldatef = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormMaFormationDetails fd = new FormMaFormationDetails(f,form);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
      }
    }

