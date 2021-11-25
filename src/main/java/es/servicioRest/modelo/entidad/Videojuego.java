package es.servicioRest.modelo.entidad;

public class Videojuego {
	private int id;
	private String name;
	private String company;
	private String note;
	
	
	public Videojuego() {
		super();
	}

	public Videojuego(int id, String name, String company, String note) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.note = note;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", name=" + name + ", company=" + company + ", note=" + note + "]";
	}
	
	
}
