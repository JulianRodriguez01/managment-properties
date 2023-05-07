<%@include file="header.jsp"%>
<%@ page
	import="java.util.ArrayList, edu.uptc.entity.ReservationLugars, edu.uptc.entity.CommunalLiving, edu.uptc.entity.Reservation, edu.uptc.model.Manager"%>
<div id="layoutSidenav_content">
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">Calcular multa</h1>
			<ol class="breadcrumb mb-4">
				<li class="breadcrumb-item"><a href="index.jsp">Multa</a></li>
				<li class="breadcrumb-item active">Cálculo generación multas</li>
			</ol>
			<div class="container">
				<form action="index.jsp">
					<input type="submit" value="Regresar" class="btn btn-primary" />
				</form>
				<br>
				<form action="calculate-penalty-fee.jsp">
					<div class="row">
						<div class="col-lg-12">
							<div class="card mb-4">

								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Documentos reservación
								</div>

								<div class="form-row">
									<br>
									<div class="row">
										<div class="col-xl-6">
											<label for="inputAddress">Cancelación menor a 5 días</label>
											<div class="form-group col-md-12">
												<div class="form-check form-check-inline">
													<input class="form-check-input" type="radio" name="value"
														id="per_admin" value="less"> <label
														class="form-check-label" for="flexRadioDefault1">
														Si </label>
												</div>
												<div class="form-check form-check-inline">
													<input class="form-check-input" type="radio" name="value"
														id="per_user" value="many" checked> <label
														class="form-check-label" for="flexRadioDefault2">
														No </label>
												</div>
											</div>
										</div>
										<div class="col-xl-6">
											<label for="inputEmail4">Horas</label> <input type="number"
												class="form-control" id="inputEmail4" min="0" max="5"
												placeholder="Ingrese el número de horas" name="res_hor"
												required>
										</div>
									</div>

									<div class="form-group col-md-12">
										<br> <label for="inputAddress">Datos del salon
											que desea reservar</label> <select name="select_comun"
											class="form-select form-select-sm"
											aria-label=".form-select-sm example">
											<%
											try {
												Manager manager = new Manager();
												ArrayList<CommunalLiving> listCommunalLiving = manager.getCommunalLivingList();
												int pos = 0;
												if (listCommunalLiving != null) {
											%>
											<option selected>Selecciones los datos del salon
												asignado.</option>
											<%
											for (CommunalLiving cl : listCommunalLiving) {
											%>
											<option value=<%=cl.getIdCommunalLiving()%>>ID:
												<%=cl.getIdCommunalLiving()%>
												<%=cl.getReservationLugars().getText()%> :
												<%=cl.getDescription()%></option>
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
									</div>
								</div>
								<br> <input class="btn btn-primary" type="submit"
									value="Consultar" class="btn btn-primary" />
								<div class="card-body">
									<canvas id="myAreaChart" width="100%" height="30"></canvas>
								</div>

								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Registros de reservación
								</div>
							</div>
						</div>
				</form>



				<form action="../../AddReservationServlet" method="post">
					<div class="row">
						<div class="col-lg-12">
							<div class="card mb-4">

								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Resultado simulación
								</div>
								<%
								try {
									Manager manager = new Manager();
									ReservationLugars reservationLugars = manager
									.searchCommunalLiving(Integer.parseInt(request.getParameter("select_comun"))).getReservationLugars();
									boolean manyFiveDay = request.getParameter("value").equals("many") ? true : false;
									double valueSMMV = 1000000;
									int hours = Integer.parseInt(request.getParameter("res_hor"));
									double result = manager.calculateValuePenaltyFee(reservationLugars, manyFiveDay, valueSMMV, hours);
								%>
								$<%=String.format("%.2f", result)%>.oo
								<%
								} catch (Exception e) {
								e.printStackTrace();
								}
								%>
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
