<%@include file="header.jsp"%>
<%@ page
	import="java.util.ArrayList, edu.uptc.entity.Person, edu.uptc.entity.CommunalLiving, edu.uptc.entity.Reservation, edu.uptc.model.Manager"%>
<div id="layoutSidenav_content">
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">Recibo de cobro</h1>
			<ol class="breadcrumb mb-4">
				<li class="breadcrumb-item"><a href="index.jsp">Inicio</a></li>
				<li class="breadcrumb-item active">Generar recibo cobro</li>
			</ol>
			<div class="container">
				<form action="index.jsp">
					<input type="submit" value="Regresar" class="btn btn-primary" />
				</form>
				<br>
				<form action="../../AddReciptPaymentServlet" method="post">
					<div class="row">
						<div class="col-lg-12">
							<div class="card mb-4">

								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Documentos reservación
								</div>

								<div class="form-row">
									<br> <label for="inputAddress">Usuario que le
										desea generar el recibo de pagos</label> <select name="select_id"
										class="form-select form-select-sm"
										aria-label=".form-select-sm example">
										<%
										try {
											Manager manager = new Manager();
											ArrayList<Person> lista = manager.getPersonList();
											int pos = 0;
											if (lista != null) {
										%>
										<option selected>Selecciones los datos del usuario
											que desee seleccionar.</option>
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
									<div class="form-group col-md-12">
										<label for="inputAddress">Descripción</label> <input
											type="text" class="form-control" id="inputAddress"
											placeholder="Ingrese la descripción del peridodo de pago"
											name="re_description" required>
									</div>
									<br>
									<div class="row">
										<div class="col-xl-6">

											<label for="inputEmail4">Valor</label> <input type="number"
												class="form-control" id="inputEmail4" min="0"
												max="100000000"
												placeholder="Ingrese el valor del recibo de pago"
												name="re_value" required>
										</div>
										<div class="col-xl-6">
											<label for="inputEmail4">Valor deuda</label> <input
												type="number" class="form-control" id="inputEmail4" min="0"
												max="100000000"
												placeholder="Ingrese el monto de la deuda (si la tiene)"
												name="re_oustanding">
										</div>
									</div>


									<br>
									<div class="form-group col-md-12">
										<label for="inputAddress">Fecha pago</label> <input
											type="date" class="form-control" id="inputAddress"
											name="re_date" required>
									</div>
									<br> <label for="inputAddress">Información
										adicional</label>
									<div class="form-group col-md-12">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												id="acuerdo" value="T" name="re_PaymentAgreement"> <label
												class="form-check-label" for="inlineCheckbox1">Acuerdo
												de pago</label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												id="pagado" value="T"  name="re_state"> <label
												class="form-check-label" for="inlineCheckbox2">Recibo
												pagado</label>
										</div>
									</div>

								</div>

								<br> <input class="btn btn-primary" type="submit"
									value="Registrar" class="btn btn-primary" />
								<div class="card-body">
									<canvas id="myAreaChart" width="100%" height="30"></canvas>
								</div>

								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Registros de pago
								</div>

							</div>


						</div>
				</form>
				<br>
			</div>
		</div>
	</main>
	<footer class="py-4 bg-light mt-auto">
		<div class="container-fluid px-4">
			<div class="d-flex align-items-center justify-content-between small">
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
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
