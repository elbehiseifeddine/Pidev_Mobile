/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.formation;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Preferences;
import com.codename1.payment.Purchase;
import com.codename1.share.FacebookShare;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pidev_mobile.MyApplication;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Formation;
import pidev_mobile.services.FormationService;


/**
 *
 * @author ASUS
 */
public class FormMaParticipationFDetails extends BaseForm {

    public FormMaParticipationFDetails(Formation f,Form previous,Resources res) {
        
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
       final Purchase p = Purchase.getInAppPurchase();
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
        
        
        ImageViewer image = new ImageViewer(MyApplication.theme.getImage(f.getImageF()).scaled(300, 400));
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
        Button BtnPayer= new Button("Payer");
          BtnPayer.setUIID("SouthButton");
          BtnPayer.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
               
                 
                
                p.pay(f.getMontant(), "Dinars");
           
                 
             }
         });
                 Image screenshot = Image.createImage(300, 400);
            String  imageFile=FileSystemStorage.getInstance().getAppHomePath()+f.getImageF();
               try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(MyApplication.theme.getImage(f.getImageF()).scaled(500, 500), os, ImageIO.FORMAT_PNG, 1);
    
} catch(IOException err) {
                   System.out.println("non");
}
          ShareButton sb = new ShareButton();
          sb.setIcon(MyApplication.theme.getImage("share.png").scaled(FormMaParticipationFDetails.this.getWidth()-30, 150));
          sb.setImageToShare(FileSystemStorage.getInstance().getAppHomePath()+f.getImageF(), "image/png");
         /* Button share =new Button(MyApplication.theme.getImage("share.png").scaled(100, 100));
          share.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
                       FacebookShare fs=new FacebookShare();
              //   fs.setImage("logo", "png");
       
                 fs.share(f.getLabelle(), FileSystemStorage.getInstance().getAppHomePath()+"logo.png", "png");
                 
             }
         });*/
          
           Container cob=BoxLayout.encloseY(sb,BtnPayer);
          Container co=BoxLayout.encloseY(holder,holderDetails);
          this.
          add(BorderLayout.SOUTH,cob).
            add(BorderLayout.CENTER, co);
          
                FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE);
fab.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 new FormationService().SupprimerFormationPart("freelancer",(int) Math.round(Preferences.get("id", 1.1)),f.getId());
                  FormAcceuilFormation p=(FormAcceuilFormation)previous;
                 p.refreshLayout();
                 
              }
          });
fab.bindFabToContainer(this.getContentPane());

      }

    
    }
    
    

