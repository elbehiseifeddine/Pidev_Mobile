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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import pidev_mobile.utils.Statics;

/**
 *
 * @author ahmed
 */
public class StatService {

    public static StatService instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;

    public static StatService getInstance() {
        if (instance == null) {
            instance = new StatService();
        }
        return instance;
    }

    public StatService() {
        req = new ConnectionRequest();
    }

    public double[] getStatReclamation() {
        double[] valeur = new double[12];

        String url = Statics.BASE_URL + "/statRecJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                String[] ch = split(responceData.substring(1, responceData.length() - 1));
                for (int i = 0; i < 12; i++) {
                    System.out.println(ch[i]);
                    valeur[i] = Double.parseDouble(ch[i]);
                }
                req.removeResponseListener(this);
            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            System.out.println("this is stat reclamation | " + ex.getMessage());
        }

        return valeur;
    }

    public double[] getStatFreelancer() {
        double[] valeur = new double[12];

        String url = Statics.BASE_URL + "/statFreeJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                String[] ch = split(responceData.substring(1, responceData.length() - 1));
                for (int i = 0; i < 12; i++) {
                    System.out.println(ch[i]);
                    valeur[i] = Double.parseDouble(ch[i]);
                }
                req.removeResponseListener(this);
            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            System.out.println("this is stat reclamation | " + ex.getMessage());
        }

        return valeur;
    }

    public double[] getStatSociete() {
        double[] valeur = new double[12];

        String url = Statics.BASE_URL + "/statSosJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                String[] ch = split(responceData.substring(1, responceData.length() - 1));
                for (int i = 0; i < 12; i++) {
                    System.out.println(ch[i]);
                    valeur[i] = Double.parseDouble(ch[i]);
                }
                req.removeResponseListener(this);
            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            System.out.println("this is stat reclamation | " + ex.getMessage());
        }

        return valeur;
    }

    public double[] getStatOffreEmploi() {
        double[] valeur = new double[12];

        String url = Statics.BASE_URL + "/statOEJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                String[] ch = split(responceData.substring(1, responceData.length() - 1));
                for (int i = 0; i < 12; i++) {
                    System.out.println(ch[i]);
                    valeur[i] = Double.parseDouble(ch[i]);
                }
                req.removeResponseListener(this);
            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            System.out.println("this is stat reclamation | " + ex.getMessage());
        }

        return valeur;
    }

    public double[] getStatOffreStage() {
        double[] valeur = new double[12];

        String url = Statics.BASE_URL + "/statOSJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                String[] ch = split(responceData.substring(1, responceData.length() - 1));
                for (int i = 0; i < 12; i++) {
                    System.out.println(ch[i]);
                    valeur[i] = Double.parseDouble(ch[i]);
                }
                req.removeResponseListener(this);
            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            System.out.println("this is stat reclamation | " + ex.getMessage());
        }

        return valeur;
    }

    public double[] getStatDemandeEmploi() {
        double[] valeur = new double[12];

        String url = Statics.BASE_URL + "/statDEJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                String[] ch = split(responceData.substring(1, responceData.length() - 1));
                for (int i = 0; i < 12; i++) {
                    System.out.println(ch[i]);
                    valeur[i] = Double.parseDouble(ch[i]);
                }
                req.removeResponseListener(this);
            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            System.out.println("this is stat reclamation | " + ex.getMessage());
        }

        return valeur;
    }

    public double[] getStatDemandeStage() {
        double[] valeur = new double[12];

        String url = Statics.BASE_URL + "/statDSJson";
        req.setUrl(url);
        req.setPost(false);
        JSONParser json = new JSONParser();
        //String data = new String(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responceData = new String(req.getResponseData());
                String[] ch = split(responceData.substring(1, responceData.length() - 1));
                for (int i = 0; i < 12; i++) {
                    System.out.println(ch[i]);
                    valeur[i] = Double.parseDouble(ch[i]);
                }
                req.removeResponseListener(this);
            }
        });

        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            System.out.println("this is stat reclamation | " + ex.getMessage());
        }

        return valeur;
    }

    public String[] split(String str) {
        ArrayList<String> splitArray = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(str, ",");//split by commas
        while (arr.hasMoreTokens()) {
            splitArray.add(arr.nextToken());
        }
        return splitArray.toArray(new String[splitArray.size()]);
    }
}
