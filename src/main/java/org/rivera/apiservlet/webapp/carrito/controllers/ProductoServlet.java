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
import java.util.List;
import java.util.Optional;

@WebServlet({"/productosSession.html"})
public class ProductoServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ProductoService service = new ProductoServiceImp();
    List<Producto> products = service.toList();

    LoginService serviceLoginSession = new LoginServiceImp();
    Optional<String> usernameOptional = serviceLoginSession.getUsername(req);

    //Estos valores los traigo de listeners
    String msgRequestListener = (String) req.getAttribute("mensajeRequest");
    String msgApp = (String) getServletContext().getAttribute("mensaje");

    resp.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = resp.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("   <head>");
      out.println("     <meta charset='UTF-8'>");
      out.println("     <title> Listado de productos con Session </title>");
      out.println("   </head>");
      out.println("   <body>");
      out.println("     <h1> Listado de productos con Session </h1>");
      if( usernameOptional.isPresent() ) {
        out.println("       <h2 style='color: blue;'> Hola " + usernameOptional.get() + " bienvenido ! </h2>");
      }
      out.println("       <table>");
      out.println("         <tr>");
      out.println("           <th> ID </th>");
      out.println("           <th> Nombre </th>");
      out.println("           <th> Tipo </th>");
      if( usernameOptional.isPresent() ) {
        out.println("           <th> Precio </th>");
        out.println("           <th> Agregar </th>");
      }
      out.println("         </tr>");
      products.forEach(p -> {
        out.println("         <tr>");
        out.println("           <td>" + p.getId() + "</td>");
        out.println("           <td>" + p.getName() + "</td>");
        out.println("           <td>" + p.getType() + "</td>");
        if( usernameOptional.isPresent() ) {
          out.println("           <td>" + p.getPrice() + "</td>");
          out.println("           <td><a href=\"" + req.getContextPath() + "/carro/agregar?id=" + p.getId() + "\"> Agregar al carro </a></td>");
        }
        out.println("         </tr>");
      });
      out.println("       </table>");
      out.println("       <p><a href='" + req.getContextPath() + "/index.html'> Volver </a></p>");
      out.println("       <p>Uso de Listeners</p>");
      out.println("       <p>requestInitialized: " + msgRequestListener + "</p>");
      out.println("       <p>contextInitialized: " + msgApp + "</p>");
      out.println("   </body>");
      out.println("</html>");
    }
  }
}
