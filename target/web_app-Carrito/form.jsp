<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.*, java.time.format.*, org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
List<Categoria> categories = (List<Categoria>) request.getAttribute("categorias");
Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
Producto product = (Producto) request.getAttribute("producto");
String dateCons = product.getRegisterDate() != null
                        ? product.getRegisterDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        : "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario producto</title>
</head>
<body>
<h1>Formulario de productos</h1>
<form action="<%=request.getContextPath()%>/productos/form" method="post">
    <div>
        <label for="name">Nombre del producto</label>
        <div>
            <input type="text" name="name" id="name" value="<%=product.getName() != null ? product.getName() : ""%>">
        </div>
        <% if( errors != null && errors.containsKey("name") ) { %>
            <span style='color: red;'> <%=errors.get("name") %> </span>
        <% } %>
    </div>
    <div>
        <label for="price">Precio</label>
        <div>
            <input type="number" name="price" id="price" value="<%=product.getPrice() != 0 ? product.getPrice() : "" %>">
        </div>
        <% if( errors != null && errors.containsKey("price") ) { %>
            <span style='color: red;'> <%=errors.get("price") %> </span>
        <% } %>
    </div>
    <div>
        <label for="register_date">Fecha de Registro</label>
        <div>
            <input type="date" name="register_date" id="register_date" value="<%=dateCons%>">
        </div>
        <% if( errors != null && errors.containsKey("dateRegister") ) { %>
            <span style='color: red;'> <%=errors.get("dateRegister") %> </span>
        <% } %>
    </div>
    <div>
        <label for="sku">Sku</label>
        <div>
            <input type="text" name="sku" id="sku" value="<%=product.getSku() != null ? product.getSku() : "" %>">
        </div>
        <% if( errors != null && errors.containsKey("sku") ) { %>
            <span style='color: red;'> <%=errors.get("sku") %> </span>
        <% } %>
    </div>
    <div>
        <label for="category">Categoría</label>
        <div>
            <select name="category" id="category">
                <option value="">--- Seleccionar ---</option>
                <% for( Categoria c: categories ) { %>
                    <option value="<%=c.getId()%>" <%=c.getId().equals(product.getCategory().getId()) ? "selected" : ""%> ><%=c.getName()%></option>
                <% } %>
            </select>
        </div>
        <% if( errors != null && errors.containsKey("category") ) { %>
            <span style='color: red;'> <%=errors.get("category") %> </span>
        <% } %>
    </div>
    <div>
        <input type="submit" value="<%=(product.getId() != null && product.getId() > 0)  ? "Editar" : "Crear" %>">
    </div>
    <input type="hidden" name="id"  value="<%=product.getId()%>">
</form>

<p><a href="<%=request.getContextPath()%>/index.html">Volver a pagina principal</a></p>

</body>
</html>