package org.rivera.apiservlet.webapp.carrito.repositories;

import org.rivera.apiservlet.webapp.carrito.models.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductosRepositoryJdbcImp implements Repository<Producto>{

  private Connection conn;

  public ProductosRepositoryJdbcImp(Connection conn) {
    this.conn = conn;
  }

  private static Producto getProducto( ResultSet rs ) throws SQLException {
    Producto product = new Producto();
    product.setId( rs.getLong("id") );
    product.setName( rs.getString("nombre") );
    product.setPrice( rs.getInt("precio") );
    product.setType( rs.getString("categoria") );
    return product;
  }

  @Override
  public List<Producto> toList() throws SQLException {
    List<Producto> products = new ArrayList<>();
    try( Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p " +
                 " INNER JOIN categorias AS c ON (p.categoria_id = c_id)") ) {
      while( rs.next() ) {
        Producto product = getProducto(rs);

        products.add(product);
      }
    }
    return products;
  }


  @Override
  public Producto byID(Long id) throws SQLException {
    return null;
  }

  @Override
  public void save(Producto producto) throws SQLException {

  }

  @Override
  public void deleteByID(Long id) throws SQLException {

  }
}
