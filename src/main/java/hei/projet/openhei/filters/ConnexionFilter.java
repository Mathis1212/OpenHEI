package hei.projet.openhei.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

public class ConnexionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String pseudo = (String) httpRequest.getSession().getAttribute("Pseudo");
        String login = (String) httpRequest.getSession().getAttribute("Login");
        String password = (String) httpRequest.getSession().getAttribute("Password");
        if((login == null || "".equals(login))||(pseudo == null || "".equals(pseudo))||(password == null || "".equals(password))) {
            System.out.println("Il faut être connecté pour accéder à cette page !");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/connection");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
