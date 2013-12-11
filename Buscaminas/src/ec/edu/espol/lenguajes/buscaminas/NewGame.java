package ec.edu.espol.lenguajes.buscaminas;

import ec.edu.espol.lenguajes.buscaminas.eventos.EventosMenuPartida;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewGame extends Activity {
	
	public Context contexto;
	protected TextView fontcustom;
	public AlertDialog.Builder alertDialogBuilder;
	public int alto=9, ancho=9, minas=10,var=658;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);			
		contexto=this;
		
		 
	
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
		buttonCustom.setOnClickListener(new Button.OnClickListener() {  
        public void onClick(View v)
            {
                //perform action
        		abrir_dialogos();
            }
         });
		
	}
				
				@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		return true;
	}
				
	public void abrir_dialogos(){
		 LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     final View layout = inflater.inflate(R.layout.custom, (ViewGroup) findViewById(R.id.toastLayout));
	     
	     AlertDialog.Builder builder = new AlertDialog.Builder(NewGame.this).setView(layout)
	     
	     .setPositiveButton("OK",
	                new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,
	                    int whichButton) {
	            	ScoreHandler.setTempDificultad(DificultadScore.INVALIDO, contexto);
	            	Tablero.columnas=ancho;
		        	Tablero.filas=alto;
		        	Tablero.numMinas=minas;
		        	Intent i = new Intent(contexto, Tablero.class );
			        contexto.startActivity(i);
		            
	            }
	        });
	     
	     
	     
	     
	     
	     
	     final AlertDialog alertDialog = builder.create();
	     alertDialog.show();
	     
	     SeekBar sbalto = (SeekBar)layout.findViewById(R.id.seekBaralto);  
	     final SeekBar sbancho = (SeekBar)layout.findViewById(R.id.seekBarancho);  
	     final SeekBar sbminas = (SeekBar)layout.findViewById(R.id.seekBarminas);
	     final TextView txtalto = (TextView)layout.findViewById(R.id.textView4);
	     final TextView txtancho = (TextView)layout.findViewById(R.id.textViewancho);
	     final TextView txtminas = (TextView)layout.findViewById(R.id.textViewminas);
	     
	     sbminas.setEnabled(false);
	     sbancho.setEnabled(false);
 
	     
	     
	     

	     sbalto.setProgress(1); //Fijamos SeekBar al un valor anterior...
	     sbancho.setProgress(1);
	     sbminas.setProgress(1);
	     sbalto.setMax(15);
	     sbancho.setMax(21);
	     sbminas.setMax(var);
	     
	     
	     
	     
	     
	     

	     sbalto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	         
	    	 
	    	 
	    	 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
	                       //Log.d("MENSAJE", "On progress seekbar");
	          int valor = progress; //grabamos el nuevo valor
	          txtalto.setText(progress + 9 + ""); 
	                //Hacer lo que queramos con con el nuevo valor 
	          alto=Integer.parseInt(txtalto.getText().toString());
	          sbminas.setMax((alto * ancho)-62);
	          sbancho.setEnabled(true);

	         }
	    	 
	    	 
	    	 
	    	 
	   public void onStartTrackingTouch(SeekBar seekBar) {
	    // TODO Auto-generated method stub
	   }
	   public void onStopTrackingTouch(SeekBar seekBar) {
	    // TODO Auto-generated method stub
	   }
	     });
	     
	     
	     sbancho.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	         
	    	 
	    	 
	    	 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
	                       //Log.d("MENSAJE", "On progress seekbar");
	          int valor = progress; //grabamos el nuevo valor
	          txtancho.setText(progress + 9 + ""); 
	                //Hacer lo que queramos con con el nuevo valor 
	          ancho=Integer.parseInt(txtancho.getText().toString());
	          sbminas.setEnabled(true);
	          sbminas.setMax((alto * ancho)-62);
	 	     

	         }
	    	 
	    	 
	    	 
	    	 
	   public void onStartTrackingTouch(SeekBar seekBar) {
	    // TODO Auto-generated method stub
	   }
	   public void onStopTrackingTouch(SeekBar seekBar) {
	    // TODO Auto-generated method stub
	   }
	     });
	     
	     
	     sbminas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	         
	    	 
	    	 
	    	 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
	                       //Log.d("MENSAJE", "On progress seekbar");
	          int valor = progress; //grabamos el nuevo valor
	          
	          txtminas.setText(progress + 10 + ""); 
	                //Hacer lo que queramos con con el nuevo valor 
	          minas=Integer.parseInt(txtminas.getText().toString());
	          

	         }
	    	 
	    	 
	    	 
	    	 
	   public void onStartTrackingTouch(SeekBar seekBar) {
	    // TODO Auto-generated method stub
	   }
	   public void onStopTrackingTouch(SeekBar seekBar) {
	    // TODO Auto-generated method stub
	   }
	     });
	     
	    
	}
	
	

}
