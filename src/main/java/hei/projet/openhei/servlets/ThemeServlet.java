package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.CoursDaoImpl;
import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/Themes")
public class ThemeServlet extends GenericServlet{
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("matiere",MatiereDaoImpl.getInstance().ListMatiere());
        context.setVariable("cour", CoursDaoImpl.getInstance().getNom(1));
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("Themes", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom_matiere= MatiereDaoImpl.getInstance().getNom(Integer.valueOf(req.getParameter("id")));
    }
}
