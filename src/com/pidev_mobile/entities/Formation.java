/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev_mobile.entities;

import java.util.Date;



/**
 *
 * @author ASUS
 */
public class Formation {
     public int id;
    public String Labelle;
    public String Description;
    public String Lieu;
    public Date DateDebut;
    public Date DateFin;
    public String Domaine;
    public float Montant;
    public boolean Etat;
    public double Lng;
    public double Lat;
    public String imageF;

    public Formation(int id) {
        this.id = id;
    }

    
    
    public Formation() {
    }

    public Formation(int id, String Labelle, String Description,String Domaine, String Lieu, Date DateDebut, Date DateFin,  float Montant, boolean Etat, double Lng, double Lat, String imageF) {
        this.id = id;
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.Montant = Montant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageF = imageF;
    }

    public Formation(String Labelle, String Description,  String Domaine,String Lieu, Date DateDebut, Date DateFin, float Montant, boolean Etat, double Lng, double Lat, String imageF) {
        this.Labelle = Labelle;
        this.Description = Description;
        this.Lieu = Lieu;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Domaine = Domaine;
        this.Montant = Montant;
        this.Etat = Etat;
        this.Lng = Lng;
        this.Lat = Lat;
        this.imageF = imageF;
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

    public float getMontant() {
        return Montant;
    }

    public void setMontant(float Montant) {
        this.Montant = Montant;
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

    public String getImageF() {
        return imageF;
    }

    public void setImageF(String imageF) {
        this.imageF = imageF;
    }
    
}
