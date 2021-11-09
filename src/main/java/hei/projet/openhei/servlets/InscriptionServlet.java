package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.DataSourceProvider;
import hei.projet.openhei.entities.User;

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
        //récupération d'un objet PrintWriter à partir de l'objet réponse
        PrintWriter out = resp.getWriter();

        //Récupération de l'id stocké en session
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
        String password = req.getParameter("password");
        String pseudo = req.getParameter("pseudo");
        if(login==null||login==""||password==null||password==""||pseudo==null||pseudo==""){
            if(login==null||login==""){
                //message erreur login absent
            }
            if(password==null||password==""){
                //message erreur password absent
            }
            if(pseudo==null||pseudo==""){
                //message erreur pseudo absent
            }
        }else{
            User actualuser = new User(pseudo,login,password);
        }
    }
}
