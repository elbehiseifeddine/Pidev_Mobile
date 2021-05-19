/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.entities;

/**
 *
 * @author ksemt
 */
public class Publications {
    
    private int id;
    private String description;
    private String image;
    private String date_publication;
    private int freelancer_id;
    private int societe_id;
    private String NomUtil;
    private String PrenomUtil;
    
    public Publications(int id,String description, String image, String date_publication, int freelancer_id, int societe_id) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.freelancer_id = freelancer_id;
        this.societe_id = societe_id;
    }
    
    public Publications(String description, String image, String date_publication, int freelancer_id) {
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.freelancer_id = freelancer_id;
    }
    
    public Publications(int id ,String description, String image, String date_publication, String NomUtil, String PrenomUtil) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.NomUtil = NomUtil;
        this.PrenomUtil = PrenomUtil;
    }
    
    public Publications() {}
    
    public int getId() {
        return id;
    }

    public void setId(int id_pub) {
        this.id = id_pub;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }
    
    public int getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(int freelancer_id) {
        this.freelancer_id = freelancer_id;
    }
    
    public int getSociete_id() {
        return societe_id;
    }

    public void setSociete_id(int societe_id) {
        this.societe_id = societe_id;
    }
    
    public String getNomUtil() {
        return NomUtil;
    }

    public void setNomUtil(String NomUtil) {
        this.NomUtil = NomUtil;
    }
    
    public String getPrenomUtil() {
        return PrenomUtil;
    }

    public void setPrenomUtil(String PrenomUtil) {
        this.PrenomUtil = PrenomUtil;
    }
    
    
    
    @Override
    public String toString() {
        return "Publications{" + "id=" + id + ", description=" + description + ", image=" + image + ", date_publication=" + date_publication + ", freelancer_id=" + freelancer_id + ", societe_id=" + societe_id + ", NomUtil=" + NomUtil + ", prenom_util=" + PrenomUtil + '}';
    }
    
}
