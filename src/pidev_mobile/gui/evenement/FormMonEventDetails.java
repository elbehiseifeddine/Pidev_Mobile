/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.evenement;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import pidev_mobile.MyApplication;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.EventLoisir;
import pidev_mobile.gui.formation.FormAcceuilFormation;
import pidev_mobile.services.EventService;



/**
 *
 * @author ASUS
 */
public class FormMonEventDetails extends BaseForm{

    public FormMonEventDetails(EventLoisir ev,Form previous,Resources res) {
     
       super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
       
        getContentPane().setScrollVisible(false);
    setLayout(new BorderLayout());
        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        Container c=BoxLayout.encloseY(lab1,lab2,lab3);
        add(BorderLayout.NORTH,c);
           setLayout(new BorderLayout());
         setTitle("Formation details");
     Container holder = new Container(BoxLayout.y());
     
        Container holderDetails = new Container(BoxLayout.y());
        
        
        ImageViewer image = new ImageViewer(MyApplication.theme.getImage(ev.getImageE()).scaled(300, 400));
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
         Container co=BoxLayout.encloseY(holder,holderDetails);
          this.
          
            add(BorderLayout.CENTER, co);
          
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
                 new FormUpdateEvent(ev,previous,res).show();
                // previous.refreshLayoutover();
                 
              }
          });
fabu.bindFabToContainer(this.getContentPane());
      }
    }
    
    

