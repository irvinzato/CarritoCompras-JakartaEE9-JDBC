package org.rivera.apiservlet.webapp.carrito.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@InterceptorLogin
@Interceptor
public class LoginInterceptor {

  //Para usarlo se debe incluir el paquete y clase en el "beans.xml"
  //Para probar de otra manera puedo implementar el log

  @AroundInvoke
  public Object login(InvocationContext invocation) throws Exception {
    System.out.println("**** Entre antes de invocar al método " +
            invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
    Object result = invocation.proceed();
    System.out.println("**** Saliendo de la invocación del método " +
            invocation.getMethod().getName());

    return result;
  }
}
