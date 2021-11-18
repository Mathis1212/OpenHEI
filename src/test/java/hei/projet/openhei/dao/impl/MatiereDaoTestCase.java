package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.entities.Matiere;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MatiereDaoTestCase {


@Test
    public void ShouldGetNomMatiere(){
        //GIVEN
        //WHEN
        //THEN

    }
    @Test
    public  void ShouldReturnListMatiere(){
        //GIVEN
        List<Matiere> result = new ArrayList<>();
         List<Matiere> list = new ArrayList<>();
         Matiere m1=new Matiere();
         Matiere m2=new Matiere();
         Matiere m3=new Matiere();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        //WHEN

        //THEN
        Assertions.assertThat(result).containsExactlyInAnyOrderElementsOf(list);
    }
    @Test
    public void ShouldGetNotionsMatiere(){
        //GIVEN
        //WHEN
        //THEN
    }
}
