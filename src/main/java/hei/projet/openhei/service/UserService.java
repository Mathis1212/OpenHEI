package hei.projet.openhei.service;

import de.mkammerer.argon2 .Argon2;
import de.mkammerer.argon2.Argon2Factory;
import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class UserService {
    //récupération de l'instance Argon2
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    static final Logger LOGGER = LogManager.getLogger();
    //création de l'insatance du service
    private static class ServiceHolder {
        private final static UserService instance = new UserService();
    }
    //création de la méthode pour récupérer l'instance
    public static UserService getInstance() {
        return ServiceHolder.instance;
    }
    //récupération de l'instance de UserDaoImpl
    private UserDao userDao = UserDaoImpl.getInstance();

    //Vérifie si un utilisateur existe déja dans la bdd avec ce login
    //enlever l'exception UserNotFoundException
    public boolean userExist(String login){
        boolean rep;
        if(userDao.getUser(login)!=null){
            rep=true;
        }else{
            rep=false;
        }
        return rep;
    }

    //Vérifie les champs de l'utilisateur et le créer
    public User CreateUser(User user) throws UserFoundException, UserNullException {
        if (user==null){
            //Exception à créer
            throw new UserNullException();
        }
        if (userExist(user.getUserlogin())){
            //Un  utilisateur existe déjà avec ce login
            throw new UserFoundException();
        }

        return user;
    }

    //Méthode qui verifie si l'utilisateur est inscrit dans la BDD et qu'il à bien saisi les bonnes informations pour se connecter
    public boolean checkUser(String login, String password){
        boolean result=false;
        if(userDao.checkUserbyLogin(login)){
            LOGGER.info("Un utilisateur existe avec ce login");
            User user=userDao.getUser(login);
            String findedPassword = user.getUserpassword();
            if(argon2.verify(findedPassword,password)){
                LOGGER.info("Le bon mot de passe à été saisie");
                result=true;
            }
        }
        return result;
    }


    //Méthode qui permet de verifier si l'utilisateur existe dans la BDD avant de changer son mot de passe
    public void changePassword(String login, String password, String newpassword) throws PasswordNotChangedException, SQLException {
        if (checkUser(login, password)){
            userDao.setNewPassword(login, newpassword);
        }else{
            LOGGER.warn("Le mot de passe n'a pas pu etre changer");
        }
    }
}
