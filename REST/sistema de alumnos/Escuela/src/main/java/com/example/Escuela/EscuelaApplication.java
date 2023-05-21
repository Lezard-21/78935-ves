package com.example.Escuela;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EscuelaApplication {

	ArrayList<pojoSalon> pojos = new ArrayList<>();
	ArrayList<pojoProfesor> pojosProfesores = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(EscuelaApplication.class, args);
	}
	@RequestMapping("/")
	public String returnRoot(){
		return "Esta es la raiz";
	}

	//poner el request body = el request body es el pay load
	@RequestMapping(value = "/salones", method=RequestMethod.POST)
	public String requestPojoSalon(@RequestBody pojoSalon salon){
		pojos.add(salon);
		return salon.toString()+"\n";
	}
	//esto elimina
	@RequestMapping(value = "/salones/{numero}", method =RequestMethod.DELETE)
	public String deletePojoSalon(@PathVariable int numero){
		pojoSalon temp = null;
		for (pojoSalon p : pojos) {
			if(p.getNumero() == numero)temp = p;
		}
		if(temp!=null){
			pojos.remove(temp);
			return temp.toString()+"\n";
		}
		return "Error numero de salon no registrado";
		
	}
	//esto devuelve alumnos
	@RequestMapping(value = "/salones", method =RequestMethod.GET)
	public List<pojoSalon> getPojos(){
		return pojos;
	}

	//esto devuelve el salon con el numero enviado
	@RequestMapping(value = "/salones/{numero}", method =RequestMethod.GET)
	public String getPojosByID(@PathVariable int numero){
		pojoSalon temp = new pojoSalon();
		for (pojoSalon p : pojos) {
			if(p.getNumero() == numero)temp = p;
		}
		return temp.toString()+"\n";
	}

	//modifica un salon- put remplaza el pojo
	@RequestMapping(value = "/salones/{numero}", method =RequestMethod.PUT)
	public String PutPojos(@PathVariable int numero,@RequestBody pojoSalon salon){
		int cont = 0;
		while(!salon.getNumero().equals(pojos.get(cont).getNumero())){
			cont ++;
		}
		pojos.set(cont, salon);
		return pojos.get(cont).toString()+"\n";
	}

	//modifica un salon- put remplaza el valor del pojo
	@RequestMapping(value = "/salones/{numero}", method =RequestMethod.PATCH)
	public String PatchPojos(@PathVariable int numero,@RequestBody pojoSalon salon){
		int cont = 0;
		while(!salon.getNumero().equals(pojos.get(cont).getNumero())){
			cont ++;
		}
		pojos.get(cont).setNumero(salon.getNumero());
		pojos.get(cont).setEdificio(salon.getEdificio());
		return pojos.get(cont).toString()+"\n";
	}

	/****************************** Metodos Profesores ***************************/
	//estructura para mandar arrays de salones
	//[{"":"","":""},{}]

	//regresa toda la lista de profesores
	@RequestMapping(value = "/salones/profes", method =RequestMethod.GET)
	public List<pojoProfesor> getProfes(){
		return pojosProfesores;
	}

	//poner el request body
	@RequestMapping(value = "/salones/{numero}/profes", method=RequestMethod.POST)
	public String requestPojoProfesor(@RequestBody pojoProfesor profesor, @PathVariable int numero){
		pojosProfesores.add(profesor);
		return profesor.toString()+"\n";
	}

}
