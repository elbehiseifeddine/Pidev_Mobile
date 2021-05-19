/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.demandeStage;

import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.DemandeEmploi;
import pidev_mobile.entities.DemandeStage;
import pidev_mobile.services.ServiceDemandeEmploi;
import pidev_mobile.services.ServiceDemandeStage;

/**
 *
 * @author seifeddine
 */
public class AdDemandeStage extends BaseForm{
    
    public AdDemandeStage(Resources res,int id_o) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Demande Stage");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
        
        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
       
        
        
         Label justatext = new Label("Formulaire demande d'emploi");
        
        justatext.setAlignment(CENTER);
        add(justatext);
        
        int id;
        id = (int) Preferences.get("id", (double)0);
         Label lbdes = new Label("Description :");
       TextField des = new TextField("", "insérer la description");
       Label lbtype = new Label("Type :");
       TextField type = new TextField("", "type de stage :");
        Label lbdomaine = new Label("Domaine :");
       TextField domaine = new TextField("", "domaine");
        Label lbetude = new Label("Etude :");
   TextField etude= new TextField("", "votre etude:");
      Label lduree = new Label("Durée :");
        TextField duree= new TextField("", "duree de stage(sem):");
       Label lblettre = new Label("Lettre de motivation :");
        TextArea lettre = new TextArea(5,10);
     
      // TextField devise = new TextField("", "devise");
       
      
       
     
      Button Envoyer=new Button("Envoyer");
       this.addAll(lbdes,des,lbdomaine,domaine,lbtype,type,lbetude,etude,lduree,duree,lblettre,lettre,Envoyer);
       Envoyer.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               int  s=Integer.parseInt(duree.getText());
               DemandeStage d=new DemandeStage(id, id_o, des.getText(), lettre.getText(), domaine.getText(), type.getText(), s, etude.getText());
               new ServiceDemandeStage().ADDemandeS(d);
               Dialog.show("Succés", " Félicitation votre Demande ete ajouté", "OK", null);
               des.setText("");
             type.setText("");
             domaine.setText("");
             etude.setText("");
             duree.setText("");
             lettre.setText("");
             
           }
       });
                 
                   

       
    
    }
}
