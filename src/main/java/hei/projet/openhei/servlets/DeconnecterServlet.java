package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deconnection")
public class DeconnecterServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("deconnexion", context, resp.getWriter());

        req.getSession().removeAttribute("utilisateurConnecte");
        req.getSession().invalidate();
        resp.sendRedirect("Accueil");

    }

}