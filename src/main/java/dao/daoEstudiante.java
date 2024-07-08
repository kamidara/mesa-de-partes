package dao;

import java.util.List;

import modelos.Estudiante;

public interface daoEstudiante {
	public boolean crear(Estudiante estudiante);
	public Estudiante leer(int id);
	public boolean actualizar(Estudiante estudiante);
	public boolean eliminar(int id);
	List<Estudiante> listar();
}
