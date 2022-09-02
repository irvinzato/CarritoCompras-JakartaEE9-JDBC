package org.rivera.apiservlet.webapp.carrito.filters;

import org.rivera.apiservlet.webapp.carrito.service.exception.ServiceJdbcException;
import org.rivera.apiservlet.webapp.carrito.util.ConexionBaseDatos;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.rivera.apiservlet.webapp.carrito.util.ConexionBaseDatosPool;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    try( Connection conn = ConexionBaseDatosPool.getConnection() ) {    //PUEDO USAR CUALQUIERA DE MIS CONEXIONES
      if( conn.getAutoCommit() ) {
        conn.setAutoCommit(false);
      }
      try {
        servletRequest.setAttribute("conn", conn);  //Le paso a los atributos del request la conexión(Asi puedo utilizarla en todos los Servlets)
        filterChain.doFilter(servletRequest, servletResponse);
        conn.commit();
      }catch(SQLException | ServiceJdbcException e) {
        conn.rollback();
        ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                e.getMessage());
        e.printStackTrace();
      }
    } catch (SQLException | NamingException e) {      //NamingException lo ocupo para la conexión POOL en singleTon no
      throw new RuntimeException(e);
    }
  }
}
