package org.rivera.apiservlet.webapp.carrito.repositories;

import org.rivera.apiservlet.webapp.carrito.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {

  Usuario byUsername(String username) throws SQLException;;
}
