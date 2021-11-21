package hei.projet.openhei.servlets;

import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNotFoundException;
import hei.projet.openhei.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newpassword")
public class NewPasswordServlet extends GenericServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//on recupere le contenu des champs de la session de changement de mdp
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        String newPassword = req.getParameter("newPassword");

    }
}
