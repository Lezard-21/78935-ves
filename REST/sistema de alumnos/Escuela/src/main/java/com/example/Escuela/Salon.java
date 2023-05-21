package com.example.Escuela;

/* 
import java.util.ArrayList;
import java.util.List;*/

public class Salon {
    Integer numero;
    //List<Profesor> profesores = new ArrayList<>();
    Salon(Integer numero){
        this.numero=numero;
    }
    /*Salon(Integer numero,List<Profesor> profesores){
        this.numero=numero;
        this.profesores=profesores;
    }*/
    public Integer getNumero() {
        return numero;
    }
    /* 
    public List<Profesor> getProfesores() {
        return profesores;
    }*/
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    /* 
    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }*/
    @Override
    public String toString() {
        return "Salon [numero=" + numero + "]";
    }
    
   
}
