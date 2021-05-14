/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.pidev_mobile.entities.EventLoisir;
import com.pidev_mobile.entities.Formation;
import static com.pidev_mobile.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class EventService {
     private static EventService instance;
    private ConnectionRequest req;
    public ArrayList<EventLoisir> Event;
        public ArrayList<EventLoisir> Event1;
    public boolean valide;
    public boolean ResultOK;

    public EventService() {
        req=new ConnectionRequest();
    }
    
      public static EventService getInstance(){
        if(instance ==null)
            instance=new EventService();
            return instance;
        
    }
      
      public boolean addEvent(EventLoisir F,String type,int idu){
         ResultOK=false;
         String url=BASE_URL+"/addEventMobile/?labelle="+F.getLabelle()+"&description="+F.getDescription()+"&domaine="+F.getDomaine()+"&lieu="+F.getLieu()+
                 "&dated="+F.getDateDebut()+"&datef="+F.getDateFin()+"&nb="+F.getNbParticipant()+/*"/"+F.isEtat()+*/"&lat="+F.getLat()+"&lng="+F.getLng()+"&image="+F.getImageE()
                 +"&type="+type+"&idu="+idu;
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
     
     public ArrayList<EventLoisir> parseResponse(String json){
        ArrayList<EventLoisir> Events=new ArrayList<>();
         try {
          
            JSONParser j=new JSONParser();
            Map<String,Object> FormationListJson=j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> List=(List<Map<String,Object>>)FormationListJson.get("root");
            for(Map<String,Object> obj:List) {
                EventLoisir F=new EventLoisir();
                //System.out.println(obj.get("id").toString().split(".")[0]);
                F.setId((int)Float.parseFloat(obj.get("id").toString()));
                F.setLabelle(obj.get("labelle").toString());
                F.setDescription(obj.get("description").toString());
                F.setDomaine(obj.get("domaine").toString());
                F.setImageE(obj.get("image").toString());
                F.setNbParticipant(Integer.parseInt(obj.get("nb").toString()));
                F.setLieu(obj.get("lieu").toString());
               F.setDateDebut(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(obj.get("dated").toString()));
                F.setDateFin(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(obj.get("datef").toString()));
                //System.out.println(obj.get("dated").toString()+"    hadha date"); 
               F.setLat(Double.parseDouble(obj.get("lat").toString()));
                F.setLng(Double.parseDouble(obj.get("lng").toString()));
                Events.add(F);
            }
           
        } catch (IOException ex) {
     
        } catch (ParseException ex) {
                  
        }
         return Events;
     }
    
     public ArrayList<EventLoisir> getAll(String type,int id){
         
         String url=BASE_URL+"/getEventMobile?type="+type+"&idu="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Event=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return Event;
         
     }
     
     
     public boolean Participaer(int idu,int idf,String typeu){
         
          String url=BASE_URL+"/ParticiperEventMobile?idu="+idu+"&idf="+idf+"&typeu="+typeu;
          req.setUrl(url);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 try {
                     JSONParser j=new JSONParser();
                     Map<String,Object> FormationListJson=j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                     //System.out.println(FormationListJson.get("root").getClass());
                             List<Map<String,Object>> List=(List<Map<String,Object>>)FormationListJson.get("root");
                     if(List.get(0).get("valide").toString().equals("true")){
                         valide=true;
                     };
                     req.removeResponseListener(this);
                 } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                 }
             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
          System.out.println("ouh mchet++"+valide);
         return valide;
     }
     
        public ArrayList<EventLoisir> getEParUser(String type,int id){
         
         String url=BASE_URL+"/getUserEventMobile?type="+type+"&idu="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Event1=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return Event1;
         
     } 
        
        public ArrayList<EventLoisir> getParticipation(String type,int id){
         
         String url=BASE_URL+"/getPartEventMobile?type="+type+"&idu="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Event=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
        
         return Event;
         
     }
     
        
  public boolean SupprimerEvent(EventLoisir e){
        ResultOK=false;
        String url=BASE_URL+"/deleteEventMobile?id="+e.getId();
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              try {
                     JSONParser j=new JSONParser();
                     Map<String,Object> FormationListJson=j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                     //System.out.println(FormationListJson.get("root").getClass());
                             List<Map<String,Object>> List=(List<Map<String,Object>>)FormationListJson.get("root");
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
        
      
  }  
  
   public boolean ModifierEvent(EventLoisir F){
       ResultOK=false;
         String url=BASE_URL+"/updateFormationMobile/?id="+F.getId()+"&labelle="+F.getLabelle()+"&description="+F.getDescription()+"&domaine="+F.getDomaine()+"&lieu="+F.getLieu()+
                 "&dated="+F.getDateDebut()+"&datef="+F.getDateFin()+"&nb="+F.getNbParticipant()+/*"/"+F.isEtat()+*/"&lat="+F.getLat()+"&lng="+F.getLng()+"&image="+F.getImageE();
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
   
   public boolean SupprimerEventPart(String type,int idu,int ide){
        ResultOK=false;
        String url=BASE_URL+"/deleteEventPartMobile?type="+type+"&idu="+idu+"&ide"+ide;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              try {
                     JSONParser j=new JSONParser();
                     Map<String,Object> FormationListJson=j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                     //System.out.println(FormationListJson.get("root").getClass());
                             List<Map<String,Object>> List=(List<Map<String,Object>>)FormationListJson.get("root");
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
        
      
  }  
    
    
    
}
