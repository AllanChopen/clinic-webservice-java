package com.clinica.dao;

import com.clinica.model.Diagnostico;
import com.clinica.dao.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class DiagnosticoDAO {
    public void crearDiagnostico(Diagnostico diagnostico) throws SQLException {
        String sql = "INSERT INTO diagnosticos (id_paciente, id_medico, descripcion, fecha) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, diagnostico.getIdPaciente());
            stmt.setInt(2, diagnostico.getIdMedico());
            stmt.setString(3, diagnostico.getDescripcion());
            stmt.setDate(4, java.sql.Date.valueOf(diagnostico.getFecha()));

            stmt.executeUpdate();
        }
    }
    
    public List<Diagnostico> buscarDiagnosticosPorNombre(String nombrePaciente) throws SQLException {
        String sql = """
            SELECT d.id, d.id_paciente, d.id_medico, d.descripcion, d.fecha
            FROM diagnosticos d
            JOIN usuarios u ON d.id_paciente = u.id
            WHERE u.nombre = ?
        """;

        List<Diagnostico> diagnosticos = new ArrayList<>();
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombrePaciente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Diagnostico diagnostico = new Diagnostico(
                        rs.getInt("id"),
                        rs.getInt("id_paciente"),
                        rs.getInt("id_medico"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate()
                    );
                    diagnosticos.add(diagnostico);
                }
            }
        }
        return diagnosticos;
    }
    
    
}
