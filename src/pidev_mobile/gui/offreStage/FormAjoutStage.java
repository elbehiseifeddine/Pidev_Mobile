/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreStage;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.entities.offreStage;
import pidev_mobile.services.emploiService;
import pidev_mobile.services.stageService;

/**
 *
 * @author Ghassen Riahi
 */
public class FormAjoutStage extends Form{

    public FormAjoutStage() {
        
        
         setLayout(BoxLayout.y());
          TextField nom = new TextField("", "nom du projet");
       TextField competences = new TextField("", "competences");
       TextField description = new TextField("", "description");
       TextField domaine = new TextField("", "domaine");
       TextField duree = new TextField("", "duree");
       TextField type = new TextField("", "typeStage");
       Picker date_creation = new Picker();
       date_creation.setType(Display.PICKER_TYPE_DATE);
       
       Picker date_expiration = new Picker();
       date_expiration.setType(Display.PICKER_TYPE_DATE);
      Button Ajouter=new Button("Ajouter");
       Ajouter.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               try {
                   offreStage of=new offreStage(nom.getText(), competences.getText(), description.getText(), domaine.getText(), duree.getText(), type.getText(),new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_creation.getDate())), new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy-mm-dd").format(date_expiration.getDate())), 1);
                new stageService().addStage(of, "Societe", 1);
               } catch (ParseException ex) {
               }
              
           }
       });
                  this.addAll(nom,competences,description,domaine,duree,type,date_creation,date_expiration,Ajouter);

       
    }
    
    
}
