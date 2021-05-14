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
public class FormationService {
    private static FormationService instance;
    private ConnectionRequest req;
    public ArrayList<Formation> Forms;
        public ArrayList<Formation> Forms1;
    public boolean valide;
    public boolean ResultOK;

    public FormationService() {
    req=new ConnectionRequest();
    }
    public static FormationService getInstance(){
        if(instance ==null)
            instance=new FormationService();
            return instance;
        
    }
    
    
     public boolean addFormation(Formation F,String type,int idu){
         ResultOK=false;
         String url=BASE_URL+"/addFormationMobile/?labelle="+F.getLabelle()+"&description="+F.getDescription()+"&domaine="+F.getDomaine()+"&lieu="+F.getLieu()+
                 "&dated="+F.getDateDebut()+"&datef="+F.getDateFin()+"&montant="+F.getMontant()+/*"/"+F.isEtat()+*/"&lat="+F.getLat()+"&lng="+F.getLng()+"&image="+F.getImageF()
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
     
     public ArrayList<Formation> parseResponse(String json){
        ArrayList<Formation> Formations=new ArrayList<>();
         try {
          
            JSONParser j=new JSONParser();
            Map<String,Object> FormationListJson=j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> List=(List<Map<String,Object>>)FormationListJson.get("root");
            for(Map<String,Object> obj:List) {
                Formation F=new Formation();
                //System.out.println(obj.get("id").toString().split(".")[0]);
                F.setId((int)Float.parseFloat(obj.get("id").toString()));
                F.setLabelle(obj.get("labelle").toString());
                F.setDescription(obj.get("description").toString());
                F.setDomaine(obj.get("domaine").toString());
                F.setImageF(obj.get("image").toString());
                F.setMontant(Float.parseFloat(obj.get("montant").toString()));
                F.setLieu(obj.get("lieu").toString());
               F.setDateDebut(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(obj.get("dated").toString()));
                F.setDateFin(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(obj.get("datef").toString()));
                //System.out.println(obj.get("dated").toString()+"    hadha date"); 
               F.setLat(Double.parseDouble(obj.get("lat").toString()));
                F.setLng(Double.parseDouble(obj.get("lng").toString()));
                Formations.add(F);
            }
           
        } catch (IOException ex) {
     
        } catch (ParseException ex) {
            System.out.println("noooo shit");        
        }
         return Formations;
     }
    
     public ArrayList<Formation> getAll(String type,int id){
         
         String url=BASE_URL+"/getFormationMobile?type="+type+"&idu="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Forms=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return Forms;
         
     }
     
     
     public boolean Participaer(int idu,int idf,String typeu){
         
          String url=BASE_URL+"/ParticiperFormationMobile?idu="+idu+"&idf="+idf+"&typeu="+typeu;
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
     
        public ArrayList<Formation> getFParUser(String type,int id){
         
         String url=BASE_URL+"/getUserFormationMobile?type="+type+"&idu="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Forms1=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return Forms1;
         
     } 
        
        public ArrayList<Formation> getParticipation(String type,int id){
         
         String url=BASE_URL+"/getPartFormationMobile?type="+type+"&idu="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Forms=parseResponse(new String(req.getResponseData()));
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
        
         return Forms;
         
     }
     
        
  public boolean SupprimerFormation(Formation f){
        ResultOK=false;
        String url=BASE_URL+"/deleteFormationMobile?id="+f.getId();
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
  
   public boolean ModifierFormation(Formation F){
       ResultOK=false;
         String url=BASE_URL+"/updateFormationMobile/?id="+F.getId()+"&labelle="+F.getLabelle()+"&description="+F.getDescription()+"&domaine="+F.getDomaine()+"&lieu="+F.getLieu()+
                 "&dated="+F.getDateDebut()+"&datef="+F.getDateFin()+"&montant="+F.getMontant()+/*"/"+F.isEtat()+*/"&lat="+F.getLat()+"&lng="+F.getLng()+"&image="+F.getImageF();
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
   
   public boolean SupprimerFormationPart(String type,int idu,int idf){
        ResultOK=false;
        String url=BASE_URL+"/deleteFormationPartMobile?type="+type+"&idu="+idu+"&idf="+idf;
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
