package hei.projet.openhei.service;

import hei.projet.openhei.dao.impl.CoursDaoImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;


import java.sql.SQLException;





@RunWith(MockitoJUnitRunner.class)
public class CoursServiceTestCase {

    @InjectMocks
    private CoursService coursService = CoursService.getInstance();

    @Mock
    private CoursDaoImpl coursDao;


    @Test
    public void ShouldReturn0DeletedRows() throws SQLException {
        //Given
        String url=null;
        //When
        Integer result =coursService.deleteCours(url);
        //Then
        Assert.assertEquals(0, (int) result);
    }

    @Test
    public void ShouldReturn0DeletedRows2() throws SQLException {
        //Given
        String url="notnull";
        //When
        Integer result =coursService.deleteCours(url);
        //Then
        Assert.assertEquals(0, (int) result);
    }

    @Test
    public void ShouldReturnDeletedRows() throws SQLException {
        //Given
        String url="notnull";
        //When
        Integer result =coursService.deleteCours(url);
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void ShouldCallDeleteCoursFromDB() throws SQLException {
        //Given
        String url="notnull";
        Mockito.when(coursDao.ExistCours(url)).thenReturn(true);
        //When
        coursService.deleteCours(url);
        //Then
        Mockito.verify(coursDao,Mockito.times(1)).deleteCoursFromDB(url);
    }

    @Test
    public void ShouldCallUpdateCoursFromDB(){
        //Given
        String urlToUpdate="urlToUpdate";
        String nom_cours="nom_cours";
        String url_cours="url_cours";

        //When
        coursService.updateCours(urlToUpdate, nom_cours, url_cours);
        //Then
        Mockito.verify(coursDao,Mockito.times(1)).updateCoursFromDB(urlToUpdate,nom_cours,url_cours);
    }
}
