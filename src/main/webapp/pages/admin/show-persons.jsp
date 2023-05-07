<%@include file="/pages/admin/header.jsp"%>
<%@ page
	import="java.util.ArrayList, edu.uptc.entity.Person,edu.uptc.model.Manager"%>

<form action="../../AdminPerson" method="post">
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Residentes</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="pages/admin/index.jsp">Residentes</a></li>
					<li class="breadcrumb-item active">Información</li>
				</ol>

				<div class="card mb-4">
					<div class="card-body">
						Si desea ingresar un nuevo usuario, ingrese a el siguiente link: <a
							href="createUser.jsp"> Agregar nuevo usuario </a> (Recuerde tener
						los siguientes datos: Nombres, documentos, N° celular, etc.)
					</div>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> Personas registradas
					</div>
					<table id="datatablesSimple">
						<thead>
							<tr>
								<th>ID</th>
								<th>Rol</th>
								<th>Documento</th>
								<th>Nombre</th>
								<th>Apellido</th>
								<th>Número celular</th>
								<th>Estado cuenta</th>
								<th>Bloque</th>
								<th>Apto</th>
								<th>Editar</th>
								<th>Eliminar</th>
							</tr>
						</thead>
						<tbody>
							<%
							try {
								Manager manager = new Manager();
								ArrayList<Person> lista = manager.getPersonList();
								int pos = 0;
								if (lista != null) {
									for (Person e : lista) {
								String block = (e.getHorizontalPropertie().getBlock()) != null ? e.getHorizontalPropertie().getBlock()
										: "---";
								String apto = (e.getHorizontalPropertie().getApartment()) != null
										? e.getHorizontalPropertie().getApartment()
										: "---";
								pos++;
							%>
							<tr>
								<th scope=row><%=pos%></th>
								<td><%=e.getRol().toString()%></td>
								<td><%=e.getNumberID()%></td>
								<td><%=e.getName()%></td>
								<td><%=e.getLastName()%></td>
								<td><%=e.getPhoneNumber()%></td>
								<td><%=e.getStateAccount().getText()%></td>
								<td><%=block%></td>
								<td><%=apto%></td>
								<td><input type="submit" name="update_user"
									value="<%=e.getNumberID()%>"
									style="background-color: white; font-size: 0px; background-image: url('../../images/editar.png'); background-size: 30px; border: solid 0px #000000; width: 30px; height: 30px;">
								</td>

								<td><input type="submit" name="delete_user"
									value="<%=e.getNumberID()%>"
									style="background-color: white; font-size: 0px; background-image: url('../../images/basura.png'); background-size: 30px; border: solid 0px #000000; width: 30px; height: 30px;">
								</td>
							</tr>
							<%
							}

							} else {
							out.print(
									"<tr><th scope=row>0</th> <td>No hay registros</td> <td>No hay registros</td><td>Sin dirección</td><td>0</td></tr>");
							}
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
						</tbody>
					</table>

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
