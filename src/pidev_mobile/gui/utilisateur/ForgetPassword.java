/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.utilisateur;

import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;
import pidev_mobile.services.UtilisateurService;
import pidev_mobile.utils.JavaMail;

/**
 *
 * @author seifeddine
 */
public class ForgetPassword extends BaseForm {

    public ForgetPassword(Resources res) {
        super(new BorderLayout());
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        add(BorderLayout.NORTH, new Label(res.getImage("LogoR.png"), "LogoLabel"));

        TextField email = new TextField("", " Email", 20, TextField.ANY);
        Label space = new Label("   ");
        Button Envoyer = new Button("Envoyer");
        Button signIn = new Button("Sign In");

        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label doneHaveAnAccount = new Label("Revenir à");

        Container content = BoxLayout.encloseY(
                email
        );
        Container content2 = FlowLayout.encloseCenterMiddle(
                content
        );
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                Envoyer,
                FlowLayout.encloseCenter(doneHaveAnAccount, signIn)
        ));
        add(BorderLayout.CENTER, content2);

        Envoyer.addActionListener(e -> {
            JavaMail mail = new JavaMail();
            mail.recipient = email.getText();
            mail.type = "ForgetPassword";
            mail.start();
            new SignInForm(res).show();
        });
    }
}
