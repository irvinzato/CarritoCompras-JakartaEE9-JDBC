package org.rivera.apiservlet.webapp.carrito.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.rivera.apiservlet.webapp.carrito.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CategoriaRepositoryJdbcImp implements Repository<Categoria>{
  private Connection conn;

  @Inject
  public CategoriaRepositoryJdbcImp( @Named("conncdi") Connection conn) {
    this.conn = conn;
  }

  private static Categoria getCategory(ResultSet rs) throws SQLException {
    Categoria c = new Categoria();
    c.setId( rs.getLong("id") );
    c.setName( rs.getString("nombre") );
    return c;
  }

  @Override
  public List<Categoria> toList() throws SQLException {
    List<Categoria> categories = new ArrayList<>();
    try( Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM categorias") ) {
      while( rs.next() ) {
        Categoria c = getCategory(rs);

        categories.add(c);
      }
    }
    return categories;
  }


  @Override
  public Categoria byID(Long id) throws SQLException {
    Categoria c = null;

    try( PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM categorias WHERE categorias.id = ?") ) {
      pstmt.setLong(1, id);

      try( ResultSet rs = pstmt.executeQuery() ) {
        if( rs.next() ) {
          c = getCategory(rs);
        }
      }
    }
    return c;
  }

  @Override
  public void save(Categoria categoria) throws SQLException {

  }

  @Override
  public void deleteByID(Long id) throws SQLException {

  }
}
