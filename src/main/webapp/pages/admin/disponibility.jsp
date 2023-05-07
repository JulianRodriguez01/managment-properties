<%@page import="edu.uptc.entity.StateAccount"%>
<%@include file="header.jsp"%>
<%@ page
	import="java.util.ArrayList, edu.uptc.entity.CommunalLiving, edu.uptc.model.Manager"%>
<div id="layoutSidenav_content">
	<main>
		<form action="../../DisponibilityServlet" method="post">
			<div class="container-fluid px-4">
				<h1 class="mt-4">Disponibilidad</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="disponibility.jsp">Dispoibilidad</a></li>
					<li class="breadcrumb-item active">Inicio</li>
				</ol>
				<div class="card mb-4">
					<div class="card-body">A continuación ingrese los datos a
						consultar.</div>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-chart-area me-1"></i> Disponibilidad
					</div>
					<div class="card-body">
						<canvas id="myAreaChart" width="100%" height="30"></canvas>
					</div>
					<label for="inputAddress">Datos del salon</label> <select
						name="select_id" class="form-select form-select-sm"
						aria-label=".form-select-sm example">
						<%
						try {
							Manager manager = new Manager();
							ArrayList<CommunalLiving> list = manager.getCommunalLivingList();
							int pos = 0;
							if (list != null) {
						%>
						<option selected>Selecciones los datos del salon que
							desea consultar.</option>
						<%
						for (CommunalLiving e : list) {
						%>
						<option value=<%=e.getIdCommunalLiving()%>>Identificación:
							<%=e.getIdCommunalLiving()%>. - Tipo:
							<%=e.getReservationLugars().getText()%>. - Descripción:
							<%=e.getDescription()%>.
						</option>
						<%
						}
						} else {
						out.print("<option selected>No hay datos disponibles, intentelo luego.</option>");
						}
						} catch (Exception e) {
						e.printStackTrace();
						}
						%>

					</select> <br> 
					<input class="btn btn-primary" type="submit"
						value="Consultar disponibilidad" class="btn btn-primary" />

					<div class="card-body">
						<canvas id="myAreaChart" width="100%" height="30"></canvas>
					</div>
					<div class="card-footer small text-muted">Actualizado ayer a
						11:59 PM</div>
				</div>
			</div>
		</form>
	</main>
	<footer class="py-4 bg-light mt-auto">
		<div class="container-fluid px-4">
			<div class="d-flex align-items-center justify-content-between small">
				<div>
					<a href="#">Privacy Policy</a> &middot; <a href="#">Terms &amp;
						Conditions</a>
				</div>
			</div>
		</div>
	</footer>
</div>
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
<script src="../../assets/demo/chart-pie-demo.js"></script>
</body>
</html>
