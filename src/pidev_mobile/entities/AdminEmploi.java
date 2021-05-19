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
public class AdminEmploi {
    private int id, id_a_e,id_offre_emploi,id_offre_stage;

    public AdminEmploi(int id_a_e, int id_offre_emploi, int id_offre_stage) {
        this.id_a_e = id_a_e;
        this.id_offre_emploi = id_offre_emploi;
        this.id_offre_stage = id_offre_stage;
    }

    public AdminEmploi() {
    }

    public int getId_a_e() {
        return id_a_e;
    }

    public void setId_a_e(int id_a_e) {
        this.id_a_e = id_a_e;
    }

    public int getId_offre_emploi() {
        return id_offre_emploi;
    }

    public void setId_offre_emploi(int id_offre_emploi) {
        this.id_offre_emploi = id_offre_emploi;
    }

    public int getId_offre_stage() {
        return id_offre_stage;
    }

    public void setId_offre_stage(int id_offre_stage) {
        this.id_offre_stage = id_offre_stage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
