package mx.uv.consumidor;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import mx.xlp.SaludarRequest;
import mx.xlp.SaludarResponse;

public class SaludosCliente extends WebServiceGatewaySupport {
    public SaludarResponse saludar(String nombre) {
        SaludarRequest sr = new SaludarRequest();
        sr.setNombre(nombre);
        System.out.println(getDefaultUri());
        SaludarResponse saludarResponse = (SaludarResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws/saludos", sr);
        return saludarResponse;
    }
}
