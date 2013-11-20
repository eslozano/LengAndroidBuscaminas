package ec.edu.espol.lenguajes.buscaminas;

public class Celda {
<<<<<<< HEAD
	private int fila, columna, contenido, ancho;
	
	public void fijarxy(int x,int y, int ancho) {
        this.columna=x;
        this.fila=y;
        this.ancho=ancho;
    }

	private int fila, columna, contenido;
	private EstadoCelda estado;

	public Celda(int fila, int columna) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.contenido = 0;
		this.estado = EstadoCelda.CUBIERTA;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public int getContenido() {
		return contenido;
	}

	public void setContenido(int contenido) {
		this.contenido = contenido;
	}

	public EstadoCelda getEstado() {
		return estado;
	}

	public void setEstado(EstadoCelda estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(Object o) {
		if (o!=null && o instanceof Celda){
			return (this.columna==((Celda)o).columna && this.fila==((Celda)o).fila);
		}
		return false;
	}


}
