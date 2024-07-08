package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Estudiante;
import modelos.Usuario;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.Fabrica;

/**
 * Servlet implementation class ControladorEstudiante
 */
public class ControladorEstudiante extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorEstudiante() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String guia = request.getParameter("guia");
        switch (guia) {
        case "listar":
            listar(request, response,"estudiante.jsp");
            break;
        case "crear":
            try {
                crear(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case "prepararActualizar":
            prepararActualizar(request, response);
            break;
        case "prepararEliminar":
            prepararEliminar(request, response);
            break;
        case "eliminar":
            eliminar(request, response);
            break;
        case "actualizar":
            actualizar(request, response);
            break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void listar(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {

        ArrayList<Estudiante> listaEstudiantes = Fabrica.adminEstudiante.listar();

        request.setAttribute("listaEstudiantes", listaEstudiantes);

        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void crear(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        Date fecha_nacimiento = Date.valueOf(request.getParameter("fecha_nacimiento"));
        
        String nombre_usuario = request.getParameter("nombre_usuario");
		String contrasenya = request.getParameter("contrasenya");
		String correo_electronico = request.getParameter("correo_electronico");	
		
		Date fecha_hoy = Date.valueOf(LocalDate.now());
		String salt = dao.CRUDsUsuario.generarSalt();
		String contrasenya_hasheada = Fabrica.adminUsuario.hashearPassword(contrasenya, salt);

		Usuario usuario = new Usuario(0, nombre_usuario, contrasenya_hasheada, fecha_hoy, salt,"Estudiante",correo_electronico,
				false);

		Fabrica.adminUsuario.crear(usuario);

		Usuario ultimo_usuario = Fabrica.adminUsuario.leer(nombre_usuario);

        Estudiante estudiante = new Estudiante(0, nombre, dni, fecha_nacimiento, ultimo_usuario.getId());

        Fabrica.adminEstudiante.crear(estudiante);

        listar(request, response,"estudiante.jsp");
    }

    protected void prepararActualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudianteActualizar = Fabrica.adminEstudiante.leer(id);

        request.setAttribute("estudianteActualizar", estudianteActualizar);
        listar(request, response, "estudiante.jsp?showModal=actualizar");
    }

    protected void prepararEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudianteEliminar = Fabrica.adminEstudiante.leer(id);

        request.setAttribute("estudianteEliminar", estudianteEliminar);
        listar(request, response, "estudiante.jsp?showModal=eliminar");
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fabrica.adminEstudiante.eliminar(id);
        listar(request, response, "estudiante.jsp");
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        Date fecha_nacimiento = Date.valueOf(request.getParameter("fecha_nacimiento"));
        
        Estudiante estudianteAntiguo=Fabrica.adminEstudiante.leer(id);

        Estudiante estudiante = new Estudiante(id, nombre, dni, fecha_nacimiento, estudianteAntiguo.getId_usuario());
        Fabrica.adminEstudiante.actualizar(estudiante);
        listar(request, response, "estudiante.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
