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
import ec.edu.espol.lenguajes.buscaminas.NewGame;
import ec.edu.espol.lenguajes.buscaminas.R;
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
			
			
		/*	
			
			LayoutInflater li = LayoutInflater.from(contexto);
			View prompt = li.inflate(R.layout.custom, null);
			alertDialogBuilder = new AlertDialog.Builder(contexto);
			alertDialogBuilder.setView(prompt);
			
			alertDialogBuilder.setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    		
    			public void onClick(DialogInterface dialog,int id) {
    				
    				
    				
    				Intent i = new Intent(contexto, Tablero.class );
    		        contexto.startActivity(i);
    			}
    		})
    		.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog,int id) {
    		// Cancelamos el cuadro de dialogo
    		  dialog.cancel();
    		}
    		});
    		
        	AlertDialog alertDialog = alertDialogBuilder.create();
    		alertDialog.show();
    	*/
        	break;
        	}
		}
	}
}
