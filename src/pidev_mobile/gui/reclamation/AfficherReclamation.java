/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.reclamation;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Reclamation;
import pidev_mobile.services.ReclamationService;

/**
 *
 * @author Ayari Ghaith
 */
public class AfficherReclamation extends BaseForm {

    protected boolean isCurrentTrending() {
        return true;
    }

    public AfficherReclamation(Resources res) {

        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));

//        super.addSideMenu(res);

//        Button gotoAjouter = new Button("Ajouter Reclamation");
//
//        gotoAjouter.addActionListener(e -> {
//
//            new AjouterReclamation(theme).show();
//
//        });

        ///affichage
        ArrayList<Reclamation> list = ReclamationService.getInstance().getAllRecs();
        System.out.println(list);

        Container by = new Container(new GridLayout(2, 1));
        by.setScrollableY(true);
        Label space1 = new Label("   ");

        by.addAll(space1);

        Label space4 = new Label("  ");

        for (int i = 0; i < list.size(); i++) {
            
            String urlImage = ("logo.png");

            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            

            int height = Display.getInstance().convertToPixels(11.5f);
            int width = Display.getInstance().convertToPixels(14f);

            Button img = new Button(urlim.fill(width, height));
            image.setUIID("Label");

            Container cnt = BorderLayout.west(image);


            Button delete = new Button("Supprimer");
            Button update = new Button("Modifier");

            Label space2 = new Label(" ");

            TextArea type = new TextArea("Type : " + list.get(i).getType());
            type.setUIID("Label");
            type.setEditable(false);

            TextArea texte_reclamation = new TextArea("texteReclamation : " + list.get(i).getTextReclamation());
            texte_reclamation.setUIID("Label");
            texte_reclamation.setEditable(false);

            TextArea date = new TextArea("Description : " + list.get(i).getDateReclamation());
            date.setUIID("Label");
            date.setEditable(false);

//            TextArea status = new TextArea("status : " + list.get(i).getStatus());
//            status.setUIID("Label");
//            status.setEditable(false);

            int x = list.get(i).getId();

            delete.addActionListener(e -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer cette Reclamation?", "Annuler", "Oui")) {

                    dig.dispose();
                } else {
                    dig.dispose();

                    Reclamation r = new Reclamation();
                    r.setId(x);

                    if (ReclamationService.getInstance().deleteRec(r)) {

                        new AfficherReclamation(res).show();
                    }

                }

            });

            update.addActionListener(e -> {

                Reclamation r = new Reclamation();

                r.setType(type.getText());
                r.setTextReclamation(texte_reclamation.getText());

                r.setId(x);
                new ModifierReclamation(res, r).show();

            });

            cnt.add(BorderLayout.WEST, BoxLayout.encloseY(type, texte_reclamation, date, BoxLayout.encloseX(update, delete)));

            by.addAll(
                    cnt,
                    space2
            );

        }

        add(BorderLayout.NORTH, space4);
//        add(BorderLayout.NORTH, (gotoAjouter);
        add(BorderLayout.CENTER, by);

    }
}

    

