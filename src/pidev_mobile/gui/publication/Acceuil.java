/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.publication;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Preferences;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.Publications;
import pidev_mobile.services.PublicationService;

/**
 *
 * @author ksemt
 */
public class Acceuil extends BaseForm{
    
    Form current;
    public Acceuil(Resources res){
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Acceuil");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
        
        //minna tibda tiktib, ma tfasa5 chay mil fo9ani
        
        ArrayList<Publications>list = PublicationService.getInstance().afficherPublication();
        
        for(Publications pub : list) {
            
            String urlImage = "file:///C:/wamp64/www/PiDev/public/picture/"+pub.getImage() ;
            
            Image placeHolder = Image.createImage(1000,1000);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
            URLImage urlimg = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            
            urlimg.fetch();
            addButton(urlimg,pub,res);
            
            ScaleImageLabel image = new ScaleImageLabel(urlimg);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            
        }
        
    }

    private void addButton(Image img, Publications pub, Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        
        Container cnt = BorderLayout.west(image) ;
        
        TextArea taNom = new TextArea(pub.getNomUtil()+ " "+pub.getPrenomUtil());
        TextArea ta1 = new TextArea(pub.getDescription());
        TextArea ta2 = new TextArea(pub.getDate_publication());
        ta1.setUIID("newsTopLine");
        ta1.setEditable(false);
        ta2.setUIID("Label");
        ta2.setEditable(false);
        taNom.setUIID("newsTopLine");
        taNom.setEditable(false);
        
            
        
        
        
        Label limg = new Label();
        limg.setIcon(img);
        
        
        //button supprimer
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
           
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","vous voulez supprimer cette publication?","Annuler","Oui")){
                dig.dispose();
            }
            else {
                dig.dispose();
                
                if(PublicationService.getInstance().deletePublication(pub.getId())) {
                    new Acceuil(res).show();
                    
                }
            }
            
        });
        
        
        cnt.add(BorderLayout.WEST, BoxLayout.encloseY(
                BoxLayout.encloseX(taNom,lSupprimer),
                BoxLayout.encloseX(ta1),
                BoxLayout.encloseX(limg),
                BoxLayout.encloseX(ta2))
        
        );
        
        add(cnt);
        add(createLineSeparator(0xffffff));
    }
    
}