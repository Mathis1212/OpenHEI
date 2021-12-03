package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.CoursDaoImpl;
import hei.projet.openhei.dao.impl.MatiereDaoImpl;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.service.Add_ThemeService;
import hei.projet.openhei.service.MatiereService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Theme/admin/add")
public class AddThemeServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("themes_admin", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom= req.getParameter("nom_cour");
        String url=req.getParameter("url_cour");
        String nom_mat= req.getParameter("nom_mat");
        Integer id_mat= MatiereDaoImpl.getInstance().getID(nom_mat);
        Cours c =new Cours(nom,url);
        c.setId(id_mat);
        try {
            Add_ThemeService.getInstance().addCour(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
