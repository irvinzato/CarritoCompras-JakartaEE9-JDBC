package org.rivera.apiservlet.webapp.carrito.service;

import org.rivera.apiservlet.webapp.carrito.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImp implements ProductoService {

  @Override
  public List<Producto> toListProduct() {
    return Arrays.asList(new Producto(1L, "NoteBook", "Computación", 17500),
            new Producto(2L, "Yeyian CPU", "Computación", 15000),
            new Producto(3L, "Mesa Escritorio", "Oficina", 10000));
  }

  @Override
  public Optional<Producto> byIdProduct(Long id) {
    return toListProduct().stream().filter( p -> p.getId().equals(id) ).findAny();
  }
}
