package hei.projet.openhei.dao;

import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.PasswordNotChangedException;
import hei.projet.openhei.exception.UserNotAddedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {


    public User getUser(String login);
    public Boolean checkUserbyLogin(String login);
    public void addUser(User user) throws UserNotAddedException;
    public User createUserFromResultSet(ResultSet resultset) throws SQLException;
    public void setNewPassword(String login, String newPassword) throws SQLException, PasswordNotChangedException;

    //Méthodes pour lister tout les utilisateurs, lister tout les logins
    public ArrayList<User> listAllUser();
    public ArrayList<String> listAllLogin();


    public List<Integer> getListIdMatiereOfUser(Integer id_user);
    public void joinIdMatiereToUser(Integer id, Integer id_matiere);
    public void setAdmin(Integer id);
    public void supUser(Integer id);


}
