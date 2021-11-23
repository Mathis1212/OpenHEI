package hei.projet.openhei.dao;

import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.PasswordNotChangedException;
import hei.projet.openhei.exception.UserFoundException;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDao {

    public User getUser(String login) throws UserNotFoundException;

    public Boolean checkUserbyLogin(String login) throws UserNotFoundException;

    public User addUser(User user) throws UserNotAddedException;

    public User createUserFromResultSet(ResultSet resultset) throws SQLException;

    public void setNewPassword(String login, String newPassword) throws SQLException, PasswordNotChangedException;

}
