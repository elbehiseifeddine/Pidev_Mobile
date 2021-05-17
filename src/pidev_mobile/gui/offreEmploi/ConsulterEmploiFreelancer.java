/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreEmploi;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.services.emploiService;

/**
 *
 * @author seifeddine
 */
public class ConsulterEmploiFreelancer extends BaseForm{
      List<offreEmploi> Emplois;
    
    public ConsulterEmploiFreelancer(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Consulter Emplois");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
        
        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
        
        Emplois=emploiService.getInstance().getAll(9);
        
        for(int i=0;i<Emplois.size();i++){
            this.add(this.addpartholder(Emplois.get(i)));
        }
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
                    Button demande=new Button("Demande emploi");
                    demande.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                  System.out.println("good");
                  emploiService.getInstance().SupprimerEMPLOI(f);
                 // refresh();
                 
              }
          });
                     holder.addAll(lbnom,lbcompetences,lbdesc,lbdomaine,lbsalaire,lbdevise,ldateC,ldateE,demande);
                   
                    lbnom.addPointerReleasedListener((evt) -> {

                         
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
     }
        
    }

