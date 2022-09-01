<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.*, org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
List<Categoria> categories = (List<Categoria>) request.getAttribute("categorias");
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
        <input type="text" name="name" id="name">
        </div>
    </div>
    <div>
        <label for="price">Precio</label>
        <div>
        <input type="number" name="price" id="price">
        </div>
    </div>
    <div>
        <label for="register_date">Fecha de Registro</label>
        <div>
        <input type="date" name="register_date", id="register_date">
        </div>
    </div>
    <div>
        <label for="sku">Sku</label>
        <div>
        <input type="text" name="sku" id="sku">
        </div>
    </div>
    <div>
        <label for="category">Categoría</label>
        <div>
            <select name="category" id="category">
                <option value="">--- Seleccionar ---</option>
                <% for( Categoria c: categories ) { %>
                <option value="<%=c.getId()%>"><%=c.getName()%></option>
                <% } %>
            </select>
        </div>
    </div>
    <div>
        <input type="submit" value="Guardar">
    </div>
</form>

<p><a href="<%=request.getContextPath()%>/index.html">Volver a pagina principal</a></p>

</body>
</html>