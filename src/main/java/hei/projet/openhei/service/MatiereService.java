package hei.projet.openhei.service;

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

    // recupere l'ensemble des matieres de la BDD et associe chaques matiere au cours qu'elle possede
    public Map<Matiere,List<String>> AssociationMatCour() {
        Map<Matiere, List<String>> hmap = new HashMap<Matiere, List<String>>();
        int taille = recupMatiereAvecListCour().size();
        for (int i = 0; i < taille; i++) {
            Matiere mat = recupMatiereAvecListCour().get(i);
            List<String> list = new ArrayList<>();
                for(int e=0;e<recupMatiereAvecListCour().get(i).recupCour().size();e++){
                   list.add(recupMatiereAvecListCour().get(i).recupCour().get(e).getnomCours());
                }
            hmap.put(mat, list);
        }
        return hmap;
    }
}
