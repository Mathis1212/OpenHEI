package hei.projet.openhei.service;

import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatiereService {
    static final Logger LOGGER = LogManager.getLogger();
    //Création de l'insatance du service
    private static class ServiceHolder {
        private final static MatiereService instance = new MatiereService();
    }
    //Création de la méthode pour récupérer l'instance
    public static MatiereService getInstance() {
        return MatiereService.ServiceHolder.instance;
    }

    //Récupération de l'instance de MatiereDaoImpl
    private MatiereDao matiereDao = MatiereDaoImpl.getInstance();


    //Méthode qui récupère les cours et qui les ajoute à une matière
    public List<Matiere> recupMatiereAvecListCour() {
        List<Matiere> list = new ArrayList<>();
        List<Cours> listCour = new ArrayList<>();
        list= matiereDao.ListMatiere();
        for (Matiere matiere : list) {
            listCour = matiereDao.getListCour(matiere.getId());
            for (Cours cours : listCour) {
                matiere.ajouterCour(cours);
            }
        }
        return list;
    }

    //Méthode qui recupere l'ensemble des matieres de la BDD et associe chaques matiere au cours qu'elle possede
    public Map<String,List<Cours>> AssociationMatCour() {
        Map<String, List<Cours>> hmap = new HashMap<String, List<Cours>>();
        int taille = recupMatiereAvecListCour().size();
        for (int i = 0; i < taille; i++) {
            String nom =recupMatiereAvecListCour().get(i).getNomMatiere();
            List<Cours> list = new ArrayList<>();
                for(int e=0;e<recupMatiereAvecListCour().get(i).recupCour().size();e++){
                   list.add(recupMatiereAvecListCour().get(i).recupCour().get(e));
                }
            hmap.put(nom, list);
        }
        return hmap;
    }
}

