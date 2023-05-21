package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	List<ALumno> alumno = new ArrayList<ALumno>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}	
	@RequestMapping("/")
	public String name1(){
		return "Hola kasc";
	}
	//esto crea 
	@RequestMapping(value = "/alumnos", method=RequestMethod.POST)
	public String name2(){
		return "crear rest";
	}
	//esto elimina
	@RequestMapping(value = "/alumnos", method =RequestMethod.DELETE)
	public String name3(){
		return "crear rest";
	}
	//esto devuelve alumnos
	@RequestMapping(value = "/alumnos", method =RequestMethod.GET)
	public List<ALumno> name4(){
		alumno.add(new ALumno("pepito",1));
		alumno.add(new ALumno("Manuela",2));
		alumno.add(new ALumno("El gordo bondiola",3));
		return alumno;
	}
}
