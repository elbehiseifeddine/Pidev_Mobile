/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.services;


import com.codename1.io.ConnectionRequest;

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
       
       
}
