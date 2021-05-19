/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreEmploi;

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
import java.util.List;


import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.services.emploiService;

/**
 *
 * @author Ghassen Riahi
 */
public class FormAjoutEmploi extends BaseForm{

    public FormAjoutEmploi(Resources res) {
        //setLayout(BoxLayout.y());
       super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Ajouter un offre d'emploi");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
        Label lbNom = new Label("nom du projet :");
       TextField nom = new TextField("", "insérer le nom du projet");
       Label lbC = new Label("Competences :");
       TextField competences = new TextField("", "saisir les competences");
        Label lbDesc = new Label("Descriprtion :");
       TextField description = new TextField("", "saisir une description");
        Label lbD = new Label("Domaine :");
       ComboBox<String> domaine = new ComboBox<String>(("Informatique"),("Design"),("Jeux vidéo"),("Artisanat"));
      // TextField domaine = new TextField("", "domaine");
       Label lbS = new Label("Salaire :");
       TextField salaire = new TextField("", "salaire");
         ComboBox<String> devise = new ComboBox<String>(("Dinars"),("Euro"),("Dollars"));
      // TextField devise = new TextField("", "devise");
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
                  
                   offreEmploi of=new offreEmploi(nom.getText(), competences.getText(), description.getText(),domaine.getSelectedItem().toString(), Float.parseFloat(salaire.getText()), devise.getSelectedItem().toString(),new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_creation.getDate())), new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_expiration.getDate())), 1);
                new emploiService().addEmploi(of, "Societe", 1);
                Dialog.show("Succés", " Félicitation votre offre ajouté", "OK", null);
               } catch (ParseException ex) {
               }
              
           }
       });
                  this.addAll(lbNom,nom,lbC,competences,lbDesc,description,lbD,domaine,lbS,salaire,devise,lbCr,date_creation,lbE,date_expiration,Ajouter);
                   

       
    }
    
    
    
}
