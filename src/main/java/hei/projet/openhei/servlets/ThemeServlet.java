package hei.projet.openhei.servlets;

import hei.projet.openhei.service.MatiereService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Themes")
public class ThemeServlet extends GenericServlet{

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        //Vérification pour l'accès aux pages privés
        if (req.getSession().getAttribute("Pseudo")!=null){
            String pseudo = (String) req.getSession().getAttribute("Pseudo");
            context.setVariable("Connected", pseudo);
            //Verfification pour savoir si l'user est un admin
            String status = (String) req.getSession().getAttribute("Admin");
            if ("true".equals(status)){
                resp.sendRedirect("/admin/ThemesAdmin");
            }
        }
        //Si l'user n'est pas admin
        templateEngine.process("themes", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

    }
}
