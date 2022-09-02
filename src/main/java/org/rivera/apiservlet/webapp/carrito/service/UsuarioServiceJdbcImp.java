package org.rivera.apiservlet.webapp.carrito.service;

import org.rivera.apiservlet.webapp.carrito.models.Usuario;
import org.rivera.apiservlet.webapp.carrito.repositories.UsuarioRepository;
import org.rivera.apiservlet.webapp.carrito.repositories.UsuarioRepositoryImp;
import org.rivera.apiservlet.webapp.carrito.service.exception.ServiceJdbcException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceJdbcImp implements UsuarioService{
  private UsuarioRepository userRepository;

  public UsuarioServiceJdbcImp(Connection conn) {
    this.userRepository = new UsuarioRepositoryImp(conn);
  }

  @Override
  public Optional<Usuario> loginByUsername(String username, String password) {
    try {
      return Optional.ofNullable(userRepository.byUsername(username))
              .filter( u -> u.getPassword().equals(password) ); //Si es igual regresa el Optional de Usuario si no, regresa null
    } catch (SQLException e) {
      throw new ServiceJdbcException(e.getMessage(), e.getCause());
    }
  }
}
