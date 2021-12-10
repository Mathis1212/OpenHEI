package hei.projet.openhei.servlets;

import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.service.CoursService;
import hei.projet.openhei.service.MatiereService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/admin/ThemesAdmin")
public class ThemesAdminServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());

        String status = (String) req.getSession().getAttribute("Admin");
        context.setVariable("Admin",status);

        String pseudo = (String) req.getSession().getAttribute("Pseudo");
        context.setVariable("Connected", pseudo);
        templateEngine.process("themes_admin", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

    //add cours
        String nom = req.getParameter("nom_cour");
        String url = req.getParameter("url_cour");
        String nom_mat = req.getParameter("nom_mat");

        //
        if ((nom != null)&&(url != null)&&(nom_mat != null)) {
            if((!"".equals(nom))&&(!"".equals(url))&&!"".equals(nom_mat)) {
                Integer id_mat = CoursService.getInstance().getIDToAdd(nom_mat);
                Cours c = new Cours(nom, url);
                c.setIdMat(id_mat);
                try {
                    CoursService.getInstance().addCour(c);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    //update d'un cours
        String urlcoursToUpdate = req.getParameter("urlcoursToUpdate");
        String nom_cours = req.getParameter("NewNomcours");
        String url_cours = req.getParameter("NewUrlcours");
        if ((urlcoursToUpdate != null&&!("".equals(urlcoursToUpdate)))&&(nom_cours != null&&!("".equals(nom_cours)))&&(url_cours != null&&!("".equals(url_cours)))) {
            LOGGER.info("cours to update : id= " + urlcoursToUpdate + " nom= " + nom_cours + " url =" + url_cours);
            try {
                CoursService.getInstance().updateCours(urlcoursToUpdate, nom_cours, url_cours);
            } catch (Exception e) {
                LOGGER.warn("Le cours n'as pas pu etre update", e);
            }} else {
                if (nom_cours == null || "".equals(nom_cours)) {
                    LOGGER.warn("le nom du cours a update ne doit pas etre vide");
                }
                if (url_cours == null || "".equals(url_cours)) {
                    LOGGER.warn("l'url du cours a update ne doit pas etre vide");
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

