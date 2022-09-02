<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="layout/header.jsp" />
<div class="px-5">
    <h3> Login utilizando Session </h3>

    <form action="/webapp_carrito/login-servlet-session" method="post">
        <div class="row mb-3">
            <label for="username"  class="col-form-label col-sm-2"> Usuario </label>
            <div class="col-sm-4"><input type="text" name="username" id="username" class="form-control"></div>
        </div>
        <div class="row mb-3">
            <label for="password" class="col-form-label col-sm-2"> Contraseña </label>
            <div class="col-sm-4"><input type="password" name="password" id="password" class="form-control"></div>
        </div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Login" class="btn btn-primary">
            </div>
        </div>
    </form>

    <p><a href="<%=request.getContextPath()%>/index.jsp">Volver a pagina principal</a></p>
</div>
<jsp:include page="layout/footer.jsp" />