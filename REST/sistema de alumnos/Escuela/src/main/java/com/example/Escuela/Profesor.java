package com.example.Escuela;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    String nombre;
    List<Salon> salones = new ArrayList<Salon>();
    Profesor(String nombre){
        this.nombre=nombre;
    }
    Profesor(String nombre, List<Salon> salones){
        this.nombre = nombre;
        this.salones = salones;
    }
    public String getNombre() {
        return nombre;
    }
    public List<Salon> getSalones() {
        return salones;
    }
    public void setSalones(List<Salon> salones) {
        this.salones = salones;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
