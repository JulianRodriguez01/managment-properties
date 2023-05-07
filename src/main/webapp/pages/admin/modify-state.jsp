<%@page import="edu.uptc.entity.StateAccount"%>
<%@include file="header.jsp"%>
<%@ page
	import="java.util.ArrayList, edu.uptc.entity.Person,edu.uptc.model.Manager"%>
<div id="layoutSidenav_content">
	<main>
		<form action="../../SetStatePersonServlet" method="post">
			<div class="container-fluid px-4">
				<h1 class="mt-4">Estado de la cuenta</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="modify-state.jsp">Estado de la cuenta</a></li>
					<li class="breadcrumb-item active">Inicio</li>
				</ol>
				<div class="card mb-4">
					<div class="card-body">A continuación ingrese el nuevo estado del usuario seleccionado.</div>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-chart-area me-1"></i> Estado de la cuenta
					</div>
					<div class="card-body">
						<canvas id="myAreaChart" width="100%" height="30"></canvas>
					</div>
					<label for="inputAddress">Datos del usuario</label>
					<select name="select_id" class="form-select form-select-sm"
						aria-label=".form-select-sm example" required>
						<%
						try {
							Manager manager = new Manager();
							ArrayList<Person> lista = manager.getPersonList();
							int pos = 0;
							if (lista != null) {
						%>
						<option selected>Selecciones los datos del usuario que
							desee verificar.</option>
						<%
						for (Person e : lista) {
						%>
						<option value=<%=e.getNumberID()%>>Identificación:
							<%=e.getNumberID()%> - Nombre:
							<%=e.getName()%>
							<%=e.getLastName()%></option>
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
					
					<label for="inputState">Datos del estado</label>
					
					<select name="select_state"
						class="form-select form-select-sm"
						aria-label=".form-select-sm example">
						
						<option selected>Selecciones el nuevo estado del usuario.</option>
						<%
						for (StateAccount state : StateAccount.values()) { 
						%>
						<option value=<%=state.getAbreviature()%>><%=state.getText()%></option>
						<%
						}
						%>
					</select> <br> <input class="btn btn-primary" type="submit"
						value="Modificar estado" class="btn btn-primary" required />
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
