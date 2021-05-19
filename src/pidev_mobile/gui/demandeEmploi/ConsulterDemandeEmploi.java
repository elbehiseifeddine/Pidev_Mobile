/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.demandeEmploi;

import com.codename1.io.Preferences;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;
import static javafx.scene.text.Font.font;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.DemandeEmploi;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.services.ServiceDemandeEmploi;

/**
 *
 * @author seifeddine
 */
public class ConsulterDemandeEmploi extends BaseForm{
    
    public ConsulterDemandeEmploi(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Consulter Demande emploi");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
        
        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
        Label justatext = new Label("Les Demandes Envoyeé !");
       
      
        justatext.setUIID("TextFieldBlack");
        justatext.setAlignment(CENTER);
         add(justatext);
        ArrayList<DemandeEmploi> demandes = ServiceDemandeEmploi.getInstance().getDemandesE();
         int id;
        id = (int) Preferences.get("id", (double)0);
        for( DemandeEmploi d :demandes){
            if(d.getFreelancer_id()==id)
         this.add(this.addpartholder(d,res));
            
        }
      

   
}
     public Container addpartholder(DemandeEmploi d, Resources res){
          Container holder = new Container(BoxLayout.y());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbdescription = new Label(d.getDescription());
                    Label lbdomaine = new Label(d.getDomaine());
                    Label lbsalaire = new Label(String.valueOf(d.getSalaire()));
                   Label line = new Label("------------------------------------------------------------------");
                    Label lbetat = new Label();
                      Label lbsupp = new Label("Supprimer");
                      
        lbsupp.setUIID("NewsTopLine");
     Style suppStyle = new Style(lbsupp.getUnselectedStyle());
        suppStyle.setFgColor(0xf21f1f);
        FontImage suppimg =FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
        lbsupp.setIcon(suppimg);
        lbsupp.setTextPosition(RIGHT);
        
                    createLineSeparator();
                  if(d.getEtat()==0){
                      lbetat.setText("non traitée");
                      
                  }else
                      if(d.getEtat()==1){
                          
                                                lbetat.setText("Approuvée !");

                          
                      }else
                      {
                          lbetat.setText("NON Approuvée"); 
                      }
                    
                    

                     
                    
            
                     
            lbsupp.addPointerPressedListener((evt) -> {
                Dialog dig=  new Dialog("Suppression");
                if(dig.show("Suppression","Vous voulez supprimer cette demande ?","Annuler","Oui")){
                    dig.dispose();
                }else {
                     if(ServiceDemandeEmploi.getInstance().DeleteDemande(d.getId())){
                   new ConsulterDemandeEmploi(res).show();
               } 
                }
              
            });
                   holder.addAll(lbdescription,lbdomaine,lbsalaire,lbetat,lbsupp,line);
                    
           
                    return holder;
     }
}
