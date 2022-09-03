package org.rivera.apiservlet.webapp.carrito.config;


import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Puedo hacer lo mismo para el servicio Login y/o Usuario pero alli solo tengo un servicio concreto
@Qualifier  //calificador
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR})   //Donde se podrá aplicar(Parecido al Named)
public @interface ProductoServicePrincipal {


}
