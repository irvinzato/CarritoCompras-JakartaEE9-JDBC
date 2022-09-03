package org.rivera.apiservlet.webapp.carrito.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducerResourses {

  @Produces //Produce un objeto en el contexto
  @RequestScoped
  @Named("conncdi")
  private Connection beansConnection() throws NamingException, SQLException {
    Context initContext =new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:/comp/env");
    DataSource ds = (DataSource)envContext.lookup("jdbc/myConMysql");
    Connection conn = ds.getConnection();
    return conn;
  }
}
