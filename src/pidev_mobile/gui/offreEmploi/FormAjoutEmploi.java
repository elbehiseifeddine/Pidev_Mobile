/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreEmploi;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.services.emploiService;

/**
 *
 * @author Ghassen Riahi
 */
public class FormAjoutEmploi extends Form{

    public FormAjoutEmploi() {
        setLayout(BoxLayout.y());
       /* super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Offre Emploi");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);*/
       
       TextField nom = new TextField("", "nom du projet");
       TextField competences = new TextField("", "competences");
       TextField description = new TextField("", "description");
       TextField domaine = new TextField("", "domaine");
       TextField salaire = new TextField("", "salaire");
       TextField devise = new TextField("", "devise");
       Picker date_creation = new Picker();
       date_creation.setType(Display.PICKER_TYPE_DATE);
       
       Picker date_expiration = new Picker();
       date_expiration.setType(Display.PICKER_TYPE_DATE);
      Button Ajouter=new Button("Ajouter");
       Ajouter.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               try {
                   offreEmploi of=new offreEmploi(nom.getText(), competences.getText(), description.getText(), domaine.getText(), Float.parseFloat(salaire.getText()), devise.getText(),new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_creation.getDate())), new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_expiration.getDate())), 1);
                new emploiService().addEmploi(of, "Societe", 1);
               } catch (ParseException ex) {
               }
              
           }
       });
                  this.addAll(nom,competences,description,domaine,salaire,devise,date_creation,date_expiration,Ajouter);

       
    }
    
    
}
