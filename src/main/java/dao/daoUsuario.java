package dao;

import java.util.List;

import modelos.Usuario;

public interface daoUsuario {
	public boolean crear(Usuario usuario);
	public Usuario leer(int id);
	public Usuario leer(String nombre_usuario);
	public boolean actualizar(Usuario usuario);
	public boolean eliminar(int id);
	List<Usuario> listarUsuarios();
}
