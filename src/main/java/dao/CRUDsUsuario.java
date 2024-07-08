package dao;

import modelos.Usuario;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class CRUDsUsuario extends CrudGeneral implements daoUsuario {

    public CRUDsUsuario() {
        super();
    }

    // Crear un nuevo usuario
    @Override
    public boolean crear(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombre_usuario, contrasenya, fecha_creacion, salt, correo_electronico, habilitado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = conex.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getContrasenya());
            preparedStatement.setDate(3, usuario.getFechaCreacion());
            preparedStatement.setString(4, usuario.getSalt());
            preparedStatement.setString(5, usuario.getCorreoElectronico());
            preparedStatement.setBoolean(6, usuario.isHabilitado());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer un usuario por ID
    @Override
    public Usuario leer(String nombre_usuario) {
        String sql = "SELECT * FROM Usuario WHERE nombre_usuario = ?";
        try {
            preparedStatement = conex.prepareStatement(sql);
            preparedStatement.setString(1, nombre_usuario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasenya"),
                    rs.getDate("fecha_creacion"),
                    rs.getString("salt"),
                    rs.getString("rol"),
                    rs.getString("correo_electronico"),
                    rs.getBoolean("habilitado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Usuario leer(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try {
            preparedStatement = conex.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasenya"),
                    rs.getDate("fecha_creacion"),
                    rs.getString("salt"),
                    rs.getString("rol"),
                    rs.getString("correo_electronico"),
                    rs.getBoolean("habilitado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar un usuario existente
    @Override
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE Usuario SET nombre_usuario = ?, contrasenya = ?, fecha_creacion = ?, salt = ?, correo_electronico = ?, habilitado = ? WHERE id = ?";
        try {
            preparedStatement = conex.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getContrasenya());
            preparedStatement.setDate(3, usuario.getFechaCreacion());
            preparedStatement.setString(4, usuario.getSalt());
            preparedStatement.setString(5, usuario.getCorreoElectronico());
            preparedStatement.setBoolean(6, usuario.isHabilitado());
            preparedStatement.setInt(7, usuario.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un usuario por ID
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try {
            preparedStatement = conex.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener todos los usuarios
    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try {
            preparedStatement = conex.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasenya"),
                    rs.getDate("fecha_creacion"),
                    rs.getString("salt"),
                    rs.getString("rol"),
                    rs.getString("correo_electronico"),
                    rs.getBoolean("habilitado")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    	
    public static String generarSalt() throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashearPassword(String password, String salt) throws Exception {
        int iterations = 65536;
        int keyLength = 128;
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = keyFactory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
    
}