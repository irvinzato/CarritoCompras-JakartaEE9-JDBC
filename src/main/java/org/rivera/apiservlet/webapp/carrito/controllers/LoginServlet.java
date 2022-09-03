package org.rivera.apiservlet.webapp.carrito.controllers;

import jakarta.inject.Inject;
import org.rivera.apiservlet.webapp.carrito.models.Usuario;
import org.rivera.apiservlet.webapp.carrito.service.LoginService;
import org.rivera.apiservlet.webapp.carrito.service.LoginServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.rivera.apiservlet.webapp.carrito.service.UsuarioService;
import org.rivera.apiservlet.webapp.carrito.service.UsuarioServiceJdbcImp;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/login-servlet-session", "/loginSession.html"})
public class LoginServlet extends HttpServlet {

  @Inject
  private UsuarioService service;

  @Inject
  private LoginService serviceAuth;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Optional<String> usernameOptional = serviceAuth.getUsername(req);

    if( usernameOptional.isPresent() ) {
      req.setAttribute("username", usernameOptional);
      getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    } else {
      req.setAttribute("tittle", "Login");
      getServletContext().getRequestDispatcher("/loginSession.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    Optional<Usuario> user = service.loginByUsername( username, password );

    if( user.isPresent() ) {
      HttpSession session = req.getSession();
      session.setAttribute("username", username);

      resp.sendRedirect(req.getContextPath() + "/loginSession.html");
    } else {
      //Status 401 con mensaje personalizado
      resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No tienes autorización para ingresar a esta página");
    }

  }
}
