package org.rivera.apiservlet.webapp.carrito.controllers;

import org.rivera.apiservlet.webapp.carrito.service.LoginService;
import org.rivera.apiservlet.webapp.carrito.service.LoginServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logoutSession")
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    LoginService auth = new LoginServiceImp();
    Optional<String> usernameSession = auth.getUsername(req);
    //Para cerrar la session
    if( usernameSession.isPresent() ) {
      HttpSession session = req.getSession();    //Obtengo la sesión
      session.invalidate();   //BORRA TODO LO QUE TENGA EN SESIÓN DE USUARIO
    }
    req.setAttribute("tittle", "Login");
    resp.sendRedirect(req.getContextPath() + "/loginSession.html");
  }
}
