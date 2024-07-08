package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.Estudiante;

public class CRUDsEstudiante extends CrudGeneral implements daoEstudiante{

    public CRUDsEstudiante() {
        super();
    }

    // Crear un nuevo estudiante
    public boolean crear(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (id, nombre, dni, fecha_nacimiento, id_usuario) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setInt(1, estudiante.getId());
            stmt.setString(2, estudiante.getNombre());
            stmt.setString(3, estudiante.getDni());
            stmt.setDate(4, estudiante.getFecha_nacimiento());
            stmt.setInt(5, estudiante.getId_usuario());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer un estudiante por ID
    public Estudiante leer(int id) {
        String sql = "SELECT * FROM estudiante WHERE id = ?";
        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getInt("id_usuario")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar un estudiante existente
    public boolean actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, dni = ?, fecha_nacimiento = ?, id_usuario = ? WHERE id = ?";
        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getDni());
            stmt.setDate(3, estudiante.getFecha_nacimiento());
            stmt.setInt(4, estudiante.getId_usuario());
            stmt.setInt(5, estudiante.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un estudiante por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";
        try (PreparedStatement stmt = conex.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener todos los estudiantes
    public ArrayList<Estudiante> listar() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (PreparedStatement stmt = conex.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("dni"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getInt("id_usuario")
                );
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
}
