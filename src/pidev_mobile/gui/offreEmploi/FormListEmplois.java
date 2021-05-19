/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.gui.offreEmploi;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import pidev_mobile.base.BaseForm;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.services.emploiService;

/**
 *
 * @author Ghassen Riahi
 */
public class FormListEmplois extends BaseForm {
    List<offreEmploi> Emplois;

    public FormListEmplois(Resources res) {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("blackContainer");
        setTitle("Offre Emploi");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        Label lab1 = new Label("  ");
        Label lab2 = new Label("  ");
        Label lab3 = new Label("  ");
        addAll(lab1,lab2,lab3);
       // setTitle("Liste des Emplois");
       // setLayout(BoxLayout.y());
       // Button pdf = new Button("télécharger");
        Emplois=emploiService.getInstance().getAll(9);
        
        for(int i=0;i<Emplois.size();i++){
            this.add(this.addpartholder(Emplois.get(i)));
             Button pdf=new Button("télécharger");
             pdf.addActionListener(l->{
             Dialog.show("Succés", " Félicitation téléchargement éffectué", "OK", null);
         // System.out.println("good");
            createPDF();
           
        });
//              Label line = new Label("--------------------------------------------------------------------------");
                     add(pdf);
        }
        
        
        
        
        
    }
    public void refresh(){
        this.removeAll();
         Emplois=emploiService.getInstance().getAll(9);
        
        for(int i=0;i<Emplois.size();i++){
            this.add(this.addpartholder(Emplois.get(i)));
        }
        this.revalidate();
    }
     public Container addpartholder(offreEmploi f){
          Container holder = new Container(BoxLayout.y());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbnom = new Label(f.getNom());
                    Label lbcompetences = new Label(f.getCompetences());
                    Label lbdesc = new Label(f.getDescription());
                    Label lbdomaine = new Label(f.getDomaine());
                    Label lbsalaire = new Label(String.valueOf(f.getSalaire()));
                    Label lbdevise = new Label(f.getDevise());
                    
                    

                     Label ldateC = new Label(new SimpleDateFormat("yyyy-mm-dd").format(f.getDateCreation()));
                     Label ldateE = new Label(new SimpleDateFormat("yyyy-mm-dd").format(f.getDateExpiration()));
                   
                    
                  
//                   
                     holder.addAll(lbnom,lbcompetences,lbdesc,lbdomaine,lbsalaire,lbdevise,ldateC,ldateE);
                   
                    lbnom.addPointerReleasedListener((evt) -> {

                         
                    });
                    holder.setLeadComponent(lbnom);
                    return holder;
     }
     
     
    //  public String PDF="Fichier PDF";
    public void createPDF() {
        try {
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("D:/Download/pidev/listE.pdf"));
            document.open();
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addContent(Document document) throws DocumentException {
        offreEmploi f=new offreEmploi();
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("19-05-2021" + "                                          " + "PDF offre emploi"));
        //addEmptyLine(preface, 1);
        preface.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        //preface.add(new Paragraph(
        //        "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), smallBold));
        preface.add(new Paragraph("Emploi DETAILS"));
        preface.add(new Paragraph("Nom du projet : Onyx \n"+f.getNom()));
        preface.add(new Paragraph("Compétences acquise : java \n"));
        preface.add(new Paragraph("description  :  appilcation mobile\n"));
        preface.add(new Paragraph("domaine  : infoematique \n"));
        preface.add(new Paragraph("salaire : 1500 Dinars \n"));

        addEmptyLine(preface, 2);
        addEmptyLine(preface, 8);

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

     
      

    
    
}
