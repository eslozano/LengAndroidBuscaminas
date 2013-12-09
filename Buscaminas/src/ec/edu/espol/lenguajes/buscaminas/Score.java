package ec.edu.espol.lenguajes.buscaminas;

public class Score {
	String Nombre;
	int tiempo;
	DificultadScore dificultad;
	
	public Score(String nombre, int tiempo, DificultadScore dificultad) {
		super();
		Nombre = nombre;
		this.tiempo = tiempo;
		this.dificultad = dificultad;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public DificultadScore getDificultad() {
		return dificultad;
	}

	public void setDificultad(DificultadScore dificultad) {
		this.dificultad = dificultad;
	}
	
	
	
}
