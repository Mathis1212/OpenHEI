package hei.projet.openhei.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getSession().getAttribute("Pseudo")!=null){
            String admin=(String) httpRequest.getSession().getAttribute("Admin");
            if(!("true".equals(admin))) {
                LOGGER.info("Il faut être connecté pour accéder à cette page !");
                httpResponse.sendRedirect("/Accueil");
                return;
            }
            chain.doFilter(request, response);
        }else{
            LOGGER.info("Il faut être connecté pour accéder à cette page !");
            httpResponse.sendRedirect("/Accueil");
        }
    }

    @Override
    public void destroy() {

    }
}
