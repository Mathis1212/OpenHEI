package hei.projet.openhei.service;

import hei.projet.openhei.entities.Cours;

import java.sql.SQLException;

public class Add_ThemeService {
    //Creation de l'instance de MatiereDaoimpl
    private static class ServiceHolder {
        private final static Add_ThemeService instance = new Add_ThemeService();
    }

    //Creation de la methode pour recuperer l'instance
    public static Add_ThemeService getInstance() {
        return Add_ThemeService.ServiceHolder.instance;
    }
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
             CoursDaoImpl.getInstance().addCour(cours);
    }
}
