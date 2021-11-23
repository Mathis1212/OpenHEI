package hei.projet.openhei.servlets;

import hei.projet.openhei.exception.PasswordNotChangedException;
import hei.projet.openhei.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/newpassword")
public class NewPasswordServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("changepassword", context, resp.getWriter());
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
        } catch (PasswordNotChangedException e) {
            LOGGER.error("Fail to change password", new PasswordNotChangedException());
            resp.sendRedirect("changepassword");
        } catch (NullPointerException throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("changepassword");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
