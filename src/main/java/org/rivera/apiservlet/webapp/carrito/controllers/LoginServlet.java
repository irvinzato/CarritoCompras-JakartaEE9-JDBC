package org.rivera.apiservlet.webapp.carrito.controllers;

import org.rivera.apiservlet.webapp.carrito.service.LoginService;
import org.rivera.apiservlet.webapp.carrito.service.LoginServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login-servlet-session", "/loginSession.html"})
public class LoginServlet extends HttpServlet {
  final static String USERNAME = "admin";
  final static String PASSWORD = "12345";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    LoginService serviceAuth = new LoginServiceImp();
    Optional<String> usernameOptional = serviceAuth.getUsername(req);

    if( usernameOptional.isPresent() ) {
      resp.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = resp.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("   <head>");
        out.println("     <meta charset='UTF-8'>");
        out.println("     <title> Bienvenido " + usernameOptional.get() + "</title>");
        out.println("   </head>");
        out.println("   <body>");
        out.println("     <h1> Bienvenido " + usernameOptional.get() + " ya has iniciado sesión anteriormente </h1>");
        out.println("     <p><a href='" + req.getContextPath() + "/index.html'> Volver </a></p>");
        out.println("     <p><a href='" + req.getContextPath() + "/logoutSession'> Cerrar sesión </a></p>");
        out.println("   </body>");
        out.println("</html>");
      }
    } else {
      getServletContext().getRequestDispatcher("/loginSession.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    if( USERNAME.equals(username) && PASSWORD.equals(password) ) {
      HttpSession session = req.getSession();
      session.setAttribute("username", username);

      resp.sendRedirect(req.getContextPath() + "/loginSession.html");
    } else {
      //Status 401 con mensaje personalizado
      resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No tienes autorización para ingresar a esta página");
    }

  }
}
