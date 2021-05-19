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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import pidev_mobile.entities.DemandeEmploi;
import pidev_mobile.entities.DemandeStage;
import pidev_mobile.utils.Statics;

/**
 *
 * @author ely
 */
public class ServiceDemandeStage {
     public static ServiceDemandeStage instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
       public static ServiceDemandeStage getInstance() {
        if (instance == null) {
            instance = new ServiceDemandeStage();
        }
        return instance;
    }

    public ServiceDemandeStage() {
        req = new ConnectionRequest();
    }
       
      //********Add******///
    public void ADDemandeS(DemandeStage d){
        String url = Statics.BASE_URL + "/aDDS?type="+d.getType()+"&etude="+d.getEtude()+"&description="+d.getDescription()+ 
                
      "&domaine=" +d.getDomaine()+"&lettre="+d.getLettre()+"&offre_stage_id="+d.getOffre_stage_id()+
        "&freelancer_id="+d.getFreelancer_id()+"&duree="+d.getDuree(); //création de l'URL
        req.setUrl(url);
         req.addResponseListener((e) -> {
            
             String str = new String(req.getResponseData());
             System.out.println("data====>"+str);
           

        });
       NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    //*********AFFICHAGE******//
    public ArrayList<DemandeStage>getDemandesE(){
        ArrayList<DemandeStage> demandes= new ArrayList();
        String url = Statics.BASE_URL +"/getDemandeS";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp;
               jsonp= new JSONParser();
                try{
                    Map<String,Object> mapDemandes=jsonp.parseJSON(new java.io.CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listofmaps=(List<Map<String,Object>>) mapDemandes.get("root");
                    for(Map<String,Object> obj: listofmaps){
                       DemandeStage d = new DemandeStage();
                       float id= Float.parseFloat(obj.get("id").toString());
                       float id_f=Float.parseFloat(obj.get("freelancer_id").toString());
                       float id_o=Float.parseFloat(obj.get("offre_stage_id").toString());
                       float etat=Float.parseFloat(obj.get("etat").toString());
                       float duree=Float.parseFloat(obj.get("duree").toString());
                       String description = obj.get("description").toString();
                        
                     
                       d.setId((int)id);
                       d.setDescription(description);
                       d.setType(obj.get("type").toString());
                       d.setFreelancer_id((int)id_f);
                       d.setOffre_stage_id((int)id_o);
                       d.setDomaine(obj.get("domaine").toString());
                       d.setEtat((int)etat);
                       d.setLettre(obj.get("lettre").toString());
                       d.setDuree((int)duree);
                       d.setEtude(obj.get("etude").toString());
                      
                       demandes.add(d);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return demandes;
    }
    
    public DemandeEmploi DetailDemande(int id, DemandeEmploi demande){
        String url = Statics.BASE_URL +"/detailDemandeE?id="+id;
        
      req.setUrl(url);
      req.addResponseListener((evt) -> {
          JSONParser jsonp = new JSONParser();
          try {
      Map<String,Object>obj=jsonp.parseJSON(new java.io.CharArrayReader(new String(req.getResponseData()).toCharArray()));
    int id_f=Integer.parseInt(obj.get("freelancer_id").toString());
    int id_o=Integer.parseInt(obj.get("offre_emploi_id").toString());
 demande.setFreelancer_id(id_f);
 demande.setOffre_emploi_id(id_o);
 demande.setDescription(obj.get("description").toString());

              
          }catch(IOException ex){
              System.out.println("error related to sql "+ex.getMessage());
          }
         
      });
        return demande;
    }
    /*****DELETE***/
    public boolean DeleteDemande(int id){
        String url = Statics.BASE_URL +"/deleteDS?id="+id;
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
    /*****UPDATE****/
    public boolean UpdateDemande(DemandeStage d){
        String url = Statics.BASE_URL +"/UpdateDS?id="+d.getId()+"&type="
        +d.getType()+"&etude="+d.getEtude()+"&Domaine="+d.getDomaine()+
         "&Description="+d.getDescription()+"&Lettre="+d.getLettre()+"&duree="+d.getDuree();
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK=req.getResponseCode() ==200;
               req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     return resultOK;   
    }
    
       
}
