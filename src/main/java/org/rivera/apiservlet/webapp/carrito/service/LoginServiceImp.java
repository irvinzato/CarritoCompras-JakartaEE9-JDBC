package org.rivera.apiservlet.webapp.carrito.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@ApplicationScoped
public class LoginServiceImp implements LoginService {

  @Override
  public Optional<String> getUsername(HttpServletRequest req) {
    HttpSession session = req.getSession();
    String username = (String) session.getAttribute("username");
    if( username != null ) {
      return Optional.of(username);  //Para transformar un objeto a un optional del tipo manejado
    }
    return Optional.empty();
  }


  /*@Override ASI ERA CON COOKIES
  public Optional<Cookie> getUsername(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
    Optional<Cookie> cookieOptional = Arrays.stream(cookies)
            .filter( c -> "username".equals(c.getName()))  //Puedo hacer un "map" para transformar la "cookie" a "string"
            .findAny();
    return cookieOptional;
  }*/
}
