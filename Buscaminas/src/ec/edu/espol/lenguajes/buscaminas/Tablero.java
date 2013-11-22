package ec.edu.espol.lenguajes.buscaminas;


import java.util.ArrayList;

import ec.edu.espol.lenguajes.buscaminas.elementos.Celda;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoCelda;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoTablero;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import ec.edu.espol.lenguajes.buscaminas.R;

public class Tablero extends Activity implements OnTouchListener{
	
	 private TableroView tableroView;
	 private Celda[][] celdas;
     public static int columnas, filas, numMinas;
     private EstadoTablero estado;
     
     public void nuevoTablero() {             
             this.estado = EstadoTablero.SIN_INICIAR;
             //this.tiempo = 0;
     }       
     

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tablero);		
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.layoutTablero);
        tableroView = new TableroView(this);
        tableroView.setOnTouchListener(this);   
        layout.addView(tableroView); 
        
        Chronometer crono = (Chronometer) findViewById(R.id.chronometer1);
        crono.setBase(SystemClock.elapsedRealtime());
        crono.start();
        
        
        nuevoTablero();
        inicializarCeldas() ;
       //this.disponerBombas();
       //this.contarBombasPerimetro();
        
	}
	
	private void contarBombasPerimetro() {
        for (int f = 0; f < Tablero.filas; f++) {
            for (int c = 0; c < Tablero.columnas; c++) {
                if (celdas[f][c].getContenido() == 0) {
                    int cant = contarCoordenada(f, c);
                    celdas[f][c].setContenido(cant);
                }
            }
        }
    }
	
	int contarCoordenada(int fila, int columna) {
        int total = 0;
        if (fila - 1 >= 0 && columna - 1 >= 0) {
            if (celdas[fila - 1][columna - 1].getContenido() == 80)
                total++;
        }
        if (fila - 1 >= 0) {
            if (celdas[fila - 1][columna].getContenido() == 80)
                total++;
        }
        if (fila - 1 >= 0 && columna + 1 < Tablero.columnas) {
            if (celdas[fila - 1][columna + 1].getContenido() == 80)
                total++;
        }

        if (columna + 1 < Tablero.columnas) {
            if (celdas[fila][columna + 1].getContenido() == 80)
                total++;
        }
        if (fila + 1 < Tablero.filas && columna + 1 < Tablero.columnas) {
            if (celdas[fila + 1][columna + 1].getContenido() == 80)
                total++;
        }

        if (fila + 1 < Tablero.filas) {
            if (celdas[fila + 1][columna].getContenido() == 80)
                total++;
        }
        if (fila + 1 < Tablero.filas && columna - 1 >= 0) {
            if (celdas[fila + 1][columna - 1].getContenido() == 80)
                total++;
        }
        if (columna - 1 >= 0) {
            if (celdas[fila][columna - 1].getContenido() == 80)
                total++;
        }
        return total;
    }
	
	private void disponerBombas() {
        int cantidad = Tablero.numMinas ;
        do {
            int fila = (int) (Math.random() * Tablero.filas);
            int columna = (int) (Math.random() * Tablero.columnas);
            if (celdas[fila][columna].getContenido() == 0 && celdas[fila][columna].getEstado()!=EstadoCelda.DESCUBIERTA ) {
                celdas[fila][columna].setContenido(80);
                cantidad--;
            }
        } while (cantidad != 0);
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tablero, menu);
		return true;
	}
	
	
	class TableroView extends View {

        public TableroView(Context context) {
            super(context);
        }
        
        protected void onDraw(Canvas canvas) {
        	
            int ancho = 0, filaact = 0;
            if (canvas.getWidth() < canvas.getHeight())
                ancho = tableroView.getWidth();
            else
                ancho = tableroView.getHeight();
            
            int anchocua = ancho / Tablero.columnas;
                        
            Paint paint = new Paint();
            paint.setTextSize(20);
            
            Paint paint2 = new Paint();
            paint2.setTextSize(20);
            paint2.setTypeface(Typeface.DEFAULT_BOLD);
            
            Paint paintlinea1 = new Paint();
            
            for (int f = 0; f < Tablero.filas; f++) {
                for (int c = 0; c < Tablero.columnas; c++) {
                	if (celdas[f][c].getEstado() == EstadoCelda.CUBIERTA)
                        paint.setARGB(153, 204, 204, 204);
                    else if(celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA)
                        paint.setARGB(255, 153, 153, 153);
                	
                	celdas[f][c].fijarxy(c * anchocua, filaact, anchocua);
                	canvas.drawRect(c * anchocua, filaact, c * anchocua
                             + anchocua - 2, filaact + anchocua - 2, paint);
                	canvas.drawLine(c * anchocua, filaact, c * anchocua
                             + anchocua, filaact, paintlinea1);
                    canvas.drawLine(c * anchocua + anchocua - 1, filaact, c
                             * anchocua + anchocua - 1, filaact + anchocua,
                             paintlinea1);    
                    
                    
                    if (celdas[f][c].getContenido() >= 1
                            && celdas[f][c].getContenido() <= 8
                            && celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA)
                        canvas.drawText(
                                String.valueOf(celdas[f][c].getContenido()), c
                                        * anchocua + (anchocua / 2) - 8,
                                filaact + anchocua / 2, paint2);

                    if (celdas[f][c].getContenido() == 80
                            && celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA) {
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(this.estado==EstadoTablero.SIN_INICIAR ){
			for (int f = 0; f < Tablero.filas; f++) {
                for (int c = 0; c < Tablero.columnas; c++) {
                    if (celdas[f][c].dentro((int) event.getX(),(int) event.getY())) {
                    	celdas[f][c].setEstado(EstadoCelda.DESCUBIERTA);                    	
                    }
                }
			}
			this.disponerBombas();
		    this.contarBombasPerimetro();			
			this.estado=EstadoTablero.SIN_TERMINAR;
		}
			
		if (this.estado==EstadoTablero.SIN_TERMINAR  )
            for (int f = 0; f < Tablero.filas; f++) {
                for (int c = 0; c < Tablero.columnas; c++) {
                    if (celdas[f][c].dentro((int) event.getX(),(int) event.getY())) {
                        celdas[f][c].setEstado(EstadoCelda.DESCUBIERTA);
                        if (celdas[f][c].getContenido() == 80) {
                            Toast.makeText(this, "Booooooooommmmmmmmmmmm",Toast.LENGTH_LONG).show();
                            this.estado = EstadoTablero.PERDIDO;
                        } else if (celdas[f][c].getContenido() == 0)
                            recorrer(f, c);
                        tableroView.invalidate();
                    }
                }
            }
        if (gano() && estado==EstadoTablero.GANADO) {
            Toast.makeText(this, "Ganaste", Toast.LENGTH_LONG).show();
        }

        return true;
	}     
	
	private boolean gano() {
        int cant = 0;
        int celdasSinMina=0;
        celdasSinMina=((Tablero.filas*Tablero.columnas)-Tablero.numMinas);
        for (int f = 0; f < Tablero.filas; f++)
            for (int c = 0; c < Tablero.columnas; c++)
                if (celdas[f][c].getEstado()==EstadoCelda.DESCUBIERTA)
                    cant++;
        if (cant == celdasSinMina )
            return true;
        else
            return false;
    }
    
	private void recorrer(int fil, int col) {
        if (fil >= 0 && fil < filas && col >= 0 && col < columnas) {
            if (celdas[fil][col].getContenido() == 0) {
            	celdas[fil][col].setEstado(EstadoCelda.DESCUBIERTA);
            	celdas[fil][col].setContenido(50);
                recorrer(fil, col + 1);
                recorrer(fil, col - 1);
                recorrer(fil + 1, col);
                recorrer(fil - 1, col);
                recorrer(fil - 1, col - 1);
                recorrer(fil - 1, col + 1);
                recorrer(fil + 1, col + 1);
                recorrer(fil + 1, col - 1);
            } else if (celdas[fil][col].getContenido() >= 1
                    && celdas[fil][col].getContenido() <= 8) {
            	celdas[fil][col].setEstado(EstadoCelda.DESCUBIERTA);;
            }
        }
    }
		
	
	private void inicializarCeldas() {
        this.celdas = new Celda[this.filas][this.columnas];
        for(int i=0;i< this.filas;i++)
                for(int j=0;j<this.columnas;j++){
                        this.celdas[i][j]=new Celda(i,j);
                }
}
}
