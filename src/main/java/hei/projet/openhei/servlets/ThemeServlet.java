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
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (req.getSession().getAttribute("Pseudo")!=null){

            context.setVariable("matiere", MatiereService.getInstance().AssociationMatCour());

            String pseudo = (String) req.getSession().getAttribute("Pseudo");
            context.setVariable("Connected", pseudo);

            String status = (String) req.getSession().getAttribute("Admin");
            if ("true".equals(status)){
                resp.sendRedirect("/admin/ThemesAdmin");
            }else if ("false".equals(status)){
                context.setVariable("Admin",status);
            }
        }
        templateEngine.process("themes", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

    }
}
