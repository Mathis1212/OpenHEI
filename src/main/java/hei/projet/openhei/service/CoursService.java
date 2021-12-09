package hei.projet.openhei.service;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.dao.impl.CoursDaoImpl;
import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class CoursService {
    private static class ServiceHolder {
        private final static CoursService instance = new CoursService();
    }

    //Création de la methode getInstance pour récuperer les méthodes de CoursService
    public static CoursService getInstance() {
        return ServiceHolder.instance;
    }

    //Récupération de l'instance CoursDaoImpl
    private CoursDao coursDao = CoursDaoImpl.getInstance();

    //Récupération de l'instance MatiereDaoImpl
    private MatiereDao matiereDao = MatiereDaoImpl.getInstance();

    //Appel de l'instance Log4j2
    static final Logger LOGGER = LogManager.getLogger();


    //Méthode qui verifie la cohérence de l'url du cours à supprimer
    //Appel de la méthode deleteCoursFromDB si l'url est conforme
    //Retourne le nombre de ligne supprimé en BDD
    public Integer deleteCours(String url_cours) throws SQLException {
        int deletedrows=0;
        if (url_cours == null||"".equals(url_cours)){
            LOGGER.warn("Un code cours ne peut pas etre null ou vide");
        }
        if(!coursDao.ExistCours(url_cours)){
            LOGGER.warn("Le cours à update n'existe pas");
        }else{
            deletedrows = coursDao.deleteCoursFromDB(url_cours);
        }
        return deletedrows;
    }


    //Méthode qui appelle updateCoursFromDB
    public void updateCours(String urlcoursToUpdate, String nom_cours, String url_cours){
        if ((urlcoursToUpdate != null&&!("".equals(urlcoursToUpdate)))&&(nom_cours != null&&!("".equals(nom_cours)))&&(url_cours != null&&!("".equals(url_cours)))) {
            coursDao.updateCoursFromDB(urlcoursToUpdate, nom_cours, url_cours);
        }else {
            LOGGER.warn("Le cours n'a pas pu etre update ");
            throw new IllegalArgumentException();
        }
    }

    //Méthode qui appelle getID
    public Integer getIDToAdd(String nom_mat){
        Integer id=0;
        if(nom_mat!=null&&!("".equals(nom_mat))){
            id=matiereDao.getID(nom_mat);
        } else{
            LOGGER.warn("L'id' n'a pas pu etre récupérer");
            throw new IllegalArgumentException();
        }
        return id;
    }

    //Méthode qui appelle addCour
    public  void addCour(Cours cours) throws SQLException {

        if (cours == null) {
            throw new IllegalArgumentException("cours is null.");
        }
        if (cours.getnomCours() == null) {
            throw new IllegalArgumentException(" name is null.");
        }
        if (cours.getUrl() == null ) {
            throw new IllegalArgumentException("url is null.");
        }
        coursDao.addCour(cours);
    }
}
