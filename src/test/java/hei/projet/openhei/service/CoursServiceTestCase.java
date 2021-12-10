package hei.projet.openhei.service;

import hei.projet.openhei.dao.impl.CoursDaoImpl;
import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
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

    @Mock
    private MatiereDaoImpl matiereDao;


    @Test
    public void ShouldReturn0DeletedRows() throws SQLException {
        //Given
        String url = null;
        //When
        Integer result = coursService.deleteCours(url);
        //Then
        Assert.assertEquals(0, (int) result);
    }

    @Test
    public void ShouldReturn0DeletedRows2() throws SQLException {
        //Given
        String url = "notnull";
        //When
        Integer result = coursService.deleteCours(url);
        //Then
        Assert.assertEquals(0, (int) result);
    }

    @Test
    public void ShouldReturnDeletedRows() throws SQLException {
        //Given
        String url = "notnull";
        //When
        Integer result = coursService.deleteCours(url);
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void ShouldCallDeleteCoursFromDB() throws SQLException {
        //Given
        String url = "notnull";
        Mockito.when(coursDao.ExistCours(url)).thenReturn(true);
        //When
        coursService.deleteCours(url);
        //Then
        Mockito.verify(coursDao, Mockito.times(1)).deleteCoursFromDB(url);
    }

    @Test
    public void ShouldCallUpdateCoursFromDB() {
        //Given
        String urlToUpdate = "urlToUpdate";
        String nom_cours = "nom_cours";
        String url_cours = "url_cours";

        //When
        coursService.updateCours(urlToUpdate, nom_cours, url_cours);
        //Then
        Mockito.verify(coursDao, Mockito.times(1)).updateCoursFromDB(urlToUpdate, nom_cours, url_cours);
    }

    @Test
    public void ShouldCallAddCours() throws SQLException {
        //Given
        Integer id = 0;
        Cours cours = new Cours("nom", "url", id);
        cours.setIdMat(1);
        //When
        coursService.addCour(cours);
        //Then
        Mockito.verify(coursDao, Mockito.times(1)).addCour(cours);
    }

    @Test
    public void ShouldThrowException1() throws SQLException {
        //Given
        Cours cours = null;
        //When
        try {
            coursService.addCour(cours);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void ShouldThrowException2() throws SQLException {
        //Given
        Integer id = 0;
        Cours cours = new Cours(null, "url", id);
        //When
        try {
            coursService.addCour(cours);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void ShouldThrowException3() throws SQLException {
        //Given
        Integer id = 0;
        Cours cours = new Cours("nom", null, id);
        //When
        try {
            coursService.addCour(cours);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void ShouldReturnID() {
        //Given
        String nom_mat = "nom";
        Mockito.when(matiereDao.getID(nom_mat)).thenReturn(1);
        //When
        Integer result = coursService.getIDToAdd(nom_mat);
        //Then
        Assert.assertEquals(1, (int) result);
    }

    @Test
    public void ShouldThrowException() {
        //Given
        String url = "";
        String urltoupdate = "urltoupdate";
        String nom_cours = "nom";
        try {
            //When
            coursService.updateCours(url, urltoupdate, nom_cours);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void ShouldThrowException4() {
        //Given
        String nom_mat = "";
        try {
            //When
            coursService.getIDToAdd(nom_mat);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }
}
