package ec.edu.espol.lenguajes.buscaminas;

import ec.edu.espol.lenguajes.buscaminas.eventos.EventosMenuPartida;
import ec.edu.espol.lenguajes.buscaminas.eventos.EventosMenuPrincipal;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;

import android.widget.Button;
import android.widget.TextView;

public class NewGame extends Activity {

	protected TextView fontcustom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);			
		
		Button 	buttonPrincipiante=	(Button)
				findViewById(R.id.principiante);				        
		buttonPrincipiante.setOnClickListener(
				new EventosMenuPartida(this, 0));
		
		Button 	buttonIntermedio=	(Button)
				findViewById(R.id.intermedio);				        
		buttonIntermedio.setOnClickListener(
				new EventosMenuPartida(this, 1));
		
		Button 	buttonAvanzado=	(Button)
				findViewById(R.id.avanzado);				        
		buttonAvanzado.setOnClickListener(
				new EventosMenuPartida(this, 2));
		
		Button 	buttonCustom=	(Button)
				findViewById(R.id.usuario);				        
		buttonCustom.setOnClickListener(
				new EventosMenuPartida(this, 3));	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		return true;
	}
	
	

}
