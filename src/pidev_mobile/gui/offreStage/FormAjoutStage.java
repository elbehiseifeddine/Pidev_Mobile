/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreStage;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;

import pidev_mobile.entities.offreStage;

import pidev_mobile.services.stageService;

/**
 *
 * @author Ghassen Riahi
 */
public class FormAjoutStage extends BaseForm{

    public FormAjoutStage(Resources res) {
        
        
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Ajouter ofrre de stage");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
         Label lbNom = new Label("nom du projet :");
          TextField nom = new TextField("", "insérer votre nom du projet");
          Label lbC = new Label("Competences :");
       TextField competences = new TextField("", "saisir les competences");
       Label lbDesc = new Label("Descriprtion :");
       TextField description = new TextField("", "saisir votre description");
       Label lbD = new Label("Domaine :");
       TextField domaine = new TextField("", "domaine");
      // ComboBox<String> domaine = new ComboBox<String>(("Informatique"),("Design"),("Jeux vidéo"),("Artisanat"));
       TextField duree = new TextField("", "duree");
       Label lbDu = new Label("Durée :");
      // ComboBox<String> duree = new ComboBox<String>(("1 mois"),("2 mois"),("3 mois"));
      TextField type = new TextField("", "typeStage");
      Label lbT = new Label("Type de stage :");
      // ComboBox<String> type = new ComboBox<String>(("stage d'été"),("stage d'initiation"),("stage PFE"));
       
       Picker date_creation = new Picker();
        Label lbCr = new Label("Date de création :");
       
       date_creation.setType(Display.PICKER_TYPE_DATE);
       
       Picker date_expiration = new Picker();
       Label lbE = new Label("Date d'expiration :");
       date_expiration.setType(Display.PICKER_TYPE_DATE);
      Button Ajouter=new Button("Ajouter");
       Ajouter.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               try {
                   offreStage of=new offreStage(nom.getText(), competences.getText(), description.getText(), domaine.getText(), duree.getText(), type.getText(),new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_creation.getDate())), new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_expiration.getDate())), 1);
                new stageService().addStage(of, "Societe", 1);
                 Dialog.show("Succés", " Félicitation votre offre ajouté", "OK", null);
               } catch (ParseException ex) {
                   ex.getMessage();
               }
              
           }
       });
                  this.addAll(lbNom,nom,lbC,competences,lbDesc,description,lbD,domaine,lbDu,duree,lbT,type,lbCr,date_creation,lbE,date_expiration,Ajouter);

       
    }
    
    
}
