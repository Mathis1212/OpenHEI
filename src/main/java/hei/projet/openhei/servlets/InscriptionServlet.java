package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.DataSourceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* récupération d'un objet PrintWriter à partir de l'objet réponse*/
        PrintWriter out = resp.getWriter();
        /* Récupération de l'id stocké en session*/
        String id = (String) req.getSession().getAttribute("utilisateurConnecte");
        if(id==null){
            //on affiche le formulaire d'inscription ou de connection

        }else{
            //on affiche la page d'accueil

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if(login==null){
            //message errur login null
        }
        String password = req.getParameter("password");
        if(password==null){
            //message erreur password null
        }
        String pseudo = req.getParameter("pseudo");
        if(pseudo==null){
            //affichage erreur pseudo null
        }


        //Check si un usager existe avec le meme login
        String sql0 ="SELECT usager WHERE user_login LIKE ?";
      try {
          DataSource datasource = DataSourceProvider.getDataSource();
          try(Connection cnx0 =datasource.getConnection();
              PreparedStatement preparedStatement0 = cnx0.prepareStatement(sql0)){
              preparedStatement0.setString(1, login);
              try(ResultSet result = preparedStatement0.executeQuery()){
                  if(result!=null){
                      //message d'erreur car un utilisateur existant a le meme login
                  }else{
                      //ajout de l'usager a la base de donné
                      String sql1 = "INSERT INTO usager (user_name, user_login, user_password) VALUES (?,?,?)";
                      try {
                          DataSource dataSource = DataSourceProvider.getDataSource();
                          try (Connection cnx1 = dataSource.getConnection();
                               PreparedStatement preparedStatement1 = cnx1.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
                              preparedStatement1.setString(1, pseudo);
                              preparedStatement1.setString(2, login);
                              preparedStatement1.setString(3, password);
                              preparedStatement1.executeUpdate();
                              ResultSet ids = preparedStatement1.getGeneratedKeys();
                              if(ids.next()){
                                  //affichage message compte crée
                              }
                          }
                      }catch (SQLException e){
                          e.printStackTrace();
                      }
                  }
              }
          }
      }catch (SQLException e){
          e.printStackTrace();
      }


    }
}
