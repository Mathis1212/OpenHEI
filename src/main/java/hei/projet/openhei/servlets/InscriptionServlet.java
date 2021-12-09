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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if ((pseudo==null||("".equals(pseudo)))||(login==null||("".equals(login)))||(password==null||("".equals(password)))){
                resp.sendRedirect("inscription");
            }else{
                //verification du pseudo en regex
                Pattern patternPs =Pattern.compile("(?=.*[0-9])(?=\\S+$).{5,}", Pattern.CASE_INSENSITIVE);
                Matcher matcherPs = patternPs.matcher(pseudo);
                if (!matcherPs.find()){
                    resp.sendRedirect("inscription");
                }
                //verification du login en regex
                Pattern patternLog =Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcherLog = patternLog.matcher(login);
                if (!matcherLog.find()){
                    resp.sendRedirect("inscription");
                }
                Pattern patternPas =Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!.?=])(?=\\S+$).{8,}");
                Matcher matcherPas = patternPas.matcher(password);
                if (!matcherPas.find()){
                    resp.sendRedirect("inscription");
                }
            }
            User user_champ=new User(pseudo,login,password);
            //pro
            User user=UserService.getInstance().CreateUser(user_champ);
            UserDaoImpl.getInstance().addUser(user);
            resp.sendRedirect("Accueil");
        } catch (UserFoundException | UserNotAddedException e) {
            e.printStackTrace();
            resp.sendRedirect("inscription");
        } catch (UserNullException e) {
            e.printStackTrace();
        }
    }
}