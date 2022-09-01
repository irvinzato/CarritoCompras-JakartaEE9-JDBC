package org.rivera.apiservlet.webapp.carrito.service;

import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.repositories.ProductosRepositoryJdbcImp;
import org.rivera.apiservlet.webapp.carrito.service.exception.ServiceJdbcException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImp implements ProductoService{
  private ProductosRepositoryJdbcImp repositoryJdbcImp;

  public ProductoServiceJdbcImp(Connection connection) {
    this.repositoryJdbcImp = new ProductosRepositoryJdbcImp(connection);
  }

  @Override
  public List<Producto> toListProduct() {
    try {
      return this.repositoryJdbcImp.toList();
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }

  @Override
  public Optional<Producto> byIdProduct(Long id) {
    try {
      return Optional.ofNullable(this.repositoryJdbcImp.byID(id));
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }
}
