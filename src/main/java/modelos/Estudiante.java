package modelos;

import java.sql.Date;

public class Estudiante {
	int id;
	String nombre;
	String dni;
	Date fecha_nacimiento;
	int id_usuario;
	
	public Estudiante(int id, String nombre, String dni, Date fecha_nacimiento, int id_usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.fecha_nacimiento = fecha_nacimiento;
		this.id_usuario = id_usuario;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
}