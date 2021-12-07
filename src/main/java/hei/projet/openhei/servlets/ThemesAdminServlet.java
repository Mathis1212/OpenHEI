package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.service.Add_ThemeService;
import hei.projet.openhei.service.CoursService;
import hei.projet.openhei.service.MatiereService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/admin/ThemesAdmin")
public class ThemesAdminServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String pseudo = (String) req.getSession().getAttribute("Pseudo");
        context.setVariable("Pseudo", pseudo);
        templateEngine.process("themes_admin", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom_cour");
        String url = req.getParameter("url_cour");
        String nom_mat = req.getParameter("nom_mat");
        Integer id_mat = MatiereDaoImpl.getInstance().getID(nom_mat);
        Cours c = new Cours(nom, url);
        c.setIdMat(id_mat);
        try {
            Add_ThemeService.getInstance().addCour(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Integer id_cours = Integer.parseInt(req.getParameter("idcoursToUpdate"));
        String  nom_cours= req.getParameter("NewNomcours");
        String url_cours= req.getParameter("NewUrlcours");
        LOGGER.info("cours to update : id= "+id_cours+" nom= "+nom_cours+" url ="+url_cours);
        if(nom_cours==null || nom_cours==" "){
            LOGGER.warn("le nom du cours a update ne doit pas etre vide");
        }
        if(url_cours==null||url_cours==" "){
            LOGGER.warn("l'url du cours a update ne doit pas etre vide");
        }else {
            try {
                CoursService.getInstance().updateCours(id_cours, nom_cours, url_cours);
            } catch (Exception e) {
                LOGGER.warn("Le cours n'as pas pu etre update", e);
            }
        }

        Integer coursid = Integer.parseInt(req.getParameter("idcoursToDelete"));
        LOGGER.info("coursid to delete : "+coursid);
        try {
            CoursService.getInstance().deleteCours(coursid);
        } catch (SQLException e) {
            LOGGER.warn("Erreur SQL", e);
        }
    }
}

