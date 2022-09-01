package org.rivera.apiservlet.webapp.carrito.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.rivera.apiservlet.webapp.carrito.service.ProductoService;
import org.rivera.apiservlet.webapp.carrito.service.ProductoServiceJdbcImp;

import java.io.IOException;
import java.sql.Connection;

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
      service.deleteProduct(id);
    }
    resp.sendRedirect(req.getContextPath() + "/productosSession.html");
  }
}
