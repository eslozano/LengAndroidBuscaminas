package ec.edu.espol.lenguajes.buscaminas;

import ec.edu.espol.lenguajes.buscaminas.eventos.EventosMenuPartida;
import ec.edu.espol.lenguajes.buscaminas.eventos.EventosMenuPrincipal;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import ec.edu.espol.lenguajes.buscaminas.Tablero;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewGame extends Activity {
	
	public Context contexto;
	protected TextView fontcustom;
	public EditText alto,ancho,minas; 
	public int n1,n2,n3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);			
		
		alto=(EditText)findViewById(R.id.editText3);
        ancho=(EditText)findViewById(R.id.editText2);
        minas=(EditText)findViewById(R.id.editText1);
    
		 
	
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
	public void capturar_contenidos(){
		
	}
	
	

}
