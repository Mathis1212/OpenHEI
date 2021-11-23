package hei.projet.openhei.service;

import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MatiereService {
    static final Logger LOGGER = LogManager.getLogger();
    //création de l'insatance du service
    private static class ServiceHolder {
        private final static MatiereService instance = new MatiereService();
    }
    //création de la méthode pour récupérer l'instance
    public static MatiereService getInstance() {
        return MatiereService.ServiceHolder.instance;
    }

    public List<Matiere> recupMatiereAvecListCour() {
        List<Matiere> list = new ArrayList<>();
        List<Cours> listCour = new ArrayList<>();
        list=MatiereDaoImpl.getInstance().ListMatiere();
        for(int i=0;i<list.size();i++){
            listCour=MatiereDaoImpl.getInstance().getListCour(list.get(i).getId());
            for(int e=0;e<listCour.size();e++){
                list.get(i).ajouterCour(listCour.get(e));
            }
        }
        return list;
    }
}
