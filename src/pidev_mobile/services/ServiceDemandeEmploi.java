/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.services;

import com.codename1.io.ConnectionRequest;
import pidev_mobile.entities.DemandeEmploi;
import pidev_mobile.utils.Statics;
import com.codename1.charts.views.DialChart;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Dialog;

import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ely
 */
public class ServiceDemandeEmploi {
    
     
    public static ServiceDemandeEmploi instance=null;
    
    public boolean resultOK=true;
    private ConnectionRequest req;

      public static ServiceDemandeEmploi getInstance() {
        if (instance == null) {
            instance = new ServiceDemandeEmploi();
        }
        return instance;
    }

    public ServiceDemandeEmploi() {
        req= new ConnectionRequest();
    }
    //********Add******///
    public void ADDemandeE(DemandeEmploi d){
        String url = Statics.BASE_URL + "/aDD?salaire="+d.getSalaire()+"&diplome="+d.getDiplome()+"&description="+d.getDescription()+ 
                
      "&domaine=" +d.getDomaine()+"&Lettre="+d.getLettre()+"&offre_emploi_id="+d.getOffre_emploi_id()+"&freelancer_id="+d.getFreelancer_id(); //création de l'URL
        req.setUrl(url);
         req.addResponseListener((e) -> {
            
             String str = new String(req.getResponseData());
             System.out.println("data====>"+str);
           

        });
       NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    //*********AFFICHAGE******//
    public ArrayList<DemandeEmploi>getDemandesE(){
        ArrayList<DemandeEmploi> demandes= new ArrayList();
        String url = Statics.BASE_URL +"/getDemandeE";
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
                       DemandeEmploi d = new DemandeEmploi();
                      int id=(int) Float.parseFloat(obj.get("id").toString());
                      int id_f=(int)Float.parseFloat(obj.get("freelancer_id").toString());
                      int id_o=(int)Float.parseFloat(obj.get("offre_emploi_id").toString());
                       int etat=(int)Float.parseFloat(obj.get("etat").toString());
                       float sa=Float.parseFloat(obj.get("salaire").toString());
                       String description = obj.get("description").toString();
                        
                       d.setId(id);
                       d.setDescription(description);
                       d.setDiplome(obj.get("diplome").toString());
                       d.setFreelancer_id(id_f);
                       d.setOffre_emploi_id(id_o);
                       d.setDomaine(obj.get("domaine").toString());
                       d.setEtat((int)etat);
                       d.setLettre(obj.get("lettre").toString());
                       d.setSalaire(sa);
                      
                       demandes.add(d);
                        System.out.println("pff");
                    }
                }catch(NullPointerException e){
                    e.printStackTrace();
                } catch (IOException ex) {
                    ex.getStackTrace();
                }
                req.removeResponseListener(this);
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
        String url = Statics.BASE_URL +"/deleteDe?id="+id;
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
    public boolean UpdateDemande(DemandeEmploi d){
        String url = Statics.BASE_URL +"/UpdateD?id="+d.getId()+"&salaire="
        +d.getSalaire()+"&Diplome="+d.getDiplome()+"&Domaine="+d.getDomaine()+
         "&Description="+d.getDescription()+"&Lettre="+d.getLettre();
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
