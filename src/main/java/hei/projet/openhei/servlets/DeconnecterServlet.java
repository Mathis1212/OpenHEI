package hei.projet.openhei.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deconnection")
public class DeconnecterServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        if(req.getSession().getAttribute("Pseudo")==null) {
            resp.sendRedirect("Accueil");
            LOGGER.trace("L'utilisateur n'est pas connecter pour se deconnecter");
        }else{
            req.getSession().removeAttribute("Pseudo");
            req.getSession().removeAttribute("Login");
            req.getSession().removeAttribute("Password");
            resp.sendRedirect("Accueil");
        }
    }

}