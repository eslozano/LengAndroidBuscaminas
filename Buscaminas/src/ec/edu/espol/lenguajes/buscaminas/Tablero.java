package ec.edu.espol.lenguajes.buscaminas;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;


public class Tablero extends Activity implements OnTouchListener{	
	private Tableroview Tableroview;
	private Celda[][] celdas;
	private int columnas, filas, numMinas;
	private ArrayList<Celda> celdaMinas;
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
	
	public void llenarMinas(int fila, int columna) {
        Random r = new Random();
        for (int i = 0; i < this.numMinas; i++) {
            int rndFila, rndColumna;
            do {
                rndColumna = r.nextInt(this.columnas);
                rndFila = r.nextInt(this.filas);
            } while ((rndFila == fila && rndColumna == columna) || this.celdaMinas.contains(new Celda(rndFila, rndColumna)));
            this.getCelda(rndFila, rndColumna).setContenido(9);//Colocar el valor de mina
            this.celdaMinas.add(this.getCelda(rndFila, rndColumna));
        }
        for (Celda mina : this.celdaMinas) {
            ArrayList<Celda> adyacentes = this.getAdyacentes(mina);
            System.out.println("Fila: "+ mina.getFila() +"  Columna: " + mina.getColumna() + "  Adyacentes: " + adyacentes.size());
            for (Celda adyacente : adyacentes) {
                adyacente.incrementaContenido();                
            }            
        }
    }

	
	public Celda getCelda(int fila, int columna) {
		return this.celdas[fila][columna];		
	}
	
	public ArrayList<Celda> getAdyacentes(int fila, int columna){
		ArrayList<Celda> adyacentes = new ArrayList<Celda>();
		for(int i=fila-1;i<fila + 2;i++)
			for(int j=columna-1; j<columna + 2;j++){
				if(i >= 0 && i < this.filas && j >= 0 && j < this.columnas && (i != fila || j != columna)){
					adyacentes.add(this.getCelda(i,j));
				}
			}
		return adyacentes;
	}
	
	public ArrayList<Celda> getAdyacentes(Celda celda) {
        return getAdyacentes(celda.getFila(), celda.getColumna());
    }
	
	public void marcarCelda(int fila, int columna){
		this.celdas[fila][columna].setEstado(EstadoCelda.BANDERA);
	}

    public void marcarCelda(Celda celda) {
        marcarCelda(celda.getFila(), celda.getColumna());
    }

    public void descubrirCelda(int fila, int columna) {
        Celda celda = this.getCelda(fila, columna);
        if (this.estado == EstadoTablero.SIN_INICIAR) {
            llenarMinas(fila, columna);
            this.estado = EstadoTablero.SIN_TERMINAR;
        }
        if (celda.getEstado() == EstadoCelda.CUBIERTA) {
            celda.descubrir();
            if (celda.getContenido() == 0) {
                for (Celda adyacente : this.getOcultasAdyacentes(fila, columna)) {
                    descubrirCelda(adyacente);
                }
            } else if (celda.getContenido() == 9) {                
                this.estado = EstadoTablero.PERDIDO;
            }
        }
    }
    
    public void descubrirCelda(Celda celda) {
        descubrirCelda(celda.getFila(), celda.getColumna());
    }
    
    public ArrayList<Celda> getOcultasAdyacentes(int fila, int columna) {
        ArrayList<Celda> ocultas = new ArrayList<Celda>();
        ArrayList<Celda> adyacentes = this.getAdyacentes(fila, columna);
        for (Celda celda : adyacentes) {
            if (celda.getEstado() == EstadoCelda.DESCUBIERTA) {
            }
            ocultas.add(celda);
        }
        return ocultas;
    }
    
    public void checkEstado() {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                if (this.celdas[i][j].getEstado() == EstadoCelda.CUBIERTA && this.celdas[i][j].getContenido() != 9) {
                    this.estado = EstadoTablero.SIN_TERMINAR;
                    return;
                }
            }
        }
        this.estado = EstadoTablero.GANADO;
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tablero, menu);
		return true;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
		
	
	 class Tableroview extends View {

	        public Tableroview(Context context) {
	            super(context);
	        }

	        protected void onDraw(Canvas canvas) {
	            canvas.drawRGB(0, 0, 0);
	            int ancho = 0;
	            if (canvas.getWidth() < canvas.getHeight())
	                ancho = Tableroview.getWidth();
	            else
	                ancho = Tableroview.getHeight();
	            int anchocua = ancho / 8;
	            Paint paint = new Paint();
	            paint.setTextSize(20);
	            Paint paint2 = new Paint();
	            paint2.setTextSize(20);
	            paint2.setTypeface(Typeface.DEFAULT_BOLD);
	            paint2.setARGB(255, 0, 0, 255);
	            Paint paintlinea1 = new Paint();
	            paintlinea1.setARGB(255, 255, 255, 255);
	            int filaact = 0;
	            for (int f = 0; f < 8; f++) {
	                for (int c = 0; c < 8; c++) {
	                    celdas[f][c].fijarxy(c * anchocua, filaact, anchocua);
	                    if (celdas[f][c].destapado == false)
	                        paint.setARGB(153, 204, 204, 204);
	                    else
	                        paint.setARGB(255, 153, 153, 153);
	                    canvas.drawRect(c * anchocua, filaact, c * anchocua
	                            + anchocua - 2, filaact + anchocua - 2, paint);
	                    // linea blanca
	                    canvas.drawLine(c * anchocua, filaact, c * anchocua
	                            + anchocua, filaact, paintlinea1);
	                    canvas.drawLine(c * anchocua + anchocua - 1, filaact, c
	                            * anchocua + anchocua - 1, filaact + anchocua,
	                            paintlinea1);

	                    if (celdas[f][c].contenido >= 1
	                            && celdas[f][c].contenido <= 8
	                            && celdas[f][c].destapado)
	                        canvas.drawText(
	                                String.valueOf(celdas[f][c].contenido), c
	                                        * anchocua + (anchocua / 2) - 8,
	                                filaact + anchocua / 2, paint2);

	                    if (celdas[f][c].contenido == 80
	                            && celdas[f][c].destapado) {
	                        Paint bomba = new Paint();
	                        bomba.setARGB(255, 255, 0, 0);
	                        canvas.drawCircle(c * anchocua + (anchocua / 2),
	                                filaact + (anchocua / 2), 8, bomba);
	                    }

	                }
	                filaact = filaact + anchocua;
	            }
	        }
	    }
}
	
