package hei.projet.openhei.entities;

import java.util.ArrayList;
import java.util.List;

public class Matiere {
    private Integer id;
    private String NomMatiere;
    private List<Cours> listCour;



    public Matiere(int id_matiere_cours, String nom_matiere) {
        this.id = id_matiere_cours;
        this.NomMatiere = nom_matiere;
        this.listCour = new ArrayList<Cours>();
    }
    public Matiere(){
    }


    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){this.id=id;}
    public String getNomMatiere(){
        return NomMatiere;
    }
    public void setNomMatiere(String nomMatiere){
        this.NomMatiere=NomMatiere;
    }
    public void ajouterCour(Cours c){this.listCour.add(c);}
    public List<Cours> recupCour(){return this.listCour;}
    
}
