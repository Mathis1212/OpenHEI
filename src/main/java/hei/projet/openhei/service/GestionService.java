package hei.projet.openhei.service;

import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionService {
    static final Logger LOGGER = LogManager.getLogger();
    //création de l'insatance du service
    private static class ServiceHolder {
        private final static GestionService instance = new GestionService();
    }
    //création de la méthode pour récupérer l'instance
    public static GestionService getInstance() {return GestionService.ServiceHolder.instance;}

    private UserDao userDao = UserDaoImpl.getInstance();

    public Map<Integer, User> CourAndId() {
        Map<Integer, User> hmap = new HashMap<Integer, User>();
        ArrayList<User> list=new ArrayList<User>();
        list=UserDaoImpl.getInstance().listAllUser();
        int taille = list.size();
        for (int i = 0; i < taille; i++) {
            int id=list.get(i).getId();
            String nom=list.get(i).getPseudo();
            User user=list.get(i);
            hmap.put(list.get(i).getId(), list.get(i) );
        }
        return hmap;
    }
}
