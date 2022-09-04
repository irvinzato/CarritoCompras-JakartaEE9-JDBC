<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.*, org.rivera.apiservlet.webapp.carrito.models.*"%>
<%
    List<Usuario> listUsers = (List<Usuario>) request.getAttribute("list_Users");
%>
<jsp:include page="layout/header.jsp" />
<div class="container">
    <h1> Tabla de usuario registrados en nuestra página </h1>
    <p> <%=listUsers %> </p>
    <table class="table table-hover table-striped">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
        </tr>
        <% for( Usuario u: listUsers ) { %>
            <tr>
                <td> <%=u.getId() %> </td>
                <td> <%=u.getUsername() %> </td>
                <td> <%=u.getEmail() %> </td>
            </tr>
        <% } %>
    </table>
    <a href="<%=request.getContextPath()%>"> Menú principal </a>
</div>


<jsp:include page="layout/footer.jsp" />