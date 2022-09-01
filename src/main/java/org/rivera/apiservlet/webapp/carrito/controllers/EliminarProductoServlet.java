package org.rivera.apiservlet.webapp.carrito.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.service.ProductoService;
import org.rivera.apiservlet.webapp.carrito.service.ProductoServiceJdbcImp;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class EliminarProductoServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Connection conn = (Connection) req.getAttribute("conn");
    ProductoService service = new ProductoServiceJdbcImp(conn);
    Long id;
    try{
      id = Long.parseLong(req.getParameter("id"));
    }catch( NumberFormatException e ) {
      id = 0L;
    }
    if( id > 0 ) {
      //La parte de encontrar por "ID" es para mejorar el proceso y estar seguro de la existencia del producto en BD
      Optional<Producto> op = service.byIdProduct(id);
      if( op.isPresent() ) {
        service.deleteProduct(id);
        resp.sendRedirect(req.getContextPath() + "/productosSession.html");
      } else {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe ID del producto");
      }
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El ID debe enviarse en la ruta URL");
    }
  }
}
