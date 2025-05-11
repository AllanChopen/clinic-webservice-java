package com.clinica.dao;

import com.clinica.model.Cita;
import com.clinica.dao.Conexion;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDAO {

    public void crearCita(Cita cita) throws SQLException {
        String sql = "INSERT INTO citas (paciente_id, fecha, hora, descripcion) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cita.getPacienteId());
            stmt.setDate(2, Date.valueOf(cita.getFecha()));
            stmt.setTime(3, Time.valueOf(cita.getHora()));
            stmt.setString(4, cita.getDescripcion());
            stmt.executeUpdate();
        }
    }

    public List<Cita> obtenerCitas() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("id"),
                        rs.getInt("paciente_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("descripcion")
                );
                citas.add(cita);
            }
        }
        return citas;
    }
    
      private Connection conectar() throws SQLException {
        return Conexion.conectar(); 
    }

    public List<Cita> buscarCitasPorNombre(String nombrePaciente) throws SQLException {
    List<Cita> citas = new ArrayList<>();
    String sql = "SELECT c.id, c.paciente_id, c.fecha, c.hora, c.descripcion " +
                 "FROM citas c " +
                 "JOIN usuarios u ON c.paciente_id = u.id " +
                 "WHERE u.nombre = ? AND u.rol = 'paciente'";

    try (Connection conn = conectar(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nombrePaciente);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int pacienteId = rs.getInt("paciente_id"); 
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            LocalTime hora = rs.getTime("hora").toLocalTime();
            String descripcion = rs.getString("descripcion");

            citas.add(new Cita(id, pacienteId, fecha, hora, descripcion));
        }
    }

    return citas;
}

}



