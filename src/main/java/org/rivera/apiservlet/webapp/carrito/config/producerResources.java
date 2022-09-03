package org.rivera.apiservlet.webapp.carrito.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class producerResources {

  @Resource(name="jdbc/myConMysql")   //Esa anotación quita las líneas comentadas
  private DataSource ds;

  @Produces           //Produce un objeto en el contexto
  @RequestScoped      //Cada que hago una request se genera
  @Named("conncdi")
  private Connection beansConnection() throws NamingException, SQLException {
    /*Context initContext =new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:/comp/env");
    DataSource ds = (DataSource)envContext.lookup("jdbc/myConMysql");*/
    return ds.getConnection();
  }

  //Se puede cerrar la conexión de forma diferente V-476
}
