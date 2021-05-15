/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.back.superAdmin;

import com.codename1.components.SpanLabel;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.DefaultLookAndFeel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import pidev_mobile.base.BaseForm;
import pidev_mobile.charts.DemandeChart;
import pidev_mobile.charts.OffreChart;
//import pidev_mobile.charts.ReclamationChart;
import pidev_mobile.charts.UserChart;

/**
 *
 * @author seifeddine
 */
public class Statistique extends BaseForm {

    public Statistique(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Statistique");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1, lab2, lab3);

        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
        Label justatext = new Label("delete this before working");

        Tabs t = new Tabs();
        t.hideTabs();

        Style s = UIManager.getInstance().getComponentStyle("Button");
        FontImage radioEmptyImage = FontImage.createMaterial(FontImage.MATERIAL_RADIO_BUTTON_UNCHECKED, s);
        FontImage radioFullImage = FontImage.createMaterial(FontImage.MATERIAL_RADIO_BUTTON_CHECKED, s);
        ((DefaultLookAndFeel) UIManager.getInstance().getLookAndFeel()).setRadioButtonImages(radioFullImage, radioEmptyImage, radioFullImage, radioEmptyImage);

        Container container1 = new Container();
        container1.add(new UserChart().execute());
        
        
        Container container2 = new Container();
        container2.add(new OffreChart().execute());
        
        Container container3 = new Container();
        container3.add(new OffreChart().execute());
        
        
        t.addTab("Tab1", container1);
        t.addTab("Tab2",container2);
        t.addTab("Tab3",container3);

        RadioButton firstTab = new RadioButton("");
        RadioButton secondTab = new RadioButton("");
        RadioButton thirdTab = new RadioButton("");
        firstTab.setUIID("Container");
        secondTab.setUIID("Container");
        thirdTab.setUIID("Container");
        new ButtonGroup(firstTab, secondTab,thirdTab);
        firstTab.setSelected(true);
        Container tabsFlow = FlowLayout.encloseCenter(firstTab, secondTab);

        add(t);
        add(BorderLayout.south(tabsFlow));

        t.addSelectionListener((i1, i2) -> {
            switch (i2) {
                case 0:
                    if (!firstTab.isSelected()) {
                        firstTab.setSelected(true);
                    }
                    break;
                case 1:
                    if (!secondTab.isSelected()) {
                        secondTab.setSelected(true);
                    }
                    break;
                    
            }
        });
        
        t.addSelectionListener((i2, i3) -> {
            switch (i3) {
                case 0:
                    if (!secondTab.isSelected()) {
                        secondTab.setSelected(true);
                    }
                    break;
                case 1:
                    if (!thirdTab.isSelected()) {
                        thirdTab.setSelected(true);
                    }
                    break;
                    
            }
        });
        

    }
}
