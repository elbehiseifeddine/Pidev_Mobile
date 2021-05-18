/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.services;

import com.codename1.charts.views.DialChart;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.util.Map;

import pidev_mobile.utils.Statics;

/**
 *
 * @author seifeddine
 */
public class UtilisateurService {

    public static UtilisateurService instance = null;
    public static String returnTypeSU = "success";
    public static String returnTypeSI = "success";

    public static boolean resultOk = true;
    private ConnectionRequest req;

    public static UtilisateurService getInstance() {
        if (instance == null) {
            instance = new UtilisateurService();
        }
        return instance;
    }

    public UtilisateurService() {
        req = new ConnectionRequest();
    }

    public void SignUpFreelancer(String nom, String prenom, String Email, String pwd) {
        String url = Statics.BASE_URL + "/SignUpJSON/new?nom=" + nom + "&prenom=" + prenom + "&type=Freelancer&email=" + Email + "&pwd=" + pwd;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            byte[] data = (byte[]) e.getMetaData();
            String responceData = new String(data);
            if (responceData.equals("\"exist\"")) {
                returnTypeSU = "exist";
            } else {
                returnTypeSI = "success";

            }
            System.out.println("data ======>" + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void SignUpSociete(String nom, String Email, String pwd) {
        String url = Statics.BASE_URL + "/SignUpJSON/new?nom=" + nom + "&type=Societe&email=" + Email + "&pwd=" + pwd;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            byte[] data = (byte[]) e.getMetaData();
            String responceData = new String(data);
            if (responceData.equals("\"exist\"")) {
                returnTypeSU = "exist";
            } else {
                returnTypeSI = "success";

            }
            System.out.println("data ======>" + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void SignIn(String Email, String pwd) {
        String url = Statics.BASE_URL + "/SignInJson/new?email=" + Email + "&pwd=" + pwd;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
            byte[] data = (byte[]) e.getMetaData();
            String responceData = new String(data);
            if (responceData.equals("\"CompteDesactiver\"")) {
                returnTypeSI = "CompteDesactiver";
            } else if (responceData.equals("\"Mot de pass est Incorrect\"")) {
                returnTypeSI = "Mot de pass est Incorrect";
            } else if (responceData.equals("\"Email exist pas\"")) {
                returnTypeSI = "Email exist pas";
            } else {
                returnTypeSI = "success";
                try {
                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (user.get("compte_facebook") != null) {
                        Preferences.set("type", "Freelancer");
                        Preferences.set("id", (Double) user.get("id"));
                        Preferences.set("nom", (String) user.get("nom"));
                        Preferences.set("prenom", (String) user.get("prenom"));
                        Preferences.set("email", (String) user.get("email"));
                        Preferences.set("adresse", (String) user.get("adresse"));
                        Preferences.set("competences", (String) user.get("competences"));
                        Preferences.set("compte_facebook", (String) user.get("compte_facebook"));
                        Preferences.set("compte_linkedin", (String) user.get("compte_linkedin"));
                        Preferences.set("compte_twitter", (String) user.get("compte_twitter"));
                        Preferences.set("langues", (String) user.get("langues"));
                        Preferences.set("sexe", (String) user.get("sexe"));
                        Preferences.set("photo_de_profile", (String) user.get("photo_de_profile"));

                    } else if (user.get("status_juridique") != null) {

                        Preferences.set("type", "Societe");
                        Preferences.set("id", (Double) user.get("id"));
                        Preferences.set("nom", (String) user.get("nom"));
                        Preferences.set("email", (String) user.get("email"));
                        Preferences.set("adresse", (String) user.get("adresse"));
                        Preferences.set("status_juridique", (String) user.get("status_juridique"));
                        Preferences.set("photo_de_profile", (String) user.get("photo_de_profile"));

                    } else if (user.get("type") != null) {

                        if (user.get("type").equals("Admin des events")) {
                            Preferences.set("type", "Admin des events");
                            Preferences.set("id", (Double) user.get("id"));
                            Preferences.set("nom", (String) user.get("nom"));
                            Preferences.set("prenom", (String) user.get("prenom"));
                            Preferences.set("email", (String) user.get("login"));
                        
                        } else if (user.get("type").equals("Admin des emplois")) {
                            Preferences.set("type", "Admin des emplois");
                            Preferences.set("id", (Double) user.get("id"));
                            Preferences.set("nom", (String) user.get("nom"));
                            Preferences.set("prenom", (String) user.get("prenom"));
                            Preferences.set("email", (String) user.get("login"));
                        
                        } else if (user.get("type").equals("Admin des reclamations")) {
                            Preferences.set("type", "Admin des reclamations");
                            Preferences.set("id", (Double) user.get("id"));
                            Preferences.set("nom", (String) user.get("nom"));
                            Preferences.set("prenom", (String) user.get("prenom"));
                            Preferences.set("email", (String) user.get("login"));
                        }

                    } else if (user.get("login") != null && user.get("type") == null) {
                        Preferences.set("type", "SuperAdmin");
                        Preferences.set("id", (Double) user.get("id"));
                        Preferences.set("nom", (String) user.get("nom"));
                        Preferences.set("prenom", (String) user.get("prenom"));
                        Preferences.set("email", (String) user.get("login"));
                    }

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
            System.out.println("data ======>" + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void Update(String Email, String pwd) {
        String url = Statics.BASE_URL + "/SignInJson/new?email=" + Email + "&pwd=" + pwd;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
            byte[] data = (byte[]) e.getMetaData();
            String responceData = new String(data);
            if (responceData.equals("\"CompteDesactiver\"")) {
                returnTypeSI = "CompteDesactiver";
            } else if (responceData.equals("\"Mot de pass est Incorrect\"")) {
                returnTypeSI = "Mot de pass est Incorrect";
            } else if (responceData.equals("\"Email exist pas\"")) {
                returnTypeSI = "Email exist pas";
            } else {
                returnTypeSI = "success";
                try {
                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (user.get("compte_facebook") != null) {
                        Preferences.set("type", "Freelancer");
                        Preferences.set("id", (Double) user.get("id"));
                        Preferences.set("nom", (String) user.get("nom"));
                        Preferences.set("prenom", (String) user.get("prenom"));
                        Preferences.set("email", (String) user.get("email"));
                        Preferences.set("adresse", (String) user.get("adresse"));
                        Preferences.set("competences", (String) user.get("competences"));
                        Preferences.set("compte_facebook", (String) user.get("compte_facebook"));
                        Preferences.set("compte_linkedin", (String) user.get("compte_linkedin"));
                        Preferences.set("compte_twitter", (String) user.get("compte_twitter"));
                        Preferences.set("langues", (String) user.get("langues"));
                        Preferences.set("sexe", (String) user.get("sexe"));
                        Preferences.set("photo_de_profile", (String) user.get("photo_de_profile"));
                    } else if (user.get("status_juridique") != null) {
                        Preferences.set("type", "Societe");
                    } else if (user.get("compte_facebook") != null) {
                        Preferences.set("type", "Freelancer");
                    }

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
            System.out.println("data ======>" + responceData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
