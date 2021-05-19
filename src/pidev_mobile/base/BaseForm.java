/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.base;

/**
 *
 * @author seifeddine
 */
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import pidev_mobile.back.AdminEmploi.AEMAccueil;
import pidev_mobile.back.AdminEmploi.EmploiStageApprouver;
import pidev_mobile.back.AdminEvent.AEAccueil;
import pidev_mobile.back.AdminEvent.EventFormApprouver;
import pidev_mobile.back.AdminReclamation.ARAccueil;
import pidev_mobile.back.AdminReclamation.ReclamationApprouver;
import pidev_mobile.back.superAdmin.ListeFreelancers;
import pidev_mobile.back.superAdmin.ListeSociete;
import pidev_mobile.back.superAdmin.SAAccueil;
import pidev_mobile.back.superAdmin.SAPublication;
import pidev_mobile.back.superAdmin.Statistique;
import pidev_mobile.gui.demandeEmploi.ConsulterDemandeEmploi;
import pidev_mobile.gui.demandeEmploi.ConsulterDemandeSociete;
import pidev_mobile.gui.demandeStage.ConsulterDemandeStage;
import pidev_mobile.gui.evenement.FormAcceuilEvent;

import pidev_mobile.gui.formation.FormAcceuilFormation;

import pidev_mobile.gui.offreEmploi.ConsulterEmploi;
import pidev_mobile.gui.offreEmploi.ConsulterEmploiFreelancer;
import pidev_mobile.gui.offreEmploi.FormAjoutEmploi;
import pidev_mobile.gui.offreEmploi.FormListEmplois;
import pidev_mobile.gui.offreEmploi.OffreEmploi;
import pidev_mobile.gui.offreStage.ConsulterStage;
import pidev_mobile.gui.offreStage.ConsulterStageFreelancer;
import pidev_mobile.gui.offreStage.FormAjoutStage;
import pidev_mobile.gui.offreStage.FormListStage;
import pidev_mobile.gui.offreStage.OffreStage;
import pidev_mobile.gui.publication.Publication;
import pidev_mobile.gui.utilisateur.NewsfeedForm;
import pidev_mobile.gui.utilisateur.ProfileForm;
import pidev_mobile.gui.utilisateur.ProfileFormSociete;
import pidev_mobile.gui.utilisateur.SignInForm;
import pidev_mobile.gui.utilisateur.WalkthruForm;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label nom = new Label(Preferences.get("prenom", "") +" "+ Preferences.get("nom", null));
        Label email = new Label(Preferences.get("email", null));
        Label space = new Label(" ");
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        tb.addComponentToSideMenu(BorderLayout.south(FlowLayout.encloseCenter(nom)));
        tb.addComponentToSideMenu(BorderLayout.south(FlowLayout.encloseCenter(email)));
        tb.addComponentToSideMenu(space);
        if (Preferences.get("type", null).equals("Freelancer")) {
            tb.addMaterialCommandToSideMenu(" Accueil", FontImage.MATERIAL_HOME, e -> new Publication(res).show());
            tb.addMaterialCommandToSideMenu(" Evenements", FontImage.MATERIAL_BOOK_ONLINE, e -> new FormAcceuilEvent(res).show());
            tb.addMaterialCommandToSideMenu(" Formations", FontImage.MATERIAL_CAST_FOR_EDUCATION, e -> new FormAcceuilFormation(res).show());
            tb.addMaterialCommandToSideMenu(" Consulter Emplois", FontImage.MATERIAL_WORK, e -> new ConsulterEmploiFreelancer(res).show());
            tb.addMaterialCommandToSideMenu(" Consulter Stage", FontImage.MATERIAL_SCHOOL, e -> new ConsulterStageFreelancer(res).show());
            tb.addMaterialCommandToSideMenu(" Consulter Demande d'emploi", FontImage.MATERIAL_UPDATE, e -> new ConsulterDemandeEmploi(res).show());
            tb.addMaterialCommandToSideMenu(" Consulter Demande d'Stage", FontImage.MATERIAL_UPDATE, e -> new ConsulterDemandeStage(res).show());
            tb.addMaterialCommandToSideMenu(" Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ProfileForm(res).show());
            tb.addMaterialCommandToSideMenu(" Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                Preferences.clearAll();
                new SignInForm(res).show();
            });
        } else if (Preferences.get("type", null).equals("Societe")) {
            tb.addMaterialCommandToSideMenu(" Accueil", FontImage.MATERIAL_HOME, e -> new Publication(res).show());
            tb.addMaterialCommandToSideMenu(" Evenements", FontImage.MATERIAL_BOOK_ONLINE, e -> new FormAcceuilEvent(res).show());
            tb.addMaterialCommandToSideMenu(" Formations", FontImage.MATERIAL_CAST_FOR_EDUCATION, e -> new FormAcceuilFormation(res).show());
            tb.addMaterialCommandToSideMenu(" Créer Offres Emplois", FontImage.MATERIAL_WORK, e -> new FormAjoutEmploi(res).show());
            tb.addMaterialCommandToSideMenu(" Consulter Offres Emplois", FontImage.MATERIAL_VISIBILITY, e -> new FormListEmplois(res).show());
            tb.addMaterialCommandToSideMenu(" Créer Offres Stage", FontImage.MATERIAL_SCHOOL, e -> new FormAjoutStage(res).show());
            tb.addMaterialCommandToSideMenu(" Consulter Offres Stages", FontImage.MATERIAL_VISIBILITY, e -> new FormListStage(res).show());
            tb.addMaterialCommandToSideMenu(" Consulter les Demandes", FontImage.MATERIAL_UPDATE, e -> new ConsulterDemandeSociete(res).show());
            tb.addMaterialCommandToSideMenu(" Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ProfileFormSociete(res).show());
            tb.addMaterialCommandToSideMenu(" Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                Preferences.clearAll();
                new WalkthruForm(res).show();
            });

        } else if (Preferences.get("type", null).equals("SuperAdmin")) {
            tb.addMaterialCommandToSideMenu(" Accueil", FontImage.MATERIAL_HOME, e -> new SAAccueil(res).show());
            tb.addMaterialCommandToSideMenu(" Statistique", FontImage.MATERIAL_HOME, e -> new Statistique(res).show());
            tb.addMaterialCommandToSideMenu(" Publication", FontImage.MATERIAL_HOME, e -> new SAPublication(res).show());
            tb.addMaterialCommandToSideMenu(" Liste Freelancers", FontImage.MATERIAL_HOME, e -> new ListeFreelancers(res).show());
            tb.addMaterialCommandToSideMenu(" Liste Societe", FontImage.MATERIAL_HOME, e -> new ListeSociete(res).show());
            tb.addMaterialCommandToSideMenu(" Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                Preferences.clearAll();
                new SignInForm(res).show();
            });
        } else if (Preferences.get("type", null).equals("Admin des events")) {
            tb.addMaterialCommandToSideMenu(" Accueil", FontImage.MATERIAL_HOME, e -> new AEAccueil(res).show());
            tb.addMaterialCommandToSideMenu(" Eventment/Formation Approuver", FontImage.MATERIAL_HOME, e -> new EventFormApprouver(res).show());
            tb.addMaterialCommandToSideMenu(" Statistique", FontImage.MATERIAL_HOME, e -> new Statistique(res).show());
            tb.addMaterialCommandToSideMenu(" Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                Preferences.clearAll();
                new SignInForm(res).show();
            });
        } else if (Preferences.get("type", null).equals("Admin des emplois")) {
            tb.addMaterialCommandToSideMenu(" Accueil", FontImage.MATERIAL_HOME, e -> new AEMAccueil(res).show());
            tb.addMaterialCommandToSideMenu(" Emploi/Stage Approuver", FontImage.MATERIAL_HOME, e -> new EmploiStageApprouver(res).show());
            tb.addMaterialCommandToSideMenu(" Statistique", FontImage.MATERIAL_HOME, e -> new Statistique(res).show());
            tb.addMaterialCommandToSideMenu(" Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                Preferences.clearAll();
                new SignInForm(res).show();
            });
        } else if (Preferences.get("type", null).equals("Admin des reclamations")) {
            tb.addMaterialCommandToSideMenu(" Accueil", FontImage.MATERIAL_HOME, e -> new ARAccueil(res).show());
            tb.addMaterialCommandToSideMenu(" Reclamation Approuver", FontImage.MATERIAL_HOME, e -> new ReclamationApprouver(res).show());
            tb.addMaterialCommandToSideMenu(" Statistique", FontImage.MATERIAL_HOME, e -> new Statistique(res).show());
            tb.addMaterialCommandToSideMenu(" Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                Preferences.clearAll();
                new SignInForm(res).show();
            });
        }

    }
}
