package es.tarea03_spring_serviciorest.modelo.entidad;

//Esta sera la entidad con la que vamos a trabajar en nuestro servicio REST
public class Videojuego{
	
	private int id;
	private String nombre;
	private String empresa;
	private double nota;
	
	public Videojuego() {
		super();
	}	

	public Videojuego(int id, String nombre, String empresa, double nota) {
		this.id = id;
		this.nombre = nombre;
		this.empresa = empresa;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		return "Videojuego [ID: " + id + ", Nombre: " + nombre + ", Empresa: " + empresa + ", Nota: " + nota + "]";
	}

	
	
}
