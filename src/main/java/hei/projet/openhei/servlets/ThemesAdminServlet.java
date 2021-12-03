package hei.projet.openhei.servlets;

import hei.projet.openhei.service.MatiereService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/ThemesAdmin")
public class ThemesAdminServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());
        String pseudo = (String) req.getSession().getAttribute("Pseudo");
        context.setVariable("Pseudo", pseudo);

        templateEngine.process("themes_admin", context, resp.getWriter());
    }
}
