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

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

  @Override //Obtengo categorías para pasarlas al jsp
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Connection conn = (Connection) req.getAttribute("conn"); //Configure conexión en el Filtro
    ProductoService service = new ProductoServiceJdbcImp(conn);
    req.setAttribute("categorias", service.toListCategories());

    getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}
