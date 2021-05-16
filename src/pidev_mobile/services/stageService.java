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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pidev_mobile.entities.offreStage;
import static pidev_mobile.utils.Statics.BASE_URL;


/**
 *
 * @author Ghassen Riahi
 */
public class stageService {
     private static stageService instance;
    private ConnectionRequest req;
    public ArrayList<offreStage> offre;
        public ArrayList<offreStage> offre1;
    public boolean valide;
    public boolean ResultOK;

    public stageService() {
         req=new ConnectionRequest();
    }
    
     public static stageService getInstance(){
        if(instance ==null)
            instance=new stageService();
            return instance;
        
    }
     
      public boolean addStage(offreStage F,String type,int idu){
         ResultOK=false;
         String url=BASE_URL+"/stage/addS/?nom="+F.getNomProjet()+"&comptetences="+F.getCompetence()+"&description="+F.getDescription()+"&domaine="+F.getDomaine()+"&duree="+F.getDuree()+
                 "&typeStage="+F.getTypeStage()+"&date_creation="+F.getDateCreation()+"&date_expiration="+F.getDateExpiration()+"&etat="+1;
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
      
      
       public ArrayList<offreStage> parseResponse(String json){
          ArrayList<offreStage> offreStage=new ArrayList<>();
      
         try {
          
            JSONParser j=new JSONParser();
            Map<String,Object> StageListJson=j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> List=(List<Map<String,Object>>)StageListJson.get("root");
             for(Map<String,Object> obj:List) {
                offreStage F=new offreStage();
                //System.out.println(obj.get("id").toString().split(".")[0]);
                F.setId((int)Float.parseFloat(obj.get("id").toString()));
                F.setNomProjet(obj.get("nom").toString());
                F.setCompetence(obj.get("competences").toString());
                F.setDescription(obj.get("description").toString());
                F.setDomaine(obj.get("domaine").toString());
                F.setDuree(obj.get("duree").toString());
               
                F.setTypeStage(obj.get("type").toString());
               F.setDateCreation(new SimpleDateFormat("yyyy-mm-dd ").parse(obj.get("dateC").toString()));
                F.setDateExpiration(new SimpleDateFormat("yyyy-mm-dd ").parse(obj.get("dateE").toString()));
                //System.out.println(obj.get("dated").toString()+"    hadha date"); 
               
                
                offreStage.add(F);
            }
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
     
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
                  
        }
         return offreStage;
     }
       
        public ArrayList<offreStage> getAll(int id){
         
         String url=BASE_URL+"/stage/getAllS?id="+id;
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
        
          public ArrayList<offreStage> getOffreParUser(int id){
         
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
  public boolean SupprimerSTAGE(offreStage e){
        ResultOK=false;
        String url=BASE_URL+"/stage/deleteS?id="+e.getId();
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              try {
                     JSONParser j=new JSONParser();
                     Map<String,Object> StageListJson=j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                     //System.out.println(FormationListJson.get("root").getClass());
                             List<Map<String,Object>> List=(List<Map<String,Object>>)StageListJson.get("root");
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
