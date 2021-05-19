/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.evenement;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Preferences;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.Date;

import pidev_mobile.MyApplication;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.EventLoisir;
import pidev_mobile.services.EventService;

/**
 *
 * @author ASUS
 */
public class FormAjoutEvent extends BaseForm {
double lat=0;
double lng=0;
TextField Lieu;
String imageE="";
    public FormAjoutEvent(Form f,Resources res) {
     super("Newsfeed", BoxLayout.y());
        
      
         
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        
        getContentPane().setScrollVisible(false);
 
        setLayout(new BorderLayout());
        super.addSideMenu(res);
         Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        Container c=BoxLayout.encloseY(lab1,lab2,lab3);
        add(BorderLayout.NORTH,c);
        setTitle("Ajout Event"); 
        setLayout(new BorderLayout());
        
         // we create 4mm material arrow images for the back button and the Get started button
   
    // The camera button is comprised of 3 pieces. A label containing the image and the transparent button
    // with the camera icon on top. This is all wrapped in the title container where the title background image
    // is placed using the theme. We chose to use a Label rather than a background using the cameraLayer so
    // the label will preserve the original size of the image without scaling it and take up the space it needs
   
 
   

    TextField Lebelle = new TextField("", "Lebelle");
    TextField domaine = new TextField("", "Domaine");
    TextField description = new TextField("", "Description");
    Lieu = new TextField("", "Lieu" );
   TextField montant = new TextField("", "nombre participant" );
   Label limg=new Label("");
  
  Button image=new Button("image");
  image.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog( ".jpg, .jpeg,.png, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                   

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);;
                            limg.setIcon(logo);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            try {
                                String namePic = saveFileToDevice(file, ext);
                                imageE=namePic;
                                System.out.println("hadha howa"+namePic);
                            } catch (URISyntaxException ex) {
                            } catch (IOException ex) {
                            }

                            revalidate();

                        
                    }
                    }
                        });
            }
                });
    Picker dateDD = new Picker();
    dateDD.setType(Display.PICKER_TYPE_DATE);
    Picker dateDT = new Picker();
    dateDT.setType(Display.PICKER_TYPE_TIME);
    
    Picker dateFD = new Picker();
    dateFD.setType(Display.PICKER_TYPE_DATE);
    Picker dateFT = new Picker();
    dateFT.setType(Display.PICKER_TYPE_TIME);
 

    // The phone and full name have vertical separators, we use two table layouts to arrange them correctly
    // so the vertical separator will be in the right place
    TableLayout fullNameLayout = new TableLayout(1, 3);
    Container fullName = new Container(fullNameLayout);
    fullName.add(fullNameLayout.createConstraint().widthPercentage(49), Lebelle).
        add(fullNameLayout.createConstraint().widthPercentage(1), createSeparator()).
        add(fullNameLayout.createConstraint().widthPercentage(50), domaine);
    
    TableLayout datedLayout = new TableLayout(1, 3);
    Container DateD = new Container(datedLayout);
    
      DateD.add(datedLayout.createConstraint().widthPercentage(49), dateDD).
        add(datedLayout.createConstraint().widthPercentage(1), createSeparator()).
        add(datedLayout.createConstraint().widthPercentage(50), dateDT);
      
      TableLayout datefLayout = new TableLayout(1, 3);
    Container DateF = new Container(datefLayout);
    
      DateF.add(datedLayout.createConstraint().widthPercentage(49), dateFD).
        add(datedLayout.createConstraint().widthPercentage(1), createSeparator()).
        add(datedLayout.createConstraint().widthPercentage(50), dateFT);
    
      
     
    



    // The button in the south portion needs the arrow icon to be on the right side so we place the text on the left
   Button southButton = new Button("Ajouter");
    
    southButton.setUIID("SouthButton");
    southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if(Lebelle.getText().isEmpty() ||description.getText().isEmpty()||domaine.getText().isEmpty()||Lieu.getText().isEmpty()||montant.getText().isEmpty()){
                    Dialog.show("Erreur","Veuillez remplir tous les champs",new Command("OK"));
                    
                }
                 else{
                try {
                    /* Date dateddeb=new SimpleDateFormat("yyyy-MM-dd").format(dateDD.getDate());*/
                    // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(dateDD.getDate()));
                    //System.out.println(new SimpleDateFormat("HH:mm:ss").format(dateDT.getText()+":00"));
                    
                    
                    Date datedeb=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(
                            new SimpleDateFormat("yyyy-mm-dd").format(dateDD.getDate())+" "+new SimpleDateFormat("HH:mm:ss").format(dateDT.getText()+":00"));
                      Date datefin=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(
                            new SimpleDateFormat("yyyy-mm-dd").format(dateFD.getDate())+" "+new SimpleDateFormat("HH:mm:ss").format(dateFT.getText()+":00"));
                    System.out.println(datedeb);
                    EventLoisir E=new EventLoisir(Lebelle.getText(), description.getText(), domaine.getText(), Lieu.getText(), datedeb, datefin, Integer.parseInt(montant.getText()), true, 1, 1, imageE);
                    //datedeb=new Date( new SimpleDateFormat("yyyy-mm-dd").format(dateDD.getDate())+" "+new SimpleDateFormat("HH:mm:ss").format(dateDT.getText()+":00"));
                   
                    if(new EventService().addEvent(E,"freelancer",(int) Math.round(Preferences.get("id", 1.1)))){
                           FormAcceuilEvent p=(FormAcceuilEvent)f;
                           p.refreshLayout();
                        
                           
                       }             
                       else{
                           System.out.println("hors jeu");
                       }

                } catch (ParseException ex) {
                    System.out.println("nien");
                }
                catch(NumberFormatException exc){
                    
                      Dialog.show("Erreur","Entrer un nombre valide",new Command("OK"));
                }
            }
            }
        });


    // we add the components and the separators the center portion contains all of the elements in a box
    // Y container which we allow to scroll. BorderLayout Containers implicitly disable scrolling
    Container by = BoxLayout.encloseY(
                    fullName,
                    createSeparator(),
                    description,
                    createSeparator(),
                    Lieu,
                    createSeparator(),
                    DateD,
                     createSeparator(),
                 
                     DateF,
                     createSeparator(),
                     montant,
                     createSeparator(),
                     image,
            createSeparator(),
            limg,createSeparator()
                   
                    
            );
    by.setScrollableY(true);
    this.
            add(BorderLayout.SOUTH, southButton).
            add(BorderLayout.CENTER, by);
      }
    
       private Label createSeparator() {
    Label sep = new Label();
    sep.setUIID("Separator");
    // the separator line  is implemented in the theme using padding and background color, by default labels
    // are hidden when they have no content, this method disables that behavior
    sep.setShowEvenIfBlank(true);
    return sep;
}
       
        public void setInfo(String l, double lat1,double lng1){
           Lieu.setText(l);
           lat=lat1;
           lng=lng1;
           
           
       }
         protected String saveFileToDevice(String hi, String ext) throws IOException, URISyntaxException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
    
}
