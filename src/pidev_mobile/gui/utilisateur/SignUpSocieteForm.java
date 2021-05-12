/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.utilisateur;

import com.codename1.components.ToastBar;
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
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import pidev_mobile.base.BaseForm;
import pidev_mobile.services.UtilisateurService;
import pidev_mobile.utils.JavaMail;

/**
 *
 * @author seifeddine
 */
public class SignUpSocieteForm extends BaseForm {

    public SignUpSocieteForm(Resources res) {
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

        Label nomCheck = new Label("   Votre nom doit contient au moins 3 caractèr");
        nomCheck.setVisible(false);
        Label emailCheck = new Label("   Ce n'est pas un Email Valide");
        emailCheck.setVisible(false);
        Label pwdCheck = new Label("   Votre Mot de Passe doit contient au moins 5 caractèr");
        pwdCheck.setVisible(false);
        TextField nom = new TextField("", " Nom", 20, TextField.ANY);
        Validator validator = new Validator();
        validator.addConstraint(nom, RegexConstraint.validEmail());
        TextField email = new TextField("", " E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", " Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", " Confirm Password", 20, TextField.PASSWORD);

        Button next = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                nom,
                nomCheck,
                email,
                emailCheck,
                password,
                pwdCheck
        );
        Container content2 = FlowLayout.encloseCenterMiddle(
                content
        );

        add(BorderLayout.CENTER, content2);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.addActionListener((e) -> {
            emailCheck.setVisible(false);
            nomCheck.setVisible(false);
            pwdCheck.setVisible(false);
            if (!validator.isValid() || nom.getText().length() <= 3 || password.getText().length() <= 3) {
                ToastBar.showErrorMessage("Les champs ne sont pas valide");
                if(!validator.isValid()){
                    emailCheck.setVisible(true);
                }
                if(nom.getText().length() <= 3){
                    nomCheck.setVisible(true);
                }
                
                if( password.getText().length() <= 3){
                    pwdCheck.setVisible(true);
                }
            } else {
                UtilisateurService.getInstance().SignUpSociete(nom.getText(), email.getText(), password.getText());
                JavaMail mail = new JavaMail();
                mail.recipient = email.getText();
                mail.type = "EmailConfirmation";
                mail.start();
                System.out.println("aaaaaaaaaaaaaaa" + UtilisateurService.returnTypeSU);
                ToastBar.showErrorMessage("Une demande de confirmation a été envoyer ");

                if (UtilisateurService.returnTypeSU.equals("exist")) {
                    Dialog.show("Erreur", "Email Exist déja", "OK", null);
                } else {
                    new SignInForm(res).show();
                }
            }

        });
    }
}

