package hei.projet.openhei.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.PasswordNotChangedException;
import hei.projet.openhei.exception.UserFoundException;
import hei.projet.openhei.exception.UserNullException;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestCase {
    @InjectMocks
    private UserService userService = UserService.getInstance();

    @Mock
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    @Mock
    private UserDaoImpl userDao;

    @Test
    public void ShouldReturnTrueIfUserIsNotNull(){
        //Given
        User user = new User("pseudo","login","password");
        Mockito.when(userDao.getUser("login")).thenReturn(user);
        //When
        Boolean resp = userService.userExist("login");
        //Then
        Assert.assertTrue(resp);
    }

    @Test
    public void ShouldReturnFalseIfUserIsNull(){
        //Given
        User user=null;
        Mockito.when(userDao.getUser("login")).thenReturn(user);
        //When
        Boolean resp = userService.userExist("login");
        //Then
        Assert.assertFalse(resp);
    }

    @Test
    public void ShouldThrowUserNullException() throws UserFoundException, UserNullException {
        //Given
        User user=null;
        //When
        try {
            userService.CreateUser(user);
            //Then
        } catch (Exception e) {
            Assertions.assertThat(e).isExactlyInstanceOf(UserNullException.class);
        }
    }

    @Test
    public void ShouldThrowUserFoundException() throws UserFoundException, UserNullException {
        //Given
        User user = new User("pseudo","login","password");
        Mockito.when(userDao.getUser("login")).thenReturn(user);
        //When
        try {
            userService.CreateUser(user);
        } catch (Exception e) {
            Assertions.assertThat(e).isExactlyInstanceOf(UserFoundException.class);
        }
    }

    @Test
    public void ShouldReturnUser() throws UserFoundException, UserNullException {
        //Given
        User user = new User("pseudo","login","password");
        Mockito.when(userDao.getUser("login")).thenReturn(null);
        //When
        User result=userService.getInstance().CreateUser(user);
        //Then
        Assertions.assertThat(result).isExactlyInstanceOf(User.class);
    }

    @Test
    public void ShouldCheckUserAndReturnFalse(){
        //Given
        String login="login";
        String password="password";
        Mockito.when(userDao.checkUserbyLogin(login)).thenReturn(false);
        //When
        boolean result= userService.checkUser(login, password);
        //Then
        Assert.assertEquals(false, result);
    }

    @Test
    public void ShouldCheckUserAndReturnTrue(){
        //Given
        User user = new User("pseudo","login","password");
        Mockito.when(userDao.checkUserbyLogin(user.getUserlogin())).thenReturn(true);
        Mockito.when(userDao.getUser(user.getUserlogin())).thenReturn(user);
        Mockito.when(argon2.verify(user.getUserpassword(),"password")).thenReturn(true);
        //When
        boolean result= userService.checkUser(user.getUserlogin(),"password");
        //Then
        Assert.assertEquals(true, result);
    }

    @Test
    public void ShouldCallSetNewPassword() throws PasswordNotChangedException, SQLException {
        //Given
        User user = new User("pseudo","login","password");
        Mockito.when(userDao.checkUserbyLogin(user.getUserlogin())).thenReturn(true);
        Mockito.when(userDao.getUser(user.getUserlogin())).thenReturn(user);
        Mockito.when(argon2.verify(user.getUserpassword(),"password")).thenReturn(true);
        //When
            userService.changePassword("login", "password", "newpassword");
        //Then
            Mockito.verify(userDao,Mockito.times(1)).setNewPassword("login","newpassword");
        }
}

