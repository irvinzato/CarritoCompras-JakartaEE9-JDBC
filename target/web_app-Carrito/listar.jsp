<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
    List<Producto> products = (List<Producto>) request.getAttribute("productos");
    Optional<Producto> usernameOptional = (Optional<Producto>) request.getAttribute("username");
    String msgRequestListener = (String) request.getAttribute("mensajeRequest");
    String msgApp = (String) getServletContext().getAttribute("mensaje");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
</head>
<body>
<h1>Listado de productos migrado a JSP</h1>
<% if( usernameOptional.isPresent() ) { %>
    <h2> Bienvenido  <%=usernameOptional.get() %> </h2>
    <p><a href="<%=request.getContextPath()%>/productos/form"> Añadir productos </a></p>
<% } %>
<table>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Categoría</th>
        <% if( usernameOptional.isPresent() ) { %>
            <th>Precio</th>
            <th>Agregar</th>
            <th>Editar</th>
            <th>Eliminar</th>
        <% } %>
    </tr>
    <% for(Producto p: products) { %>
    <tr>
        <td><%=p.getId() %></td>
        <td><%=p.getName() %></td>
        <td><%=p.getCategory().getName() %></td>
        <% if( usernameOptional.isPresent() ) { %>
            <td><%=p.getPrice() %></td>
            <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">Agregar al carro</a></td>
            <td><a href="<%=request.getContextPath()%>/productos/form?id=<%=p.getId()%>">Editar producto</a></td>
            <td><a onclick="return confirm('¿Estas seguro de eliminar?');"
            href="<%=request.getContextPath()%>/productos/eliminar?id=<%=p.getId()%>">Eliminar producto</a></td>
        <% } %>
    </tr>
    <% } %>
</table>

<p><a href="<%=request.getContextPath()%>/index.html">Volver a pagina principal</a></p>
<p><%=msgRequestListener%></p>
<p><%=msgApp%></p>

</body>
</html>