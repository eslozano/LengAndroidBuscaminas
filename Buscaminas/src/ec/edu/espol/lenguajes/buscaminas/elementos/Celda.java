package ec.edu.espol.lenguajes.buscaminas.elementos;


public class Celda {

	private int fila, columna, contenido, ancho;
	private EstadoCelda estado;
	
	public Celda(int fila, int columna) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.contenido = 0;
		this.estado = EstadoCelda.CUBIERTA;
	}
	
	public void fijarxy(int x,int y, int ancho) {
        this.columna=x;
        this.fila=y;
        this.ancho=ancho;
    }
	
	public boolean dentro(int xx,int yy) {
        if (xx>=this.columna && xx<=this.columna+ancho && yy>=this.fila && yy<=this.fila+ancho) 
            return true;
        else
            return false;
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
