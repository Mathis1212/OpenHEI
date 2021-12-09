package hei.projet.openhei.dao;

import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CoursDao {
    public Cours createCoursFromResultSet(ResultSet resultSelect) throws SQLException; // corriger la creation d'instance dans le DaoImpl

    public List<Cours> ListCour();

    public String getNom(Integer id);

    public Matiere getMatiere(Integer id);
    public void addCour(Cours cours) throws SQLException;
    public Integer deleteCoursFromDB(String url_cours);
    public Integer updateCoursFromDB(String urlcoursToUpdate, String nom_cours, String url_cours);
    public boolean ExistCours(String url_cours) throws SQLException;

}