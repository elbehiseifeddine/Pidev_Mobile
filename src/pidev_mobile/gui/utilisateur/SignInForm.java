/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.utilisateur;

/**
 *
 * @author seifeddine
 */
import pidev_mobile.base.BaseForm;
import com.codename1.components.FloatingHint;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.services.UtilisateurService;
import pidev_mobile.gui.utilisateur.ProfileFormSociete;
import static pidev_mobile.services.UtilisateurService.returnTypeSI;
import pidev_mobile.utils.JavaMail;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public SignInForm(Resources res) {
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
        TextField password = new TextField("", " Password", 20, TextField.PASSWORD);
        Label space = new Label("   ");
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        Button pwdo = new Button("mot de pass Oublier?");

        signUp.addActionListener(e -> new SignUpTypes(res).show());
        signUp.setUIID("Link");

        pwdo.addActionListener(e -> new ForgetPassword(res).show());
        pwdo.setUIID("Link");

        Label doneHaveAnAccount = new Label("Vous n'avez pas de compte?");
        Label pwd = new Label("ou");

        Container content = BoxLayout.encloseY(
                email,
                space,
                password
        );
        Container content2 = FlowLayout.encloseCenterMiddle(
                content
        );
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),
                FlowLayout.encloseCenter(pwd, pwdo)
        ));
        add(BorderLayout.CENTER, content2);
        signIn.addActionListener(e -> {
            
            UtilisateurService.getInstance().SignIn(email.getText(), password.getText());
            if (UtilisateurService.returnTypeSI.equals("CompteDesactiver")) {
                Dialog.show("Erreur", "Compte Désactiver", "OK", null);
            } else if (UtilisateurService.returnTypeSI.equals("Mot de pass est Incorrect")) {
                Dialog.show("Erreur", "Mot de pass est Incorrect", "OK", null);
            } else if (UtilisateurService.returnTypeSI.equals("Email exist pas")) {
                Dialog.show("Erreur", "Email n\'exist pas", "OK", null);
            } else {
                if (Preferences.get("type", null).equals("Freelancer")) {
                    new ProfileForm(res).show();
                } else if (Preferences.get("type", null).equals("Societe")) {
                    new ProfileFormSociete(res).show();
                }

            }
            //new ProfileForm(res).show();

        });
    }

}
