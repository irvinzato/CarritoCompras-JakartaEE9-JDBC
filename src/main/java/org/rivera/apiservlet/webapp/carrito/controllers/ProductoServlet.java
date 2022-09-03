package org.rivera.apiservlet.webapp.carrito.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productosSession.html"})
public class ProductoServlet extends HttpServlet {

  @Inject
  @Named("productoServiceJdbc")
  private ProductoService service;

  @Inject
  private LoginService serviceLoginSession;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Producto> products = service.toListProduct();

    Optional<String> usernameOptional = serviceLoginSession.getUsername(req);

    //MIGRE A LA VISTA "JSP"
    req.setAttribute("productos", products);
    req.setAttribute("username", usernameOptional);
    req.setAttribute("tittle", "Listado de productos");
    getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);
  }
}
