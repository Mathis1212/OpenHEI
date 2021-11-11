package hei.projet.openhei.entities;

import java.util.List;

public class Cours {
    private Integer id;
    private String nomCours;



    public Cours( String nomCours){
        this.nomCours=nomCours;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getnomCours(){
        return nomCours;
    }
    public void setNomCours(){
        this.nomCours=nomCours;
    }

}
