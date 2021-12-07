package hei.projet.openhei.service;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.dao.impl.CoursDaoImpl;
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

    static final Logger LOGGER = LogManager.getLogger();


    public Integer deleteCours(String url_cours) throws SQLException {
        int deletedrows=0;
        if (url_cours == null){
            LOGGER.warn("Un code cours ne peut pas etre null");
        }else if(!CoursDaoImpl.getInstance().ExistCours(url_cours)){
            LOGGER.warn("Le cours n'existe pas");
        }else{
            deletedrows=CoursDaoImpl.getInstance().deleteCoursFromDB(url_cours);
            if(deletedrows!=0){
                LOGGER.info("Some rows deleted");
            }else{
                LOGGER.info("nothing deleted");
            }
        }
        return deletedrows;
    }

    public void updateCours(String urlcoursToUpdate, String nom_cours, String url_cours){
        CoursDaoImpl.getInstance().updateCoursFromDB(urlcoursToUpdate,nom_cours,url_cours);

    }

}
