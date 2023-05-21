package mx.uv.practica03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;


import https.t4is_uv_mx.materia.*;

@Endpoint
public class EndPoint{

    protected String[] myArray = new String[10];

    public boolean estaVacia(String[] array, int posicion){
        return array[posicion] == null;
    }

    @Autowired
   
    private IMateria iMateria;

    //saludar, buscar saludos, modificar saludo, borrar saludo
    @PayloadRoot(localPart = "materiaSaludarRequest", namespace="https://t4is.uv.mx/materia")
    @ResponsePayload 
    public MateriaResponse MateriaSaludar(@RequestPayload MateriaRequest peticion){
        
        //Base de datos
        Materia saludador = new Materia();
        saludador.setMateriaNombre(peticion.getMateriaNombre());
        saludador.setMaestro(peticion.getMaestro());
        saludador.setMateriasDescripcion(peticion.getMateriasDescripcion());
        iMateria.save(saludador);

        //Flujo normal
        MateriaResponse respuesta = new MateriaResponse();
        for(int i=0; i<myArray.length;i++){
            if(estaVacia(myArray, i)){
                myArray[i] = peticion.getMateriaNombre();
                break;
            }
        }
        respuesta.setRespuesta("Hola " + peticion.getMateriaNombre() + ", mucho gusto");
        return respuesta;
    }

    @PayloadRoot (localPart="materiaBuscarRequest", namespace="https://t4is.uv.mx/materia")
    @ResponsePayload
    public MateriaBuscarResponse MateriaBuscar(@RequestPayload MateriaBuscarRequest posicion){
        MateriaBuscarResponse respuesta = new MateriaBuscarResponse();

        List<Materia> saludos = new ArrayList<>();
        iMateria.findAll().forEach(saludos::add);

        for (Materia saludador : saludos) {
            if(saludador.getRfc()==posicion.getPosicion()){
                respuesta.setRespuesta("Hola "+saludador.getMateriaNombre());
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

    @PayloadRoot (localPart="materiaModificarRequest", namespace="https://t4is.uv.mx/materia")
    @ResponsePayload
    public MateriaModificarResponse MateriaModificar(@RequestPayload MateriaModificarRequest posicion, @RequestPayload MateriaModificarRequest peticion){
    MateriaModificarResponse respuesta = new MateriaModificarResponse();
    
    List<Materia> saludos = new ArrayList<>();
    iMateria.findAll().forEach(saludos::add);

    for (Materia saludador : saludos) {
        if(saludador.getRfc()==posicion.getPosicion()){
            saludador.setMateriaNombre(peticion.getMateriaNombre());
            saludador.setMaestro(peticion.getMaestro());
            saludador.setMateriasDescripcion(peticion.getMateriasDescripcion());
            iMateria.save(saludador);
            respuesta.setRespuesta("Modificado: Hola "+saludador.getMateriaNombre());
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
    
    @PayloadRoot (localPart="materiaEliminarRequest", namespace="https://t4is.uv.mx/materia")
    @ResponsePayload
    public MateriaEliminarResponse  MateriaEliminar(@RequestPayload MateriaEliminarRequest posicion){
        MateriaEliminarResponse respuesta = new MateriaEliminarResponse();

        List<Materia> saludos = new ArrayList<>();
        iMateria.findAll().forEach(saludos::add);
    
        for (Materia saludador : saludos) {
            if(saludador.getRfc()==posicion.getPosicion()){
                iMateria.delete(saludador);
                respuesta.setRespuesta("Se elimino a: "+saludador.getMateriaNombre());
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
    
    @PayloadRoot (localPart="materiaHistorialRequest", namespace="https://t4is.uv.mx/materia")
    @ResponsePayload
    public MateriaHistorialResponse  MateriaHistorial(){
        MateriaHistorialResponse respuesta = new MateriaHistorialResponse();

        List<Materia> saludos = new ArrayList<>();
        iMateria.findAll().forEach(saludos::add);
        
        List<String> temp = new ArrayList<>();
        String tempo = new String();

        for (Materia saludador : saludos) {
            tempo ="| "+ saludador.getRfc()+ " | " + saludador.getMateriaNombre()+" |";
            temp.add(tempo);
        }
        respuesta.getRespuesta().addAll(temp);
        return respuesta;
    }
    
}