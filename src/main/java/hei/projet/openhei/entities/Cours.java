package hei.projet.openhei.entities;

import java.util.List;

//Class Cours
public class Cours {
    private Integer id;
    private String nomCours;
    private String url;
    private Integer id_mat;

    //Premier contructeur de la class Cours
    public Cours( String nomCours,String url,Integer id){
        this.nomCours=nomCours;
        this.url=url;
        this.id=id;
    }

    //Deuxième contructeur de la class Cours
    public Cours( String nomCours,String url){
        this.nomCours=nomCours;
        this.url=url;
    }

    //Getters
    public Integer getId() {
        return id;
    }
    public String getUrl(){return this.url;}
    public String getnomCours(){
        return nomCours;
    }
    public Integer getId_mat() {
        return id_mat;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUrl(String url){this.url=url;}
    public void setNomCours(String nomCours){
        this.nomCours=nomCours;
    }
    public void setIdMat(Integer id){this.id_mat=id;};

}

