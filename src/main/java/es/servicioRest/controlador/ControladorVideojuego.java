package es.servicioRest.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.servicio.modelo.persistencia.DaoVideojuego;
import es.servicioRest.modelo.entidad.Videojuego;

@RestController
public class ControladorVideojuego {

	@Autowired
	private DaoVideojuego daoVideojuego;

	// Mapeamos un GET con un método que busca un videojuego por medio de un ID que
	// se ha
	// configurado en el DaoVideojuego.
	// Para introducir el ID que deseamos buscar lo consegimos através del atributo
	// path con "{id} y la anotación @PathVariable.
	@GetMapping(path = "videojuego/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> getVideoGame(@PathVariable("id") int id) {
		Videojuego v = daoVideojuego.getVideoGame(id);
		if (v != null) {
			return new ResponseEntity<Videojuego>(v, HttpStatus.OK);// 200 OK
		} else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);// 404 NOT FOUND
		}
	}

	// Mappeamos un POST para dar de alta un videojuego que se pasa por parámetro
	// en formato JSON

	@PostMapping(path = "videojuego", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> addVideoGame(@RequestBody Videojuego v) {
		System.out.println("Video Juego dado de alta: " + v);
		daoVideojuego.addVideoGame(v);
		return new ResponseEntity<Videojuego>(v, HttpStatus.CREATED);// 201 CREATED
	}

/*
	// Mapamos otro GET pero con la variante que no usamos el mismo PATH ya que
	// sino nos daría error la aplicación nada más arrancar.
	// Este mapeo es para un método contruido en DaoVideojuego con la funcionalidad
	// de listar todos los videojuegos que hay en la base de datos.
	


	@GetMapping(path="listaVideojuegos", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<Videojuego>> listVideoGame {
		List<Videojuego> lVideojuegos  = null;
		
		lVideojuegos = daoVideojuego.listVideoGame();
		return new ResponseEntity<List<Videojuego>>(lVideojuegos, HttpStatus.OK);
	}
*/
	
	// Mapeamos el PUT con el método upDateVideoGame construido en DaoVideojuego,
	// este métedo busca un viedojuego por medio de un ID y modifica los datos.
	// Con la anotación @PathVariable decimos que el ID que nos pasan por el path
	// es el ID que queremos encontrar.
	// Con la anotación @RequestBody es el viedojuego que queremos meter por medio
	// del body del mensaje HTTP

	@PutMapping(path = "videojuego/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> updateVideoGame(@PathVariable("id") int id, @RequestBody Videojuego v) {
		System.out.println("Identificador de videojuego a modificar: " + id);
		System.out.println("Datos a modificar: " + v);
		Videojuego upDVG = daoVideojuego.updateVideoGame(v);
		if (upDVG != null) {
			return new ResponseEntity<Videojuego>(HttpStatus.OK);// 200 OK
		} else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);// 404 NOT FOUND
		}
	}

	// Mapeamos el DELETE con el método deleteVideoGame construido en DaoVideojuego.
	// Retornamos un mensaje de contastación 200 OK si ha sido borrado y
	// 404 si no se encuentra en la base de datos.

	@DeleteMapping(path = "videojuego/{id}")
	public ResponseEntity<Videojuego> deleteVideoGame(@PathVariable("id") int id) {
		System.out.println("Videojuego con ID: " + id + " borrado");
		Videojuego v = daoVideojuego.deleteVideoGame(id);
		if (v != null) {
			return new ResponseEntity<Videojuego>(v, HttpStatus.OK);// 200 OK
		} else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);// 404 NOT FOUND
		}
	}
}
