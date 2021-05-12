/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.evenement;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.pidev_mobile.entities.EventLoisir;
import com.pidev_mobile.myapp.MyApplication;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FormEventList extends Form {
    private List<EventLoisir> events;

    public FormEventList() {
        setLayout(BoxLayout.y());
       
        final FormEventList fl = this;

         events = new ArrayList<>();
        Container List = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    //formations = List.getEquipes();
                }
                if (index + amount > events.size()) {
                    amount = events.size() - index;
                }
                if (amount <= 0) {
                    return null;
                }
                Component[] more = new Component[amount];
                for (int i = 0; i < amount; i++) {
                    EventLoisir e = events.get(i);
                    Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(events.get(i).getLabelle());

                    ImageViewer image = new ImageViewer(MyApplication.theme.getImage(events.get(i).getImageE()).scaled(300, 400));
                     Label ldated = new Label(String.valueOf(events.get(i).getDateDebut()));
                     Label ldatef = new Label(String.valueOf(events.get(i).getDateFin()));
                    holderDetails.addAll(lbnom,ldated,ldatef);
                    holder.addAll(image, holderDetails);
                    lbnom.addPointerReleasedListener((evt) -> {

                        FormEventDetails fd = new FormEventDetails(e);
                        fd.show();
                    });
                    holder.setLeadComponent(lbnom);
                    more[i] = holder;
                }
                return more;

            }

        };
        List.setScrollableY(false);
        this.add(List);
    }
    
    
}
