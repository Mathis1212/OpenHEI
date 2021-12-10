package hei.projet.openhei.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import hei.projet.openhei.service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/connection")
public class ConnectionServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {

        if(req.getSession().getAttribute("Pseudo")!=null) {
            resp.sendRedirect("Accueil");
        }else{
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("connexion", context, resp.getWriter());
        }
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
            if ((login==null||("".equals(login)))||(password==null||("".equals(password)))){
                LOGGER.info("un des champs est inccorect");
                resp.sendRedirect("connexion");
            }else{
                if (UserService.getInstance().checkUser(login, password)) {
                    HttpSession session=req.getSession();
                    //on stocke les informations de l'user dans la session
                    ArrayList<String> informations=UserService.getInstance().getInformationsForSession(login);
                    session.setAttribute("Pseudo",informations.get(0));
                    session.setAttribute("Admin",informations.get(1));
                    session.setAttribute("Id",informations.get(2));
                    session.setAttribute("Login",informations.get(3));

                    resp.sendRedirect("Accueil");
                }else{
                    throw new NullPointerException();
                }
            }
        } catch (NullPointerException e) {
            resp.sendRedirect("connection");
            LOGGER.info("Error aucun utilisateur existe pour cette combinaison:",e);
        }
    }
}
