package hei.projet.openhei.servlets;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.projet.openhei.exception.UserNotFoundException;
import hei.projet.openhei.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/connection")
public class ConnectionServlet extends GenericServlet {
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("test_connexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        try {
            if (UserService.getInstance().checkUser(login, password)) {
                resp.sendRedirect("Acceuil");
            }else
        } catch (UserNotFoundException e) {
            resp.sendRedirect("inscription");
            e.printStackTrace();
        }
    }
}
