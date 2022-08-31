package org.rivera.apiservlet.webapp.carrito.service.exception;

//Excepción para cuando caiga en el Servicio pase al filter y haga rollback
//Puente de comunicación entre Servicio y Filter
public class ServiceJdbcException extends RuntimeException{

  public ServiceJdbcException(String message) {
    super(message);
  }

  public ServiceJdbcException(String message, Throwable cause) {
    super(message, cause);
  }
}
