/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.back.AdminReclamation;

import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.back.superAdmin.AjoutAdminForm;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Admin;
import pidev_mobile.entities.Reclamation;
import pidev_mobile.services.AdminReclamationService;
import pidev_mobile.services.AdminService;

/**
 *
 * @author ahmed
 */
public class DetailReclamation extends BaseForm {
    
    
     public DetailReclamation(Resources res, Form previous,Reclamation rec) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Admin profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1, lab2, lab3);

        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
        
        Label textVide1 = new Label("");
            Label textVide2 = new Label("");
            textVide1.setTextPosition(BOTTOM);
            textVide2.setTextPosition(BOTTOM);
        add(LayeredLayout.encloseIn(
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                textVide1,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("Reclamation.png"), "PictureWhiteBackgrond")),
                                textVide2
                        )
                )
        ));
        //setLayout(new FlowLayout(CENTER, CENTER));
        Label Owner = new Label(rec.getNomUser());
        Owner.setUIID("LabelBlack");
        addStringValue("Rédigé par :", Owner);

        Label Type = new Label(rec.getType());
        Type.setUIID("LabelBlack");
        addStringValue("Type :", Type);

        Label Description = new Label(rec.getTextReclamation());
        Description.setUIID("LabelBlack");
        addStringValue("Description : ", Description);
        
        

        
        Label Date = new Label(rec.getDateReclamation());
        Date.setUIID("LabelBlack");
        addStringValue("Reçu le :", Date);

        
        
        Button update = new Button("Approuver");
        Button delete = new Button("Supprimer");
        

        add(FlowLayout.encloseCenterBottom(update, delete));
        update.addActionListener((evt) -> {
            AdminReclamationService.getInstance().ActiverReclamation(rec.getId(), Integer.parseInt(Preferences.get("id", null)));
            ToastBar.showMessage("Reclamation traité", FontImage.MATERIAL_CHECK);
            new ARAccueil(res).show();
        });

        delete.addActionListener((evt) -> {
            AdminReclamationService.getInstance().DeactiverReclamation(rec.getId());

            this.refreshTheme();

            ToastBar.showMessage("Reclamation Supprimer!", FontImage.MATERIAL_INFO);
            
            new ARAccueil(res).show();


        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
