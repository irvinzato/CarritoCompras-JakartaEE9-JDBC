<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
Carro car = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
<h1>Carro de compras</h1>
<% if( car == null || car.getItems().isEmpty() ) { %>
    <p>No hay productos en el carrito de compras</p>
    <p>¡ Debes iniciar sesión y agregar productos !</p>
<% } else { %>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Total</th>
        </tr>
        <% for( ItemCarro item: car.getItems() ) { %>
        <tr>
            <td><%=item.getProduct().getId() %></td>
            <td><%=item.getProduct().getName() %></td>
            <td><%=item.getAmount() %></td>
            <td><%=item.getProduct().getPrice() %></td>
            <td><%=item.getTotalPrice() %></td>
        </tr>
        <% } %>
        <tr>
            <td colspan="4">Total</td>
            <td><%=car.getTotal() %></td>
        </tr>
    </table>
<% } %>
<p><a href="<%=request.getContextPath() %>/productosSession.html">Seguir comprando</a></p>
<p><a href="<%=request.getContextPath() %>/index.html">Regresar a pagina principal</a></p>

</body>
</html>