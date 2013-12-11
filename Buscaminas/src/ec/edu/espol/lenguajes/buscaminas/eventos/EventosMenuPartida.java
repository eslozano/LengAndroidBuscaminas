package ec.edu.espol.lenguajes.buscaminas.eventos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import ec.edu.espol.lenguajes.buscaminas.DificultadScore;
import ec.edu.espol.lenguajes.buscaminas.NewGame;
import ec.edu.espol.lenguajes.buscaminas.R;
import ec.edu.espol.lenguajes.buscaminas.ScoreHandler;
import ec.edu.espol.lenguajes.buscaminas.Tablero;

public class EventosMenuPartida   implements OnClickListener{

	private int opcion;
	private Context contexto;
	private NewGame c;
	public static int filadet, columnadet;
	public AlertDialog.Builder alertDialogBuilder;
	
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
			ScoreHandler.setTempDificultad(DificultadScore.FACIL, this.contexto);
			Tablero.filas=5;
			Tablero.columnas=5;
			Tablero.numMinas=5;
			Intent nuevaActividad = new Intent(this.contexto,Tablero.class);
			this.contexto.startActivity(nuevaActividad);
			break;
		}
		case 1:{
			ScoreHandler.setTempDificultad(DificultadScore.MEDIO, this.contexto);
			Tablero.filas=8;
			Tablero.columnas=8;
			Tablero.numMinas=20;
			Intent nuevaActividad = new Intent(this.contexto,Tablero.class);
			this.contexto.startActivity(nuevaActividad);
			break;
		}
		case 2:{
			ScoreHandler.setTempDificultad(DificultadScore.MEDIO, this.contexto);
			Tablero.filas=11;
			Tablero.columnas=11;
			Tablero.numMinas=80;
			Intent nuevaActividad = new Intent(this.contexto,Tablero.class);
			this.contexto.startActivity(nuevaActividad);
			break;
		}
		case 3:{
			
			
		        	break;
        	}
		}
	}
}
