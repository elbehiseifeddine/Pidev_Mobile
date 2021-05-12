/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.utilisateur;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;
import pidev_mobile.services.UtilisateurService;

/**
 *
 * @author seifeddine
 */
public class SignUpTypes extends BaseForm {

    public SignUpTypes(Resources res) {
        super(new BorderLayout());
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }

        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        setUIID("SignIn");
        add(BorderLayout.NORTH, new Label(res.getImage("LogoR.png"), "LogoLabel"));

        Button f = new Button("Freelancer");
        Button s = new Button("Societe");
        Label space= new Label("  ");
        
        Container content = BoxLayout.encloseY(
                f,
                space,
                s
        );
        Container content2 = FlowLayout.encloseCenterMiddle(
                content
        );
        
        add(BorderLayout.CENTER, content2);
        
        
        f.addActionListener((e) -> {
            new SignUpFreelancerForm(res).show();
        });
       
        s.addActionListener((e) -> {
            
            new SignUpSocieteForm(res).show();
        });
    }
}
