<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelos.Estudiante"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.Fabrica"%>

  <title>Estudiante</title>
  
<body>
<%@include file="cabecera.jsp"%>
    <%
    //Búsqueda aún no funciona, botón de creación funcional.
    int idActualizar=0;
    int idEliminar=0;
    ArrayList<Estudiante> listaEstudiantes;
    if(request.getAttribute("listaEstudiantes")!=null){
        listaEstudiantes=(ArrayList<Estudiante>)request.getAttribute("listaEstudiantes");
    %>
		<div class="container mt-2" align="center">
			<form action="ControladorEstudiante">
			<table>
				<tr>
				<td>
					<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#crearEstudiante">
					  	Registrar nuevo estudiante
					</button>
				</td>
					<td>
						<div class="mb-2 mt-2">
					      <label for="nombre">Nombre:</label>
					      <input type="text" class="form-control" id="nombre" placeholder="Ingrese el nombre del estudiante" name="nombre">
					    </div>
				    </td>
				    <td>
					    <div class="mb-2 mt-2">
					      <label for="dni">DNI:</label>
					      <input type="text" class="form-control" id="dni" placeholder="Ingrese el DNI del estudiante" name="dni">
					    </div>
				    </td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="guia" value="buscar">
			    		<button type="submit" class="btn btn-primary">Buscar estudiante</button>	
					</td>					
				</tr>
			</table>			    
			    		    
			</form>
		</div>	  
		
	<!-- Lista de Estudiantes  -->
	
    <div class="container">
        <table class="table">
            <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>DNI</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Nombre de Usuario</th>
                    <th>Actualizar</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <% if (listaEstudiantes != null) {
                    for (Estudiante estudiante : listaEstudiantes) { %>
                        <tr>
                        <!-- Se usan los métodos del modelo para mostrar los datos -->
                            <td><%= estudiante.getId() %></td>
                            <td><%= estudiante.getNombre() %></td>
                            <td><%= estudiante.getDni() %></td>
                            <td><%= estudiante.getFecha_nacimiento() %></td>
                            <td><%= Fabrica.adminUsuario.leer(estudiante.getId_usuario()).getNombreUsuario() %></td>
                            <!-- Botones de preparación para actualizar y eliminar -->
                            <td>
	                            <form action="ControladorEstudiante" method="post">
	                            	<input type="hidden" name="id" value="<%=estudiante.getId()%>">
	                            	<input type="hidden" name="guia" value="prepararActualizar">
	                            	<button type="submit" class="btn btn-primary">Actualizar</button>
	                            </form>
                            </td>	                            
	                        <td>
		                        <form action="ControladorEstudiante" method="post">
		                        	<input type="hidden" name="id" value="<%=estudiante.getId()%>">
	                            	<input type="hidden" name="guia" value="prepararEliminar">
		                            <button type="submit" class="btn btn-danger">Eliminar</button>
	                            </form>
                            </td>
                            
                        </tr>
                    <% }
                } %>
            </tbody>
        </table>
    </div>
    <%
    }
    %>
    
    
<!-- Modal para la creación de estudiantes  -->

<div class="modal fade" id="crearEstudiante">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header">
        <h4 class="modal-title">Creación de estudiantes</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <div class="modal-body">
        <div class="container mt-3">
		  <h2>Registrar nuevo estudiante</h2>
		  <form action="ControladorEstudiante" method="post">
		    <div class="mb-3 mt-3">
		      <label for="nombre">Nombre:</label>
		      <input type="text" class="form-control" id="nombre" placeholder="Ingrese el nombre del estudiante" name="nombre">
		    </div>
		    <div class="mb-3 mt-3">
		      <label for="dni">DNI:</label>
		      <input type="text" class="form-control" id="dni" placeholder="Ingrese el DNI del estudiante" name="dni">
		    </div>
		    <div class="mb-3 mt-3">
		      <label for="fecha_nacimiento">Fecha de nacimiento:</label>
		      <input type="date" class="form-control" id="fecha_nacimiento" placeholder="Ingrese la fecha de nacimiento del estudiante" name="fecha_nacimiento">
		    </div>
		    <div class="mb-3 mt-3">
		      <label for="correo_electronico">Correo electrónico:</label>
		      <input type="email" class="form-control" id="correo_electronico" placeholder="Ingrese el correo del cliente" name="correo_electronico">
		    </div>
			<div class="mb-3 mt-3">
		      <label for="nombre_usuario">Nombre de usuario:</label>
		      <input type="text" class="form-control" id="nombre_usuario" placeholder="Ingrese el nombre de usuario del cliente" name="nombre_usuario">
		    </div>
		    <div class="mb-3 mt-3">
		      <label for="contrasenya">Contraseña:</label>
		      <input type="password" class="form-control" id="contrasenya" placeholder="Ingrese contraseña del cliente" name="contrasenya">
		    </div>
		    <div class="mb-3 mt-3">
		      <label for="imagen">Imagen:</label>
		      <input type="file" class="form-control" id="imagen" placeholder="Ingrese la imagen del cliente" name="imagen">
		    </div>
		    <input type="hidden" name="guia" value="crear">
		    <button type="submit" class="btn btn-success">Registrar estudiante</button>
		    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar registro</button>
		  </form>
		</div>
      </div>

    </div>
  </div>
