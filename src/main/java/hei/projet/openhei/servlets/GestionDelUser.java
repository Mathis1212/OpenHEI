package hei.projet.openhei.servlets;

import hei.projet.openhei.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/Gestiondel")
public class GestionDelUser extends GenericServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //delete user
        Integer idSup= Integer.parseInt(req.getParameter("id_sup"));
        if(idSup !=null ){
           UserDaoImpl.getInstance().supUser(idSup);
        }
    }
}
