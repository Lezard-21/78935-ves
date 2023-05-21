package mx.uv.consumidor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mx.xlp.SaludarResponse;

@SpringBootApplication
public class ConsumidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumidorApplication.class, args);
	}

	@Bean
	public CommandLineRunner ejecutar(SaludosCliente saludoscliente){
		//*  este código con expresión lambda se ejecuta una vez concluida la carga de la aplicacion */
		return x -> {
			String nombre = "David";
			if (x.length > 0){
				nombre = x[0];
			}
			SaludarResponse sr = saludoscliente.saludar(nombre);
			System.err.println(sr.getRespuesta());
		};
		
		//* en cambio, este codigo se ejecuta durante la carga de la aplicaicón */
		// SaludarResponse sr = saludoscliente.saludar("yo-yo");
		// System.err.println(sr.getRespuesta());
		// return null;
	}

}
