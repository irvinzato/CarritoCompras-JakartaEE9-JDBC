package org.rivera.apiservlet.webapp.carrito.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.rivera.apiservlet.webapp.carrito.models.Categoria;
import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.service.ProductoService;
import org.rivera.apiservlet.webapp.carrito.service.ProductoServiceJdbcImp;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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
    Connection conn = (Connection) req.getAttribute("conn");
    ProductoService service = new ProductoServiceJdbcImp(conn);

    String name = req.getParameter("name");
    Integer price;
    try{
      price = Integer.parseInt(req.getParameter("price"));
    } catch( NumberFormatException e ) {  //Por si mete un carácter inválido el usuario
      price = 0;
    }
    String dateStr = req.getParameter("register_date");
    String sku = req.getParameter("sku");
    Long categoryId;
    try {
      categoryId = Long.parseLong(req.getParameter("category"));
    } catch( NumberFormatException e ) {
      categoryId = 0L;
    }
    Map<String, String> errors = new HashMap<>();

    if( name == null || name.isBlank() ) {
      errors.put("name", "El nombre no puede estar vacío");
    }
    if( sku == null || sku.isBlank() ) {
      errors.put("sku", "El sku no puede estar en blanco");
    } else if( sku.length() > 10 ) {
      errors.put("sku", "El sku no puede tener mas de 10 caracteres");
    }
    if( dateStr == null || dateStr.isBlank() ) {
      errors.put("dateRegister", "Debe seleccionar una fecha de registro");
    }
    if( price.equals(0) ) {
      errors.put("price", "El precio es requerido");
    }
    if( categoryId.equals(0L) ) {
      errors.put("category", "La categoría es requerida");
    }

    if( errors.isEmpty() ) {
      LocalDate registerDate = LocalDate.parse( dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));  //La fecha String la parseo a "LocalDate"
      Producto product = new Producto();
      product.setName(name);
      product.setPrice(price);
      product.setRegisterDate(registerDate);
      product.setSku(sku);
      Categoria c = new Categoria();
      c.setId(categoryId);
      product.setCategory(c);

      service.saveProduct(product);

      resp.sendRedirect(req.getContextPath() + "/productosSession.html"); //Importante redirección para evitar refresh con información duplicada
    } else {
      //Si hay errores paso la lista de errores a la vista y vuelvo a mostrar el formulario
      req.setAttribute("errors", errors);
      doGet(req, resp);
    }

   }
}