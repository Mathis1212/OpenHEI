package hei.projet.openhei.servlets;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Accueil")
public class HomeServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        if (req.getSession().getAttribute("Pseudo")!=null){
            String pseudo = (String) req.getSession().getAttribute("Pseudo");
            context.setVariable("Connected", pseudo);

            //récupère le paramètre de status de l'user
            String status = (String) req.getSession().getAttribute("Admin");
            if ("true".equals(status)){
                context.setVariable("Admin",status);
            }
        }
        templateEngine.process("index", context, resp.getWriter());
    }


}
