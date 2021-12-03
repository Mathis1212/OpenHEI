package hei.projet.openhei.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/admin/*")
//Filtre d'authentification des pages admin
public class AuthentificationFiltre  implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getSession().getAttribute("Pseudo")!=null){
            boolean admin=(boolean) httpRequest.getSession().getAttribute("Admin");
            if(!admin) {
                System.out.println("Il faut être connecté pour accéder à cette page !");
                httpResponse.sendRedirect("/Accueil");
                return;
            }
            chain.doFilter(request, response);
        }else{
            System.out.println("Il faut être connecté pour accéder à cette page !");
            httpResponse.sendRedirect("/Accueil");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
