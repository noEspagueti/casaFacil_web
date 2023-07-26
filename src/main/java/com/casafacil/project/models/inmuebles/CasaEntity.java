package com.casafacil.project.models.inmuebles;

public class CasaEntity {
    private int numeroPisos;
    private int numeroHabitaciones;
    private int numeroBanio;
    private double areaConstruida;
    private double areaTotal;

    public CasaEntity() {
    }

    public CasaEntity(int numeroPisos, int numeroHabitaciones, int numeroBanio, double areaConstruida,
            double areaTotal) {
        this.numeroPisos = numeroPisos;
        this.numeroHabitaciones = numeroHabitaciones;
        this.numeroBanio = numeroBanio;
        this.areaConstruida = areaConstruida;
        this.areaTotal = areaTotal;
    }

    public int getNumeroPisos() {
        return this.numeroPisos;
    }

    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
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

    public double getAreaConstruida() {
        return this.areaConstruida;
    }

    public void setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public double getAreaTotal() {
        return this.areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

}
