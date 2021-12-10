package hei.projet.openhei.dao;

import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import java.util.List;


//Interface MatiereDao
public interface MatiereDao {

    //Getters
    public String getNom(Integer id);
    public int getnbCour(Integer id);

    //Méthodes pour lister les matières, lister les cours d'une matière, récupérer l'id d'une matière
    public List<Matiere> ListMatiere();
    public List<Cours> getListCour(Integer id);
    public Integer getID(String nom);
}

