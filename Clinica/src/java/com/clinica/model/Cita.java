package com.clinica.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private int id;
    private int pacienteId;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;

    public Cita(int id, int pacienteId, LocalDate fecha, LocalTime hora, String descripcion) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
