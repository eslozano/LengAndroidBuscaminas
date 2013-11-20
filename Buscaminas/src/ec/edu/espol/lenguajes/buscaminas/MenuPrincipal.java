package ec.edu.espol.lenguajes.buscaminas;

import ec.edu.espol.lenguajes.buscaminas.eventos.EventosMenuPrincipal;
import ec.edu.espol.lenguajes.buscaminas.R;
import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;

public class MenuPrincipal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		//BOTON START 
		Button 	button_iniciarPartida=	(Button)
		findViewById(R.id.iniciarPartida);
		        
		button_iniciarPartida.setOnClickListener(
		new EventosMenuPrincipal(this, 0));
		        
		//BOTON ALTAS PUNTUACIONES
		        
		Button 	button_puntuaciones = (Button)
		findViewById(R.id.puntuaciones);
		        
		button_puntuaciones.setOnClickListener(
		new EventosMenuPrincipal(this, 1));
		        
		//BOTON ACERCA DE - AQUI VAN LAS INSTRUCCIONES Y LA INFORMACION DE LA VERSION DEL JUEGO
		Button 	button_acercaDe = (Button)
		findViewById(R.id.acercaDe);
		        
		button_acercaDe.setOnClickListener(
		new EventosMenuPrincipal(this, 2));       
	}

	

}
