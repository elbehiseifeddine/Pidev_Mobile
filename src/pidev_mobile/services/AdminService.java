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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pidev_mobile.entities.Admin;
import pidev_mobile.utils.Statics;

/**
 *
 * @author ahmed
 */
public class AdminService {

    public static AdminService instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    public AdminService() {
        req = new ConnectionRequest();
    }

    public boolean ajouterAdmin(Admin a) {
        boolean ok = false;
        String url = Statics.BASE_URL + "/super/admin/CreateAdminJson?"
                + "nom=" + a.getNom()
                + "&prenom=" + a.getPrenom()
                + "&type=" + a.getType()
                + "&login=" + a.getLogin()
                + "&password=" + a.getPass()
                + "&etat=" + a.isEtat();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());

                System.out.println("this is ajouter Admin in AdminService | data == " + responceData);
                req.removeResponseListener(this);
                if (responceData.equals("Exist")){
                    Dialog.show("Error", "Admin existe deja ! ", "Ok", null);
                }
            }
        });
//        req.addResponseListener((e) -> {
//
//            String responceData = new String(req.getResponseData());
//
//            System.out.println("this is ajouter Admin in AdminService | data == " + responceData);
//
//        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
            ok = true;
        } catch (Exception e) {
            System.out.println("erreur lors de l'insertion de l admin");
        }
        return ok;

    }

    public ArrayList<Admin> AfficherAdmins() {
        ArrayList<Admin> list = new ArrayList<>();
        String url = Statics.BASE_URL + "/super/adminJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Map<String, Object> MapAdmins = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMap = (List<Map<String, Object>>) MapAdmins.get("root");

                    for (Map<String, Object> obj : ListOfMap) {

                        Admin a = new Admin();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = String.valueOf(obj.get("nom"));
                        String prenom = String.valueOf(obj.get("prenom"));
                        String login = String.valueOf(obj.get("login"));
                        String password = String.valueOf(obj.get("password"));
                        String type = String.valueOf(obj.get("type"));
                        boolean etat = Boolean.parseBoolean(obj.get("etat").toString());
                        System.out.println(id + "*******" + prenom + "*********" + nom + "*********** " + password);
                        float approuve = Float.parseFloat(obj.get("approuve").toString());
                        float nonapprouve = Float.parseFloat(obj.get("nonapprouve").toString());

                        a.setId((int) id);
                        a.setNom(nom);
                        a.setPrenom(prenom);
                        a.setLogin(login);
                        a.setPass(password);
                        a.setType(type);
                        a.setEtat(etat);
                        a.setApprouve((int) approuve);
                        a.setNonApprouve((int) nonapprouve);

                        list.add(a);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return list;
    }

    public Admin DetailAdmin(int id) {
        Admin a = new Admin();
        String url = Statics.BASE_URL + "/super/admin/ViewAdminProfileJson?id=" + id;
        req.setUrl(url);
        String data = new String(req.getResponseData());
        req.addResponseListener((evt) -> {
            JSONParser json = new JSONParser();
            try {
                Map<String, Object> MapAdmins = json.parseJSON(new CharArrayReader(data.toCharArray()));

                String nom = String.valueOf(MapAdmins.get("nom"));
                String prenom = String.valueOf(MapAdmins.get("prenom"));
                String login = String.valueOf(MapAdmins.get("login"));
                String password = String.valueOf(MapAdmins.get("pass"));
                String type = String.valueOf(MapAdmins.get("type"));
                boolean etat = Boolean.parseBoolean(MapAdmins.get("type").toString());

                float approuve = Float.parseFloat(MapAdmins.get("approuve").toString());
                float nonapprouve = Float.parseFloat(MapAdmins.get("nonapprouve").toString());

                a.setId(id);
                a.setNom(nom);
                a.setPrenom(prenom);
                a.setLogin(login);
                a.setPass(password);
                a.setType(type);
                a.setEtat(etat);
                a.setApprouve((int) approuve);
                a.setNonApprouve((int) nonapprouve);
            } catch (IOException ex) {

                System.out.println("this is detail fucnction in admin service || " + ex.getMessage());
            }
            System.out.println("detail Admin ==> " + data);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return a;

    }

    public boolean updateAdmin(Admin a) {
        boolean ok = false;

        System.out.println("      yoooo          admin id = " + a.getId() + "                 ");

        String url = Statics.BASE_URL + "/super/admin/UpdateAdminJson?"
                + "id=" + a.getId()
                + "&nom=" + a.getNom()
                + "&prenom=" + a.getPrenom()
                + "&type=" + a.getType()
                + "&login=" + a.getLogin()
                + "&password=" + a.getPass()
                + "&etat=" + a.isEtat();
        System.out.println(url);
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());

                System.out.println("this is update Admin in AdminService | data == " + responceData);
                req.removeResponseListener(this);
            }
        });
//        req.addResponseListener((e) -> {
//
//            String responceData = new String(req.getResponseData());
//
//            System.out.println("this is update Admin in AdminService | reponse == " + responceData);
//
//        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
            ok = true;
        } catch (Exception ex) {
            System.out.println("this is update Admin in AdminService | " + ex.getMessage());
        }
        return ok;
    }

    public boolean deleteAdmin(int id) {
        boolean ok = false;
        String url = Statics.BASE_URL + "/super/admin/DeleteAdminJson?id=" + id;
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());

                System.out.println("this is delete Admin in AdminService | reponse == " + responceData);
                req.removeResponseListener(this);
            }
        });
//        req.addResponseListener((e) -> {
//
//            String responceData = new String(req.getResponseData());
//
//            System.out.println("this is delete Admin in AdminService | reponse == " + responceData);
//
//        });
try {
             NetworkManager.getInstance().addToQueueAndWait(req);
             ok = true;
        } catch (Exception e) {
            System.out.println("erreur lors du suppression de l admin");
        }
       return ok;
    }

}
