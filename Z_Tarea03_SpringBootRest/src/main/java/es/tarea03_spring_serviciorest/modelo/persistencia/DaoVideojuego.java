package es.tarea03_spring_serviciorest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.tarea03_spring_serviciorest.modelo.entidad.Videojuego;

/**
 * Patron DAO (Data Access Object), objeto que se encarga de hacer las consultas
 * a algun motor de persistencia (BBDD, Ficheros, etc). En este caso vamos a 
 * simular que los datos estan guardados en una BBDD trabajando con una lista
 * de objetos cargada en memoria para simplificar el ejemplo.
 * 
 *  
 * Mediante la anotacion @Component, damos de alta un unico objeto de esta clase
 * dentro del contexto de Spring, su ID sera el nombre de la case en notacion
 * lowerCamelCase
 * 
 */
@Component
public class DaoVideojuego {

	public List<Videojuego> listaVideojuegos;
	public int contador;
	
	/**
	 * Cuando se cree el objeto dentro del contexto de Spring, se ejecutara
	 * su constructor, que creara los videojuegos y las metera en una lista
	 * para que puedan ser consumidas por nuestros clientes
	 */
	public DaoVideojuego() {
		System.out.println("DaoVideojuego -> Creando la lista de videojuegos!");
		// Declaramos una lista del tipo ArrayList y añadimos 5 videojuegos de serie:
		listaVideojuegos = new ArrayList<Videojuego>();
		Videojuego v1 = new Videojuego(++contador,"Age of Empires", "Ensemble Studios", 9.2);//ID: 1
		Videojuego v2 = new Videojuego(++contador,"Counter Strike", "Valve", 7.5);//ID: 2
		Videojuego v3 = new Videojuego(++contador,"Colin McRae Rally", "Codemasters", 8.3);//ID: 3
		Videojuego v4 = new Videojuego(++contador,"Snowboard Kids", "Racdym", 8.9);//ID: 4
		Videojuego v5 = new Videojuego(++contador,"Football Manager", "Sports Interactive", 9.6);//ID: 5
		listaVideojuegos.add(v1);
		listaVideojuegos.add(v2);
		listaVideojuegos.add(v3);
		listaVideojuegos.add(v4);
		listaVideojuegos.add(v5);
	}
	
	/**
	 * Metodo que introduce una videojuego al final de la lista
	 * @param v el videojuego que queremos introducir
	 */
	public String darAlta(Videojuego v) {
		// Recorremos el ArrayLista listaVideojuegos y ponemos una condición para que no se 
		// añadan videojuegos con nombre o ID duplicado:
			for (Videojuego p: listaVideojuegos) {
				if (p.getNombre().equalsIgnoreCase(v.getNombre()) || p.getId() == v.getId()) {
					return "No podemos añadir el videojuego - Nombre o ID duplicado";
				}
			}			
		// En el caso de que no se cumpla la condición if se añade el videojuego:
		listaVideojuegos.add(v);
		return "Videojuego añadido con exito";
	}
	
	/**
	 * Método que borra un videojuego según el ID pasado por parámetro
	 * @param id el ID del videojuego a borrar
	 */
		
	public Videojuego darBaja(int id) {
		// Declaramos una variable de posición y la inicializamos a 0:
		int posicionEncontrada = 0;
		// Recorremos el ArrayList listaVideojuegos para averiguar la posición 
		// del objeto videojuego según el ID que le pasamos como parámetro: 
		try {
			for (Videojuego p: listaVideojuegos) {
				if (p.getId() == id) {
					posicionEncontrada = listaVideojuegos.indexOf(p);
				}
			}
			
			// Borramos el videojuego:
			return listaVideojuegos.remove(posicionEncontrada);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Videojuego fuera de rango");
			return null;
		}
	}
	
	/**
	 * Metodo que modifica un videojuego de según su ID
	 * @param id el ID del videojuego a modificar
	 * @param v contiene todos los datos que queremos modificar, pero 
	 * p.getId() contiene el ID del videojuego que queremos modificar
	 * @return el videojuego modificado en caso de que exista, null en caso
	 * contrario
	 */
	
	public Videojuego update(int id, Videojuego v) {
		// Declaramos una variable de posición y la inicializamos a 0:
		int posicionEncontrada = 0;
		// Recorremos el ArrayList listaVideojuegos para averiguar la posición 
		// del objeto videojuego según el ID que le pasamos como parámetro:
		try {
			// Creamos un objeto Videojuego auxiliar - vAux:
			Videojuego vAux = new Videojuego();
			for (Videojuego p: listaVideojuegos) {
				if (p.getId() == id) {
					posicionEncontrada = listaVideojuegos.indexOf(p);
					v.setId(posicionEncontrada);
					vAux = listaVideojuegos.get(v.getId());
					v.setId(id);
					// vAux.setId(id);
					vAux.setNombre(v.getNombre());
					vAux.setEmpresa(v.getEmpresa());
					vAux.setNota(v.getNota());	
				}
			}
			return vAux;
		}catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Videojuego fuera de rango");
			return null;
		}
	}
	
	/*
	public Videojuego update(Videojuego v) {
		try {
			Videojuego vAux = listaVideojuegos.get(v.getId());
			vAux.setNombre(v.getNombre());
			vAux.setEmpresa(v.getEmpresa());
			vAux.setNota(v.getNota());

			return vAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Videojuego fuera de rango");
			return null;
		}
	}
	*/
	
	/**
	 * Devuelve un videojuego a partir de su ID
	 * @param id el ID del videojuego que buscamos
	 * @return el videojuego que tenga el ID introducido como parámetro,
	 * null en caso de que no exista o se haya ido fuera de rango del array
	 */
	
	  public Videojuego get(int id) {
		  // Creamos una lista auxiliar listaVideojuegosId de ArrayList:
		  List<Videojuego> listaVideojuegosId = new ArrayList<Videojuego>();
		  // Recorremos el ArrayList listaVideojuegos y añadimos el videojuego
		  // que coincida con el ID introducido:
		 	  try {
		 		 for (Videojuego p : listaVideojuegos) {
		 			 if (p.getId() == id) {
		 				 listaVideojuegosId.add(p); 
		 			 }
				  }
		 	// Devolvemos el primer elemento de esta listaVideojuegosId 
		 	// auxiliar porque lo habremos añadido al principio de la lista: 
		 		return listaVideojuegosId.get(0);
			  } catch (IndexOutOfBoundsException iobe) {
				  System.out.println("Videojuego fuera de rango");
				  return null;
			  }
		  
	}
	/**
	 * Metodo que devuelve todos los videojuegos por id.
	 * @param id representa el id por el que vamos a hacer la búsqueda
	 * @return una lista con las videojuegos que coincidan en el id.
	 * La lista estará vacia en caso de que no hay coincidencias
	 */
	public List<Videojuego> listById(int id){
		// Instanciamos una lista listaVideojuegosAux de ArrayList
		List<Videojuego> listaVideojuegosAux = new ArrayList<Videojuego>();
		// Recorremos el ArrayList listaVideojuegos y añadimos el videojuego
		// que tenga el ID introducido por parámetro:
		for(Videojuego p : listaVideojuegos) {
			if(p.getId() == id) {
				listaVideojuegosAux.add(p);
			}
		}
		// Devolvemos la lista auxiliar listaVideojuegosAux
		// que contendrá nuestro videojuego:
		return listaVideojuegosAux;
	}
	
	/**
	 * Metodo que devuelve todos los videojuegos del array
	 * @return una lista con todos los videojuegos del array
	 */
	public List<Videojuego> list() {
		return listaVideojuegos;
	}
	
}
