package org.rivera.apiservlet.webapp.carrito.controllers;

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

import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long id = Long.parseLong(req.getParameter("id"));
    ProductoService service = new ProductoServiceImp();
    Optional<Producto> product = service.byId(id);

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
    resp.sendRedirect(req.getContextPath() + "/carro/ver");
  }
}