</div>
    
    <!-- Modal para la actualización de estudiantes  -->

	<%
		Estudiante estudianteActualizar=null;
		if(request.getAttribute("estudianteActualizar")!=null){
			estudianteActualizar=(Estudiante)request.getAttribute("estudianteActualizar");
			%>
			<div class="modal fade" id="actualizar">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title">Actualizar estudiante</h4>
			        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			      </div>

			      <div class="modal-body">
			        <div class="container mt-3">
					  <h2>Actualizar un estudiante</h2>
					  <form action="ControladorEstudiante" method="post">
					    <div class="mb-3 mt-3">
					      <label for="nombre">Nombre:</label>
					      <input type="text" class="form-control" id="nombre" value="<%=estudianteActualizar.getNombre()%>" name="nombre">
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="dni">DNI:</label>
					      <input type="text" class="form-control" id="dni" value="<%=estudianteActualizar.getDni()%>" name="dni">
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="fecha_nacimiento">Fecha de nacimiento:</label>
					      <input type="date" class="form-control" id="fecha_nacimiento" value="<%=estudianteActualizar.getFecha_nacimiento()%>" name="fecha_nacimiento">
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="correo_electronico">Correo electrónico:</label>
					      <input type="email" class="form-control" id="correo_electronico" value="<%=Fabrica.adminUsuario.leer(estudianteActualizar.getId_usuario()).getCorreoElectronico() %>" name="correo_electronico">
					    </div>
						<div class="mb-3 mt-3">
					      <label for="nombre_usuario">Nombre de usuario:</label>
					      <input type="text" class="form-control" id="nombre_usuario" value="<%=Fabrica.adminUsuario.leer(estudianteActualizar.getId_usuario()).getNombreUsuario() %>" name="nombre_usuario">
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="contrasenya">Contraseña:</label>
					      <input type="password" class="form-control" id="contrasenya" placeholder="Ingrese contraseña del estudiante" name="contrasenya">
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="imagen">Imagen:</label>
					      <input type="file" class="form-control" id="imagen" placeholder="Ingrese la imagen del estudiante" name="imagen">
					    </div>
					    <input type="hidden" name="guia" value="actualizar">
					    <input type="hidden" name="id" value="<%=estudianteActualizar.getId()%>">
					    <button type="submit" class="btn btn-success">Actualizar estudiante</button>
					    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar actualización</button>
					  </form>
					</div>
			      </div>
			      </div>

			    </div>
			  </div>
			<%
		}
	%>

<!-- Modal para la eliminación de estudiantes  -->

<%
Estudiante estudianteEliminar = null;
if (request.getAttribute("estudianteEliminar") != null) {
    estudianteEliminar = (Estudiante) request.getAttribute("estudianteEliminar");
    %>
    <div class="modal fade" id="eliminar">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Eliminar estudiante</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <h5>Desea realmente eliminar a este estudiante?</h5>
                </div>
                <div class="modal-body">
                    <table class="table table-striped">
                        <tr>
                            <th>Elemento</th>
                            <th>Dato</th>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><%= estudianteEliminar.getNombre() %></td>
                        </tr>
                        <tr>
                            <td>DNI</td>
                            <td><%= estudianteEliminar.getDni() %></td>
                        </tr>
                        <tr>
                            <td>Fecha de nacimiento</td>
                            <td><%= estudianteEliminar.getFecha_nacimiento() %></td>
                        </tr>
                        <tr>
                            <td>Nombre de usuario</td>
                            <td><%= Fabrica.adminUsuario.leer(estudianteEliminar.getId_usuario()).getNombreUsuario() %></td>
                        </tr>
                        <tr>
                            <td>Correo Electrónico</td>
                            <td><%= Fabrica.adminUsuario.leer(estudianteEliminar.getId_usuario()).getCorreoElectronico() %></td>
                        </tr>
                        <tr>
                            <td>
                                <form action="ControladorEstudiante" method="post">
                                    <input type="hidden" name="id" value="<%= estudianteEliminar.getId() %>">
                                    <input type="hidden" name="guia" value="eliminar">
                                    <button type="submit" class="btn btn-dark">Eliminar estudiante</button>
                                </form>
                            </td>
                            <td><button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar eliminación</button></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%
}
%>

<script>
$(document).ready(function(){
  $('#actualizar').modal('show');
});
</script>    
<script>
$(document).ready(function(){
  $('#eliminar').modal('show');
});
</script> 


</body>
</html>
