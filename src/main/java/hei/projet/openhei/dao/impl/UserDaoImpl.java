package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.UserFoundException;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNotFoundException;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    //création de l'instance de UserDao
    private static class ServiceHolder {
        private final static UserDao instance = new UserDaoImpl();
    }
    //création de la méthode getInstance pour récupérer les méthodes de UserDaoImpl
    public static UserDao getInstance() {
        UserDao instance = ServiceHolder.instance;
        return instance;
    }

    @Override
    //méthode qui permet de récuperer depuis son login un user inscrit dans la bdd
    public User getUser(String login) throws UserNotFoundException{
        User user=null;
        //requete sql
        String sql ="SELECT usager WHERE user_login LIKE ?";
        try {
            DataSource datasource = DataSourceProvider.getDataSource();
            try(Connection cnx =datasource.getConnection();
                PreparedStatement preparedStatement = cnx.prepareStatement(sql)){
                //paramètre de la requete sql (login)
                preparedStatement.setString(1,login);
                try(ResultSet result = preparedStatement.executeQuery()){
                    if(result.next()){
                        //si un user corespond au login on crée un objet user avec ses informations grace à la méthode "createUserFromResultSet()"
                        user= createUserFromResultSet(result);
                    }
                }
            }
        }catch (SQLException e){
            //envoi d'une exception si on ne trouve pas d'user corespondant au login
            throw new UserNotFoundException();
        }
        return user;
    }

    //méthode qui crée un objet user depuis la requete faite dans la méthode "getUser()"
    public User createUserFromResultSet(ResultSet resultSelect) throws SQLException {
        return new User(
                resultSelect.getString("user_pseudo"),
                resultSelect.getString("user_login"),
                resultSelect.getString("user_password"));
    }

    @Override
    //méthode qui renvoie true si un user est crée par la méthode "getUser()", false si la méthode "getUser()" renvoie une exception
    public Boolean getUserbyLogin(String login){
        boolean result;
        try{
            User usager=getUser(login);
            result=true;
        }catch(UserNotFoundException e){
            result=false;
        }
        return result;
    }

    @Override
    //méthode qui inscrit l'user a la bdd si toutes les conditions sont respectées
    public User addUser(User user) throws UserNotAddedException {
        //requete sql
        String sql = "INSERT INTO usager(user_name, user_login, user_password) VALUES (?, ?, ?)";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                //paramètres de la requete sql
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getUserlogin());
                preparedStatement.setString(3,user.getUserpassword());
                preparedStatement.executeUpdate();
                ResultSet ids = preparedStatement.getGeneratedKeys();
                if (ids.next()) {
                    user.setUserId(ids.getInt(1));
                    //l'id crée dans la bdd est récupéré et ajouté au champs id de l'objet user créé
                    return user;
                    //on renvoi l'objet créé
                }
            }
        } catch (SQLException e) {
            //envoi d'une exception si il y a une erreur dans l'ajout de l'user à la bdd
            throw new UserNotAddedException();
        }
        throw new RuntimeException("Erreur lors de l'inscription");
    }

    public 
}
