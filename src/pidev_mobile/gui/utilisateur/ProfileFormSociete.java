/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.utilisateur;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;

/**
 *
 * @author seifeddine
 */
public class ProfileFormSociete extends BaseForm{
    public ProfileFormSociete(Resources res) {
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

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg").scaled(300, 300), "PictureWhiteBackgrond"))
                )
        ));
        
        TextField nom = new TextField(Preferences.get("nom", null));
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);

        TextField email = new TextField(Preferences.get("email", null), "Email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("Email", email);

        TextField adresse = new TextField(Preferences.get("adresse", null));
        adresse.setUIID("TextFieldBlack");
        addStringValue("Adresse", adresse);
        
        TextField StatusJu = new TextField(Preferences.get("status_juridique", null));
        StatusJu.setUIID("TextFieldBlack");
        addStringValue("Status Juridique", StatusJu);
        
        Button Save = new Button("Save");
        add(Save);
        Save.requestFocus();
        Save.addActionListener(e -> new ProfileForm(res).show());
        
        
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add("    ");
        add(createLineSeparator(0xeeeeee));
        add("");
    }
}
