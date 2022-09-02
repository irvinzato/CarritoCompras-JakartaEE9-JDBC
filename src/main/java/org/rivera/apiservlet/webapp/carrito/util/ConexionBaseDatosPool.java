package org.rivera.apiservlet.webapp.carrito.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatosPool {

//DOCUMENTACIÓN DE TOMCAT PARA HACER POOL(META-INF Y WEB-INF) forman parte
  public static Connection getConnection() throws SQLException, NamingException {
    Context initContext = null;
    DataSource ds = null;

    initContext = new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:/comp/env");
    ds = (DataSource)envContext.lookup("jdbc/myConMysql");
    Connection conn = ds.getConnection();
    return conn;
    }

}
