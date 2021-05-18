/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.back.superAdmin;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Admin;
import pidev_mobile.services.AdminService;

/**
 *
 * @author ahmed
 */
public class ViewAdminProfileForm extends BaseForm {

//    private Admin admin;
//
//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }

    public ViewAdminProfileForm(Resources res, Form previous,Admin admin) {
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
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                textVide2
                        )
                )
        ));
        //setLayout(new FlowLayout(CENTER, CENTER));
        Label firstname = new Label(admin.getPrenom());
        firstname.setUIID("LabelBlack");
        addStringValue("Prenom", firstname);

        Label lastname = new Label(admin.getNom());
        lastname.setUIID("LabelBlack");
        addStringValue("Nom", lastname);

        Label email = new Label(admin.getLogin());
        email.setUIID("LabelBlack");
        addStringValue("E-Mail", email);
        Label password = new Label(admin.getPass());
        password.setUIID("LabelBlack");
        addStringValue("Mot de passe", password);
        CheckBox cb1;
        if (admin.isEtat() == true) {
            Label etat = new Label("Compte activé");
            etat.setUIID("LabelBlack");
            addStringValue("Etat de compte", etat);
        } else {
            Label etat = new Label("Compte activé");
            etat.setUIID("LabelBlack");
            addStringValue("Etat de compte", etat);
        }

        //addStringValue("Twitter", FlowLayout.encloseMiddle(cb2));
        Label type = new Label(admin.getType());
        email.setUIID("LabelBlack");
        addStringValue("Type de compte", type);

        Label approuve = new Label(admin.getApprouve()+"");
        email.setUIID("LabelBlack");
        addStringValue("Approuvés : ", approuve);
        Label nonapprouve = new Label(admin.getNonApprouve()+"");
        email.setUIID("LabelBlack");
        addStringValue("Non Approuvé : ", nonapprouve);
        
        Button update = new Button("Modifier");
        Button delete = new Button("Supprimer");
        System.out.println("*****************admin id = "+admin.getId()+"*******************");

        add(FlowLayout.encloseCenterBottom(update, delete));
        update.addActionListener((evt) -> {
            AjoutAdminForm modifier = new AjoutAdminForm(res, this , true , admin);
            //modifier.setUpdate(true);
            //modifier.setAdmin(admin);
            modifier.show();
        });

        delete.addActionListener((evt) -> {
            if (Dialog.show("Confirmation", "Vous voulez vraiment supprimer " + admin.getNom() + " " + admin.getPrenom() + "?", "non", "oui")) {
                if(AdminService.getInstance().deleteAdmin(admin.getId())){
                previous.showBack();
                }
                else{
                    Dialog.show("Erreur", "Erreur de suppression de l'admin " + admin.getNom() + " " + admin.getPrenom() + ":", "ok", null);
                }
            }

        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

}
