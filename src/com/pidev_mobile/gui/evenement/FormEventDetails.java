/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.evenement;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.pidev_mobile.entities.EventLoisir;
import com.pidev_mobile.myapp.MyApplication;

/**
 *
 * @author ASUS
 */
public class FormEventDetails extends Form {

    public FormEventDetails(EventLoisir e) {
        Container holder = new Container(BoxLayout.x());
        Container holderDetails = new Container(BoxLayout.y());
        
        ImageViewer image = new ImageViewer(MyApplication.theme.getImage(e.getImageE()).scaled(300, 400));
        Label lbnom = new Label(e.getLabelle());
        Label ldomaine = new Label(e.getDomaine());
        Label ldesc = new Label(e.getDescription());
        Label ldated = new Label(String.valueOf(e.getDateDebut()));
        Label ldatef = new Label(String.valueOf(e.getDateFin()));
        Label lmontant = new Label(String.valueOf(e.getNbParticipant()));
        

        holderDetails.addAll(lbnom,ldesc,ldomaine,ldated,ldatef,lmontant);
        holder.addAll(image, holderDetails);
    }
    
    
}
