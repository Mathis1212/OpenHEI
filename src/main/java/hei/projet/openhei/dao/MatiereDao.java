package hei.projet.openhei.dao;

import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import java.util.List;

public interface MatiereDao {
    public List<Matiere> ListMatiere();
    public String getNom(Integer id);
    public int getnbCour(Integer id);
    public List<Cours> getListCour(Integer id);

}
