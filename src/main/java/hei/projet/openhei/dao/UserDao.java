package hei.projet.openhei.dao;

import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.UserNotFoundException;

public interface UserDao {

    public User getUser(String login) throws UserNotFoundException;
    public String getUserlogin();
    public Boolean getUserbyLogin(String login) throws UserNotFoundException;
}
