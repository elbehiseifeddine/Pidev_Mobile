/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.formation;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.pidev_mobile.entities.Formation;
import com.pidev_mobile.myapp.MyApplication;
import com.pidev_mobile.services.FormationService;

/**
 *
 * @author ASUS
 */
public class FormMaParticipationFDetails extends Form {

    public FormMaParticipationFDetails(Formation f,Form previous) {
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
        Label lbnom = new Label(f.getLabelle());
        Label ldomaine = new Label(f.getDomaine());
        Label ldesc = new Label(f.getDescription());
        Label ldated = new Label(String.valueOf(f.getDateDebut()));
        Label ldatef = new Label(String.valueOf(f.getDateFin()));
        Label lmontant = new Label(String.valueOf(f.getMontant()));
        Label llieu=new Label(f.getLieu());
        
        holder.add(image);
        holder.setUIID("ImageCenter");
        holderDetails.addAll(lbnom,ldesc,ldomaine,ldated,ldatef,lmontant,llieu);
        Button BtnPayer= new Button("Participer");
          BtnPayer.setUIID("SouthButton");
          BtnPayer.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 System.out.println("BtnPayer pressed");
             }
         });
          
          
          this.add(BorderLayout.NORTH,holder).
          add(BorderLayout.SOUTH,BtnPayer).
            add(BorderLayout.CENTER, holderDetails);
          
                FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE);
fab.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 new FormationService().SupprimerFormationPart("freelancer",1,f.getId());
                  FormAcceuilFormation p=(FormAcceuilFormation)previous;
                 p.refreshLayout();
                 
              }
          });
fab.bindFabToContainer(this.getContentPane());

      }

    
    }
    
    

