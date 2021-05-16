/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.entities;

import java.util.Date;

/**
 *
 * @author Ghassen Riahi
 */
public class offreEmploi {
       public int id;
    public String nom;
    public String competences;
    public String description;
    public String domaine;
    public float salaire;
    public String devise;
    public Date dateCreation;
    public Date dateExpiration;
    private int etat;
    private int idSociete;

    public offreEmploi() {
    }

    public offreEmploi(int id) {
        this.id = id;
    }
    
    

    public offreEmploi(String nom, String competences, String description, String domaine, float salaire, String devise, Date dateCreation, Date dateExpiration, int etat) {
        this.nom = nom;
        this.competences = competences;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.devise = devise;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public offreEmploi(int id, String nom, String competences, String description, String domaine, float salaire, String devise, Date dateCreation, Date dateExpiration, int etat, int idSociete) {
        this.id = id;
        this.nom = nom;
        this.competences = competences;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.devise = devise;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.idSociete = idSociete;
    }

    public offreEmploi(String nom, String competences, String description, String domaine, float salaire, String devise, Date dateCreation, Date dateExpiration, int etat, int idSociete) {
        this.nom = nom;
        this.competences = competences;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.devise = devise;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
        this.idSociete = idSociete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(int idSociete) {
        this.idSociete = idSociete;
    }
    
}
