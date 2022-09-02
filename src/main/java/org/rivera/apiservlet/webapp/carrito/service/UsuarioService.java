package org.rivera.apiservlet.webapp.carrito.service;

import org.rivera.apiservlet.webapp.carrito.models.Usuario;

import java.util.Optional;

public interface UsuarioService {

  Optional<Usuario> loginByUsername( String username, String password );

}
