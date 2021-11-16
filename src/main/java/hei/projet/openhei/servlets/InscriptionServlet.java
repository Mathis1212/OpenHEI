package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.DataSourceProvider;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNotFoundException;
import hei.projet.openhei.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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
public class InscriptionServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        //Récupération de l'id stocké en session
        String id = (String) req.getSession().getAttribute("utilisateurConnecte");


        if(id==null){
            templateEngine.process("test_inscription", context, resp.getWriter());
        }else{
          resp.sendRedirect("Acceuil");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//on recupere le contenu des champs de la session d'inscription
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        String pseudo = req.getParameter("Pseudo");
        //on crée un objet user à partir du contenu des champs
        try{
            User newUser=new User(pseudo,login,password);
            UserService.getInstance().creatUser(newUser);
        } catch (IllegalArgumentException | UserNotFoundException | UserNotAddedException iae) {
            //si erreur dans les champs on envoi une erreur et on redirige l'user vers la page d'inscription
            req.getSession().setAttribute("errorMessage", iae.getMessage());
            resp.sendRedirect("inscription");
        }
    }
}
