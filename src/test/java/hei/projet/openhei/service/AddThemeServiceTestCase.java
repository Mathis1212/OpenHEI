package hei.projet.openhei.service;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.dao.impl.CoursDaoImpl;
import hei.projet.openhei.entities.Cours;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class AddThemeServiceTestCase {
    @InjectMocks
    private Add_ThemeService addThemeService = Add_ThemeService.getInstance();

    @Mock
    private CoursDaoImpl coursDao;

    @Test
    public void ShouldCallAddCours() throws SQLException {
        //Given
        Integer id=0;
        Cours cours= new Cours("nom","url",id);
        cours.setIdMat(1);
        //When
        addThemeService.addCour(cours);
        //Then
        Mockito.verify(coursDao,Mockito.times(1)).addCour(cours);
    }

    @Test
    public void ShouldThrowException1() throws SQLException {
        //Given
        Cours cours=null;
        //When
        try{
            addThemeService.addCour(cours);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void ShouldThrowException2() throws SQLException {
        //Given
        Integer id=0;
        Cours cours= new Cours(null,"url",id);
        //When
        try{
            addThemeService.addCour(cours);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void ShouldThrowException3() throws SQLException {
        //Given
        Integer id=0;
        Cours cours= new Cours("nom",null,id);
        //When
        try{
            addThemeService.addCour(cours);
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }
}
