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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.events.ActionListener;
import pidev_mobile.entities.offreEmploi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static pidev_mobile.utils.Statics.BASE_URL;
//import static utils.Statics.BASE_URL;

/**
 *
 * @author Ghassen Riahi
 */
public class emploiService {
    
     private static emploiService instance;
    private ConnectionRequest req;
    public ArrayList<offreEmploi> offre;
        public ArrayList<offreEmploi> offre1;
    public boolean valide;
    public boolean ResultOK;

    public emploiService() {
        req=new ConnectionRequest();
    }
    
      public static emploiService getInstance(){
        if(instance ==null)
            instance=new emploiService();
            return instance;
        
    }
      
       public boolean addEmploi(offreEmploi F,String type,int idu){
         ResultOK=false;
         String url=BASE_URL+"/emploi/addE/?nom="+F.getNom()+"&comptetences="+F.getCompetences()+"&description="+F.getDescription()+"&domaine="+F.getDomaine()+"&salaire="+F.getSalaire()+
                 "&devise="+F.getDevise()+"&date_creation="+F.getDateCreation()+"&date_expiration="+F.getDateExpiration()+"&etat="+1;
         //+"&ids="+idu;
                
          req.setUrl(url);
            req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 ResultOK=req.getResponseCode()==200;
                 req.removeResponseListener(this);
             }
         });
        NetworkManager.getInstance().addToQueueAndWait(req);
         return ResultOK;
     }
       
      public ArrayList<offreEmploi> parseResponse(String json){
          ArrayList<offreEmploi> offreEmploi=new ArrayList<>();
      
         try {
          
            JSONParser j=new JSONParser();
            Map<String,Object> EmploiListJson=j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> List=(List<Map<String,Object>>)EmploiListJson.get("root");
             for(Map<String,Object> obj:List) {
                offreEmploi F=new offreEmploi();
                //System.out.println(obj.get("id").toString().split(".")[0]);
                F.setId((int)Float.parseFloat(obj.get("id").toString()));
                F.setNom(obj.get("nom").toString());
                F.setCompetences(obj.get("competences").toString());
                F.setDescription(obj.get("description").toString());
                F.setDomaine(obj.get("domaine").toString());
                F.setSalaire(Float.parseFloat(obj.get("salaire").toString()));
               
                F.setDevise(obj.get("devise").toString());
               F.setDateCreation(new SimpleDateFormat("yyyy-mm-dd").parse(obj.get("dateC").toString()));
               F.setDateExpiration(new SimpleDateFormat("yyyy-mm-dd").parse(obj.get("dateE").toString()));
                //System.out.println(obj.get("dated").toString()+"    hadha date"); 
               
                
                offreEmploi.add(F);
            }
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
     
     }
catch (ParseException ex) {
            System.out.println(ex.getMessage());
                  
        }
         return offreEmploi;
     }
       
        public ArrayList<offreEmploi> getAll(int id){
         
         String url=BASE_URL+"/emploi/getAllE?id="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                offre=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return offre;
         
     }
        
          public ArrayList<offreEmploi> getOffreParUser(int id){
         
         String url=BASE_URL+"/showOwnEmploiE?id="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              //   offre1=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return offre1;
         
     } 
          
           public boolean SupprimerEMPLOI(offreEmploi e){
        ResultOK=false;
        String url=BASE_URL+"/emploi/deleteE?id="+e.getId();
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              try {
                     JSONParser j=new JSONParser();
                     Map<String,Object> EmploiListJson=j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                     //System.out.println(FormationListJson.get("root").getClass());
                             List<Map<String,Object>> List=(List<Map<String,Object>>)EmploiListJson.get("root");
                     if(List.get(0).get("valide").toString().equals("true")){
                         ResultOK=true;
                     };
                     req.removeResponseListener(this);
                 } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                 }
             }
             
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return ResultOK;
//        
      
  } } 
  
    
//}
