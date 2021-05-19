/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreStage;

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
import pidev_mobile.entities.offreStage;
import pidev_mobile.gui.demandeStage.AdDemandeStage;
import pidev_mobile.services.stageService;

/**
 *
 * @author seifeddine
 */
public class ConsulterStageFreelancer extends BaseForm {

    List<offreStage> Stages;

    public ConsulterStageFreelancer(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Consulter Stages");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1, lab2, lab3);

        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
        Stages = stageService.getInstance().getAll(9);

        for (int i = 0; i < Stages.size(); i++) {
            this.add(this.addpartholder(Stages.get(i),res));

        }
    }

    public Container addpartholder(offreStage f,Resources res) {
        Container holder = new Container(BoxLayout.y());
        Container holderDetails = new Container(BoxLayout.y());
        Label lbnom = new Label(f.getNomProjet());
        Label lbcompetences = new Label(f.getCompetence());
        Label lbdesc = new Label(f.getDescription());
        Label lbdomaine = new Label(f.getDomaine());
        Label lbduree = new Label(f.getDuree());
        Label lbtype = new Label(f.getTypeStage());

        Label ldateC = new Label(new SimpleDateFormat("yyyy-mm-dd").format(f.getDateCreation()));
        Label ldateE = new Label(new SimpleDateFormat("yyyy-mm-dd").format(f.getDateExpiration()));
        Button demande = new Button("Postuler");
        Label line = new Label("---------------------------------------------------------------");
        demande.getStyle().setBgColor(0xcccccc);
        demande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                demande.requestFocus();
                new AdDemandeStage(res, f.getId()).show();

            }
        });
        holder.addAll(lbnom, lbcompetences, lbdesc, lbdomaine, lbduree, lbtype, ldateC, ldateE, demande, line);

        lbnom.addPointerReleasedListener((evt) -> {

        });
        holder.setLeadComponent(lbnom);
        return holder;
    }

}
