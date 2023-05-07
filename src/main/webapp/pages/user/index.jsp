<%@include file="header.jsp"%>
<%@ page
	import="java.util.ArrayList, edu.uptc.entity.Person,edu.uptc.model.Manager"%>

<form action="../../AdminPerson" method="post">
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-12">
				<h1 class="mt-4">Bienvenido ${person.name}</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="pages/admin/index.jsp">Propiedades horizontales</a></li>
					<li class="breadcrumb-item active">Inicio</li>
				</ol>
				<div class="card mb-4">
					<div class="card-body">Bienvenido a la administración de propiedades horizontales! Es un gusto tenerte.</div>
				</div>
				<img src="../../images/mujer.jpg" class="img-fluid"
					alt="Responsive image" width="100%" height="100%">
			</div>
		</main>
		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
				</div>
			</div>
		</footer>
	</div>
</form>



</div>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="../../assets/demo/chart-area-demo.js"></script>
<script src="../../assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
