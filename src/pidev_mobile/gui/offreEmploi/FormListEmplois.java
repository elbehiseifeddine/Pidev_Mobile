/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreEmploi;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.List;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.services.emploiService;

/**
 *
 * @author Ghassen Riahi
 */
public class FormListEmplois extends Form {
    List<offreEmploi> Emplois;

    public FormListEmplois() {
        setTitle("Liste des Emplois");
        setLayout(BoxLayout.y());
        Emplois=emploiService.getInstance().getAll(9);
        
        for(int i=0;i<Emplois.size();i++){
            this.add(this.addpartholder(Emplois.get(i)));
        }
        
    }
    public void refresh(){
        this.removeAll();
         Emplois=emploiService.getInstance().getAll(9);
        
        for(int i=0;i<Emplois.size();i++){
            this.add(this.addpartholder(Emplois.get(i)));
        }
        this.revalidate();
    }
     public Container addpartholder(offreEmploi f){
          Container holder = new Container(BoxLayout.y());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getNom());
                    Label lbcompetences = new Label(f.getCompetences());
                    Label lbdesc = new Label(f.getDescription());
                    Label lbdomaine = new Label(f.getDomaine());
                    Label lbsalaire = new Label(String.valueOf(f.getSalaire()));
                    Label lbdevise = new Label(f.getDevise());
                    
                    

                     Label ldateC = new Label(new SimpleDateFormat("yyyy-mm-dd").format(f.getDateCreation()));
                     Label ldateE = new Label(new SimpleDateFormat("yyyy-mm-dd").format(f.getDateExpiration()));
                    Button supprimer=new Button("suprimer");
                    supprimer.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                  System.out.println("good");
                  emploiService.getInstance().SupprimerEMPLOI(f);
                  refresh();
                 
              }
          });
                     holder.addAll(lbnom,lbcompetences,lbdesc,lbdomaine,lbsalaire,lbdevise,ldateC,ldateE,supprimer);
                   
                    lbnom.addPointerReleasedListener((evt) -> {

                         
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
     }

    
    
}
