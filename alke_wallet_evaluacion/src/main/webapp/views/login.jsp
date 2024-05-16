<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Alke Wallet</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKZcN_kYk_PKrOJ5lEeVX_tZYHC6qsRhkBhw&s');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: Arial, sans-serif;
            color: #fff;
            margin: 0;
            padding: 0;
        }
        .login-container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 10px;
            margin-top: 100px;
        }
        .login-form {
            max-width: 400px;
            margin: auto;
        }
        .login-form input[type="text"],
        .login-form input[type="password"] {
            background-color: rgba(255, 255, 255, 0.8);
            border: none;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 15px;
        }
        .login-form button {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="text-center">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4riIGDL4H2FYaJIyqPSe_is4bTo0QeVNp4Q&s" alt="Alke Wallet Logo" style="border-radius: 10px; margin-bottom: 20px;">
            <h1>Alke Wallet</h1>
        </div>
        <div class="login-container">
            <h2 class="text-center mb-4">Inicia  Sesión</h2>
            <form class="login-form" action="/alke_wallet_evaluacion/login" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Usuario:</label>
                    <input type="text" id="username" name="username" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-3" role="alert">
                        <p>${error}</p>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</body>
</html>