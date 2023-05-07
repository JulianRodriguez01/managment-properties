<%@include file="header.jsp"%>
<%@ page
	import="java.util.ArrayList, java.util.Date, edu.uptc.entity.Person, edu.uptc.model.Manager, edu.uptc.entity.DetailsPayment"%>
<div id="layoutSidenav_content">
	<main>
		<form action="historial-payment.jsp" method="post">
			<div class="container-fluid px-4">
				<h1 class="mt-4">Historial de pago</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="historial-payment.jsp">Historial
							pago</a></li>
					<li class="breadcrumb-item active">Inicio</li>
				</ol>
				<div class="card mb-4">
					<div class="card-body">A continuación ingrese el usuario para
						verificar mostrar el historial de pagos.</div>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-chart-area me-1"></i> Historial
					</div>
					<div class="card-body">
						<canvas id="myAreaChart" width="100%" height="30"></canvas>
					</div>

					<select name="select_id" class="form-select form-select-sm"
						aria-label=".form-select-sm example" type="submit">
						<%
						try {
							Manager manager = new Manager();
							int idSelected = -1;
							ArrayList<Person> lista = manager.getPersonList();
							int pos = 0;
							if (lista != null) {
						%>
						<option selected value=0>Selecciones los datos del
							usuario que desee consultar.</option>
						<%
						for (Person e : lista) {
						%>
						<option value=<%=e.getNumberID()%>>Identificación:
							<%=e.getNumberID()%> - Nombre:
							<%=e.getName()%>
							<%=e.getLastName()%> Apartamentto:
							<%=e.getHorizontalPropertie().getApartment()%>
						</option>
						<%
						idSelected = e.getNumberID();
						}
						} else {
						out.print("<option selected>No hay datos disponibles, intentelo luego.</option>");
						}
						%>

					</select> <br> <input class="btn btn-primary" type="submit"
						value="Comprobar" class="btn btn-primary" /> <br>
					<%
					} catch (Exception e) {
					e.printStackTrace();
					}
					%>

				</div>
			</div>
		</form>

		<form action="../../ShowDetailsServlet" method="post">
			<div class="container-fluid px-4">
				<h1 class="mt-4">Datos</h1>
				<div class="card mb-4">
					<%
					try {
						Manager manager = new Manager();
					%>
					<table id="datatablesSimple">
						<thead>
							<tr>
								<th>ID</th>
								<th>Descripción</th>
								<th>Costo</th>
								<th>Fecha</th>
								<th>Acuerdo de pago</th>
								<th>Valor deuda</th>
								<th>Estado</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<%
								if (request.getParameter("select_id") != null) {
									ArrayList<DetailsPayment> listaPaymentReceipts = manager
									.generateHistorialPaymentReceipts(Integer.parseInt(request.getParameter("select_id")));

									if (listaPaymentReceipts.size() > 0) {
										int pos = 0;
										for (DetailsPayment paymentReceipts : listaPaymentReceipts) {
									pos++;
									String description = paymentReceipts.getDescription();
									double value = paymentReceipts.getValue();
									Date date = paymentReceipts.getDatePayment();
									String paymentAgreement = paymentReceipts.isPaymentAgreement() ? "No" : "Si";
									double outstandingBalance = paymentReceipts.getOutstandingBalance();
									String statePayment = paymentReceipts.isStatePayment() ? "Pagado" : "No pagado";
								%>
								<th scope=row><%=pos%></th>
								<td><%=description%></td>
								<td>$<%=value%></td>
								<td><%=date%></td>
								<td><%=paymentAgreement%></td>
								<td>$<%=outstandingBalance%></td>
								<td><%=statePayment%></td>
							</tr>
							<%
							}
							} else {
							out.print(
									"<tr><th scope=row>0</th> <td>No hay registros</td> <td>$0.0</td> <td>No hay registros</td> <td>$0.0</td> <td>No hay registros</td> <td>No hay registros</td></tr>");
							}
							} else {
							out.print("<tr><th scope=row>0</th> <td>No hay registros</td> <td>No hay registros</td></tr>");
							}
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
						</tbody>
					</table>

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
				<div class="text-muted">Copyright &copy; Your Website 2022</div>
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
