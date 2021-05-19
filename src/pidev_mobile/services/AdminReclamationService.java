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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pidev_mobile.entities.Admin;
import pidev_mobile.entities.Reclamation;
import pidev_mobile.utils.Statics;

/**
 *
 * @author ahmed
 */
public class AdminReclamationService {

    public static AdminReclamationService instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    
    public static AdminReclamationService getInstance() {
        if (instance == null) {
            instance = new AdminReclamationService();
        }
        return instance;
    }
    
    public AdminReclamationService() {
        req = new ConnectionRequest();
    }
    
    public void ActiverReclamation(int idReclamation, int IdAdmin) {
        
        String url = Statics.BASE_URL + "/activateReclamationJson?"
                + "id=" + idReclamation
                + "&idAdmin=" + IdAdmin;
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                
                System.out.println("this is activate reclamation in AdminReclamationService | data == " + responceData);
                req.removeResponseListener(this);
                
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void DeactiverReclamation(int idReclamation) {
        
        String url = Statics.BASE_URL + "/deactivateReclamationJson?"
                + "id=" + idReclamation;
               
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                
                System.out.println("this is Deactivate reclamation in AdminReclamationService | data == " + responceData);
                req.removeResponseListener(this);
                
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public ArrayList<Reclamation> AfficherReclamations() {
        ArrayList<Reclamation> list = new ArrayList<>();
        String url = Statics.BASE_URL + "/admin/reclamationJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Map<String, Object> MapRecs = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMap = (List<Map<String, Object>>) MapRecs.get("root");

                    for (Map<String, Object> obj : ListOfMap) {

                        Reclamation a = new Reclamation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String texteReclamation = String.valueOf(obj.get("texteReclamation"));
                        String dateReclamation = String.valueOf(obj.get("dateReclamation"));
                        String emailUtilisateur = String.valueOf(obj.get("emailUtilisateur"));
                        String nomUtilisateur = String.valueOf(obj.get("nomUtilisateur"));
                        String type = String.valueOf(obj.get("type"));
                        boolean etat = Boolean.parseBoolean(obj.get("etat").toString());
                        

                        a.setId((int) id);
                        a.setDateReclamation(dateReclamation);
                        a.setEmailUser(emailUtilisateur);
                        a.setNomUser(nomUtilisateur);
                        a.setTextReclamation(texteReclamation);
                        a.setType(type);
                        a.setEtat(etat);
                       

                        list.add(a);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return list;
    }
    
    public ArrayList<Reclamation> AfficherReclamationsApprouve() {
        ArrayList<Reclamation> list = new ArrayList<>();
        String url = Statics.BASE_URL + "/AllReclamationJson?idAdmin="+(int) Math.round(Preferences.get("id", 1.1));
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Map<String, Object> MapRecs = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMap = (List<Map<String, Object>>) MapRecs.get("root");

                    for (Map<String, Object> obj : ListOfMap) {

                        Reclamation a = new Reclamation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String texteReclamation = String.valueOf(obj.get("texteReclamation"));
                        String dateReclamation = String.valueOf(obj.get("dateReclamation"));
                        String emailUtilisateur = String.valueOf(obj.get("emailUtilisateur"));
                        String nomUtilisateur = String.valueOf(obj.get("nomUtilisateur"));
                        String type = String.valueOf(obj.get("type"));
                        boolean etat = Boolean.parseBoolean(obj.get("etat").toString());
                        

                        a.setId((int) id);
                        a.setDateReclamation(dateReclamation);
                        a.setEmailUser(emailUtilisateur);
                        a.setNomUser(nomUtilisateur);
                        a.setTextReclamation(texteReclamation);
                        a.setType(type);
                        a.setEtat(etat);
                       

                        list.add(a);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return list;
    }
    
}
