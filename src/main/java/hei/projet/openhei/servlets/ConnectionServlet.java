package hei.projet.openhei.servlets;


import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.projet.openhei.service.UserService;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/connection")
public class ConnectionServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {

        HttpSession session=req.getSession();

        String login= (String) session.getAttribute("login");

        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //on recupere le contenu des champs de la session de connexion
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        //on peut créer un User en paramètre de la session

        //permet de mettre fin à la connexion sur le click du bouton déconnexion
        //session.invalidate()
        try {
            if(login==null||"".equals(login)){
                LOGGER.info("champ login inccorect");
                resp.sendRedirect("connexion");
            }
            if(password==null||"".equals(password)){
                LOGGER.info("champ password inccorect");
                resp.sendRedirect("connexion");
            }
            if (UserService.getInstance().checkUser(login, password)) {
                HttpSession session=req.getSession();
                //on stocke les informations de l'user dans la session
                ArrayList<String> informations=UserService.getInstance().getInformationsForSession(login);
                session.setAttribute("Pseudo",informations.get(0));
                session.setAttribute("Admin",informations.get(1));
                resp.sendRedirect("Accueil");
            }else{
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            resp.sendRedirect("connection");
            LOGGER.info("Error :",e);
        }
    }
}
