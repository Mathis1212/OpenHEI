package hei.projet.openhei.servlets;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.projet.openhei.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connection")
public class ConnectionServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //on recupere le contenu des champs de la session de connexion
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");

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
