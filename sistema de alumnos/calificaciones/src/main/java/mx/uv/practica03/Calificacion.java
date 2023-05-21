package mx.uv.practica03;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Calificacion {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private int idAlumno;
    private int bfc;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getIdAlumno() {
        return idAlumno;
    }
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    public int getBfc() {
        return bfc;
    }
    public void setBfc(int bfc) {
        this.bfc = bfc;
    } 
}
