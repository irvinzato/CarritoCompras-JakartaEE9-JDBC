<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login con Session</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</head>
<body>
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

    <p><a href="<%=request.getContextPath()%>/index.html">Volver a pagina principal</a></p>
</div>
</body>
</html>