/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.back.superAdmin;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Admin;
import pidev_mobile.services.AdminService;

/**
 *
 * @author seifeddine
 */
public class SAAccueil extends BaseForm {

    private ArrayList<Admin> list;

    public SAAccueil(Resources res) {
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
        Label justatext = new Label("delete this before working");

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
        Button AddAdmin = new Button("Créer Admin");

        AddAdmin.addActionListener((evt) -> {
            AjoutAdminForm ajout = new AjoutAdminForm(res, this, false, null);
            //ajout.setUpdate(false);
            ajout.show();

        });
//         addAll(justatext,AddAdmin);

        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_QUESTION_ANSWER, s);

        refreshList();
        ArrayList<Admin> listAdminReclamation = new ArrayList<>();
        ArrayList<Admin> listAdminEmploi = new ArrayList<>();
        ArrayList<Admin> listAdminEvent = new ArrayList<>();

        for (Admin admin : list) {
            if (admin.getType().equals("Admin des reclamations")) {
                listAdminReclamation.add(admin);
            }
            if (admin.getType().equals("Admin des emplois")) {
                listAdminEmploi.add(admin);
            }
            if (admin.getType().equals("Admin des events")) {
                listAdminEvent.add(admin);
            }
        }

        Container contReclamation = new Container();
        for (Admin admin : listAdminReclamation) {

            contReclamation.addComponent(addAdminItem(admin, res));

        }
        Container container1 = BoxLayout.encloseY(contReclamation);

        Container contEvent = new Container();
        for (Admin admin : listAdminEvent) {

            contEvent.addComponent(addAdminItem(admin, res));
        }
        Container container2 = BoxLayout.encloseY(contEvent);

        Container contEmploi = new Container();
        for (Admin admin : listAdminEmploi) {

            contEmploi.addComponent(addAdminItem(admin, res));
        }
        Container container3 = BoxLayout.encloseY(contEmploi);
        t.addTab("Reclamations", icon1, container1);
        t.addTab("Events", icon1, container2);
        t.addTab("Emplois", icon1, container3);
        //t.addTab("Tab2", new SpanLabel("Some text directly in the tab"));

        addAll(AddAdmin, t);

        this.getContentPane().addPullToRefresh(new Runnable() {
            @Override
            public void run() {
                refreshList();
                revalidate();
            }
        });

    }

    public Container addAdminItem(Admin admin, Resources res) {

        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());
        Container All = new Container(BoxLayout.y());

        Label Name = new Label(admin.getNom() + " " + admin.getPrenom());
        Label login = new Label(admin.getLogin());
        Label etat = new Label();
        if (admin.isEtat() == true) {
            etat.setText("Compte Activé");
            etat.getAllStyles().setFgColor(0x00FF00);
        } else {
            etat.setText("Compte desactivé");
            etat.getAllStyles().setFgColor(0xFF0000);
        }
        Label approuve = new Label(String.valueOf(admin.getApprouve() + " Approuvés  | " + admin.getNonApprouve() + " Non Approuvés"));
        Label nonapprouve = new Label(String.valueOf(admin.getNonApprouve() + " Non Approuvés"));
        Component lineseparator = createLineSeparator(0xeeeeee);

        details.addAll(Name, login, etat, approuve);

        //EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("load.jpg"), false);
        Image img = res.getImage("profile-pic.jpg");

        ImageViewer image = new ImageViewer(img);
        holder.addAll(image, details);

        System.out.println("------------------admin id = " + admin.getId() + "--------" + admin.isEtat() + "----------");

        Name.addPointerReleasedListener(ev -> {
            ViewAdminProfileForm view = new ViewAdminProfileForm(res, this, admin);
            //view.setAdmin(admin);
            view.show();
        });

        All.addAll(holder, lineseparator);
        All.setLeadComponent(Name);
        return All;
    }

    public void refreshList() {
        list = AdminService.getInstance().AfficherAdmins();
    }
}
