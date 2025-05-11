package com.clinica.model;

import java.time.LocalDate;

public class Receta {
    private int id;
    private int idPaciente;
    private int idMedico;
    private String descripcion;
    private LocalDate fecha;
    

    public Receta(int id, int idPaciente, int idMedico, String descripcion, LocalDate fecha) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public int getIdMedico() { return idMedico; }
    public void setIdMedico(int idMedico) { this.idMedico = idMedico; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
