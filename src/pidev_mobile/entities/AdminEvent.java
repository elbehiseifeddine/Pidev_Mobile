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
public class AdminEvent {
    
    private int id, id_a_e,id_event_loisir,id_formation;

    public AdminEvent(int id_a_e, int id_event_loisir, int id_formation) {
        this.id_a_e = id_a_e;
        this.id_event_loisir = id_event_loisir;
        this.id_formation = id_formation;
    }

    public AdminEvent() {
    }

    public int getId_a_e() {
        return id_a_e;
    }

    public void setId_a_e(int id_a_e) {
        this.id_a_e = id_a_e;
    }

    public int getId_event_loisir() {
        return id_event_loisir;
    }

    public void setId_event_loisir(int id_event_loisir) {
        this.id_event_loisir = id_event_loisir;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
