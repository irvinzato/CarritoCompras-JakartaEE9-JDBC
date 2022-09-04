<%@page import="java.util.*, org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
String tittle = (String) request.getAttribute("tittle");
Optional<String> usernameOptional = (Optional<String>) request.getAttribute("username");
Carro car = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><%=tittle%></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/webapp_carrito">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/webapp_carrito">Inicio de aplicacion</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/webapp_carrito/productosSession.html">Productos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/webapp_carrito/carro/ver">Carrito <%=car == null ? 0 : car.getItems().size() %> </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <%=(usernameOptional == null || usernameOptional.isPresent() == false ) ? "Cuenta" : usernameOptional.get() %>
          </a>
          <ul class="dropdown-menu">
          <% if( usernameOptional == null ) { %>
            <li><a class="dropdown-item" href="/webapp_carrito/loginSession.html">Iniciar Sesion</a></li>
          <% } else { %>
            <li><a class="dropdown-item" href="/webapp_carrito/logoutSession">Cerrar sesion</a></li>
          <% } %>
          </ul>
        </li>
      </ul>

    </div>
  </div>
</nav>