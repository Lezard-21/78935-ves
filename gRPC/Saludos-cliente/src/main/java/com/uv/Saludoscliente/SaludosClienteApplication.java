package com.uv.Saludoscliente;
import io.grpc.*;
public class SaludosClienteApplication {

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
				.usePlaintext()
				.build();

		HelloServiceGrpc.HelloServiceBlockingStub stub
				= HelloServiceGrpc.newBlockingStub(channel);


			HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
					.setTitle("Enchilada")
					.setDescription("Enchilada con pollo")
					.setUrl("https://www.google.com")
					.build());

		System.out.println("La respuesta es: " + helloResponse);

		channel.shutdown();
	}

}
