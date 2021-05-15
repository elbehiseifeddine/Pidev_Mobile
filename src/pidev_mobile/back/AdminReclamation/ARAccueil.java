/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.back.AdminReclamation;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import pidev_mobile.back.superAdmin.AjoutAdminForm;
import pidev_mobile.back.superAdmin.ViewAdminProfileForm;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Admin;
import pidev_mobile.entities.Reclamation;
import pidev_mobile.services.AdminReclamationService;
import pidev_mobile.services.AdminService;

/**
 *
 * @author seifeddine
 */
public class ARAccueil extends BaseForm {

    private ArrayList<Reclamation> list;

    public ARAccueil(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Accueil");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1, lab2, lab3);

        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

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

//         addAll(justatext,AddAdmin);
        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_QUESTION_ANSWER, s);

        refreshList();
        revalidate();

        Container contReclamation = new Container();
        if (list.isEmpty()) {
            Label erreur = createForFont(largeBoldSystemFont, "Aucune reclamations pour le moment !!");
            contReclamation.addComponent(erreur);
        } else {
            for (Reclamation reclamation : list) {

                contReclamation.addComponent(createRankWidget(reclamation, res));
            }
        }
        Container container1 = BoxLayout.encloseY(contReclamation);

        t.addTab("Reclamations", icon1, container1);

        //t.addTab("Tab2", new SpanLabel("Some text directly in the tab"));
        addAll(t);

        
        this.getContentPane().addPullToRefresh(new Runnable() {
            @Override
            public void run() {
                refreshList();
                revalidate();
            }
        });

    }
    

    public Container addRecItem(Reclamation rec, Resources res) {

        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());
        Container All = new Container(BoxLayout.y());

        Label From = new Label(rec.getNomUser());
        Label Type = new Label(rec.getType());
        Label Date = new Label(rec.getDateReclamation());

        Component lineseparator = createLineSeparator(0xeeeeee);

        details.addAll(From, Type, Date);

        //EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("load.jpg"), false);
        Image img = res.getImage("Reclamation.png");

        ImageViewer image = new ImageViewer(img);
        holder.addAll(image, details);

        All.addAll(holder, lineseparator);

        return All;
    }

    public void refreshList() {
        list = AdminReclamationService.getInstance().AfficherReclamations();
    }

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getAllStyles().setFgColor(0xFF0000);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public SwipeableContainer createRankWidget(Reclamation reclamation, Resources res) {
        MultiButton button = new MultiButton(reclamation.getNomUser());
        button.setTextLine2(reclamation.getType());
        button.setTextLine3(reclamation.getDateReclamation());
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createButtons(reclamation)),
                addRecItem(reclamation, res));
    }
    private Container createButtons(Reclamation reclamation) {
        Container slider = new Container();
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Button Approuver = new Button(FontImage.createMaterial(FontImage.MATERIAL_CHECK, s).toImage());
        Button Supprimer = new Button(FontImage.createMaterial(FontImage.MATERIAL_DELETE, s).toImage());

        Approuver.addActionListener((evt) -> {
            AdminReclamationService.getInstance().ActiverReclamation(reclamation.getId(), Integer.parseInt(Preferences.get("id", null)));

            ToastBar.showMessage("Reclamation traité", FontImage.MATERIAL_CHECK);
            refreshList();
            getCurrentForm().revalidate();
            this.refreshTheme();

        });

        Supprimer.addActionListener((evt) -> {
            AdminReclamationService.getInstance().DeactiverReclamation(reclamation.getId());
            refreshList();     
            revalidate();
            this.refreshTheme();

            ToastBar.showMessage("Reclamation Supprimer!", FontImage.MATERIAL_INFO);

        });
        slider.addAll(Approuver, Supprimer);
        return slider;
    }

}
