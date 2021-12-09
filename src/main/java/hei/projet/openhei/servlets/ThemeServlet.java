package hei.projet.openhei.servlets;

import hei.projet.openhei.service.MatiereService;
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
        context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
<<<<<<< HEAD

=======
>>>>>>> dev
        Boolean status = (Boolean) req.getSession().getAttribute("Admin");
        if ((status!=null)&&status){
            resp.sendRedirect("/admin/ThemesAdmin");
        }else {
            String pseudo = (String) req.getSession().getAttribute("Pseudo");
            context.setVariable("Connected", pseudo);
            templateEngine.process("themes", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
