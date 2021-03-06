package hei.projet.openhei.entities;

import java.util.ArrayList;
import java.util.List;

//Class Matière
public class Matiere {
    private Integer id;
    private String NomMatiere;
    private List<Cours> listCour;


    //Contructeur de la class Matiere
    public Matiere(int id_matiere_cours, String nom_matiere) {
        this.id = id_matiere_cours;
        this.NomMatiere = nom_matiere;
        this.listCour = new ArrayList<Cours>();
    }

    //Deuxième constructeur de la class Matiere
    public Matiere(){
    }

    //Getters
    public String getNomMatiere(){
        return NomMatiere;
    }
    public Integer getId(){
        return this.id;
    }

    //Setters
    public void setId(Integer id){this.id=id;}
    public void setNomMatiere(String nomMatiere){
        this.NomMatiere=NomMatiere;
    }

    //Méthodes ajouter, récupérer un cours
    public void ajouterCour(Cours c){this.listCour.add(c);}
    public List<Cours> recupCour(){return this.listCour;}
}
