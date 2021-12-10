package hei.projet.openhei.service;

import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import hei.projet.openhei.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuiviMatService {

    private static class ServiceHolder {
        private final static SuiviMatService instance = new SuiviMatService();
    }

    //Creation de la methode pour recuperer l'instance
    public static SuiviMatService getInstance() {
        return SuiviMatService.ServiceHolder.instance;
    }

    UserDao userDao = UserDaoImpl.getInstance();

    MatiereDao matiereDao = MatiereDaoImpl.getInstance();

    MatiereService matiereService = MatiereService.getInstance();


    public void AjouterMat(Integer id, String nom) {
       Integer idMat= matiereDao.getID(nom);
        userDao.joinIdMatiereToUser(id,idMat);

    }
    //revoir une hashmap contenant en cle l'id de touts les users et le nom des cours qu'ils suivent
    public Map<Integer, List<String>> UserandMat(){
        Map<Integer, List<String>> hmap = new HashMap<Integer, List<String>>();
        List<Integer> matsuivie=new ArrayList<Integer>();
        ArrayList<String> nomSuivie=new ArrayList<>();
        ArrayList<User> listuser=new ArrayList<>();
        listuser=userDao.listAllUser();
        for(int i=0;i<listuser.size();i++){
            Integer userid=listuser.get(i).getId();
            matsuivie = userDao.getListIdMatiereOfUser(userid);
            for(int e=0;e< matsuivie.size();e++){
<<<<<<< HEAD
                nomSuivie.add(matiereDao.getNom(matsuivie.get(e)));
=======
                nomSuivie.add(MatiereDaoImpl.getInstance().getNom(matsuivie.get(e)));
>>>>>>> 1ad7f56b5a4af351551c040f1d64670a6cdeddb8
            }
            hmap.put(userid,nomSuivie);
        }
        return hmap;
    }
// renvoie une hash map avec le nom des Matieres ajoute pour un user en cle et en valeur les cours qui lui sont associes
    public Map<String, List<Cours>> listMat(Integer id){
        Map<String, List<Cours>> hmap = new HashMap<String, List<Cours>>();
        Map<Integer, List<String>>map=UserandMat();
        List<String> listNom=map.get(id);
        List<Matiere> comparaison=matiereService.recupMatiereAvecListCour();
        List<Matiere> mat=new ArrayList<>();
        for(int i=0;i< comparaison.size();i++) {
            for(int e=0;e<listNom.size() ;e++){
                if(listNom.get(e).equals(comparaison.get(i).getNomMatiere())){
                    mat.add(comparaison.get(i));
                }
            }
        }
        for (int a = 0; a < mat.size(); a++) {
            String nom =mat.get(a).getNomMatiere();
            List<Cours> list = new ArrayList<>();
            for(int b=0;b<mat.get(a).recupCour().size();b++){
                list.add(mat.get(a).recupCour().get(b));
            }
            hmap.put(nom, list);
        }
        return hmap;
    }
}
