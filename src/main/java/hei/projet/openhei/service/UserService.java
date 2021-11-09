package hei.projet.openhei.service;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;

public class UserService {

    private UserDao userDao = UserDaoImpl.getInstance();
    //Check si un usager existe avec le meme login

    public void creatUser(String pseudo,String login, String password){
        User user=new User(pseudo,login,password);
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
