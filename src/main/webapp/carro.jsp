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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>Carro de compras</h1>
    <% if( car == null || car.getItems().isEmpty() ) { %>
        <div class="alert alert-warning">No hay productos en el carrito de compras</div>
        <p>¡ Debes iniciar sesión y agregar productos !</p>
    <% } else { %>
        <form action="<%=request.getContextPath()%>/actualizar-carro" method="post">
            <table class="table table-hover table-striped">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Total</th>
                    <th>Eliminar</th>
                </tr>
                <% for( ItemCarro item: car.getItems() ) { %>
                <tr>
                    <td><%=item.getProduct().getId() %></td>
                    <td><%=item.getProduct().getName() %></td>
                    <td><input type="text" size="4" value="<%=item.getAmount()%>" name="cant_<%=item.getProduct().getId()%>" /></td>
                    <td><%=item.getProduct().getPrice() %></td>
                    <td><%=item.getTotalPrice() %></td>
                    <td><input type="checkbox" value="<%=item.getProduct().getId()%>" name="deleteProductos" /></td>
                </tr>
                <% } %>
                <tr>
                    <td colspan="5">Total</td>
                    <td><%=car.getTotal() %></td>
                </tr>
            </table>
            <!--  <a href="javascript:document.formcarro.submit();">Actualizar</a> -->
            <input class="btn btn-sm btn-success" type="submit" value="Actualizar" />
        </form>
    <% } %>
    <div class="my-2">
    <p><a class="btn btn-sm btn-primary" href="<%=request.getContextPath() %>/productosSession.html">Seguir comprando</a></p>
    <p><a class="btn btn-sm btn-primary" href="<%=request.getContextPath() %>/index.html">Regresar a pagina principal</a></p>
    </div>
</div>
</body>
</html>