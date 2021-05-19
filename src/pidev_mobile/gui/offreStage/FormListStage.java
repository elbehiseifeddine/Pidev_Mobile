/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreStage;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;
import javax.mail.Quota.Resource;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.offreStage;
import pidev_mobile.services.stageService;

/**
 *
 * @author Ghassen Riahi
 */
public class FormListStage  extends BaseForm{
    List<offreStage> Stages;

    public FormListStage(Resources res) {
          super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
       // setTitle("Consulter tous les Stages");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
         setTitle("Liste des stages");
        setLayout(BoxLayout.y());
        Stages=stageService.getInstance().getAll(9);
        
        for(int i=0;i<Stages.size();i++){
            this.add(this.addpartholder(Stages.get(i)));

    }
    }
     public void refresh(){
        this.removeAll();
         Stages=stageService.getInstance().getAll(9);
        
        for(int i=0;i<Stages.size();i++){
            this.add(this.addpartholder(Stages.get(i)));
        }
        this.revalidate();
    }
     
      public Container addpartholder(offreStage f){
          Container holder = new Container(BoxLayout.y());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getNomProjet());
                    Label lbcompetences = new Label(f.getCompetence());
                    Label lbdesc = new Label(f.getDescription());
                    Label lbdomaine = new Label(f.getDomaine());
                    Label lbduree = new Label(f.getDuree());
                    Label lbtype = new Label(f.getTypeStage());
                    
                    

                     Label ldateC = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateCreation()));
                     Label ldateE = new Label(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(f.getDateExpiration()));
                    Button supprimer=new Button("suprimer");
                    supprimer.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                  System.out.println("good");
                  stageService.getInstance().SupprimerSTAGE(f);
                  refresh();
                 
              }
          });
                     holder.addAll(lbnom,lbcompetences,lbdesc,lbdomaine,lbduree,lbtype,ldateC,ldateE,supprimer);
                   
                    lbnom.addPointerReleasedListener((evt) -> {

                         
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
     }

    
    
}
