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

@WebServlet("/updateCours")
public class UpdateCoursServlet {
    static final Logger LOGGER = LogManager.getLogger();

/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //on recupere le contenu des champs de la session de connexion
        String nom_cours = req.getParameter("nom_cours");
        String url_cours = req.getParameter("url_cours");
        HttpSession session=req.getSession();

        req.setAttribute("nom_cours", nom_cours);
        req.setAttribute("url_cours", url_cours);

        this.getServletContext().getRequestDispatcher("/WEB-INF/template/themes_admins.html").forward(req, resp);
    }
    }*/
}
