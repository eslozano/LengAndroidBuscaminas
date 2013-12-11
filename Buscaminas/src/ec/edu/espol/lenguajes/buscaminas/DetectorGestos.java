package ec.edu.espol.lenguajes.buscaminas;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Chronometer;
import android.widget.Toast;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoCelda;
import ec.edu.espol.lenguajes.buscaminas.elementos.EstadoTablero;

class DetectorGestos extends SimpleOnGestureListener {
	Tablero tablero;
	boolean comenzarCron;

	public DetectorGestos(Tablero tablero) {
		this.tablero = tablero;
		this.comenzarCron = false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		if (tablero.estado == EstadoTablero.SIN_TERMINAR
				|| tablero.estado == EstadoTablero.SIN_INICIAR) {
			for (int f = 0; f < Tablero.filas; f++) {
				for (int c = 0; c < Tablero.columnas; c++) {
					if (tablero.celdas[f][c].dentro((int) event.getX(),
							(int) event.getY())) {
						if (tablero.celdas[f][c].getEstado() == EstadoCelda.CUBIERTA) {
							tablero.celdas[f][c].setEstado(EstadoCelda.BANDERA);
						} else if (tablero.celdas[f][c].getEstado() == EstadoCelda.BANDERA) {
							tablero.celdas[f][c]
									.setEstado(EstadoCelda.CUBIERTA);
						}

						if (tablero.estado == EstadoTablero.SIN_INICIAR) {
							comenzarjuego();
						}
						tablero.tableroView.invalidate();
					}
				}
			}
		}
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent event) {

		if (tablero.estado == EstadoTablero.SIN_TERMINAR
				|| tablero.estado == EstadoTablero.SIN_INICIAR) {
			for (int f = 0; f < Tablero.filas; f++) {
				for (int c = 0; c < Tablero.columnas; c++) {
					if (tablero.celdas[f][c].dentro((int) event.getX(), (int) event.getY()) && tablero.celdas[f][c].getEstado()==EstadoCelda.CUBIERTA) {
						tablero.celdas[f][c].setEstado(EstadoCelda.DESCUBIERTA);
						if (tablero.estado == EstadoTablero.SIN_INICIAR) {
							comenzarjuego();
						}
						if (tablero.celdas[f][c].getEstado() != EstadoCelda.BANDERA) {
							if (tablero.celdas[f][c].getContenido() == 80) {
								Toast.makeText(tablero,
										"Booooooooommmmmmmmmmmm",
										Toast.LENGTH_LONG).show();
								tablero.estado = EstadoTablero.PERDIDO;
								tablero.crono.stop();
								tablero.marcarBombas();
							} else if (tablero.celdas[f][c].getContenido() == 0)
								tablero.recorrer(f, c);
						}
						tablero.tableroView.invalidate();

					}
				}
			}
		}
		if (tablero.estado != EstadoTablero.PERDIDO) {
			if (tablero.gano() && tablero.estado == EstadoTablero.GANADO) {
				tablero.crono.stop();
				int time = tablero.getTiempo();
				Toast.makeText(tablero, "Ganaste", Toast.LENGTH_LONG)
						.show();
				tablero.marcarBanderas();
				if (ScoreHandler.checkCurrentScore(time, tablero)) {
					ScoreHandler.setTempTime(time, tablero);
					tablero.abrirDialogo();
				}

			}
		}

		return true;
	}

	public void comenzarjuego() {
		if (tablero.estado == EstadoTablero.SIN_INICIAR) {
			iniciarcronometro();
			tablero.estado = EstadoTablero.SIN_TERMINAR;
			tablero.disponerBombas();
			tablero.contarBombasPerimetro();
		}

	}

	public void iniciarcronometro() {
		if (!this.comenzarCron) {
			tablero.crono = (Chronometer) tablero
					.findViewById(R.id.chronometer1);
			tablero.crono.setBase(SystemClock.elapsedRealtime());
			tablero.crono.start();
			this.comenzarCron = true;
		}
	}

}
