package org.rivera.apiservlet.webapp.carrito.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.rivera.apiservlet.webapp.carrito.config.ProductoServicePrincipal;
import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.service.ProductoService;
import org.rivera.apiservlet.webapp.carrito.service.ProductoServiceJdbcImp;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class EliminarProductoServlet extends HttpServlet {

  @Inject
  @ProductoServicePrincipal
  private ProductoService service;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        req.setAttribute("tittle", "Listado de productos");
        resp.sendRedirect(req.getContextPath() + "/productosSession.html");
      } else {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe ID del producto");
      }
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El ID debe enviarse en la ruta URL");
    }
  }
}
