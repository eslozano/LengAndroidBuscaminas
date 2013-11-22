package ec.edu.espol.lenguajes.buscaminas.eventos;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import ec.edu.espol.lenguajes.buscaminas.NewGame;
import ec.edu.espol.lenguajes.buscaminas.Tablero;

public class EventosMenuPartida  implements OnClickListener{

	private int opcion;
	private Context contexto;
	public static int filadet, columnadet;
	
	public EventosMenuPartida(Context contexto, int opcion) {
		super();
		this.contexto = contexto;
		this.opcion=opcion;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (this.opcion){
		case 0:{
			Tablero.filas=5;
			Tablero.columnas=5;
			Tablero.numMinas=8;
			Intent nuevaActividad = new Intent(this.contexto,Tablero.class);
			this.contexto.startActivity(nuevaActividad);
			break;
		}
		case 1:{
			Tablero.filas=8;
			Tablero.columnas=8;
			Tablero.numMinas=10;
			Intent nuevaActividad = new Intent(this.contexto,Tablero.class);
			this.contexto.startActivity(nuevaActividad);
			break;
		}
		case 2:{
			Tablero.filas=11;
			Tablero.columnas=11;
			Tablero.numMinas=20;
			Intent nuevaActividad = new Intent(this.contexto,Tablero.class);
			this.contexto.startActivity(nuevaActividad);
			break;
		}
		case 3:{
			Tablero.filas=50;
			Tablero.columnas=50;
			Intent nuevaActividad = new Intent(this.contexto,Tablero.class);
			this.contexto.startActivity(nuevaActividad);
			break;
		}	
			
		}
	}

}