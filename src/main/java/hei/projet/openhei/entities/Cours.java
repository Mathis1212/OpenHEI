package hei.projet.openhei.entities;

import java.util.List;

public class Cours {
    private Integer id;
    private String nomCours;
    private String url;
    private Integer id_mat;


    public Cours( String nomCours,String url,Integer id){
        this.nomCours=nomCours;
        this.url=url;
        this.id=id;
    }
    public Cours( String nomCours,String url){
        this.nomCours=nomCours;
        this.url=url;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUrl(String url){this.url=url;}
    public String getUrl(){return this.url;}
    public String getnomCours(){
        return nomCours;
    }
    public void setNomCours(String nomCours){
        this.nomCours=nomCours;
    }
    public void setIdMat(Integer id){this.id_mat=id;};
    public Integer getId_mat() {
        return id_mat;
    }
}

