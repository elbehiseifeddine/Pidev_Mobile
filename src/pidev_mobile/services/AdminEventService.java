/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pidev_mobile.entities.EventLoisir;
import pidev_mobile.entities.Formation;
import pidev_mobile.utils.Statics;

/**
 *
 * @author ahmed
 */
public class AdminEventService {
    public static AdminEventService instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    
    public static AdminEventService getInstance() {
        if (instance == null) {
            instance = new AdminEventService();
        }
        return instance;
    }

    public AdminEventService() {
        req = new ConnectionRequest();
    }
    
     public void ActiverEventLoisir(int idEvent,int IdAdmin){
        
        String url = Statics.BASE_URL + "/activateEvenementJson?"
                + "id_event_loisir=" + idEvent
                + "&idAdmin=" + IdAdmin;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is Activate evenement in AdminEventService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void ActiverFormation(int idformation,int IdAdmin){
        
        String url = Statics.BASE_URL + "/activateFormationJson?"
                + "id_formation=" + idformation
                + "&idAdmin=" + IdAdmin;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is Activate formation in AdminEventService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void DesactiverEventLoisir(int idEvent){
        
        String url = Statics.BASE_URL + "/deactivateEvenementJson?"
                + "id_event_loisir=" + idEvent;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is deactivate event loisir in AdminEventService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void DesactiverOffreStage(int idFormation){
        
        String url = Statics.BASE_URL + "/deactivateFormationJson?"
                + "id_formation=" + idFormation;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is deactivate Formation in AdminEvantService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<EventLoisir> ListeEventLoisirApprouve(int idAdmin){
        ArrayList<EventLoisir> list = new ArrayList<>();
        String url = Statics.BASE_URL + "/AllEventJson?idAdmin="+idAdmin;
        req.setUrl(url);
        String data = new String(req.getResponseData());
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser json;
            json = new JSONParser();
            try {
                Map<String, Object> MapAdmins = json.parseJSON(new CharArrayReader(data.toCharArray()));
                List<Map<String, Object>> ListOfMap = (List<Map<String, Object>>) MapAdmins.get("root");

                for (Map<String, Object> obj : ListOfMap) {

                    EventLoisir a = new EventLoisir();
                    float id = Float.parseFloat(obj.get("id").toString());
                    String labelle = String.valueOf(obj.get("labelle"));
                    String lieu = String.valueOf(obj.get("lieu"));
                    String description = String.valueOf(obj.get("description"));
                    String domaine = String.valueOf(obj.get("domaine"));
                    boolean etat = Boolean.parseBoolean(obj.get("etat").toString());

                    float nbParticipant = Float.parseFloat(obj.get("nb_participant").toString());
                    float idf = Float.parseFloat(obj.get("id_fr_id").toString());
                    float ids = Float.parseFloat(obj.get("id_so_id").toString());

                    //conversion date 
//                    String dateConverter = obj.get("date_debut").toString().substring(obj.get("date_debut").toString().indexOf("timestamp")+10,  obj.get("date_debut").toString().lastIndexOf(")"));
//                    Date currentDate = new Date(Double.valueOf(dateConverter).longValue() *1000);
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy_mm_dd");
//                    String dateString = formatter.format(currentDate);
                    //a.setDateDebut(dateString);
                    
                    //sinon
//                    Timestamp dateCreation = (Timestamp) obj.get("date_debut");
//                    a.setDateDebut(dateCreation);
                    
                   a.setId((int)id);
                   a.setDescription(description);
                   a.setDomaine(domaine);
                   a.setEtat((boolean)etat);
                   a.setLabelle(labelle);
                   a.setLieu(lieu);
                   a.setNbParticipant((int)nbParticipant);
                   

                    list.add(a);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return list;
    }
    
    public ArrayList<Formation> ListeFormationApprouve(int idAdmin){
        ArrayList<Formation> list = new ArrayList<>();
        String url = Statics.BASE_URL + "/AllFormationJson?idAdmin="+idAdmin;
        req.setUrl(url);
        String data = new String(req.getResponseData());
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser json;
            json = new JSONParser();
            try {
                Map<String, Object> MapAdmins = json.parseJSON(new CharArrayReader(data.toCharArray()));
                List<Map<String, Object>> ListOfMap = (List<Map<String, Object>>) MapAdmins.get("root");

                for (Map<String, Object> obj : ListOfMap) {

                    Formation a = new Formation();
                    float id = Float.parseFloat(obj.get("id").toString());
                    String labelle = String.valueOf(obj.get("labelle"));
                    String lieu = String.valueOf(obj.get("lieu"));
                    String description = String.valueOf(obj.get("description"));
                    String domaine = String.valueOf(obj.get("domaine"));
                    boolean etat = Boolean.parseBoolean(obj.get("etat").toString());

                    float montant = Float.parseFloat(obj.get("montant").toString());
//                    float idf = Float.parseFloat(obj.get("id_fr_id").toString());
//                    float ids = Float.parseFloat(obj.get("id_so_id").toString());

                    //conversion date 
//                    String dateConverter = obj.get("date_debut").toString().substring(obj.get("date_debut").toString().indexOf("timestamp")+10,  obj.get("date_debut").toString().lastIndexOf(")"));
//                    Date currentDate = new Date(Double.valueOf(dateConverter).longValue() *1000);
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy_mm_dd");
//                    String dateString = formatter.format(currentDate);
                    //a.setDateDebut(dateString);
                    
                    //sinon
//                    Timestamp dateCreation = (Timestamp) obj.get("date_debut");
//                    a.setDateDebut(dateCreation);
                    
                    a.setDescription(description);
                    a.setDomaine(domaine);
                    a.setLabelle(labelle);
                    a.setLieu(lieu);
                    a.setMontant(montant);
                    a.setId((int)id);
                    a.setEtat((boolean)etat);
                   

                    list.add(a);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return list;
    }
    
}
