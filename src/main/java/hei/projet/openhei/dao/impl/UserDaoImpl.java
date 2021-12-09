package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.PasswordNotChangedException;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.service.DataSourceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    static final Logger LOGGER = LogManager.getLogger();
    //récupération de l'instance Argon2
    Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);

    //création de l'instance de UserDao
    private static class ServiceHolder {
        private final static UserDao instance = new UserDaoImpl();
    }
    //création de la méthode getInstance pour récupérer les méthodes de UserDaoImpl
    public static UserDao getInstance() {
        UserDao instance = ServiceHolder.instance;
        return instance;
    }

    //méthode qui permet de récuperer depuis son login un user inscrit dans la bdd
    @Override
    public User getUser(String login){
        User user = null;
        //requete sql
        String sql ="SELECT * FROM usager WHERE user_login=?";
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
            e.printStackTrace();
            LOGGER.error("Exception : !");
        }
        return user;
    }

    //méthode qui crée un objet user depuis la requete faite dans la méthode "getUser()"
    @Override
    public User createUserFromResultSet(ResultSet resultSelect) throws SQLException {
        User user=new User(resultSelect.getString("user_pseudo"), resultSelect.getString("user_login"), resultSelect.getString("user_password"),resultSelect.getBoolean("user_admin"));
        return user;
    }


    //méthode qui renvoie true si un user est crée par la méthode "getUser()", false si la méthode "getUser()" renvoie une exception
    @Override
    public Boolean checkUserbyLogin(String login){
        boolean result=false;
        if(getUser(login).getUserlogin().equals(login)){
            result=true;
        }else if(login==null){
            result=false;
        }
        return result;
    }


    //retourne la liste de tous les logins d'ultisateurs présent dans la bdd
    @Override
    public void addUser(User user) throws UserNotAddedException {
        //Hashage du mdp
        String Encryptedmdp = argon2.hash(4, 1024 * 1024, 8, user.getUserpassword());
        //Verfification du hashage
        Boolean VerifyEncrypte = argon2.verify(Encryptedmdp, user.getUserpassword());
        //Si le hashage est verifié on ajoute l'user
        if (VerifyEncrypte) {
            User Encrypteduser = new User(user.getPseudo(), user.getUserlogin(), Encryptedmdp);
            //requete sql
            try {
                DataSource dataSource = DataSourceProvider.getDataSource();
                try (Connection cnx = dataSource.getConnection()) {
                    String sql = "INSERT INTO usager(user_pseudo, user_login, user_password) VALUES (?,?,?)";
                    try (PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        //paramètres de la requete sql
                        preparedStatement.setString(1, Encrypteduser.getPseudo());
                        preparedStatement.setString(2, Encrypteduser.getUserlogin());
                        preparedStatement.setString(3, Encrypteduser.getUserpassword());

                        //Le problème est à cette ligne

                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    //envoi d'une exception si il y a une erreur dans l'ajout de l'user à la bdd
                    LOGGER.error("Exception : !",e);
                    throw new UserNotAddedException();
                }
           } catch (SQLException throwables) {
                LOGGER.error("Exception : !",throwables);
                throw new RuntimeException("Erreur lors de l'inscription");
            }
        }
    }

    @Override
    public void setNewPassword(String login, String newPassword) throws PasswordNotChangedException {
        //Hashage du nouveau password
        String Encryptednewmdp=argon2.hash(4, 1024*1024,8, newPassword);
        String sql = "UPDATE usager SET user_password=? WHERE user_login=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                //paramètres de la requete sql
                preparedStatement.setString(2, login);
                preparedStatement.setString(1, Encryptednewmdp);
                preparedStatement.executeUpdate();
                preparedStatement.getGeneratedKeys();
            }
        }catch (SQLException e){
            throw new PasswordNotChangedException();
        }
    }

    //retourne la liste de tous les Users présent dans la bdd
    @Override
    public ArrayList<User> listAllUser() {
        ArrayList<User> listOfUser = new ArrayList<>();
        try{
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet results = statement.executeQuery("select * from usager order by user_id")) {
                        while (results.next()) {
                            User user = new User(
                                    results.getString("user_pseudo"),
                                results.getString("user_login"),
                                results.getString("user_password"));
                            listOfUser.add(user);
                        }
                    }
                }
            }
        }catch (SQLException e) {
            // Gestion des erreurs
            LOGGER.error("Exception : !",e);
        }
        return listOfUser;
    }

    //retourne la liste de tous les logins d'ultisateurs présent dans la bdd
    @Override
    public ArrayList<String> listAllLogin() {
        ArrayList<String> listOfLogin = new ArrayList<>();
        try{
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet results = statement.executeQuery("select user_login from usager")) {
                        while (results.next()) {
                            String login = new String(results.getString("user_login"));
                            listOfLogin.add(login);
                        }
                    }
                }
            }
        }catch (SQLException e) {
            // Gestion des erreurs
            LOGGER.error("Exception : !",e);
        }
        return listOfLogin;
    }

}
