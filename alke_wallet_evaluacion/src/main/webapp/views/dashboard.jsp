<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
     
        .my-container {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        .my-table {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container my-container">
        <div style="text-align: center;">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4riIGDL4H2FYaJIyqPSe_is4bTo0QeVNp4Q&s" alt="Imagen con bordes redondeados" style="border-radius: 10px; margin-right: 20px;">
            <h1>Alke Wallet</h1>
            <h2>Menú Principal</h2>
        </div>

        <h2>Bienvenido, ${user.username}!</h2>
        <p>Saldo actual: $${balance}</p>

        <h3>Transacciones</h3>
        <div class="table-responsive">
            <table class="table table-striped my-table">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Tipo</th>
                        <th>Monto</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${transactions}" var="transaction">
                        <tr>
                            <td>${transaction.dateTime}</td>
                            <td>${transaction.transactionType}</td>
                            <td>${transaction.amount}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div style="margin-top: 20px;">
            <a href="/alke_wallet_evaluacion/views/deposit.jsp" class="btn btn-primary">Depositar</a>
            <a href="/alke_wallet_evaluacion/views/withdraw.jsp" class="btn btn-primary">Retirar</a>
            <a href="/alke_wallet_evaluacion/views/login.jsp" class="btn btn-secondary">Cerrar Sesión</a>
        </div>
    </div>
</body>
</html>