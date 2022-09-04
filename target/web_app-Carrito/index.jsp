<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />
<div class="container px-5">
    <h3> CARRITO DE COMPRAS CON CONEXIÓN A BASE DE DATOS - MVC - LISTENERS - FILTROS- SESSION </h3>

    <ul class="list-group">
        <li class="list-group-item active">Menu de opciones</li>
        <li class="list-group-item"><a href="/webapp_carrito/productosSession.html"> Productos Session </a></li>
        <li class="list-group-item"><a href="/webapp_carrito/loginSession.html"> Login con Session </a></li>
        <li class="list-group-item"><a href="/webapp_carrito/logoutSession"> Cerrar Sesión </a></li>
        <li class="list-group-item"><a href="/webapp_carrito/carro/ver"> Ver carro de compras </a></li>
        <li class="list-group-item"><a href="/webapp_carrito/usuarios-tabla"> Usuarios registrado en nuestra aplicación </a></li>
    </ul>
</div>

<jsp:include page="layout/footer.jsp" />