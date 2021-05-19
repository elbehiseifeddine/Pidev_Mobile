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
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import pidev_mobile.services.UtilisateurService;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        Image img = res.getImage("ProfileBackground.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Image placeholder = Image.createImage(300, 300, 0xbfc9d2); //square image set to 10% of screen width
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        
        URLImage image= URLImage.createToStorage(encImage,"aaaaaaaaa","ftp://user:123456789@192.168.1.52/"+Preferences.get("photo_de_profile", null));
        image.fetch();
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        FlowLayout.encloseCenter(
                                new Label(image.scaled(300, 300), "PictureWhiteBackgrond"))
                )
        ));

         TextField nom = new TextField(Preferences.get("nom", null));
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);
        TextField prenom = new TextField(Preferences.get("prenom", null));
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom);

        TextField email = new TextField(Preferences.get("email", null), "Email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("Email", email);

        String oldEmail = email.getText();

        TextField adresse = new TextField(Preferences.get("adresse", null));
        adresse.setUIID("TextFieldBlack");
        addStringValue("Adresse", adresse);

        TextField langues = new TextField(Preferences.get("langues", null));
        langues.setUIID("TextFieldBlack");
        addStringValue("Langues", langues);

        TextField Sexe = new TextField(Preferences.get("sexe", null));
        Sexe.setUIID("TextFieldBlack");
        addStringValue("Sexe", Sexe);

        TextField Compete = new TextField(Preferences.get("competences", null));
        Compete.setUIID("TextFieldBlack");
        addStringValue("Competence", Compete);

        Button Save = new Button("Save");
        add(Save);
        Save.requestFocus();
         Save.addActionListener(e -> {
            UtilisateurService.getInstance().Update(nom.getText(), prenom.getText(), oldEmail, email.getText(), adresse.getText(), Sexe.getText(), Compete.getText(), langues.getText());
            if (UtilisateurService.returnTypeSI.equals("successss")) {
                Preferences.set("nom", nom.getText());
                Preferences.set("prenom", prenom.getText());
                Preferences.set("email", email.getText());
                Preferences.set("adresse", adresse.getText());
                Preferences.set("competences", Compete.getText());
                Preferences.set("langues", langues.getText());
                Preferences.set("sexe", Sexe.getText());
                ToastBar.showErrorMessage("Votre modification est éffectuer avec success");
            }
        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add("    ");
        add(createLineSeparator(0xeeeeee));
        add("");
    }
}
