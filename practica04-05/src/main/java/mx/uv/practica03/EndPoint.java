package mx.uv.practica03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Endpoint
public class EndPoint{

    protected String[] myArray = new String[10];
    @Autowired
    private ISaludador iSaludador;

    public boolean estaVacia(String[] array, int posicion){
        return array[posicion] == null;
    }

    //saludar, buscar saludos, modificar saludo, borrar saludo
    @PayloadRoot(localPart = "SaludarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload 
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        
        //Base de datos
        Saludador saludador = new Saludador();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);

        //Flujo normal
        SaludarResponse respuesta = new SaludarResponse();
        for(int i=0; i<myArray.length;i++){
            if(estaVacia(myArray, i)){
                myArray[i] = peticion.getNombre();
                break;
            }
        }
        respuesta.setRespuesta("Hola " + peticion.getNombre() + ", mucho gusto");
        return respuesta;
    }

    @PayloadRoot (localPart="BuscarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarResponse Buscar(@RequestPayload BuscarRequest posicion){
        BuscarResponse respuesta = new BuscarResponse();

        List<Saludador> saludos = new ArrayList<>();
        iSaludador.findAll().forEach(saludos::add);

        for (Saludador saludador : saludos) {
            if(saludador.getId()==posicion.getPosicion()){
                respuesta.setRespuesta("Hola "+saludador.getNombre());
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

    @PayloadRoot (localPart="ModificarRequest", namespace="https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarResponse Modificar(@RequestPayload ModificarRequest posicion, @RequestPayload ModificarRequest peticion){
    ModificarResponse respuesta = new ModificarResponse();
    
    List<Saludador> saludos = new ArrayList<>();
    iSaludador.findAll().forEach(saludos::add);

    for (Saludador saludador : saludos) {
        if(saludador.getId()==posicion.getPosicion()){
            saludador.setNombre(peticion.getNombre());
            iSaludador.save(saludador);
            respuesta.setRespuesta("Modificado: Hola "+saludador.getNombre());
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

        List<Saludador> saludos = new ArrayList<>();
        iSaludador.findAll().forEach(saludos::add);
    
        for (Saludador saludador : saludos) {
            if(saludador.getId()==posicion.getPosicion()){
                iSaludador.delete(saludador);
                respuesta.setRespuesta("Se elimino a: "+saludador.getNombre());
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

        List<Saludador> saludos = new ArrayList<>();
        iSaludador.findAll().forEach(saludos::add);
        
        List<String> temp = new ArrayList<>();
        String tempo = new String();

        for (Saludador saludador : saludos) {
            tempo ="| "+ saludador.getId()+ " | " + saludador.getNombre()+" |";
            temp.add(tempo);
        }
        respuesta.getRespuesta().addAll(temp);
        return respuesta;
    }
}