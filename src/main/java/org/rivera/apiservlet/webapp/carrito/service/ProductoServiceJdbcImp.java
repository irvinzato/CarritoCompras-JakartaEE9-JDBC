package org.rivera.apiservlet.webapp.carrito.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.rivera.apiservlet.webapp.carrito.models.Categoria;
import org.rivera.apiservlet.webapp.carrito.models.Producto;
import org.rivera.apiservlet.webapp.carrito.repositories.CategoriaRepositoryJdbcImp;
import org.rivera.apiservlet.webapp.carrito.repositories.ProductosRepositoryJdbcImp;
import org.rivera.apiservlet.webapp.carrito.repositories.Repository;
import org.rivera.apiservlet.webapp.carrito.service.exception.ServiceJdbcException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("productoServiceJdbc")
public class ProductoServiceJdbcImp implements ProductoService{

  @Inject
  private Repository<Producto> productRepositoryJdbcImp;
  @Inject
  private Repository<Categoria> categoriaRepositoryJdbcImp;

  @Override
  public List<Producto> toListProduct() {
    try {
      return this.productRepositoryJdbcImp.toList();
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }

  @Override
  public Optional<Producto> byIdProduct(Long id) {
    try {
      return Optional.ofNullable(this.productRepositoryJdbcImp.byID(id));
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }

  @Override
  public void saveProduct(Producto product) {
    try {
      this.productRepositoryJdbcImp.save(product);
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }

  @Override
  public void deleteProduct(Long id) {
    try {
      this.productRepositoryJdbcImp.deleteByID(id);
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }

  @Override
  public List<Categoria> toListCategories() {
    try {
      return this.categoriaRepositoryJdbcImp.toList();
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }

  @Override
  public Optional<Categoria> byIdCategory(Long id) {
    try {
      return Optional.ofNullable(this.categoriaRepositoryJdbcImp.byID(id));
    } catch (SQLException e) {
      throw new ServiceJdbcException( e.getMessage(), e.getCause() );
    }
  }
}
