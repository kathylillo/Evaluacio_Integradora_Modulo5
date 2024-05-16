<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	color: #333;
	margin-top: 50px;
}

.container-error {
	background-color: #ffffff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<div class="container-error">
		<div class="text-center">
			<img
				src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4riIGDL4H2FYaJIyqPSe_is4bTo0QeVNp4Q&s"
				alt="Alke Wallet Logo"
				style="border-radius: 10px; margin-bottom: 20px;">
				<h1>Alke Wallet</h1>
		</div>
	</div>
	<div class="container mt-5">
		<div class="alert alert-danger" role="alert">
			<h4 class="alert-heading">¡Error!</h4>
			<p>Ocurrió un error al procesar tu transacción.</p>
			<hr>
			<p class="mb-0">Por favor, intenta nuevamente verificando el monto.</p>
		</div>
		<a href="${pageContext.request.contextPath}/dashboard"class="btn btn-primary">Volver al Menú Principal</a>
	</div>
</body>
</html>