package ec.edu.espol.lenguajes.buscaminas;

public class Celda {
	private int fila, columna, contenido, ancho;
	
	public void fijarxy(int x,int y, int ancho) {
        this.columna=x;
        this.fila=y;
        this.ancho=ancho;
    }
	
	
}
