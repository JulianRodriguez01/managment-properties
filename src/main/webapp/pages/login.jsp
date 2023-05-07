<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<title>login</title>

<link href="vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
	rel="stylesheet">
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="vendor/datepicker/daterangepicker.css" rel="stylesheet"
	media="all">

<link href="../css/loginTheme.css" rel="stylesheet" media="all">
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="page-wrapper bg-blue p-t-100 p-b-100 font-robo">
		<div class="wrapper wrapper--w680">
			<form action="../LoginServlet" method="post">
				<div class="card card-1">
					<div class="card-heading"></div>
					<div class="card-body">
						<h2 class="title">INGRESAR</h2>
						<div class="input-group">
							<input class="input--style-1" type="text" placeholder="Nombre"
								name="per_name">
						</div>
						<div class="input-group">
							<input class="input--style-1" type="password"
								placeholder="Contraseña" name="per_password">
						</div>
						<div class="p-t-20">
							<button class="btn btn--radius btn--green" type="submit">Ingresar</button>
						</div>
					</div>
				</div>
			</form>
			<form action="../index.jsp">
				<input type="submit" value="Regresar" class="btn btn--radius btn--green" />
			</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
</body>
</html>

