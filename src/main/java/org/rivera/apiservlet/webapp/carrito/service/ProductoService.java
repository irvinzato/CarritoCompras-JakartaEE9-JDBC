package org.rivera.apiservlet.webapp.carrito.service;

import org.rivera.apiservlet.webapp.carrito.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
  List<Producto> toListProduct();

  Optional<Producto> byIdProduct(Long id);


}
