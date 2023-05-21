package mx.uv.practica03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.BuscarRequest;
import https.t4is_uv_mx.saludos.BuscarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.HistorialResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.alumno.*;
import https.t4is_uv_mx.materia.*;

@Endpoint
public class EndPoint{

    protected String[] myArray = new String[10];

    public boolean estaVacia(String[] array, int posicion){
        return array[posicion] == null;
    }

    @Autowired
    private IAlumno iAlumno;

    //saludar, buscar saludos, modificar saludo, borrar saludo
    @PayloadRoot(localPart = "alumnoRequest", namespace="https://t4is.uv.mx/alumno")
    @ResponsePayload 
    public AlumnoResponse Alumno(@RequestPayload AlumnoRequest peticion){
        
        //Base de datos
        Alumno saludador = new Alumno();
        saludador.setPrimerNombre(peticion.getNombre());
        saludador.setApellido(peticion.getApellido());
        saludador.setCarrera(peticion.getLicenciatura());
        saludador.setCorreoElectronico(peticion.getEmail());
        saludador.setFechaNacimiento(peticion.getNacimiento());
        saludador.setTelefono(peticion.getTelefono());
        saludador.setGenero(peticion.getGenero());
        iAlumno.save(saludador);

        //Flujo normal
        AlumnoResponse respuesta = new  AlumnoResponse();
        for(int i=0; i<myArray.length;i++){
            if(estaVacia(myArray, i)){
                myArray[i] = peticion.getNombre();
                break;
            }
        }
        respuesta.setRespuesta("Hola " + peticion.getNombre() + ", mucho gusto");
        return respuesta;
    }

    @PayloadRoot (localPart="alumnoBuscarRequest", namespace="https://t4is.uv.mx/alumno")
    @ResponsePayload
    public  AlumnoBuscarResponse  AlumnoBuscar(@RequestPayload AlumnoBuscarRequest posicion){
        AlumnoBuscarResponse respuesta = new  AlumnoBuscarResponse();

        List<Alumno> saludos = new ArrayList<>();
        iAlumno.findAll().forEach(saludos::add);

        for ( Alumno saludador : saludos) {
            if(saludador.getId()==posicion.getPosicion()){
                respuesta.setRespuesta("Hola "+saludador.getPrimerNombre());
                return respuesta;
            }
        }
        respuesta.setRespuesta("No se encontro");
        /*
        if(!estaVacia(myArray,posicion.getPosicion())){
            respuesta.setRespuesta("Hola " + myArray[posicion.getPosicion()].toString() + ", mucho gusto");
        }else{
            respuesta.setRespuesta("No se encontro");    
        }*/
        return respuesta;
    }

    @PayloadRoot (localPart="alumnoModificarRequest", namespace="https://t4is.uv.mx/alumno")
    @ResponsePayload
    public AlumnoModificarResponse AlumnoModificar(@RequestPayload AlumnoModificarRequest posicion, @RequestPayload AlumnoModificarRequest peticion){
        AlumnoModificarResponse respuesta = new AlumnoModificarResponse();
    
    List<Alumno> saludos = new ArrayList<>();
    iAlumno.findAll().forEach(saludos::add);

    for (Alumno saludador : saludos) {
        if(saludador.getId()==posicion.getPosicion()){
            saludador.setPrimerNombre(peticion.getNombre());
            saludador.setApellido(peticion.getApellido());
            saludador.setCarrera(peticion.getLicenciatura());
            saludador.setCorreoElectronico(peticion.getEmail());
            saludador.setFechaNacimiento(peticion.getNacimiento());
            saludador.setTelefono(peticion.getTelefono());
            saludador.setGenero(peticion.getGenero());
            iAlumno.save(saludador);

            respuesta.setRespuesta("Modificado: Hola "+saludador.getPrimerNombre());
            return respuesta;
         }
    }

    /*
    if(!estaVacia(myArray, posicion.getPosicion())){
        myArray[posicion.getPosicion()] = peticion.getNombre();
        respuesta.setRespuesta("Saludo Modificado");
    }else{
        respuesta.setRespuesta("No se encontro");
    }*/
    respuesta.setRespuesta("Error no se encontro el id");
    return respuesta;
    }
    
    @PayloadRoot (localPart="alumnoEliminarRequest", namespace="https://t4is.uv.mx/alumno")
    @ResponsePayload
    public AlumnoEliminarResponse  AlumnoEliminar(@RequestPayload EliminarRequest posicion){
        AlumnoEliminarResponse respuesta = new AlumnoEliminarResponse();

        List<Alumno> saludos = new ArrayList<>();
        iAlumno.findAll().forEach(saludos::add);
    
        for (Alumno saludador : saludos) {
            if(saludador.getId()==posicion.getPosicion()){
                iAlumno.delete(saludador);
                respuesta.setRespuesta("Se elimino a: "+saludador.getPrimerNombre());
                return respuesta;
            }
        }

        /*
        if(!estaVacia(myArray, posicion.getPosicion())){
            myArray[posicion.getPosicion()] = null;
            respuesta.setRespuesta("Saludo eliminado");
        }*/
        respuesta.setRespuesta("Error no se encontro id");

        return respuesta;
    }
    
    @PayloadRoot (localPart="alumnoHistorialRequest", namespace="https://t4is.uv.mx/alumno")
    @ResponsePayload
    public AlumnoHistorialResponse  AlumnoHistorial(){
        AlumnoHistorialResponse respuesta = new AlumnoHistorialResponse();

        List<Alumno> saludos = new ArrayList<>();
        iAlumno.findAll().forEach(saludos::add);
        
        List<String> temp = new ArrayList<>();
        String tempo = new String();

        for (Alumno saludador : saludos) {
            tempo ="| "+ saludador.getId()+ " | " + saludador.getPrimerNombre()+" |";
            temp.add(tempo);
        }
        respuesta.getRespuesta().addAll(temp);
        return respuesta;
    }

   
}