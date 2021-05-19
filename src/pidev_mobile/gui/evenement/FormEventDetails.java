/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.evenement;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
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
import pidev_mobile.services.EventService;


/**
 *
 * @author ASUS
 */
public class FormEventDetails extends BaseForm {

    public FormEventDetails(EventLoisir ev,Form previous,Resources res) {
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
         setTitle("Event details");
      
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
         Button BtnParticiper= new Button("Participer");
          BtnParticiper.setUIID("SouthButton");
    //    southButton.setTextPosition(Component.CENTER);
        BtnParticiper.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                if( new EventService().Participaer(1,ev.getId(),"freelancer")){
         /*        Dialog dlg = new Dialog();
dlg.setLayout(new BorderLayout());
// span label accepts the text and the UIID for the dialog body
dlg.add(BorderLayout.CENTER,new SpanLabel("Dialog Body text"));
int h = Display.getInstance().getDisplayHeight();
dlg.setDisposeWhenPointerOutOfBounds(true);
dlg.getStyle().setBgColor(0x000000);
dlg.getStyle().setBgTransparency(255);
dlg.setUIID("dialog");
dlg.show(h /8 * 7, 150, 0, 0);*/
//Dialog.show("confirmation","participation confirmé",new Command("OK"));
//ToastBar.showErrorMessage("participation confirmée");
 /* ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.setExpires(7000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();*/
  ToastBar.showMessage("participation confirmé", FontImage.MATERIAL_INFO);
 FormAcceuilEvent p=(FormAcceuilEvent)previous;
 p.refreshLayout();

                }
                else{
                      
     ToastBar.showMessage("vous avez deja participé", FontImage.MATERIAL_WARNING);
                    
                    
                }
             }
         });
        
         Container co=BoxLayout.encloseY(holder,holderDetails);
          this.
            add(BorderLayout.SOUTH, BtnParticiper).
            add(BorderLayout.CENTER, co);
      }

    
    
    
}
