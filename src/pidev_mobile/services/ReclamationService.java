/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.services;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
<<<<<<< Updated upstream
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
=======
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes
import java.util.Map;
import pidev_mobile.entities.Reclamation;
import pidev_mobile.utils.Statics;

/**
 *
<<<<<<< Updated upstream
 * @author Ayari Ghaith
 */
public class ReclamationService {
    
    public static ReclamationService instance = null;
    
    private ConnectionRequest req;
    
    public static ReclamationService getInstance() {
        if (instance == null)
                instance = new ReclamationService();
        return instance;
    }
    
    public ReclamationService(){
      req = new ConnectionRequest();
    }




public void ajouterReclamation(Reclamation reclamation) {
    
    String url =Statics.BASE_URL+"/ajouterReclamation"+reclamation.getType()+"texte reclamation"+reclamation.getTexte_reclamation();
    
    req.setUrl(url);
    req.addResponseListener( (e) -> {
        
        String str = new String(req.getResponseData());
        System.out.println("data == "+str);
    });
    NetworkManager.getInstance().addToQueueAndWait(req);

}


public ArrayList<Reclamation>affichageReclamatio() {

    ArrayList<Reclamation> result = new ArrayList<>();
    
    String url = Statics.BASE_URL+"/showReclamation";
    
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();
            
            try {
                  Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  
                  List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapReclamations.get("root");
                  
                  for(Map<String, Object> obj: listOfMaps) {
                      Reclamation re = new  Reclamation();
                      
                      float id = Float.parseFloat( obj.get("id").toString());
                      
                      String objet = obj.get("objet").toString();
                      
                      
                      String type = obj.get("type").toString();
                      String texte_reclamation = obj.get("texte reclamation").toString();
                      String email_utilisateur = obj.get("email utilisateur").toString();
                      String nom_utilisateur = obj.get("nom utilisateur").toString();
//                      boolean etat =  obj.get("etat").toString());

                     re.setId((int) id);
                     re.setType(type);
                     re.setTexte_reclamation(texte_reclamation);
                     re.setEmail_utilisateur(email_utilisateur);
                     re.setNom_utilisateur(nom_utilisateur);
//                     re.setEtat( etat);
                     
                     String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("obj").toString().lastIndexOf("}"));
                     
                     Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                     
                      SimpleDateFormat Formatter = new SimpleDateFormat("yyyy-MM-dd");
                      String dateString = Formatter.format(currentTime);
                      re.setDate_reclamation(dateString);
                  }
                  
                 
            }catch(Exception ex){
                ex.printStackTrace();
            }
        
        }
    });
        }
}
=======
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
  String url = Statics.BASE_URL + "/api_reclamation_new" + r.getType()
                + "&description_reclamation=" + r.getTextReclamation();
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
        String url = Statics.BASE_URL + "/api/reclamation/delete/" + r.getId();
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
        String url = Statics.BASE_URL+"/api/reclamation";
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
        String url = Statics.BASE_URL + "/api/reclamation/update/" + r.getId()
                +"?type=" + r.getType()
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
>>>>>>> Stashed changes
