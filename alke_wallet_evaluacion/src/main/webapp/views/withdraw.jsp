<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Retiro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; 
            color: #333; 
            margin-top: 50px; 
        }
        .withdraw-container {
            background-color: #ffffff; 
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); 
        }
        .form-group {
            margin-bottom: 20px; 
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="text-center">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4riIGDL4H2FYaJIyqPSe_is4bTo0QeVNp4Q&s" alt="Alke Wallet Logo" style="border-radius: 10px; margin-bottom: 20px;">
            <h1>Alke Wallet</h1>
            <h2>Retiro</h2>
        </div>
        <div class="withdraw-container">
            <form action="${pageContext.request.contextPath}/withdraw" method="post">
                <div class="form-group">
                    <label for="amount">Monto a retirar:</label>
                    <input type="text" id="amount" name="amount" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Retirar</button>
            </form>
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary mt-3">Volver a Menú Principal</a>
        </div>
    </div>
</body>
</html>