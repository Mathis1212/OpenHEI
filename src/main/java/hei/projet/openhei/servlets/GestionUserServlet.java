package hei.projet.openhei.servlets;


import hei.projet.openhei.dao.impl.UserDaoImpl;

import hei.projet.openhei.service.GestionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
//set Admin
       Integer id = Integer.parseInt(req.getParameter("id_user"));

        if (id != null) {
            UserDaoImpl.getInstance().setAdmin(id);
        }
        //delete user
        //Integer idSup= Integer.parseInt(req.getParameter("id_sup"));
        //if(idSup !=null ){
         //   UserDaoImpl.getInstance().supUser(idSup);
        //}
    }
}
