package hei.projet.openhei.service;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.UserNotFoundException;

public class UserService {

    private UserDao userDao = UserDaoImpl.getInstance();
    //Check si un usager existe avec le meme login

    public void creatUser(String login) throws UserNotFoundException {
        if(userDao.getUser(login) throws UserNotFoundException()){

    }
}
