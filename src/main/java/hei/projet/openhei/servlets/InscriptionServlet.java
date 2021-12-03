package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.UserDaoImpl;
import hei.projet.openhei.entities.User;
import hei.projet.openhei.exception.UserFoundException;
import hei.projet.openhei.exception.UserNotAddedException;
import hei.projet.openhei.exception.UserNullException;
import hei.projet.openhei.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        if(req.getSession().getAttribute("Pseudo")!=null) {
            resp.sendRedirect("Accueil");
        }else{
            templateEngine.process("inscription", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //on recupere le contenu des champs de la session d'inscription
        String pseudo = req.getParameter("Pseudo");
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");

        try {
            //Vérification des champs
            if(pseudo==null||"".equals(pseudo)){
                resp.sendRedirect("inscription");
            }
            if(login==null||"".equals(login)){
                resp.sendRedirect("inscription");
            }
            if(password==null||"".equals(password)){
                resp.sendRedirect("inscription");
            }

            User user_champ=new User(pseudo,login,password);
            //pro
            User user=UserService.getInstance().CreateUser(user_champ);
            UserDaoImpl.getInstance().addUser(user);
            resp.sendRedirect("Accueil");
        } catch (UserFoundException e) {
            e.printStackTrace();
            resp.sendRedirect("inscription");
        } catch (UserNotAddedException e) {
            e.printStackTrace();
            resp.sendRedirect("inscription");
        } catch (UserNullException e) {
            e.printStackTrace();
        }
    }
}
