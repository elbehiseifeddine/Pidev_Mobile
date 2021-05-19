/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.demandeEmploi;

import com.codename1.io.Preferences;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.List;
import pidev_mobile.entities.DemandeEmploi;
import pidev_mobile.services.ServiceDemandeEmploi;


/**
 *
 * @author seifeddine
 */
public class AdDemandeEmploi extends BaseForm{
    
    public AdDemandeEmploi(Resources res,int id_o) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Demande Emploi");
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
       Label lbsalaire = new Label("salaire :");
       TextField salaire = new TextField("", "salaire demandée :");
        Label lbdiplome = new Label("Diplome en :");
       TextField diplome = new TextField("", "diplome en");
        Label lbD = new Label("Domaine :");
   TextField domaine = new TextField("", "votre domaine:");
      
       Label lblettre = new Label("Lettre de motivation :");
        TextArea lettre = new TextArea(5,10);
     
      // TextField devise = new TextField("", "devise");
       
      
       
     
      Button Envoyer=new Button("Envoyer");
       this.addAll(lbdes,des,lbD,domaine,lbdiplome,diplome,lbsalaire,salaire,lblettre,lettre,Envoyer);
       Envoyer.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               float s= Float.parseFloat(salaire.getText());
               DemandeEmploi d=new DemandeEmploi(id,id_o, des.getText(), lettre.getText(),
                       domaine.getText(),diplome.getText(), s);
               new ServiceDemandeEmploi().ADDemandeE(d);
               Dialog.show("Succés", " Félicitation votre Demande ete ajouté", "OK", null);
             
           }
       });
                 
                   

       
    }
    
        
        
    }

