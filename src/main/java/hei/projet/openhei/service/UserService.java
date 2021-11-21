package hei.projet.openhei.service;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.PasswordNotChangedException;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNotFoundException;

public class UserService {
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
    //méthode d'ajout d'un user a la bdd
    public void creatUser(User user) throws UserNotFoundException, UserNotAddedException {
        //on regarde si l'objet user crée à partir des champs remplis par l'utilisateur est null
        if(user==null){
            throw new IllegalArgumentException("user can not be null");
        }else{
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
            }else{
                    //Check si un usager existe avec le meme login
                    if(!userDao.checkUserbyLogin((user.getUserlogin()))){
                        //Si pas d'user avec le meme pseudo, on ajoute l'user à la bdd
                        try {
                            userDao.addUser(user);
                        } catch (UserNotAddedException e) {
                            //on affiche une exception si il y a une erreur dans l'ajout à la bdd
                            throw new InternalError("fail to add to bdd");
                        }
                    }else{
                        throw new UserNotAddedException();
                    }
            }
        }

    }

    public boolean checkUser(String login, String password) throws UserNotFoundException {
        boolean result=false;
        if(userDao.checkUserbyLogin(login)){
            User user=userDao.getUser(login);
            String findedPassword = user.getUserpassword();
            if(findedPassword.equals(password)){
                result=true;
            }
        }
        return result;
    }

    public void changePassword(String login, String password, String newpassword) throws UserNotFoundException, PasswordNotChangedException {
        if (checkUser(login, password)){
            changePassword(login,password,newpassword);
        }else{
            throw new PasswordNotChangedException();
        }
    }
}
