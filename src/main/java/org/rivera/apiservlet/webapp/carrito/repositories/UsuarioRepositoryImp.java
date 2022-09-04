package org.rivera.apiservlet.webapp.carrito.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.rivera.apiservlet.webapp.carrito.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioRepositoryImp implements UsuarioRepository{

  @Inject
  @Named("conncdi")
  private Connection conn;

  @Override
  public List<Usuario> toList() throws SQLException {
    List<Usuario> listUsers = new ArrayList<>();
    try( Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")  ) {
      while( rs.next() ) {
        listUsers.add( getUsuario( rs ) );
      }
    }
    return listUsers;
  }

  @Override
  public Usuario byID(Long id) throws SQLException {
    return null;
  }

  @Override
  public void save(Usuario usuario) throws SQLException {

  }

  @Override
  public void deleteByID(Long id) throws SQLException {

  }

  @Override
  public Usuario byUsername(String username) throws SQLException {
    Usuario user = null;
    try( PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username = ?" ) ) {
      pstmt.setString(1, username );
      try( ResultSet rs = pstmt.executeQuery() ) {
        if( rs.next() ) {
          user = getUsuario(rs);
        }
      }
    }
    return user;
  }

  private static Usuario getUsuario(ResultSet rs) throws SQLException {
    Usuario user = new Usuario();
    user.setId( rs.getLong("id") );
    user.setUsername( rs.getString("username") );
    user.setPassword( rs.getString("password") );
    user.setEmail( rs.getString("email") );
    return user;
  }
}
