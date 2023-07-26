package com.casafacil.project.models.inmuebles;

public class DepartamentoEntity {
    private int numeroHabitaciones;
    private int numeroBanio;
    private int numeroPisos;
    private double areaTotal;

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(int numeroHabitaciones, int numeroBanio, int numeroPisos, double areaTotal) {
        this.numeroHabitaciones = numeroHabitaciones;
        this.numeroBanio = numeroBanio;
        this.numeroPisos = numeroPisos;
        this.areaTotal = areaTotal;
    }

    public int getNumeroHabitaciones() {
        return this.numeroHabitaciones;
    }

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public int getNumeroBanio() {
        return this.numeroBanio;
    }

    public void setNumeroBanio(int numeroBanio) {
        this.numeroBanio = numeroBanio;
    }

    public int getNumeroPisos() {
        return this.numeroPisos;
    }

    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public double getAreaTotal() {
        return this.areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

}
