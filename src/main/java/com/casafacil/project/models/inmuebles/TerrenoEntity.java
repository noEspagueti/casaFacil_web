package com.casafacil.project.models.inmuebles;

public class TerrenoEntity {
    private double areaConstruida;
    private double areaTotal;

    public TerrenoEntity() {
    }

    public TerrenoEntity(double areaConstruida, double areaTotal) {
        this.areaConstruida = areaConstruida;
        this.areaTotal = areaTotal;
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
