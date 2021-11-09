package hei.projet.openhei.entities;

public class Matiere {
    private Integer id;
    private String NomMatiere;
    private Integer notion;

    public Matiere(Integer id, String NomMatiere,Integer num_notion){
        this.id=id;
        this.NomMatiere=NomMatiere;
        this.notion=num_notion;
    }

    public Matiere(int id_matiere_cours, String nom_matiere) {
        this.id=id_matiere_cours;
        this.NomMatiere=nom_matiere;
    }
    public Matiere(){
    }


    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public Integer getNotion(){return this.notion;}
    public String getNomMatiere(){
        return NomMatiere;
    }
    public void setNomMatiere(String nomMatiere){
        this.NomMatiere=NomMatiere;
    }
}
