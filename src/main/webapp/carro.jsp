<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
Carro car = (Carro) session.getAttribute("carro");
%>
<jsp:include page="layout/header.jsp" />
<div class="container">
    <h1>Carro de compras</h1>
    <p>Me ha llegado <%=session.getAttribute("carro") %></p>
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
    <p><a class="btn btn-sm btn-primary" href="<%=request.getContextPath() %>/index.jsp">Regresar a pagina principal</a></p>
    </div>
</div>
<jsp:include page="layout/footer.jsp" />