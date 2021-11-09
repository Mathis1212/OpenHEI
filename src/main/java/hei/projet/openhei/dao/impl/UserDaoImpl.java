package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.UserDao;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.service.UserService;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    private static class ServiceHolder {
        private final static UserDao instance = new UserDaoImpl();
    }
    public static UserDao getInstance() {
        UserDao instance = ServiceHolder.instance;
        return instance;
    }

    @Override
    public User getUser(String login) {
        User user=null;
        String sql ="SELECT usager WHERE user_login LIKE ?";
        try {
            DataSource datasource = DataSourceProvider.getDataSource();
            try(Connection cnx =datasource.getConnection();
                PreparedStatement preparedStatement = cnx.prepareStatement(sql)){
                preparedStatement.setString(1,login);
                try(ResultSet result = preparedStatement.executeQuery()){
                    if(result.next()){
                        user= createUserFromResultSet(result);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String getUserlogin() {
        return q;
    }

    private User createUserFromResultSet(ResultSet resultSelect) throws SQLException {
        return new User(
                resultSelect.getString("user_pseudo"),
                resultSelect.getString("user_login"),
                        resultSelect.getString("user_password"));
    }
}
