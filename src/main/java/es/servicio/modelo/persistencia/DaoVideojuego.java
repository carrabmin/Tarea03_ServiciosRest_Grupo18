package es.servicio.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

import es.servicioRest.modelo.entidad.Videojuego;

@Component
public class DaoVideojuego {

	public List<Videojuego> listaVideojuegos;

	public DaoVideojuego() {

		System.out.println("DaoPersona -> Creando la lista de personas!");
		listaVideojuegos = new ArrayList<Videojuego>();
		Videojuego v1 = new Videojuego(1, "Super Mario 3D World", "Nintendo EAD Tokyo",
				"mismos creadores de Super Mario Galaxy 2 y Super Mario 3D Land");
		Videojuego v2 = new Videojuego(2, "Animal Crossing New Horizons", "Nintendo",
				"fecha de lanzamiento mundial: 20 de marzo de 2020");
		Videojuego v3 = new Videojuego(3, "The Legend of Zelda", "Nintendo",
				"King's Canon ubicado en el Territorio del Norte, Australia");
		Videojuego v4 = new Videojuego(4, "Mario Party Superstars", "Nintendo", "para Nintendo Switch");
		Videojuego v5 = new Videojuego(5, "FIFA 22", " EA Vancouver y EA Rumania",
				"insertar el disco de FIFA 22 para PS4/Xbox One en tu PS5/Xbox Series X");

		listaVideojuegos.add(v1);
		listaVideojuegos.add(v2);
		listaVideojuegos.add(v3);
		listaVideojuegos.add(v4);
		listaVideojuegos.add(v5);
	}

	// Da de alta un videojuego que se le pasa por parámetro

	public void addVideoGame(Videojuego v) {
		listaVideojuegos.add(v);
	}

	// Borra un Videojuego de la lista por id que se le pasa por parámetro

	public Videojuego deleteVideoGame(int id) {
		int indiceEncontrado = 0;
		try {
			for (Videojuego ie : listaVideojuegos) {
				if (ie.getId() == id) {
					indiceEncontrado = listaVideojuegos.indexOf(ie);
				}
			}
			System.out.println("Videojuego con id: " + indiceEncontrado + " ha sido borrado");
			return listaVideojuegos.remove(indiceEncontrado);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Not Found");
			return null;
		}
	}

	// Actualiza datos de una persona que se le pasa por parámetro, se le busca por
	// su ID

	public Videojuego updateVideoGame(Videojuego v) {
		try {
			Videojuego upDateGame = listaVideojuegos.get(v.getId());
			upDateGame.setId(v.getId());
			upDateGame.setName(v.getName());
			upDateGame.setCompany(v.getCompany());
			upDateGame.setNote(v.getNote());

			return upDateGame;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Video game not found");
			return null;
		}
	}

	// Buscar videojuego por su ID

	public Videojuego getVideoGame(int id) {
		int indiceEncontrado = 0;
		try {
			for (Videojuego ie : listaVideojuegos) {
				if (ie.getId() == id) {
					indiceEncontrado = listaVideojuegos.indexOf(ie);
				}
			}
			return listaVideojuegos.get(indiceEncontrado);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Not Found");
			return null;
		}
	}

	// Retorna toda la lista de videojuegos

	public List<Videojuego> listVideoGame() {
		return listaVideojuegos;
	}

}
