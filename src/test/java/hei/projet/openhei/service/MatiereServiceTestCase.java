package hei.projet.openhei.service;

import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MatiereServiceTestCase {

    @InjectMocks
    private MatiereService matiereService = MatiereService.getInstance();

    @Mock
    private MatiereDaoImpl matiereDao;


    @Test
    public void ShouldReturnListOfMatiere(){
        //Given
        Integer id=0;
        List<Matiere> list = new ArrayList<>();
        List<Cours> listCour = new ArrayList<>();
        Cours cours = new Cours("nom","url",1);
        listCour.add(cours);
        Matiere matiere=new Matiere(id,"nom");
        list.add(matiere);
        Mockito.when(matiereDao.ListMatiere()).thenReturn(list);
        Mockito.when(matiereDao.getListCour(id)).thenReturn(listCour);
        //When
        List<Matiere> result=matiereService.recupMatiereAvecListCour();
        //Then
        Assertions.assertThat(result).containsExactlyInAnyOrderElementsOf(list);
    }

    @Test
    public void ShouldReturnSameHmap(){
        //Given
        Cours cours =new Cours("nom","url",1);
        Matiere matiere = new Matiere(1,"nom");
        matiere.ajouterCour(cours);
        List<Matiere> listmat = new ArrayList<>();
        listmat.add(matiere);
        Map<String, List<Cours>> hmap = new HashMap<String, List<Cours>>();
        List<Cours> list = new ArrayList<>();
        list.add(cours);
        hmap.put("nom",list);
        Mockito.when(matiereService.recupMatiereAvecListCour()).thenReturn(listmat);
        //When
        Map<String, List<Cours>> result=matiereService.AssociationMatCour();
        //Then
        Assert.assertEquals(result, hmap);
    }
}
