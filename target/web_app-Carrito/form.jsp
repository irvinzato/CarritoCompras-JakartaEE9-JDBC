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
<jsp:include page="layout/header.jsp" />
<div class="container">
    <h1>Formulario de productos</h1>
    <form action="<%=request.getContextPath()%>/productos/form" method="post">
        <div class="row mb-2">
            <label for="name" class="col-form-label col-sm-2">Nombre del producto</label>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="name" id="name" value="<%=product.getName() != null ? product.getName() : ""%>">
            </div>
            <% if( errors != null && errors.containsKey("name") ) { %>
                <span style='color: red;'> <%=errors.get("name") %> </span>
            <% } %>
        </div>
        <div class="row mb-2">
            <label for="price" class="col-form-label col-sm-2">Precio</label>
            <div class="col-sm-4">
                <input class="form-control" type="number" name="price" id="price" value="<%=product.getPrice() != 0 ? product.getPrice() : "" %>">
            </div>
            <% if( errors != null && errors.containsKey("price") ) { %>
                <span style='color: red;'> <%=errors.get("price") %> </span>
            <% } %>
        </div>
        <div class="row mb-2">
            <label for="register_date" class="col-form-label col-sm-2">Fecha de Registro</label>
            <div class="col-sm-4">
                <input class="form-control" type="date" name="register_date" id="register_date" value="<%=dateCons%>">
            </div>
            <% if( errors != null && errors.containsKey("dateRegister") ) { %>
                <span style='color: red;'> <%=errors.get("dateRegister") %> </span>
            <% } %>
        </div>
        <div class="row mb-2">
            <label for="sku" class="col-form-label col-sm-2">Sku</label>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="sku" id="sku" value="<%=product.getSku() != null ? product.getSku() : "" %>">
            </div>
            <% if( errors != null && errors.containsKey("sku") ) { %>
                <span style='color: red;'> <%=errors.get("sku") %> </span>
            <% } %>
        </div>
        <div class="row mb-2">
            <label for="category" class="col-form-label col-sm-2">Categoría</label>
            <div class="col-sm-4">
                <select name="category" id="category" class="form-select">
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
        <div class="row mb-2">
            <div>
                <input class="btn btn-primary" type="submit" value="<%=(product.getId() != null && product.getId() > 0)  ? "Editar" : "Crear" %>">
            </div>
        </div>
        <input type="hidden" name="id"  value="<%=product.getId()%>">
    </form>

    <p><a href="<%=request.getContextPath()%>/index.jsp">Volver a pagina principal</a></p>
</div>
<jsp:include page="layout/footer.jsp" />