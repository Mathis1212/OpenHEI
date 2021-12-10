package hei.projet.openhei.service;

import hei.projet.openhei.dao.UserDao;
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
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class GestionServiceTestCase {

    @InjectMocks
    GestionService gestionService = GestionService.getInstance();

    @Mock
    private UserDao userDao;

    @Test
    public void ShouldReturnSameHmap(){
        //Given
        User user =new User(1,"pseudo","login","password",false);
        ArrayList<User> listuser = new ArrayList<>();
        listuser.add(user);
        Map<Integer, User> hmap = new HashMap<Integer, User>();
        hmap.put(1,user);
        Mockito.when(userDao.listAllUser()).thenReturn(listuser);
        //When
        Map<Integer, User> result=gestionService.CourAndId();
        //Then
        Assert.assertEquals(result, hmap);
    }
}
