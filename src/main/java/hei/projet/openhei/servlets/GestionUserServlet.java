package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.service.Add_ThemeService;
import hei.projet.openhei.service.CoursService;
import hei.projet.openhei.service.GestionService;
import hei.projet.openhei.service.MatiereService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/GestionAdmin")
public class GestionUserServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("user", GestionService.getInstance().CourAndId());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String pseudo = (String) req.getSession().getAttribute("Pseudo");
        context.setVariable("Pseudo", pseudo);
        templateEngine.process("gestion_user_admin", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//add user
        String nom = req.getParameter("nom_cour");
        String url = req.getParameter("url_cour");
        String nom_mat = req.getParameter("nom_mat");
        if ((nom != null)&&(url != null)&&(nom_mat != null)) {
            if((!"".equals(nom))&&(!"".equals(url))&&!"".equals(nom_mat)) {
                Integer id_mat = MatiereDaoImpl.getInstance().getID(nom_mat);
                Cours c = new Cours(nom, url);
                c.setIdMat(id_mat);
                try {
                    Add_ThemeService.getInstance().addCour(c);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
//delete un cours

        String coursurl = req.getParameter("urlcoursToDelete");
        if (coursurl != null&&!"".equals(coursurl)) {
            LOGGER.info("coursid to delete : " + coursurl);
            try {
                CoursService.getInstance().deleteCours(coursurl);
            } catch (SQLException e) {
                LOGGER.warn("Erreur SQL", e);
            }
        }else{
            LOGGER.warn("Le cours n'a pas pu etre supprimé");
        }
    }
}
