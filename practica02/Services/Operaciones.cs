using System;
using WSDL.mensajes;
using System.Collections.Generic;
namespace WSDL.operaciones{
    public class Operaciones : Mensajes{
      List<string> n = new List<string>();
        public string Saludar(string nombre){
        String msj = "Hola" + nombre;
        n.Add(nombre);
        return msj;
        //implementar para agregar los nombres a una coleccion
      } 
      //opcion en numero y regresa la posicion del arreglo 
      public String Mostrar(int id){
        //String cad = "";
        if((n.Count())==0){
          return "Error no hay ninguna persona en la lista :(";
        }
       for (int i = 0; i < n.Count(); i++)
       {
        if(i == id){
          return "El id "+id + "le pertenece a" +n[i];
        }
        //cad = "persona numero.-"+i+"  || Nombre:"+n[i]+"\n";
       }
       return "Error el id "+id+" no pertenece a ninguna persona";
      }
    }
}
