package org.rivera.apiservlet.webapp.carrito.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.rivera.apiservlet.webapp.carrito.config.ProductoServicePrincipal;
import org.rivera.apiservlet.webapp.carrito.models.Carro;
import org.rivera.apiservlet.webapp.carrito.models.ItemCarro;
import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.service.ProductoService;
import org.rivera.apiservlet.webapp.carrito.service.ProductoServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.rivera.apiservlet.webapp.carrito.service.ProductoServiceJdbcImp;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

  @Inject
  @ProductoServicePrincipal //En lugar del Named
  private ProductoService service;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long id = Long.parseLong(req.getParameter("id"));
    Optional<Producto> product = service.byIdProduct(id);

    if( product.isPresent() ) {
      ItemCarro item = new ItemCarro(1, product.get());
      HttpSession session = req.getSession();
      Carro car;
      if( session.getAttribute("carro") == null ) { //Primera vez que se usa el carro
        car = new Carro();
        session.setAttribute("carro", car);
      } else {                                        //Ya existe en la sesión el carro
        car = (Carro) session.getAttribute("carro");
      }
      car.addItemCar(item);
    }
    req.setAttribute("tittle", "Carro de compras");
    resp.sendRedirect(req.getContextPath() + "/carro/ver");
  }
}
