package hei.projet.openhei.service;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.CoursDaoImpl;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class CoursService {
    private static class ServiceHolder {
        private final static CoursService instance = new CoursService();
    }

    public static CoursService getInstance() {
        return ServiceHolder.instance;
    }
    private CoursDao coursDao = CoursDaoImpl.getInstance();
    static final Logger LOGGER = LogManager.getLogger();


    public Integer deleteCours(String url_cours) throws SQLException {
        int deletedrows=0;
        if (url_cours == null||"".equals(url_cours)){
            LOGGER.warn("Un code cours ne peut pas etre null ou vide");
        }
        if(!coursDao.ExistCours(url_cours)){
            LOGGER.warn("Le cours a update n'existe pas");
        }else {
            deletedrows = coursDao.deleteCoursFromDB(url_cours);
        }
        return deletedrows;
    }

    public void updateCours(String urlcoursToUpdate, String nom_cours, String url_cours){
        coursDao.updateCoursFromDB(urlcoursToUpdate,nom_cours,url_cours);

    }

}
