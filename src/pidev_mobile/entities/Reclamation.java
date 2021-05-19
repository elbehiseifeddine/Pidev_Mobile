<<<<<<< Updated upstream
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.entities;

/**
 *
 * @author Ayari Ghaith
 */
public class Reclamation {
    private int id;
    private boolean etat;
    private String type,texte_reclamation,date_reclamation,email_utilisateur,nom_utilisateur;

    public Reclamation() {
    }

    
    
    
    public Reclamation(int id, boolean etat, String type, String texte_reclamation, String date_reclamation, String email_utilisateur, String nom_utilisateur) {
        this.id = id;
        this.etat = etat;
        this.type = type;
        this.texte_reclamation = texte_reclamation;
        this.date_reclamation = date_reclamation;
        this.email_utilisateur = email_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
    }

    public Reclamation(boolean etat, String type, String texte_reclamation, String date_reclamation, String email_utilisateur, String nom_utilisateur) {
        this.etat = etat;
        this.type = type;
        this.texte_reclamation = texte_reclamation;
        this.date_reclamation = date_reclamation;
        this.email_utilisateur = email_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTexte_reclamation() {
        return texte_reclamation;
    }

    public void setTexte_reclamation(String texte_reclamation) {
        this.texte_reclamation = texte_reclamation;
    }

    public String getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(String date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getEmail_utilisateur() {
        return email_utilisateur;
    }

    public void setEmail_utilisateur(String email_utilisateur) {
        this.email_utilisateur = email_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

   
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.entities;

/**
 *
 * @author ahmed
 */
public class Reclamation {
     private int id;
    private String type;
    private String TextReclamation;
    private String DateReclamation;
    private String EmailUser;
    private String nomUser;
    private boolean Etat;

    public Reclamation() {
    }

    public Reclamation(String type, String TextReclamation, String DateReclamation, String EmailUser, String nomUser, boolean Etat) {
        this.type = type;
        this.TextReclamation = TextReclamation;
        this.DateReclamation = DateReclamation;
        this.EmailUser = EmailUser;
        this.nomUser = nomUser;
        this.Etat = Etat;
    }

    public Reclamation(int id, String type, String TextReclamation, String DateReclamation, String EmailUser, String nomUser, boolean Etat) {
        this.id = id;
        this.type = type;
        this.TextReclamation = TextReclamation;
        this.DateReclamation = DateReclamation;
        this.EmailUser = EmailUser;
        this.nomUser = nomUser;
        this.Etat = Etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTextReclamation() {
        return TextReclamation;
    }

    public void setTextReclamation(String TextReclamation) {
        this.TextReclamation = TextReclamation;
    }

    public String getDateReclamation() {
        return DateReclamation;
    }

    public void setDateReclamation(String DateReclamation) {
        this.DateReclamation = DateReclamation;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String EmailUser) {
        this.EmailUser = EmailUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public boolean isEtat() {
        return Etat;
    }

    public void setEtat(boolean Etat) {
        this.Etat = Etat;
    }
    
}
>>>>>>> Stashed changes
