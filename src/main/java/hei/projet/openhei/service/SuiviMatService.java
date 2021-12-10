package hei.projet.openhei.service;

import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.dao.impl.UserDaoImpl;
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

    public void AjouterMat(Integer id, String nom) {
       Integer idMat= MatiereDaoImpl.getInstance().getID(nom);
        UserDaoImpl.getInstance().joinIdMatiereToUser(id,idMat);

    }
    public Map<Integer, List<String>> UserandMat(){
        Map<Integer, List<String>> hmap = new HashMap<Integer, List<String>>();
        ArrayList<Integer> matsuivie=new ArrayList<Integer>();
        ArrayList<String> nomSuivie=new ArrayList<>();
        ArrayList<User> listuser=new ArrayList<>();
        listuser=UserDaoImpl.getInstance().listAllUser();
        for(int i=0;i<listuser.size();i++){
            Integer userid=listuser.get(i).getId();
            matsuivie = (ArrayList<Integer>) UserDaoImpl.getInstance().getListIdMatiereOfUser(userid);
            for(int e=0;e< matsuivie.size();e++){
                nomSuivie.add(MatiereDaoImpl.getInstance().getNom(matsuivie.get(e)));
            }
            hmap.put(userid,nomSuivie);
        }
        return hmap;
    }
    public List<String> listMat(Integer id){
        Map<Integer, List<String>>map=UserandMat();
        List<String> mat=map.get(id);
        return mat;
    }
}
