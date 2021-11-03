package hei.projet.openhei.entities;

public class Matiere {
    private Integer id;
    private String NomMatiere;

    public Matiere(Integer id, String NomMatiere){
        this.id=id;
        this.NomMatiere=NomMatiere;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getNomMatiere(){
        return NomMatiere;
    }
    public void setNomMatiere(String nomMatiere){
        this.NomMatiere=NomMatiere;
    }
}
