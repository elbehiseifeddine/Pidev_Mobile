/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pidev_mobile.entities.Reclamation;
import pidev_mobile.utils.Statics;

/**
 *
 * @author Djap_ii
 */
public class ReclamationService {

    public ArrayList<Reclamation> reclamations;
    public static ReclamationService instance = null;
    public boolean resultOK;

    private final ConnectionRequest req;

    public ReclamationService() {
        req = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }

    public boolean addRec(Reclamation r) {
        //création de l'URL
  String url = Statics.BASE_URL + "/reclamation/new?type=" + r.getType()
                + "&texteReclamation=" + r.getTextReclamation()
          + "&email=" + Preferences.get("email", null)
          + "&nom=" + Preferences.get("nom", null)
          ;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteRec(Reclamation r) {
        String url = Statics.BASE_URL + "/reclamation/delete?id=" + r.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public ArrayList<Reclamation> parseRecs(String jsonText) throws ParseException{
         
         ArrayList<Reclamation> rec=new ArrayList<>();
         try {
            
            JSONParser j = new JSONParser();
            Map<String,Object> recsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)recsListJson.get("root");
           
            for (Map<String,Object> obj : list){
                
                Reclamation r = new Reclamation();
                
                float id = Float.parseFloat(obj.get("id").toString());
               String  type= obj.get("type").toString();
                String date_Reclamation = obj.get("dateReclamation").toString();
                String texte_reclamation = obj.get("texteReclamation").toString();
                String email_utilisateur = obj.get("email_utilisateur").toString();
                String nom_utilisateur = obj.get("nom_utilisateur").toString();
                String etat = obj.get("etat").toString();
                
                 r.setId((int)id);
                r.setType(type);
                r.setDateReclamation(date_Reclamation);
                r.setTextReclamation(texte_reclamation);
                
                r.setEmailUser(email_utilisateur);
                r.setNomUser(nom_utilisateur);
                r.setEtat(false);
                
                rec.add(r);
            }   
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return rec;
    }
     
     public ArrayList<Reclamation> getAllRecs(){
        String url = Statics.BASE_URL+"/reclamation";
        req.setUrl(url);
        req.setPost(false);
        reclamations = new ArrayList<>();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                try {
                reclamations.clear();
                    reclamations = parseRecs(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
     public boolean updateRec(Reclamation r) {
        String url = Statics.BASE_URL + "/reclamation/update?id=" + r.getId()
                +"&type=" + r.getType()
                + "&texteReclamation=" + r.getTextReclamation();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}

 