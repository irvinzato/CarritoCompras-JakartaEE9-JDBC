package org.rivera.apiservlet.webapp.carrito.filters;

import org.rivera.apiservlet.webapp.carrito.service.LoginService;
import org.rivera.apiservlet.webapp.carrito.service.LoginServiceImp;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/carro/*")
public class LoginFiltro implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    LoginService serviceLogin = new LoginServiceImp();
    Optional<String> username = serviceLogin.getUsername((HttpServletRequest) servletRequest );

    if( username.isPresent() ) {
      filterChain.doFilter(servletRequest, servletResponse);  //Para continuar con la cadena de los filtros o recursos hasta llegar al Servlet
    } else {
      ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
              "FILTRO DE SERVLET - No tienes autorización para ingresar a esta pagina, por favor haz login");
    }
  }
}
