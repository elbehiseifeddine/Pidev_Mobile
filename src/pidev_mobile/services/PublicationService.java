/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.e;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import pidev_mobile.entities.Publications;
import pidev_mobile.utils.Statics;

/**
 *
 * @author ksemt
 */
public class PublicationService {
    
    public static PublicationService instance = null ;
    
    public static boolean resultOk=true ;
    
    private ConnectionRequest req;
    
    
    public static PublicationService getInstance() {
        if(instance == null)
            instance = new PublicationService();
        return instance;
    }
    
    public PublicationService(){
        req = new ConnectionRequest();
        
        
    }
    
    //AjoutPublication
    public void ajoutPublication(Publications publications){
        
        String url =Statics.BASE_URL+"/AddPublication?description="+publications.getDescription()+"&image="+publications.getImage();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    //AffichagePublication
    public ArrayList<Publications>afficherPublication() {
        ArrayList<Publications> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/list_publications";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapPublications = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapPublications.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps){
                        Publications pub = new Publications();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        String description = obj.get("description").toString();
                        String image = obj.get("image").toString();
                        String date = obj.get("date_publication").toString();
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        
                        pub.setId((int)id);
                        pub.setDescription(description);
                        pub.setImage(image);
                        pub.setDate_publication(date);
                        pub.setNomUtil(nom);
                        pub.setPrenomUtil(prenom);
                        
                        result.add(pub);
                    }
                    
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    
    //SupprimerPublications
    public boolean deletePublication(int id) {
        String url =Statics.BASE_URL+"/DelPublication?id="+id ;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    
}
