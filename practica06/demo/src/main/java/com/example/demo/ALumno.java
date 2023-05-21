package com.example.demo;

public class ALumno {
    String nombre; 
    Integer edad;
    ALumno(String nombre, Integer edad){
        this.nombre = nombre;
        this.edad = edad;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
