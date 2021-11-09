package hei.projet.openhei.dao;

import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import java.util.List;

public interface CoursDao {
    public List<Cours> ListCour();
    public String getNom(Integer id);
    public Matiere getMatiere(Integer id);
}
