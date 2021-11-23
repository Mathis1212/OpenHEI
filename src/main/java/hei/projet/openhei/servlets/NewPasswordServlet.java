package hei.projet.openhei.servlets;

import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.PasswordNotChangedException;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNotFoundException;
import hei.projet.openhei.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newpassword")
public class NewPasswordServlet extends GenericServlet{
    static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("test_newpassword", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Logger LOGGER = LogManager.getLogger();
//on recupere le contenu des champs de la session de changement de mdp
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        String newPassword = req.getParameter("newPassword");
        //on essaie de changer le mdp
        try{
            UserService.getInstance().changePassword(login,password,newPassword);
            resp.sendRedirect("connection");
        } catch (Exception e) {
            LOGGER.error(e);
            resp.sendRedirect("newpassword");
        }
    }
}
