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
    public boolean resultOK;
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
        String url = Statics.BASE_URL + "/aDD?salaire="+d.getSalaire()+"?diplome="+d.getDiplome()+"?description="+d.getDescription()+ 
                
      "?domaine=" +d.getDomaine()+"?lettre"+d.getLettre()+"?offre_emploi_id="+d.getOffre_emploi_id()+"?freelancer_id="+d.getFreelancer_id(); //création de l'URL
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
                       float id= Float.parseFloat(obj.get("id").toString());
                       String description = obj.get("description").toString();
                        
                       d.setId((int)id);
                       d.setDescription(description);
                       
                       String dateconverter = obj.get("date_creation").toString().substring(obj.get("date_creation").toString().indexOf("timestamp") 
                               +10,obj.get("obj").toString().lastIndexOf("}"));
                       Date currentTime= new Date(Double.valueOf(dateconverter).longValue() *1000);
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
    
    
    
}
