<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
    List<Producto> products = (List<Producto>) request.getAttribute("productos");
    Optional<Producto> usernameOptional = (Optional<Producto>) request.getAttribute("username");
    String msgRequestListener = (String) request.getAttribute("mensajeRequest");
    String msgApp = (String) getServletContext().getAttribute("mensaje");
%>
<jsp:include page="layout/header.jsp" />
<div class="container">
    <h1>Listado de productos migrado a JSP</h1>
    <% if( usernameOptional.isPresent() ) { %>
        <h2 class="alert alert-info"> Bienvenido  <%=usernameOptional.get() %> </h2>
        <a class="btn btn-success my-2" href="<%=request.getContextPath()%>/productos/form"> Añadir productos </a>
    <% } %>
    <table class="table table-hover table-striped">
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
                <td><a class="btn btn-sm btn-success" href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">Agregar al carro</a></td>
                <td><a class="btn btn-sm btn-primary" href="<%=request.getContextPath()%>/productos/form?id=<%=p.getId()%>">Editar producto</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Estas seguro de eliminar?');"
                href="<%=request.getContextPath()%>/productos/eliminar?id=<%=p.getId()%>">Eliminar producto</a></td>
            <% } %>
        </tr>
        <% } %>
    </table>

    <p><a href="<%=request.getContextPath()%>/index.jsp">Volver a pagina principal</a></p>
    <p><%=msgRequestListener%></p>
    <p><%=msgApp%></p>
</div>
<jsp:include page="layout/footer.jsp" />