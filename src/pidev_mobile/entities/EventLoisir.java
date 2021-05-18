/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.entities;

import java.util.Date;




/**
 *
 * @author ASUS
 */
public class EventLoisir {
      public int id;
    public String Labelle;
    public String Description;
    public String Lieu;
    public Date DateDebut;
    public Date DateFin;
    public String Domaine;
    public int nbParticipant;
    public boolean Etat;
    public double Lng;
    public double Lat;
    private String imageE;

    public EventLoisir() {
    }

    public EventLoisir(int id) {
        this.id = id;
    }

    public EventLoisir(int id, String Labelle, String Description,String Domaine, String Lieu, Date DateDebut, Date DateFin,  int nbParticipant, boolean Etat, double Lng, double Lat,String image) {
        this.id = id;
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.nbParticipant = nbParticipant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageE=image;
    }

    public EventLoisir(String Labelle, String Description,String Domaine, String Lieu, Date DateDebut, Date DateFin,  int nbParticipant, boolean Etat, double Lng, double Lat,String image) {
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.nbParticipant = nbParticipant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageE=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelle() {
        return Labelle;
    }

    public void setLabelle(String Labelle) {
        this.Labelle = Labelle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String Domaine) {
        this.Domaine = Domaine;
    }

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

    public boolean isEtat() {
        return Etat;
    }

    public void setEtat(boolean Etat) {
        this.Etat = Etat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double Lng) {
        this.Lng = Lng;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public String getImageE() {
        return imageE;
    }

    public void setImageE(String imageE) {
        this.imageE = imageE;
    }
    
}
