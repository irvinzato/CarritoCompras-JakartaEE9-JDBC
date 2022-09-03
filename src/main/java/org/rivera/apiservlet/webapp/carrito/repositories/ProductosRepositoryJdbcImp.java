package org.rivera.apiservlet.webapp.carrito.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.rivera.apiservlet.webapp.carrito.models.Categoria;
import org.rivera.apiservlet.webapp.carrito.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductosRepositoryJdbcImp implements Repository<Producto>{

  @Inject
  @Named("conncdi")
  private Connection conn;

  private static Producto getProducto( ResultSet rs ) throws SQLException {
    Producto product = new Producto();
    product.setId( rs.getLong("id") );
    product.setName( rs.getString("nombre") );
    product.setPrice( rs.getInt("precio") );
    product.setRegisterDate( rs.getDate("fecha_registro").toLocalDate() );   //Importante el cast
    product.setSku( rs.getString("sku") );
    Categoria c = new Categoria();
    c.setId( rs.getLong("categoria_id") );
    c.setName( rs.getString("categoria") );
    product.setCategory(c);
    return product;
  }

  @Override
  public List<Producto> toList() throws SQLException {
    List<Producto> products = new ArrayList<>();
    try( Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p " +
                 " INNER JOIN categorias AS c ON (p.categoria_id = c.id) ORDER BY p.id ASC") ) {
      while( rs.next() ) {
        Producto product = getProducto(rs);

        products.add(product);
      }
    }
    return products;
  }


  @Override
  public Producto byID(Long id) throws SQLException {
    Producto producto = null;
    try( PreparedStatement pstmt = conn.prepareStatement("SELECT p.*, c.nombre AS categoria FROM productos AS p " +
            " INNER JOIN categorias AS c ON(p.categoria_id = c.id) WHERE p.id = ?")) {
      pstmt.setLong(1, id);

      try( ResultSet rs = pstmt.executeQuery() ) {
        if( rs.next() ) {
          producto = getProducto(rs);
        }
      }
    }
    return producto;
  }

  @Override
  public void save(Producto producto) throws SQLException {
    String query;
    if( producto.getId() != null && producto.getId() > 0 ) {
      query = "UPDATE productos SET nombre=?, precio=?, categoria_id=?, sku=? WHERE id=?";
    } else {
      query = "INSERT INTO productos(nombre, precio, categoria_id, sku, fecha_registro) VALUES(?, ?, ?, ?, ?)";
    }
    try( PreparedStatement pstmt = conn.prepareStatement(query) ) {
      pstmt.setString(1, producto.getName());
      pstmt.setInt(2, producto.getPrice() );
      pstmt.setLong(3, producto.getCategory().getId()); //El la tabla Sql es Long "id"
      pstmt.setString(4, producto.getSku());
      if( producto.getId() != null && producto.getId() > 0 ) {
        pstmt.setLong(5, producto.getId());
      } else {
        pstmt.setDate(5, Date.valueOf(producto.getRegisterDate()));   //Importante el cast
      }
      pstmt.executeUpdate();
    }
  }

  @Override
  public void deleteByID(Long id) throws SQLException {
    try( PreparedStatement pstmt = conn.prepareStatement("DELETE FROM productos WHERE id=?") ) {
      pstmt.setLong(1, id);
      pstmt.executeUpdate();
    }
  }
}
