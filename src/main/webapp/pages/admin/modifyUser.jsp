<%@include file="header.jsp"%>
<%@ page
	import="java.util.ArrayList, edu.uptc.entity.Person,edu.uptc.model.Manager"%>
<div id="layoutSidenav_content">
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">Modificar Usuarios</h1>
			<ol class="breadcrumb mb-4">
				<li class="breadcrumb-item"><a href="index.jsp">Residentes</a></li>
				<li class="breadcrumb-item active">Modificar residente</li>
			</ol>
			<div class="container">
				<form action="index.jsp">
					<input type="submit" value="Regresar" class="btn btn-primary" />
				</form>
				<br>

				<div class="card mb-4">
					<div class="card-body">Si desea modificar algun dato del
						usuario seleccionado, modifique los campos (No es necesario
						rellenar todos los campos)</div>
				</div>
				<form action="../../SetPersonServlet" method="post">
					<div class="row">
						<div class="col-lg-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Documentos propietario
								</div>
								<div class="form-row">
									<select name="select_id" class="form-select form-select-sm"
										aria-label=".form-select-sm example">
										<%
										try {
											Manager manager = new Manager();
											ArrayList<Person> lista = manager.getPersonList();
											int pos = 0;
											if (lista != null) {
										%>
										<option selected>Selecciones los datos del usuario
											que desee modififcar.</option>
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
										<label for="inputPassword4">Rol</label>
										<div class="form-check">
											<input class="form-check-input" type="radio" name="edi_rol"
												id="per_admin" value="admin"> <label
												class="form-check-label" for="flexRadioDefault1">
												Administrador </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="radio" name="edi_rol"
												id="per_user" value="user" checked> <label
												class="form-check-label" for="flexRadioDefault2">
												Usuario </label>
										</div>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="inputEmail4">Documento</label> <input
											type="number" class="form-control" id="pro_blo"
											placeholder="Ingrese el nombre propiedad horizontal."
											name="edi_document">
									</div>
									<div class="form-group col-md-12">
										<label for="inputEmail4">Nombres</label> <input type="text"
											class="form-control" id="inputEmail4"
											placeholder="Ingrese su nombre" name="edi_name">
									</div>
									<div class="form-group col-md-12">
										<label for="inputPassword4">Apellidos</label> <input
											type="text" class="form-control" id="inputPassword4"
											placeholder="Ingrese su apellido" name="edi_last_name">
									</div>

									<div class="form-group col-md-12">
										<label for="inputAddress">Teléfono</label> <input
											type="number" class="form-control" id="inputAddress"
											placeholder="Ingrese su número de teléfono" name="edi_phone">
									</div>
									<div class="form-group col-md-12">
										<label for="inputAddress">Username</label> <input type="text"
											class="form-control" id="inputAddress"
											placeholder="Ingrese su número de teléfono"
											name="edi_username">
									</div>
									<div class="form-group col-md-12">
										<label for="inputAddress">Contraseña</label> <input
											type="password" class="form-control" id="inputAddress"
											placeholder="Ingrese su número de teléfono"
											name="edi_password">
									</div>
								</div>
								<div class="card-body">
									<canvas id="myBarChart" width="100%" height="5"></canvas>
								</div>
								<div class="card-footer small text-muted">Datos del
									usuario inscrito en la propiedad</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-pie me-1"></i> Documentos propiedad
								</div>
								<div class="form-row">
									<div class="form-group col-md-12">
										<label for="inputEmail4">Nombre</label> <input type="text"
											class="form-control" id="pro_blo"
											placeholder="Ingrese el nombre propiedad horizontal."
											name="edi_name">
									</div>
									<div class="form-group col-md-12">
										<label for="inputEmail4">Bloque</label> <input type="number"
											class="form-control" id="pro_blo"
											placeholder="Ingrese el número de bloque." name="edi_blo">
									</div>
									<div class="form-group col-md-12">
										<label for="inputPassword4">Apartamento</label> <input
											type="number" class="form-control" id="inputPassword4"
											placeholder="Ingrese el número del apartamento"
											name="edi_apt">
									</div>
									<div class="form-group col-md-12">
										<label for="inputPassword4">Datos adicionales</label> <input
											type="text" class="form-control" id="inputPassword4"
											placeholder="Ingrese datos adisionales (Opcional)"
											name="edi_adic" style="height: 200px;">
									</div>
								</div>
								<div class="card-body">
									<canvas id="myPieChart" width="100%" height="100"></canvas>
								</div>
								<div class="card-footer small text-muted">Datos de la
									propiedad horizontal</div>
							</div>
						</div>
						<br> <input class="btn btn-primary" type="submit"
							value="Modificar" class="btn btn-primary" />
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
