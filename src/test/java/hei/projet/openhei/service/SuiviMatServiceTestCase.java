package hei.projet.openhei.service;

import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import hei.projet.openhei.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.ScopedMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class SuiviMatServiceTestCase {
    @InjectMocks
    SuiviMatService suiviMatService = SuiviMatService.getInstance();

    @Mock
    MatiereDaoImpl matiereDao;

    @Mock
    UserDaoImpl userDao;

    @Mock
    MatiereService matiereService;

    @Test
    public void ShouldReturnHmap(){
        //Given
        Map<Integer, List<String>> hmap = new HashMap<Integer, List<String>>();
        List<Integer> matsuivie=new ArrayList<Integer>();
        ArrayList<String> nomSuivie=new ArrayList<>();
        ArrayList<User> listuser=new ArrayList<>();
        User user = new User(0,"pseudo","login", "password", false);
        listuser.add(user);
        nomSuivie.add(null);
        matsuivie.add(1);
        hmap.put(user.getId(),nomSuivie);
        Mockito.when(userDao.listAllUser()).thenReturn(listuser);
        Mockito.when(userDao.getListIdMatiereOfUser(0)).thenReturn(matsuivie);
        Mockito.when(matiereDao.getNom(0)).thenReturn("nom");
        //When
        Map<Integer, List<String>> result=suiviMatService.UserandMat();
        //Then
        Assert.assertEquals(result, hmap);
    }

    @Test
    public void ShouldReturnHmap2() {
        //Given
        ArrayList<String> nomSuivie=new ArrayList<>();
        nomSuivie.add(null);
        User user = new User(0,"pseudo","login", "password", false);
        Map<Integer, List<String>> hmap = new HashMap<>();
        hmap.put(user.getId(),nomSuivie);
        Mockito.when(suiviMatService.UserandMat()).thenReturn(hmap);
        Cours cours = new Cours("nom","url");
        List<Cours> listcours = new ArrayList<>();
        listcours.add(cours);
        List<Matiere> comparaison =new ArrayList<>();
        Matiere matiere = new Matiere(0,"nom");
        matiere.ajouterCour(cours);
        comparaison.add(matiere);
        Mockito.when(matiereService.recupMatiereAvecListCour()).thenReturn(comparaison);
        Map<String, List<Cours>> hmap2 = new HashMap<String, List<Cours>>();
        hmap2.put("nom",listcours);
        //When
        Map<String, List<Cours>> result=suiviMatService.listMat(0);
        //Then
        Assert.assertEquals(result, hmap2);

    }

}
