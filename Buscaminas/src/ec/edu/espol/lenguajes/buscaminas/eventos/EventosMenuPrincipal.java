package ec.edu.espol.lenguajes.buscaminas.eventos;

import ec.edu.espol.lenguajes.buscaminas.About;
import ec.edu.espol.lenguajes.buscaminas.HighScores;
import ec.edu.espol.lenguajes.buscaminas.NewGame;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class EventosMenuPrincipal implements OnClickListener{
	
	private Context contexto;
	private int opcion;
	
	public EventosMenuPrincipal(Context contexto, int opcion) {
		super();
		this.contexto = contexto;
		this.opcion = opcion;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				switch (this.opcion){
				case 0:{
					//Se crea la ventana para escoger los datos de la nueva partida
					Intent nuevaActividad = new Intent(this.contexto,NewGame.class);
					// se llama a la nueva actividad
					this.contexto.startActivity(nuevaActividad);
					break;
				}
				case 1:{
					//Se crea la actividad que muestra los altos puntajes guardados
					Intent nuevaActividad = new Intent(this.contexto,HighScores.class);
					// se llama a la nueva actividad
					this.contexto.startActivity(nuevaActividad);
					break;
				}
				case 2:{
					//Se crea la actividad que descrribe las instrucciones del juego y la informacion
					Intent nuevaActividad = new Intent(this.contexto,About.class);
					// se llama a la nueva actividad
					this.contexto.startActivity(nuevaActividad);
					break;
				}
		
				}
	}

}
