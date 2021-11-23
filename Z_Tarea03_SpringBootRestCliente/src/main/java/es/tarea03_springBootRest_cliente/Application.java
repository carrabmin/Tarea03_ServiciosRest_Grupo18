package es.tarea03_springBootRest_cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.tarea03_springBootRest_cliente.entidad.Videojuego;

import es.tarea03_springBootRest_cliente.servicio.ServicioProxyVideojuego;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	/* Primero inyectaremos todos los objetos que necesitamos para
	 * acceder a nuestro ServicioRest y el ServicioProxyVideojuego 
	*/
	@Autowired
	private ServicioProxyVideojuego spp;
	
	/* Tambien necesitaremos acceder al contexto de Spring para parar
	 * la aplicacion, ya que esta app al ser una aplicacion web se
	 * lanzará en un tomcat. De esta manera le decimos a Spring que
	 * nos inyecte su propio contexto.
	*/
	@Autowired
	private ApplicationContext context;
	
	/* En este metodo daremos de alta un objeto de tipo RestTemplate que sera
	 * el objeto mas importante de esta aplicacion. Sera usado por los 
	 * objetos ServicioProxy para hacer las peticiones HTTP a nuestro
	 * servicio REST. Como no podemos anotar la clase RestTemplate porque
	 * no la hemos creado nosotros, usaremos la anotacion @Bean para decirle
	 * a Spring que ejecute este metodo y meta el objeto devuelto dentro
	 * del contexto de Spring con ID "restTemplate" (el nombre del metodo)
	*/
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	//Metodo main que lanza la aplicacion
	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Application.class, args);
	}
	
	//Este metodo es dinamico por la tanto ya podemos acceder a los atributos dinamicos
	@Override
	public void run(String... args) throws Exception {
		
		Scanner leer = new Scanner(System.in);
		
		System.out.println("****** Arrancando el cliente REST ******");
		boolean condicion = true;
		
		// Establecemos un menu dentro de una estructura de control do-while
		// que se ejecutará hasta que cambiemos la condición a false con la opción 6 salir:
		// Llamando a los métodos spp. del ServicioServidorProxy ejecutaremos el programa 
		do {
			System.out.println("\n--- MENÚ CLIENTE: Elige una opción (1-6) ---");
			System.out.println("1. Dar de alta un videojuego");
			System.out.println("2. Dar de baja un videojuego por ID");
			System.out.println("3. Modificar un videojuego por ID");
			System.out.println("4. Obtener un videojuego por ID");
			System.out.println("5. Listar todos los videojuegos");
			System.out.println("6. Salir");
			
			int opcion = leer.nextInt();
			
			// Con un switch y la clase leer de Scanner establecemos la ejecución de 
			// las opciones del menú. Pedimos y recogemos los datos necesarios:
			switch (opcion){
				case 1:
					System.out.println("\n*********** ALTA VIDEOJUEGO ***********");
					System.out.println("Introduzca el ID:");
					String idNuevo01 = leer.next();
					
					int idEntero = Integer.parseInt(idNuevo01);
					System.out.println("Introduzca el nombre:");
					leer.nextLine();
					String nombreNuevo = leer.nextLine();
					
					System.out.println("Introduzca la empresa:");
					String empresaNueva = leer.nextLine();
					System.out.println("Introduca la nota:");
					String notaNueva = leer.nextLine();
					double notaNueva01 = Double.parseDouble(notaNueva);
					
					Videojuego videojuego = new Videojuego();
					videojuego.setId(idEntero);
					videojuego.setNombre(nombreNuevo);
					videojuego.setEmpresa(empresaNueva);
					videojuego.setNota(notaNueva01);
					
					Videojuego vAlta = spp.alta(videojuego);
					System.out.println("run -> Persona dada de alta " + vAlta);
					break;
					
				case 2:
					System.out.println("\n*********** BORRAR VIDEOJUEGO ***********");
					System.out.println("Introduzca el ID del videojuego a borrar:");
					String idBorrado = leer.next();
					int idBorrado01 = Integer.parseInt(idBorrado);
					
					boolean vBorrado = spp.borrar(idBorrado01);
					System.out.println("run -> Videojuego con id: " + idBorrado + " borrado? " + vBorrado);
					break;
					
				case 3:
					System.out.println("\n*********** MODIFICAR VIDEOJUEGO ***********");
					System.out.println("Introduzca el ID del videojuego a modificar:");
					String idModificar01 = leer.next();
					int idModificar02 = Integer.parseInt(idModificar01);
					System.out.println("Introduzca el nombre:");
					leer.nextLine();
					String nombreModificado = leer.nextLine();
					System.out.println("Introduzca la empresa:");
					String empresaModificada = leer.nextLine();
					System.out.println("Introduzca la nota:");
					String notaModificada01 = leer.nextLine();
					double notaModificada02 = Double.parseDouble(notaModificada01);
					
					Videojuego vModificar = new Videojuego();
					vModificar.setId(idModificar02);
					vModificar.setNombre(nombreModificado);
					vModificar.setEmpresa(empresaModificada);
					vModificar.setNota(notaModificada02);
					
					boolean modificada = spp.modificar(vModificar);
					System.out.println("run -> Videojuego modificado? " + modificada);
					break;
					
				case 4:
					System.out.println("\n*********** OBTENER VIDEOJUEGO ***********");
					System.out.println("Introduzca el ID:");
					int idObtener = leer.nextInt();
					
					Videojuego videojuego01= new Videojuego();
					videojuego01 = spp.obtener(idObtener);
					System.out.println("run -> Videojuego con id: " + idObtener + " " + videojuego01);
					break;
					
				case 5:
					System.out.println("\n*********** LISTAR VIDEOJUEGOS ***********");
					List<Videojuego> listaVideojuegos = spp.listar(null);
					//Recorremos la lista y la imprimimos con funciones lambda
					//Tambien podriamos haber usado un for-each clasico de java
					listaVideojuegos.forEach((v) -> System.out.println(v));
					break;
					
				case 6:
					condicion = false;
					System.out.println("******** Parando el cliente REST *********");
					//Mandamos parar nuestra aplicacion Spring Boot
					pararAplicacion();
					break;
				
				default:
					System.out.println("Opción no válida (1-6)");
					
			}

		}while(condicion);
		
		leer.close();
		
	}
	
	public void pararAplicacion() {
		/* Esta aplicacion levanta un servidor web, por lo que tenemos que darle
		 * la orden de pararlo cuando acabemos. Para ello usamos el metodo exit, 
		 * de la clase SpringApplication, que necesita el contexto de Spring y 
		 * un objeto que implemente la interfaz ExitCodeGenerator. 
		 * Podemos usar la funcion lambda "() -> 0" para simplificar 
		*/
		SpringApplication.exit(context, () -> 0);
		
		/* Podriamos hacerlo tambien de una manera clasica, es decir, creando
		 * la clase anonima a partir de la interfaz.
		 * El codigo de abajo sería equivalente al de arriba
		*/
		/*
		SpringApplication.exit(context, new ExitCodeGenerator() {
			
			@Override
			public int getExitCode() {
				return 0;
			}
		});
		*/
	}

}
