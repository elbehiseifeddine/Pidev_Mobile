/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.back.superAdmin;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
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
public class AjoutAdminForm extends BaseForm {

//    private boolean update = false;
//    private Admin admin;
//
//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }
//
//    public boolean isUpdate() {
//        return update;
//    }
//
//    public void setUpdate(boolean update) {
//        this.update = update;
//    }

    public AjoutAdminForm(Resources res, Form Accueil,boolean update,Admin admin) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");

        if (update) {
            setTitle("Modifier Admin");

            getContentPane().setScrollVisible(false);

            super.addSideMenu(res);
            Label lab1 = new Label("  ");
            Label lab2 = new Label("  ");
            Label lab3 = new Label("  ");
            addAll(lab1, lab2, lab3);

            //minna tibda tiktib, ma tfasa5 chay mil fo9ani
            add(LayeredLayout.encloseIn(
                    BorderLayout.south(
                            GridLayout.encloseIn(3,
                                    FlowLayout.encloseCenter(
                                            new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                            )
                    )
            ));
            setLayout(new FlowLayout(CENTER, CENTER));
            TextField firstname = new TextField(admin.getPrenom());
            firstname.setUIID("TextFieldBlack");
            addStringValue("Prenom", firstname);

            TextField lastname = new TextField(admin.getNom());
            lastname.setUIID("TextFieldBlack");
            addStringValue("Nom", lastname);

            TextField email = new TextField(admin.getLogin(), "E-Mail", 20, TextField.EMAILADDR);
            email.setUIID("TextFieldBlack");
            addStringValue("E-Mail", email);
            TextField password = new TextField(admin.getPass());
            password.setUIID("TextFieldBlack");
            addStringValue("Mot de passe", password);
            CheckBox cb1;
            if (admin.isEtat() == true) {
                cb1 = CheckBox.createToggle(res.getImage("on-off-on.png"));
                cb1.setUIID("Label");
                cb1.setPressedIcon(res.getImage("on-off-off.png"));
                addStringValue("Etat de compte", FlowLayout.encloseMiddle(cb1));
            } else {
                cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
                cb1.setUIID("Label");
                cb1.setPressedIcon(res.getImage("on-off-on.png"));
                addStringValue("Etat de compte", FlowLayout.encloseMiddle(cb1));
            }

            //addStringValue("Twitter", FlowLayout.encloseMiddle(cb2));

            String[] characters = {"Admin des reclamations", "Admin des emplois", "Admin des events"};

            MultiButton b = new MultiButton(admin.getType());

            b.addActionListener(e -> {
                Dialog d = new Dialog();
                d.setLayout(BoxLayout.y());
                d.getContentPane().setScrollableY(true);
                for (int iter = 0; iter < characters.length; iter++) {
                    MultiButton mb = new MultiButton(characters[iter]);

                    d.add(mb);
                    mb.addActionListener(ee -> {
                        b.setTextLine1(mb.getTextLine1());

                        d.dispose();
                        b.revalidate();
                    });
                }
                d.showPopupDialog(b);
            });

            addStringValue("Type de compte", b);

            Button submit = new Button("Submit");
        System.out.println("                admin id = "+admin.getId()+"                 ");

            add(FlowLayout.encloseCenterBottom(submit));
            submit.addActionListener((evt) -> {

                if (firstname.getText().equals("")
                        || lastname.getText().equals("")
                        || email.getText().equals("")
                        || password.getText().equals("")
                        || b.getText().equals("")) {
                    ToastBar.showMessage("Veuillez verifier les champs", FontImage.MATERIAL_INFO);
                } else {
                    
                    
                    admin.setLogin(email.getText());
                    admin.setPass(password.getText());
                    admin.setPrenom(firstname.getText());
                    admin.setNom(lastname.getText());

                    if (cb1.getPressedIcon().getImageName().equals("on-off-on.png")) {
                        admin.setEtat(true);
                    } else {
                        admin.setEtat(false);
                    }

                    admin.setType(b.getText());

                    

                    if (AdminService.getInstance().updateAdmin(admin)){
                    Dialog.show("Succes !", "Admin modifié avec succes!", "ok", null);
                    Accueil.refreshTheme();
                    Accueil.showBack();
                    }else {
                        Dialog.show("Erreur !", "Erreur de modification!", "ok", null);
                    }
                }
            });

        } else {
            setTitle("Créer Admin");

            getContentPane().setScrollVisible(false);

            super.addSideMenu(res);
            Label lab1 = new Label("  ");
            Label lab2 = new Label("  ");
            Label lab3 = new Label("  ");
            addAll(lab1, lab2, lab3);

            //minna tibda tiktib, ma tfasa5 chay mil fo9ani
//        Image img = res.getImage("LogoR.png");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
//        ScaleImageLabel sl = new ScaleImageLabel(img);
//        sl.setUIID("BottomPad");
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        add(LayeredLayout.encloseIn(
//                sl,
//                BorderLayout.south(
//                    GridLayout.encloseIn(3, 
//                            facebook,
//                            FlowLayout.encloseCenter(
//                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
//                            twitter
//                    )
//                )
//        ));

 
            Label textVide1 = new Label("");
            Label textVide2 = new Label("");
            textVide1.setTextPosition(BOTTOM);
            textVide2.setTextPosition(BOTTOM);
            add(LayeredLayout.encloseIn(
            GridLayout.encloseIn(3, 
                            textVide1,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("PetitLogoR.png"), "PictureWhiteBackgrond")),
                            textVide2
                    )
            ));
            Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
//            add(LayeredLayout.encloseIn(
//                    BorderLayout.south(
//                            GridLayout.encloseIn(1,
//                                    FlowLayout.encloseCenter(createForFont(largeBoldSystemFont, "Créer nouveau admin"))
//                            )
//                    )
//            ));
//            setLayout(new FlowLayout(CENTER, CENTER));
            TextField firstname = new TextField("", "Entrer le prenom");
            firstname.setUIID("TextFieldBlack");
            addStringValue("Prenom", firstname);

            TextField lastname = new TextField("", "Entrer le nom");
            lastname.setUIID("TextFieldBlack");
            addStringValue("Nom", lastname);

            TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
            email.setUIID("TextFieldBlack");
            addStringValue("E-Mail", email);
            TextField password = new TextField("", "mot de passe", 20, TextField.PASSWORD);
            password.setUIID("TextFieldBlack");
            addStringValue("Mot de passe", password);
            CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
            cb1.setUIID("Label");
            cb1.setPressedIcon(res.getImage("on-off-on.png"));

            addStringValue("Etat de compte", FlowLayout.encloseMiddle(cb1));
            //addStringValue("Twitter", FlowLayout.encloseMiddle(cb2));

            String[] characters = {"Admin des reclamations", "Admin des emplois", "Admin des events"};
//        AutoCompleteTextField act = new AutoCompleteTextField(characters);
//        act.setHint(new Label("Type de compte", "PaddedLabel").getText());
//act.addActionListener(e -> ToastBar.showMessage("You picked " + act.getText(), FontImage.MATERIAL_INFO));
//Button down = new Button();
//FontImage.setMaterialIcon(down, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);
//add(
//        BorderLayout.center(act).
//                add(BorderLayout.EAST, down));
//down.addActionListener(e -> act.showPopup());
//

            MultiButton b = new MultiButton("");
            addStringValue("Type de compte", b);
            b.addActionListener(e -> {
                Dialog d = new Dialog();
                d.setLayout(BoxLayout.y());
                d.getContentPane().setScrollableY(true);
                for (int iter = 0; iter < characters.length; iter++) {
                    MultiButton mb = new MultiButton(characters[iter]);

                    d.add(mb);
                    mb.addActionListener(ee -> {
                        b.setTextLine1(mb.getTextLine1());

                        d.dispose();
                        b.revalidate();
                    });
                }
                d.showPopupDialog(b);
            });

            Button submit = new Button("Submit");
            Button reset = new Button("Reset");

            add(FlowLayout.encloseCenterBottom(submit, reset));
            submit.addActionListener((evt) -> {

                if (firstname.getText().equals("")
                        || lastname.getText().equals("")
                        || email.getText().equals("")
                        || password.getText().equals("")
                        || b.getText().equals("")) {
                    ToastBar.showMessage("Veuillez verifier les champs", FontImage.MATERIAL_INFO);
                } else {
                    Admin a = new Admin();
                    a.setLogin(email.getText());
                    a.setPass(password.getText());
                    a.setPrenom(firstname.getText());
                    a.setNom(lastname.getText());

                    if (cb1.getPressedIcon().getImageName().equals("on-off-on.png")) {
                        a.setEtat(true);
                    } else {
                        a.setEtat(false);
                    }

                    a.setType(b.getText());

                    

                    if(AdminService.getInstance().ajouterAdmin(a)){
                    Dialog.show("Succes !", "Admin ajouté avec succes!", "ok", null);
                    Accueil.showBack();
                    }else{
                        Dialog.show("Erreur !", "Admin existe deja!", "ok", null);
                    }
                }
            });

            reset.addActionListener((evt) -> {
                firstname.setText("");
                lastname.setText("");
                email.setText("");
                password.setText("");
                b.setText("");
            });
        }
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getAllStyles().setFgColor(0xFF0000);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
}
