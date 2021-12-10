package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.Matiere;
import hei.projet.openhei.service.MatiereService;
import hei.projet.openhei.service.SuiviMatService;
import hei.projet.openhei.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Profil")
public class SuiviMatServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        int id= Integer.parseInt(String.valueOf(req.getSession().getAttribute("Id")));

        if (req.getSession().getAttribute("Pseudo")!=null){
            String status = (String) req.getSession().getAttribute("Admin");
            context.setVariable("matiere", SuiviMatService.getInstance().listMat(id));
            if ("true".equals(status)||status==null){
                resp.sendRedirect("/Accueil");
            }else if ("false".equals(status)){
                context.setVariable("Admin",status);
            }

            String pseudo = (String) req.getSession().getAttribute("Pseudo");
            context.setVariable("Connected", pseudo);
            templateEngine.process("profil", context, resp.getWriter());
        }else{
            resp.sendRedirect("Accueil");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        //add cours
        int id= Integer.parseInt(String.valueOf(req.getSession().getAttribute("Id")));
        String Mat = req.getParameter("nom_mat");
        SuiviMatService.getInstance().AjouterMat(id,Mat);

        //
    }
}
