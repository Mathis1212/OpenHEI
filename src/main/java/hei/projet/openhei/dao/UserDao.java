package hei.projet.openhei.dao;

import hei.projet.openhei.entities.User;

public interface UserDao {

    public User getUser(String login);
    public String getUserlogin();

}
