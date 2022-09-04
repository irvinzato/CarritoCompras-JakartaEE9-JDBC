package org.rivera.apiservlet.webapp.carrito.controllers.users;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.rivera.apiservlet.webapp.carrito.models.Usuario;
import org.rivera.apiservlet.webapp.carrito.service.UsuarioService;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios-tabla")
public class UsuariosServlet extends HttpServlet {

  @Inject
  UsuarioService userService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Usuario> listUsers = userService.toListUsers();

    req.setAttribute("list_Users", listUsers);
    getServletContext().getRequestDispatcher("/usuariosTable.jsp").forward(req, resp);
  }
}
