/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.evenement;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.pidev_mobile.entities.EventLoisir;
import com.pidev_mobile.gui.formation.FormAcceuilFormation;
import com.pidev_mobile.gui.formation.FormUpdateFormation;
import com.pidev_mobile.myapp.MyApplication;
import com.pidev_mobile.services.EventService;
import com.pidev_mobile.services.FormationService;

/**
 *
 * @author ASUS
 */
public class FormMonEventDetails extends Form{

    public FormMonEventDetails(EventLoisir ev,Form previous) {
        setLayout(new BorderLayout());
         setTitle("Formation details");
         this.getToolbar().setUIID("toolbar");
            Style iconStyle = this.getUIManager().getComponentStyle("Title");
    FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
    FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);

    // we place the back and done commands in the toolbar, we need to change UIID of the "Done" command
    // so we can color it in Red
    this.getToolbar().addCommandToLeftBar("", leftArrow, (e) -> previous.show());
     Container holder = new Container(BoxLayout.y());
     
        Container holderDetails = new Container(BoxLayout.y());
        
        
        ImageViewer image = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
        Label lbnom = new Label(ev.getLabelle());
        Label ldomaine = new Label(ev.getDomaine());
        Label ldesc = new Label(ev.getDescription());
        Label ldated = new Label(String.valueOf(ev.getDateDebut()));
        Label ldatef = new Label(String.valueOf(ev.getDateFin()));
        Label lmontant = new Label(String.valueOf(ev.getNbParticipant()));
        Label llieu=new Label(ev.getLieu());
        
        holder.add(image);
        holder.setUIID("ImageCenter");
        holderDetails.addAll(lbnom,ldesc,ldomaine,ldated,ldatef,lmontant,llieu);
        
          this.add(BorderLayout.NORTH,holder).
          
            add(BorderLayout.CENTER, holderDetails);
          
                FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE);
fab.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 new EventService().SupprimerEvent(ev);
                  FormAcceuilFormation p=(FormAcceuilFormation)previous;
                  p.refreshLayout();
                 
              }
          });
fab.bindFabToContainer(this.getContentPane());
FloatingActionButton fabu = FloatingActionButton.createFAB(FontImage.MATERIAL_UPDATE);
fabu.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 new FormUpdateEvent(ev,previous).show();
                // previous.refreshLayoutover();
                 
              }
          });
fabu.bindFabToContainer(this.getContentPane());
      }
    }
    
    

