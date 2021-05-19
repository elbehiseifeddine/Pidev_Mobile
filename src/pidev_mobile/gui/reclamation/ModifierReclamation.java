/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.reclamation;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.entities.Reclamation;
import pidev_mobile.services.ReclamationService;
import pidev_mobile.entities.Reclamation;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import pidev_mobile.base.BaseForm;
/**
 *
 * @author Ayari Ghaith
 */
public class ModifierReclamation extends BaseForm {
    ReclamationService cs = ReclamationService.instance.getInstance();
    Form current;

    public ModifierReclamation(Resources res, Reclamation r) {

        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));
//        super.addSideMenu(res);

        Label FormTitle = new Label("Modifier Reclamation");
        FormTitle.getAllStyles().setAlignment(CENTER);

        TextField type = new TextField(r.getType(), "Type", 15, TextField.ANY);
        type.setUIID("SignInForm");

        TextField description = new TextField(r.getTextReclamation(), "Texte Reclamation", 15, TextField.ANY);
        description.setUIID("SignInForm");

        type.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);

        Button ModifierRec = new Button("Enregistrer");
        ModifierRec.addActionListener(e -> {

            r.setType(type.getText());
            r.setTextReclamation(description.getText());

            if (ReclamationService.getInstance().updateRec(r)) {
                new AfficherReclamation(res).show();
            }

        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new AfficherReclamation(res).show();
        });

        Container labels = new Container(BoxLayout.yCenter()).addAll(type, description, ModifierRec, btnAnnuler);
        labels.setUIID("SignInForm");
        Container by = new Container(new GridLayout(2, 1));
        by.addAll(
                labels
        );
        add(BorderLayout.NORTH, FormTitle);
        add(BorderLayout.CENTER, by);

    }
    
}
