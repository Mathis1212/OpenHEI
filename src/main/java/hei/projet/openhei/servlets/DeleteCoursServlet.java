package hei.projet.openhei.servlets;

import hei.projet.openhei.service.CoursService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/cours/delete")
public class DeleteCoursServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp){
        Integer coursid = Integer.parseInt(req.getParameter("id"));
        LOGGER.info("coursid to delete : "+coursid);
        try {
            CoursService.getInstance().deleteCours(coursid);
        } catch (SQLException e) {
            LOGGER.warn("Erreur SQL", e);
        }
    }
}
