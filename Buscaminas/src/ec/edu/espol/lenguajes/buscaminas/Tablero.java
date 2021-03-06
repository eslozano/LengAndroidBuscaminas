package ec.edu.espol.lenguajes.buscaminas;

import java.util.ArrayList;

import ec.edu.espol.lenguajes.buscaminas.elementos.Celda;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoCelda;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoTablero;
import ec.edu.espol.lenguajes.buscaminas.eventos.EventosMenuPartida;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.annotation.TargetApi;
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
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Tablero extends Activity implements OnClickListener {

	public TableroView tableroView;
	public Celda[][] celdas;
	public static int columnas, filas, numMinas;
	public EstadoTablero estado;
	private String name;
	public Chronometer crono;
	private MotionEvent event;
	private GestureDetector detector;
	private View.OnTouchListener mGestureListener;
	private Bitmap bmpUno, bmpDos, bmpTres, bmpCuatro, bmpCinco, bmpSeis,
			bmpSiete, bmpOcho, bmpBand, bmpBomb;
	Context contexto;

	public void nuevoTablero() {
		this.estado = EstadoTablero.SIN_INICIAR;
		// this.tiempo = 0;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tablero);
		contexto = this;
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

		ImageButton botonReiniciar = (ImageButton) findViewById(R.id.reiniciar);
		botonReiniciar.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View view) {
				Tablero.this.recreate();

			}
		});

		inicializarBmps();
		nuevoTablero();
		inicializarCeldas();

	}

	public void inicializarBmps() {
		bmpUno = BitmapFactory.decodeResource(getResources(), R.drawable.uno);
		bmpDos = BitmapFactory.decodeResource(getResources(), R.drawable.dos);
		bmpTres = BitmapFactory.decodeResource(getResources(), R.drawable.tres);
		bmpCuatro = BitmapFactory.decodeResource(getResources(),
				R.drawable.cuatro);
		bmpCinco = BitmapFactory.decodeResource(getResources(),
				R.drawable.cinco);
		bmpSeis = BitmapFactory.decodeResource(getResources(), R.drawable.seis);
		bmpSiete = BitmapFactory.decodeResource(getResources(),
				R.drawable.siete);
		bmpOcho = BitmapFactory.decodeResource(getResources(), R.drawable.ocho);
		bmpBand = BitmapFactory.decodeResource(getResources(),
				R.drawable.mrsatan);
		bmpBomb = BitmapFactory.decodeResource(getResources(),
				R.drawable.majinboo);
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
		int cantidad = Tablero.numMinas;
		do {
			int fila = (int) (Math.random() * Tablero.filas);
			int columna = (int) (Math.random() * Tablero.columnas);
			if (celdas[fila][columna].getContenido() == 0
					&& celdas[fila][columna].getEstado() != EstadoCelda.DESCUBIERTA) {
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

			int ancho = 0, filaact = 0, alto = 0, margen = 0;
			if (canvas.getWidth() < canvas.getHeight()) {
				ancho = tableroView.getWidth();
				alto = tableroView.getHeight();
			} else {
				ancho = tableroView.getHeight();
				alto = tableroView.getWidth();
			}
			int anchocua;
			if (ancho / Tablero.columnas < alto / Tablero.filas)
				anchocua = (ancho / Tablero.columnas);
			else
				anchocua = alto / Tablero.filas;

			margen = (ancho - (anchocua * Tablero.columnas)) / 2;

			Paint paint = new Paint();
			paint.setTextSize(20);

			Paint paint2 = new Paint();
			paint2.setTextSize(20);
			paint2.setTypeface(Typeface.DEFAULT_BOLD);

			Paint paint3 = new Paint();

			Paint paintlinea1 = new Paint();

			bmpUno = Bitmap.createScaledBitmap(bmpUno, anchocua, anchocua,
					false);
			bmpDos = Bitmap.createScaledBitmap(bmpDos, anchocua, anchocua,
					false);
			bmpTres = Bitmap.createScaledBitmap(bmpTres, anchocua, anchocua,
					false);
			bmpCuatro = Bitmap.createScaledBitmap(bmpCuatro, anchocua,
					anchocua, false);
			bmpCinco = Bitmap.createScaledBitmap(bmpCinco, anchocua, anchocua,
					false);
			bmpSeis = Bitmap.createScaledBitmap(bmpSeis, anchocua, anchocua,
					false);
			bmpSiete = Bitmap.createScaledBitmap(bmpSiete, anchocua, anchocua,
					false);
			bmpOcho = Bitmap.createScaledBitmap(bmpOcho, anchocua, anchocua,
					false);
			bmpBand = Bitmap.createScaledBitmap(bmpBand, anchocua, anchocua,
					false);
			bmpBomb = Bitmap.createScaledBitmap(bmpBomb, anchocua, anchocua,
					false);

			for (int f = 0; f < Tablero.filas; f++) {
				for (int c = 0; c < Tablero.columnas; c++) {
					if (celdas[f][c].getEstado() == EstadoCelda.CUBIERTA)
						paint.setARGB(200, 200, 200, 255);
					else if (celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA) {
						if (celdas[f][c].getContenido() >= 1
								&& celdas[f][c].getContenido() <= 8) {
							paint.setARGB(160, 255, 255, 160);
						} else {
							paint.setARGB(160, 0, 0, 255);
						}
					} else if (celdas[f][c].getEstado() == EstadoCelda.BANDERA) {
						paint.setARGB(209, 209, 209, 0);
					}

					celdas[f][c].fijarxy(margen + (c * anchocua), filaact,
							anchocua);
					canvas.drawRect(margen + (c * anchocua), filaact, margen
							+ c * anchocua + anchocua - 2, filaact + anchocua
							- 2, paint);
					canvas.drawLine(margen + c * anchocua, filaact, margen + c
							* anchocua + anchocua, filaact, paintlinea1);
					canvas.drawLine(margen + c * anchocua + anchocua - 1,
							filaact, margen + c * anchocua + anchocua - 1,
							filaact + anchocua, paintlinea1);

					if (celdas[f][c].getContenido() >= 1
							&& celdas[f][c].getContenido() <= 8
							&& celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA)
						switch (celdas[f][c].getContenido()) {
						case 1: {
							canvas.drawBitmap(bmpUno, margen + c * anchocua,
									filaact, null);
							break;
						}
						case 2: {

							canvas.drawBitmap(bmpDos, margen + c * anchocua,
									filaact, null);
							break;
						}
						case 3: {
							canvas.drawBitmap(bmpTres, margen + c * anchocua,
									filaact, null);
							break;
						}
						case 4: {
							canvas.drawBitmap(bmpCuatro, margen + c * anchocua,
									filaact, null);
							break;
						}
						case 5: {
							canvas.drawBitmap(bmpCinco, margen + c * anchocua,
									filaact, null);
							break;
						}
						case 6: {
							canvas.drawBitmap(bmpSeis, margen + c * anchocua,
									filaact, null);
							break;
						}
						case 7: {
							canvas.drawBitmap(bmpSiete, margen + c * anchocua,
									filaact, null);
							break;
						}
						case 8: {
							canvas.drawBitmap(bmpOcho, margen + c * anchocua,
									filaact, null);
							break;
						}
						}

					/*
					 * canvas.drawText(
					 * String.valueOf(celdas[f][c].getContenido()), margen+c
					 * anchocua + (anchocua / 2) - 8, filaact + anchocua / 2,
					 * paint2);
					 */

					if (celdas[f][c].getEstado() == EstadoCelda.BANDERA) {

						canvas.drawBitmap(bmpBand, margen + c * anchocua,
								filaact, null);

						/*
						 * Paint bandera = new Paint(); bandera.setARGB(255, 0,
						 * 0, 0); canvas.drawCircle(margen+c * anchocua +
						 * (anchocua / 2), filaact + (anchocua / 2), 8,
						 * bandera);
						 */
					}

					if (celdas[f][c].getContenido() == 80
							&& celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA) {
						canvas.drawBitmap(bmpBomb, margen + c * anchocua,
								filaact, null);
						// Paint bomba = new Paint();
						// bomba.setARGB(255, 255, 0, 0);
						// canvas.drawCircle(margen+c * anchocua + (anchocua /
						// 2),
						// filaact + (anchocua / 2), 8, bomba);
					}

				}
				filaact = filaact + anchocua;
			}

		}

	}

	public int getTiempo() {
		int tiempo = 0;
		Chronometer crono = (Chronometer) findViewById(R.id.chronometer1);
		String chronoText = crono.getText().toString();
		String array[] = chronoText.split(":");
		if (array.length == 2) {
			tiempo = Integer.parseInt(array[0]) * 60
					+ Integer.parseInt(array[1]);
		} else if (array.length == 3) {
			tiempo = Integer.parseInt(array[0]) * 60 * 60
					+ Integer.parseInt(array[1]) * 60
					* +Integer.parseInt(array[2]);
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
						ScoreHandler.setNewScore(name,
								ScoreHandler.getTempTime(Tablero.this),
								Tablero.this);

						Tablero.this.finish();

					}
				});
		builder.setTitle("Congratulations, new record!!!");

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
		int celdasSinMina = 0;
		celdasSinMina = ((Tablero.filas * Tablero.columnas) - Tablero.numMinas);

		for (int f = 0; f < Tablero.filas; f++)
			for (int c = 0; c < Tablero.columnas; c++)
				if (celdas[f][c].getEstado() == EstadoCelda.DESCUBIERTA)
					cant++;
		if (cant == celdasSinMina) {
			this.estado = EstadoTablero.GANADO;
			return true;
		} else
			return false;
	}

	public void recorrer(int fil, int col) {
		if (fil >= 0 && fil < filas && col >= 0 && col < columnas) {
			if (celdas[fil][col].getContenido() == 0
					&& celdas[fil][col].getEstado() != EstadoCelda.BANDERA) {
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
					&& celdas[fil][col].getContenido() <= 8
					&& celdas[fil][col].getEstado() != EstadoCelda.BANDERA) {
				celdas[fil][col].setEstado(EstadoCelda.DESCUBIERTA);
				;
			}
		}
	}

	private void inicializarCeldas() {
		this.celdas = new Celda[this.filas][this.columnas];
		for (int i = 0; i < this.filas; i++)
			for (int j = 0; j < this.columnas; j++) {
				this.celdas[i][j] = new Celda(i, j);
			}
	}

	public void marcarBombas() {
		for (int f = 0; f < Tablero.filas; f++) {
			for (int c = 0; c < Tablero.columnas; c++) {
				if (Tablero.this.celdas[f][c].getEstado() == EstadoCelda.CUBIERTA
						&& Tablero.this.celdas[f][c].getContenido() == 80) {
					Tablero.this.celdas[f][c]
							.setEstado(EstadoCelda.DESCUBIERTA);					
				}
			}
		}
	}

	public void marcarBanderas() {
		for (int f = 0; f < Tablero.filas; f++) {
			for (int c = 0; c < Tablero.columnas; c++) {
				if (Tablero.this.celdas[f][c].getEstado() == EstadoCelda.CUBIERTA
						&& Tablero.this.celdas[f][c].getContenido() == 80) {
					Tablero.this.celdas[f][c]
							.setEstado(EstadoCelda.BANDERA);					
				}
			}
		}
	}
}
