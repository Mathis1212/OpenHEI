package hei.projet.openhei.service;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNotFoundException;

public class UserService {
    private static class ServiceHolder {
        private final static UserService instance = new UserService();
    }
    public static UserService getInstance() {
        return ServiceHolder.instance;
    }

    private UserDao userDao = UserDaoImpl.getInstance();
    //méthode d'ajout d'un user a la bdd
    public void creatUser(User user) throws UserNotFoundException {
        //on regarde si l'objet user crée à partir des champs remplis par l'utilisateur est null
        if(user==null){
            throw new IllegalArgumentException("user can not be null");
        }
        //check si chaque champs est bien remplis
        if(user.getUserlogin()==null||"".equals(user.getUserlogin())||user.getUserpassword()==null||"".equals(user.getUserpassword())||user.getUsername()==null||"".equals(user.getUsername())){
            //login
            if(user.getUserlogin()==null||"".equals(user.getUserlogin())){
                //envoi exception login non remplis
                throw new IllegalArgumentException("field login must be filled");
            }
            //password
            if(user.getUserpassword()==null||"".equals(user.getUserpassword())){
                //envoi exception password non remplis
                throw new IllegalArgumentException("field password must be filled");
            }
            //pseudo
            if(user.getUsername()==null||"".equals(user.getUsername())){
                //envoi exception pseudo non remplis
                throw new IllegalArgumentException("field pseudo must be filled");
            }
        }
        //Check si un usager existe avec le meme login
        if(userDao.getUserbyLogin(user.getUserlogin())==false){
            //Si pas d'user avec le meme pseudo, on ajoute l'user à la bdd
            try {
                userDao.addUser(user);

            } catch (UserNotAddedException e) {
                //on affiche une exception si il y a une erreur dans l'ajout à la bdd
                e.printStackTrace();
            }
        }

    }
}
