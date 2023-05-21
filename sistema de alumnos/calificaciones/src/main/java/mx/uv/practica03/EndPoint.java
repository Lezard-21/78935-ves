package mx.uv.practica03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import https.t4is_uv_mx.calificacion.*;;

@Endpoint
public class EndPoint{

    protected String[] myArray = new String[10];

    public boolean estaVacia(String[] array, int posicion){
        return array[posicion] == null;
    }

    @Autowired
    private ICalificacion iSaludador;

    //saludar, buscar saludos, modificar saludo, borrar saludo
    @PayloadRoot(localPart = "Request", namespace="https://t4is.uv.mx/calificacion")
    @ResponsePayload 
    public Response Saludar(@RequestPayload Request peticion){
        
        //Base de datos
        Calificacion saludador = new Calificacion();
        saludador.setId(peticion.hashCode());
        iSaludador.save(saludador);

        //Flujo normal
        Response respuesta = new Response();
        for(int i=0; i<myArray.length;i++){
            if(estaVacia(myArray, i)){
                myArray[i] = peticion.toString();
                break;
            }
        }
        respuesta.setRespuesta("Hola " + peticion.toString() + ", mucho gusto");
        return respuesta;
    }

    @PayloadRoot (localPart="BuscarRequest", namespace="https://t4is.uv.mx/calificaciones")
    @ResponsePayload
    public BuscarResponse Buscar(@RequestPayload BuscarRequest posicion){
        BuscarResponse respuesta = new BuscarResponse();

        List<Calificacion> saludos = new ArrayList<>();
        iSaludador.findAll().forEach(saludos::add);

        for (Calificacion saludador : saludos) {
            if(saludador.getId()==posicion.getPosicion()){
                respuesta.setRespuesta("Hola "+saludador.getBfc());
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

    @PayloadRoot (localPart="ModificarRequest", namespace="https://t4is.uv.mx/calificaciones")
    @ResponsePayload
    public ModificarResponse Modificar(@RequestPayload ModificarRequest posicion, @RequestPayload ModificarRequest peticion){
    ModificarResponse respuesta = new ModificarResponse();
    
    List<Calificacion> saludos = new ArrayList<>();
    iSaludador.findAll().forEach(saludos::add);

    for (Calificacion saludador : saludos) {
        if(saludador.getId()==posicion.getPosicion()){
            saludador.setBfc(peticion.getPosicion());
            iSaludador.save(saludador);
            respuesta.setRespuesta("Modificado: Hola "+saludador.toString());
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
    
    @PayloadRoot (localPart="EliminarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse  Eliminar(@RequestPayload EliminarRequest posicion){
        EliminarResponse respuesta = new EliminarResponse();

        List<Calificacion> saludos = new ArrayList<>();
        iSaludador.findAll().forEach(saludos::add);
    
        for (Calificacion saludador : saludos) {
            if(saludador.getId()==posicion.getPosicion()){
                iSaludador.delete(saludador);
                respuesta.setRespuesta("Se elimino a: "+saludador.toString());
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
    
    @PayloadRoot (localPart="HistorialRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public HistorialResponse  Historial(){
        HistorialResponse respuesta = new HistorialResponse();

        List<Calificacion> saludos = new ArrayList<>();
        iSaludador.findAll().forEach(saludos::add);
        
        List<String> temp = new ArrayList<>();
        String tempo = new String();

        for (Calificacion saludador : saludos) {
            tempo ="| "+ saludador.getId()+ " | " + saludador.toString()+" |";
            temp.add(tempo);
        }
        respuesta.getRespuesta().addAll(temp);
        return respuesta;
    }

}