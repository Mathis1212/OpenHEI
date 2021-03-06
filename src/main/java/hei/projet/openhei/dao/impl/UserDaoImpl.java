package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
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
import java.util.List;

public class UserDaoImpl implements UserDao {
    //Récupération de l'instance Log4j2
    static final Logger LOGGER = LogManager.getLogger();

    //Récupération de l'instance Argon2
    Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);

    //Création de l'instance de UserDao
    private static class ServiceHolder {
        private final static UserDao instance = new UserDaoImpl();
    }
    //Création de la méthode getInstance pour récupérer les méthodes de UserDaoImpl
    public static UserDao getInstance() {
        UserDao instance = ServiceHolder.instance;
        return instance;
    }

    //Méthode qui permet de récuperer depuis son login un user inscrit dans la BDD
    @Override
    public User getUser(String login){
        String string=login;
        String sql ="SELECT * FROM usager WHERE user_login=?";
        User user=new User();
        try {
            DataSource datasource = DataSourceProvider.getDataSource();
            try(Connection cnx =datasource.getConnection();
                PreparedStatement preparedStatement = cnx.prepareStatement(sql)){
                preparedStatement.setString(1,login);
                try(ResultSet result = preparedStatement.executeQuery()){
                    if(result.next()){
                        int id=result.getInt("user_id");
                        user.setUserId(id);
                        String pseudo=result.getString("user_pseudo");
                        user.setPseudo(pseudo);
                        String mdp=result.getString("user_password");
                        user.setUserpassword(mdp);
                        Boolean admin=result.getBoolean("user_admin");
                        user.setUserAdmin(admin);
                        user.setUserlogin(login);

                    }
                }
            }
        }catch (SQLException e){
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de l'user de login :"+ login+ " :", e);
        }

        return user;
    }

    //Méthode de création d'un objet user à la récupération des informations de la BDD
    @Override
    public User createUserFromResultSet(ResultSet resultSelect) throws SQLException {
        LOGGER.info("Création d'un objet user avec les informations récupérées de la BDD");
        User user=new User(resultSelect.getString("user_pseudo"), resultSelect.getString("user_login"), resultSelect.getString("user_password"),resultSelect.getBoolean("user_admin"));
        return user;
    }

    //Méthode qui vérifie si un utilisateur existe dans la bdd grace à son login

    @Override
    public Boolean checkUserbyLogin(String login){
        boolean result=false;
        String test=getUser(login).getUserlogin();
        String logine=login;
        if(getUser(login).getUserlogin().equals(login)){
            result=true;
        }else if(login==null||"".equals(login)){
            result=false;
            LOGGER.info("Aucun utilisateur trouvé car le champ login est vide ou null");
        }
        return result;
    }


    //Méthode qui ajoute un utilisateur dans la BDD
    @Override
    public void addUser(User user) throws UserNotAddedException {
        //Hashage du mdp
        String Encryptedmdp = argon2.hash(4, 1024 * 1024, 8, user.getUserpassword());
        //Verfification du hashage
        Boolean VerifyEncrypte = argon2.verify(Encryptedmdp, user.getUserpassword());
        //Si le hashage est verifié on ajoute l'user
        if (VerifyEncrypte) {
            User Encrypteduser = new User(user.getPseudo(), user.getUserlogin(), Encryptedmdp);
            try {
                DataSource dataSource = DataSourceProvider.getDataSource();
                try (Connection cnx = dataSource.getConnection()) {
                    String sql = "INSERT INTO usager(user_pseudo, user_login, user_password) VALUES (?,?,?)";
                    try (PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        preparedStatement.setString(1, Encrypteduser.getPseudo());
                        preparedStatement.setString(2, Encrypteduser.getUserlogin());
                        preparedStatement.setString(3, Encrypteduser.getUserpassword());
                        preparedStatement.executeUpdate();
                    }
                }
           } catch (SQLException e) {
                LOGGER.error("Erreur dans la transmission avec la BDD lors de l'ajout de l'utilisateur :", e);
            }
        }
    }

    //Méthode qui permet à l'utilisateur de changer son mot de passe en BDD
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
            LOGGER.error("Erreur dans la transmission avec la BDD lors du changement de mot de passe :", e);
        }
    }

    //Méthode qui retourne la liste de tous les utilisateurs présent en BDD
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
                                    results.getInt("user_id"),
                                    results.getString("user_pseudo"),
                                results.getString("user_login"),
                                results.getString("user_password"),
                                results.getBoolean("user_admin"));
                            listOfUser.add(user);
                        }
                    }
                }
            }
        }catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de la liste des utilisateurs :", e);
        }
        return listOfUser;
    }

    //Méthode qui retourne la liste de tous les logins d'ultisateurs présent dans la BDD
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
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de la liste des logins :", e);
        }
        return listOfLogin;
    }
    @Override
    public List<Integer> getListIdMatiereOfUser(Integer id_user) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT id_matiere_suivi FROM suivi WHERE user_id=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id_user);
                try (ResultSet resultSelect = preparedStatement.executeQuery()) {
                    while(resultSelect.next()){
                        int id=resultSelect.getInt("id_matiere_suivi");
                        list.add(id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void joinIdMatiereToUser(Integer id, Integer id_matiere) {
        String sql = "INSERT INTO Projet_openHEI.suivi (user_id,id_matiere_suivi) values(?,?)";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, id_matiere);
                try (ResultSet resultSelect = preparedStatement.executeQuery()) {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update l'attribut admin de l'usager avec son id
    @Override
    public void setAdmin(Integer id) {

        String sql = "UPDATE usager SET user_admin=? WHERE user_id=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                //paramètres de la requete sql
                preparedStatement.setInt(2, id);
                preparedStatement.setInt(1,1 );
                preparedStatement.executeUpdate();
                preparedStatement.getGeneratedKeys();
            }
        }catch (SQLException e){
        }
    }
    @Override
    public void supUser(Integer id ){
        String sqlQuery = " DELETE FROM usager WHERE usager.user_id=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.info("Erreur SQL");
        }

    }

}
