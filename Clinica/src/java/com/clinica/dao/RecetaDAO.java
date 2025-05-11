package com.clinica.dao;

import com.clinica.model.Receta;
import com.clinica.dao.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class RecetaDAO {
    public void crearReceta(Receta receta) throws SQLException {
        String sql = "INSERT INTO recetas (id_paciente, id_medico, descripcion, fecha) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, receta.getIdPaciente());
            stmt.setInt(2, receta.getIdMedico());
            stmt.setString(3, receta.getDescripcion());
            stmt.setDate(4, java.sql.Date.valueOf(receta.getFecha()));

            stmt.executeUpdate();
        }
    }
    
    public List<Receta> buscarRecetasPorNombre(String nombrePaciente) throws SQLException {
        String sql = """
            SELECT r.id, r.id_paciente, r.id_medico, r.descripcion, r.fecha
            FROM recetas r
            JOIN usuarios u ON r.id_paciente = u.id
            WHERE u.nombre = ?
        """;

        List<Receta> recetas = new ArrayList<>();
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombrePaciente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Receta receta = new Receta(
                        rs.getInt("id"),
                        rs.getInt("id_paciente"),
                        rs.getInt("id_medico"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate()
                    );
                    recetas.add(receta);
                }
            }
        }
        return recetas;
    }
    
    
    
}
