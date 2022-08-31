package org.rivera.apiservlet.webapp.carrito.controllers;

import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productosSession.html"})
public class ProductoServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Connection conn = (Connection) req.getAttribute("conn");  //Configure conexión en el Filtro
    ProductoService service = new ProductoServiceJdbcImp(conn);
    List<Producto> products = service.toList();

    LoginService serviceLoginSession = new LoginServiceImp();
    Optional<String> usernameOptional = serviceLoginSession.getUsername(req);

    //MIGRE A LA VISTA "JSP"
    req.setAttribute("productos", products);
    req.setAttribute("username", usernameOptional);
    getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);
  }
}
