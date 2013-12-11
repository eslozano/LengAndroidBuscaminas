package ec.edu.espol.lenguajes.buscaminas;


import java.util.ArrayList;

import ec.edu.espol.lenguajes.buscaminas.elementos.Celda;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoCelda;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoTablero;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Tablero extends Activity implements OnClickListener{
	
	 public TableroView tableroView;
	 public Celda[][] celdas;
     public static int columnas, filas, numMinas;
     public EstadoTablero estado;
     private String name;
     public Chronometer crono;
     private MotionEvent event;
     private GestureDetector detector;
     private View.OnTouchListener mGestureListener;
     Context contexto;
     public void nuevoTablero() {             
             this.estado = EstadoTablero.SIN_INICIAR;
             //this.tiempo = 0;
     }       
     
     @Override
 	public void onClick(View v) {
 		// TODO Auto-generated method stub
 		
 	}
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tablero);
		contexto= this;		         
		detector = new GestureDetector(this, new DetectorGestos(this));
		mGestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
		};
		LinearLayout layout = (LinearLayout) findViewById(R.id.layoutTablero);
        tableroView = new TableroView(this);
        
        tableroView.setOnClickListener(this);
        tableroView.setOnTouchListener(mGestureListener);
        
        layout.addView(tableroView); 
        
        nuevoTablero();
        inicializarCeldas() ;       
        
	}
	
	public void contarBombasPerimetro() {
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
	
	public void disponerBombas() {
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
            
            Paint paint3= new Paint();
            
            Paint paintlinea1 = new Paint();
            
            for (int f = 0; f < Tablero.filas; f++) {
                for (int c = 0; c < Tablero.columnas; c++) {
                	if (celdas[f][c].getEstado() == EstadoCelda.CUBIERTA)
                        paint.setARGB(200, 200, 200, 255);
                    else if(celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA){
                        if (celdas[f][c].getContenido() >= 1 && celdas[f][c].getContenido() <= 8){
                        	paint.setARGB(160, 255,255, 160);
                        }else{
                        	paint.setARGB(160, 0,0, 255);
                        }
                    }
                    else if(celdas[f][c].getEstado() == EstadoCelda.BANDERA){
                    	paint.setARGB(209,209,209,0);
                    }
                	
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
                    
                    if(celdas[f][c].getEstado() == EstadoCelda.BANDERA){
                    	Paint bandera = new Paint();
                        bandera.setARGB(255, 0, 0, 0);
                        canvas.drawCircle(c * anchocua + (anchocua / 2),
                                filaact + (anchocua / 2), 8, bandera);                     	
                    }
                                     

                    if (celdas[f][c].getContenido() == 80
                            && celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA) {
                    	//Picture p = new Picture();
                    	//canvas.drawPicture() 
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

	
	

	public int getTiempo() {
		int tiempo=0;
		Chronometer crono = (Chronometer) findViewById(R.id.chronometer1);
		String chronoText = crono.getText().toString();
		String array[] = chronoText.split(":");
		if (array.length == 2) {
			tiempo = Integer.parseInt(array[0]) * 60 
					+ Integer.parseInt(array[1]);
		} else if (array.length == 3) {
			tiempo = Integer.parseInt(array[0]) * 60 * 60  
					+ Integer.parseInt(array[1]) * 60 * 
					+ Integer.parseInt(array[2]) ;
		}		
		return tiempo;
	}


	public void abrirDialogo() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.score_name_layout,
				(ViewGroup) findViewById(R.id.scorenaming));

		AlertDialog.Builder builder = new AlertDialog.Builder(Tablero.this)
				.setView(layout)

				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					

					public void onClick(DialogInterface dialog, int whichButton) {
						/*
						 * Tablero.columnas=ancho; Tablero.filas=alto;
						 * Tablero.numMinas=minas; Intent i = new
						 * Intent(contexto, Tablero.class );
						 * contexto.startActivity(i);
						 */
						ScoreHandler.setNewScore(name,
								ScoreHandler.getTempTime(Tablero.this),
								Tablero.this);
						
						
						Tablero.this.finish();
							
						
						
						
					}
				});

		final AlertDialog alertDialog = builder.create();
		alertDialog.show();

		EditText texto = (EditText) layout.findViewById(R.id.scorename);

		texto.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				name = s.toString();
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

	}


	public boolean gano() {
        int cant = 0;
        int celdasSinMina=0;
        celdasSinMina=((Tablero.filas*Tablero.columnas)-Tablero.numMinas);
        
        for (int f = 0; f < Tablero.filas; f++)
            for (int c = 0; c < Tablero.columnas; c++)
                if (celdas[f][c].getEstado()==EstadoCelda.DESCUBIERTA)
                    cant++;
        if (cant == celdasSinMina ){
        	this.estado=EstadoTablero.GANADO;
            return true;
	    }
        else
            return false;
    }
    
	public void recorrer(int fil, int col) {
        if (fil >= 0 && fil < filas && col >= 0 && col < columnas) {
            if (celdas[fil][col].getContenido() == 0 && celdas[fil][col].getEstado()!=EstadoCelda.BANDERA) {
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
                    && celdas[fil][col].getContenido() <= 8 && celdas[fil][col].getEstado()!=EstadoCelda.BANDERA) {
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
