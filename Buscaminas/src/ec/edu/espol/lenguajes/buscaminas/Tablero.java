package ec.edu.espol.lenguajes.buscaminas;

import java.util.ArrayList;
import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Tablero extends Activity {
	private int columnas, filas, numMinas;
	private ArrayList<Celda> celdaMinas;
	private Celda[][] celdas;
	private EstadoTablero estado;
	
	public Tablero(int columnas, int filas, int numMinas) {
		super();
		this.columnas = columnas;
		this.filas = filas;
		this.numMinas = numMinas;
		this.estado = EstadoTablero.SIN_INICIAR;
		//this.tiempo = 0;
		this.celdaMinas = new ArrayList<Celda>();		 
		inicilizarCeldas();
	}
	
	private void inicilizarCeldas() {
		this.celdas = new Celda[this.filas][this.columnas];
		for(int i=0;i< this.filas;i++)
			for(int j=0;j<this.columnas;j++){
				this.celdas[i][j]=new Celda(i,j);
			}
	}
	
	public void llenarMinas(int fila, int columna){
		Random r=new Random();
		for(int i =0;i<this.numMinas;i++){
			int rndFila, rndColumna;
			do{
				rndColumna = r.nextInt(this.columnas);
				rndFila = r.nextInt(this.filas);				
			}while((rndFila==fila && rndColumna == columna) || !this.celdaMinas.contains(new Celda(rndFila,rndColumna)));
			this.getCelda(rndFila,rndColumna).setContenido(9);//Colocar el valor de mina
			this.celdaMinas.add(this.getCelda(rndFila, rndColumna));
		}
	}

	public Celda getCelda(int fila, int columna) {
		return this.celdas[fila][columna];		
	}
	
	public ArrayList<Celda> getAdyacentes(int fila, int columna){
		ArrayList<Celda> adyacentes = new ArrayList<Celda>();
		for(int i=fila-1;i<fila + 2;i++)
			for(int j=columna-1; j<columna + 2;j++){
				if(i>=0 && i<this.filas && j>=0 && j<this.columnas && (i!=fila || i!=columna)){
					adyacentes.add(this.getCelda(i,j));
				}
			}
		return adyacentes;
	}
	
	public void marcarCelda(int fila, int columna){
		this.celdas[fila][columna].setEstado(columna);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tablero);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tablero, menu);
		return true;
	}

}
