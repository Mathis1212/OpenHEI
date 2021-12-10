package hei.projet.openhei.servlets;

import hei.projet.openhei.entities.Matiere;
import hei.projet.openhei.service.MatiereService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Themes")
public class SuiviMatServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    WebContext context = new WebContext(req, resp, req.getServletContext());
    TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());

    String pseudo = (String) req.getSession().getAttribute("Pseudo");
        context.setVariable("Connected", pseudo);
        templateEngine.process("themes_admin", context, resp.getWriter());
}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        //add cours
        Integer id_user;
        Integer idMat = Integer.parseInt( req.getParameter("id_mat"));

        //
    }
}
