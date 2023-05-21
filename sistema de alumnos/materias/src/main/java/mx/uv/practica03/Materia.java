package mx.uv.practica03;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Materia {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int rfc;

    private String materiaNombre;
    private String materiasDescripcion;
    private String maestro;
   
    public int getRfc() {
        return rfc;
    }
    public void setRfc(int rfc) {
        this.rfc = rfc;
    }
    public String getMateriaNombre() {
        return materiaNombre;
    }
    public void setMateriaNombre(String materiaNombre) {
        this.materiaNombre = materiaNombre;
    }
    public String getMateriasDescripcion() {
        return materiasDescripcion;
    }
    public void setMateriasDescripcion(String materiasDescripcion) {
        this.materiasDescripcion = materiasDescripcion;
    }
    public String getMaestro() {
        return maestro;
    }
    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

     
}
