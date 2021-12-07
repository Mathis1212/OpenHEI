package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.service.CoursService;
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

@WebServlet("admin/ThemeAdmin/update")
public class UpdateCoursServlet extends  GenericServlet{
    static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp){
        Integer id_cours = Integer.parseInt(req.getParameter("idcours"));
        String  nom_cours= req.getParameter("nom_cours");
        String url_cours= req.getParameter("url_cours");

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
    }
}
