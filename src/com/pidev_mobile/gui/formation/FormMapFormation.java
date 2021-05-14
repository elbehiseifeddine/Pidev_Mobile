/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.gui.formation;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.location.Location;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 *
 * @author ASUS
 */
public class FormMapFormation extends Form {
     private static final String HTML_API_KEY = "AIzaSyAUHynp54xyoYOWvfUUUH8c1pcexnmm7EI";
    private Form current;
    MapContainer.MapObject sydney;
     boolean tapDisabled = false;
     double lat=0;
     double lng=0;
     String lieu="";

    public FormMapFormation(Form previous) {
        setLayout(new BorderLayout());
           final MapContainer cnt = new MapContainer(HTML_API_KEY);
         
        //final MapContainer cnt = new MapContainer();
        cnt.setCameraPosition(new Coord(36.740461, 10.224472));//this breaks the code //because the Google map is not loaded yet
        cnt.addMapListener(new MapListener() {

            @Override
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
                System.out.println("Map position updated: zoom="+zoom+", Center="+center);
            }
            
        });
        
        cnt.addLongPressListener(e->{
            System.out.println("Long press");
            ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
    
        cnt.addTapListener(e->{
            e.consume();
            
            System.out.println("lat");
             Style s = new Style();
         s.setBgTransparency(0);
         s.setFgColor(0);
         String url ="https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="+cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude()+"&lon="+cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude();
                  Coord coord = cnt.getCoordAtPosition(e.getX(), e.getY());
         ConnectionRequest requ=new ConnectionRequest(url);
            System.out.println("Hadha url=>>"+url);
             requ.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              try {
                     JSONParser j=new JSONParser();
                     Map<String,Object> FormationListJson=j.parseJSON(new CharArrayReader(new String(requ.getResponseData()).toCharArray()));
        
                    lieu=FormationListJson.get("display_name").toString();
                    lat=coord.getLatitude();
                    lng=coord.getLongitude();
                    
                     
              
                     
    
    //  org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
    //  Object obj = parser.parse(new String(requ.getResponseData()));
                  System.out.println("hadha howa"+FormationListJson.get("display_name"));
                 
                  
//                  JSONArray array = (JSONArray)obj;
/*
                  JSONObject obj2 = (JSONObject)FormationListJson;
                  JSONArray array = (JSONArray)obj2.get("features");
                  JSONObject obj3 = (JSONObject)array.get(0);
                   JSONObject obj4 = (JSONObject)obj3.get("properties");
                  JSONObject obj5 = (JSONObject) obj4.get("geocoding");
                  System.out.println("hadi hia=>"+obj5.get("label").toString());*/
                  
                 
             
                     //System.out.println(FormationListJson.get("root").getClass());
                     
                         
                     requ.removeResponseListener(this);
                 } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                 } 
             }
             
         });
         NetworkManager.getInstance().addToQueueAndWait(requ);
        
        
        
           
        
            ToastBar.showMessage("Received tap at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
           // System.out.println("=========>>>>"+ new ParseGeoPoint(e.getX(),e.getY()));
      
            MapContainer.MarkerOptions mo=new MapContainer.MarkerOptions(coord, FontImage.createMaterial(FontImage.MATERIAL_ALARM, s).toEncodedImage());
       cnt.addMarker(mo);
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), coord, "", "", null);
        });
        
        int maxZoom = cnt.getMaxZoom();
        System.out.println("Max zoom is "+maxZoom);
        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(-33.867, 151.206));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        
      
        
       
    
        
      
        
       FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_CONFIRMATION_NUM);
        nextForm.addActionListener(e->{
            
          FormAjoutFormation form=(FormAjoutFormation)previous;
          form.setInfo(lieu,lat,lng);
          previous.showBack();
        });
        
        
        
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(nextForm.bindFabToContainer(cnt))
                
        );
        
        this.add(BorderLayout.CENTER, root);
       
    }
    
    
}
