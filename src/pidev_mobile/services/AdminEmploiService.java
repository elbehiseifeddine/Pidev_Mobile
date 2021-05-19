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
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pidev_mobile.entities.offreEmploi;
import pidev_mobile.entities.offreStage;
import pidev_mobile.utils.Statics;

/**
 *
 * @author ahmed
 */
public class AdminEmploiService {
    
    public static AdminEmploiService instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    
    public static AdminEmploiService getInstance() {
        if (instance == null) {
            instance = new AdminEmploiService();
        }
        return instance;
    }

    public AdminEmploiService() {
        req = new ConnectionRequest();
    }
    
    public void ActiverOffreEmploi(int idOffre,int IdAdmin){
        
        String url = Statics.BASE_URL + "/activateOffreEmploiJson?"
                + "id_offre_emploi=" + idOffre
                + "&idAdmin=" + IdAdmin;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is Activate offre emploi in AdminEmploiService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void ActiverOffreStage(int idOffre,int IdAdmin){
        
        String url = Statics.BASE_URL + "/activateOffreStageJson?"
                + "id_offre_Stage=" + idOffre
                + "&idAdmin=" + IdAdmin;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is Activate offre stage in AdminEmploiService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void DesactiverOffreEmploi(int idOffre){
        
        String url = Statics.BASE_URL + "/deactivateOffreEmploiJson?"
                + "id_offre_emploi=" + idOffre;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is deactivate offre emploi in AdminEmploiService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void DesactiverOffreStage(int idOffre){
        
        String url = Statics.BASE_URL + "/deactivateOffreStageJson?"
                + "id_offre_stage=" + idOffre;
        
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String responceData = new String(req.getResponseData());

            System.out.println("this is deactivate offre stage in AdminEmploiService | data == " + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<offreEmploi> ListeOffreEmploiApprouve(int idAdmin){
        ArrayList<offreEmploi> list = new ArrayList<>();
        String url = Statics.BASE_URL + "/AllAllOffreEmploiJson?idAdmin="+idAdmin;
        req.setUrl(url);
        String data = new String(req.getResponseData());
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser json;
            json = new JSONParser();
            try {
                Map<String, Object> MapAdmins = json.parseJSON(new CharArrayReader(data.toCharArray()));
                List<Map<String, Object>> ListOfMap = (List<Map<String, Object>>) MapAdmins.get("root");

                for (Map<String, Object> obj : ListOfMap) {

                    offreEmploi a = new offreEmploi();
                    float id = Float.parseFloat(obj.get("id").toString());
                    String nomprojet = String.valueOf(obj.get("nom_projet"));
                    String competance = String.valueOf(obj.get("competances"));
                    String description = String.valueOf(obj.get("description"));
                    String domaine = String.valueOf(obj.get("domaine"));
                    Date dateCreation = (Date) obj.get("date_creation");
                    String devise = String.valueOf(obj.get("devise"));
                    float etat = Float.parseFloat(obj.get("etat").toString());

                    float salaire = Float.parseFloat(obj.get("salaire").toString());

                   a.setCompetences(competance);
                   a.setDateCreation(dateCreation);
                   a.setDescription(description);
                   a.setNom(nomprojet);
                   a.setEtat((int)etat);
                   a.setDomaine(domaine);
                   a.setDevise(devise);
                   a.setSalaire(salaire);
                   a.setId((int)id);
                   

                    list.add(a);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return list;
    }
    
    public ArrayList<offreStage> ListeOffreStageApprouve(int idAdmin){
        ArrayList<offreStage> list = new ArrayList<>();
        String url = Statics.BASE_URL + "/AllAllOffreEmploiJson?idAdmin="+idAdmin;
        req.setUrl(url);
        String data = new String(req.getResponseData());
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser json;
            json = new JSONParser();
            try {
                Map<String, Object> MapAdmins = json.parseJSON(new CharArrayReader(data.toCharArray()));
                List<Map<String, Object>> ListOfMap = (List<Map<String, Object>>) MapAdmins.get("root");

                for (Map<String, Object> obj : ListOfMap) {

                    offreStage a = new offreStage();
                    float id = Float.parseFloat(obj.get("id").toString());
                    String nomprojet = String.valueOf(obj.get("nom_projet"));
                    String competance = String.valueOf(obj.get("competances"));
                    String description = String.valueOf(obj.get("description"));
                    String domaine = String.valueOf(obj.get("domaine"));
                    String type = String.valueOf(obj.get("type_stage"));
                    String duree = String.valueOf(obj.get("duree"));
                    Date dateCreation = (Date) obj.get("date_creation");
                    float etat = Float.parseFloat(obj.get("etat").toString());

                    float idsos = Float.parseFloat(obj.get("societe_id").toString());

                   a.setCompetence(competance);
                   a.setDescription(description);
                   a.setNomProjet(nomprojet);
                   a.setId((int)id);
                   a.setDuree(duree);
                   a.setEtat((int)etat);
                   a.setDomaine(domaine);
                   a.setTypeStage(type);
                   a.setDateCreation(dateCreation);
                   a.setIdSociete((int)idsos);

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
