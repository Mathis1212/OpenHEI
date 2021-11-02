package hei.projet.openhei.entities;

public class Cours {
    private Integer id;
    private String nomCours;
    private Matiere matiere;

    public Cours(Integer id, String nomCours, Matiere matiere){
        this.id=id;
        this.nomCours=nomCours;
        this.matiere=matiere;
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
    public Matiere getMatiere(){
        return matiere;
    }
    public void setMatiere(){
        this.matiere=matiere;
    }
}
