/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.demandeStage;

import com.codename1.io.Preferences;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.DemandeEmploi;
import pidev_mobile.entities.DemandeStage;
import pidev_mobile.gui.demandeEmploi.ConsulterDemandeEmploi;
import pidev_mobile.services.ServiceDemandeEmploi;
import pidev_mobile.services.ServiceDemandeStage;

/**
 *
 * @author seifeddine
 */
public class ConsulterDemandeStage extends BaseForm{
    
    public ConsulterDemandeStage(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
     
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
        
        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
       
        
          Label justatext = new Label("Les Demandes stage Envoyeé !");
       
      
        justatext.setUIID("TextFieldBlack");
        justatext.setAlignment(CENTER);
         add(justatext);
        ArrayList<DemandeStage> demandes = ServiceDemandeStage.getInstance().getDemandesE();
         int id;
        id = (int) Preferences.get("id", (double)0);
        for( DemandeStage d :demandes){
            if(d.getFreelancer_id()==id)
         this.add(this.addpartholder(d,res));
            
        }
      

   
}
     public Container addpartholder(DemandeStage d, Resources res){
          Container holder = new Container(BoxLayout.y());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbdescription = new Label(d.getDescription());
                    Label lbdomaine = new Label(d.getDomaine());
                    Label lbType = new Label(d.getType());
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
                     if(ServiceDemandeStage.getInstance().DeleteDemande(d.getId())){
                         System.out.println("aaaawork");
                   new ConsulterDemandeStage(res).show();
               } 
                }
              
            });
                   holder.addAll(lbdescription,lbdomaine,lbType,lbetat,lbsupp,line);
                    
           
                    return holder;
     }
    }

