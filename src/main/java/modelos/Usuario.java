package modelos;

import java.sql.Date;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasenya;
    private Date fechaCreacion;
    private String salt;
    private String rol;
    private String correoElectronico;
    private boolean habilitado;
	public Usuario(int id, String nombreUsuario, String contrasenya, Date fechaCreacion, String salt, String rol,
			String correoElectronico, boolean habilitado) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.contrasenya = contrasenya;
		this.fechaCreacion = fechaCreacion;
		this.salt = salt;
		this.rol = rol;
		this.correoElectronico = correoElectronico;
		this.habilitado = habilitado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
    
}