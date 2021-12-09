package hei.projet.openhei.dao;

import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


//Interface CoursDao
public interface CoursDao {

    //Méthode de création d'un objet cours à la récupération des informations de la BDD
    public Cours createCoursFromResultSet(ResultSet resultSelect) throws SQLException;

    //Getters
    public String getNom(Integer id);
    public Matiere getMatiere(Integer id);

    //Méthode d'ajout, d'update, et de suppression des cours dans la BDD
    public void addCour(Cours cours) throws SQLException;
    public Integer deleteCoursFromDB(String url_cours);
    public Integer updateCoursFromDB(String urlcoursToUpdate, String nom_cours, String url_cours);

    //Méthode qui permet de vérifier si un cours éxiste dans la BDD grâce à son url (contrainte unique en BDD)
    public boolean ExistCours(String url_cours) throws SQLException;


    public List<Cours> ListCour();
}