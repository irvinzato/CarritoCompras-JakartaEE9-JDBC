package org.rivera.apiservlet.webapp.carrito.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
  List<T> toList() throws SQLException;
  T byID(Long id) throws SQLException;
  void save(T t) throws SQLException;
  void deleteByID(Long id) throws SQLException;
}
