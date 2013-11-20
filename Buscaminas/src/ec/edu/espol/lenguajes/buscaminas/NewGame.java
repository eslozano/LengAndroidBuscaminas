package ec.edu.espol.lenguajes.buscaminas;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.textservice.SentenceSuggestionsInfo;
import android.widget.RadioButton;
import android.widget.TextView;

public class NewGame extends Activity {

	protected TextView fontcustom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
		
		
		
		fontcustom = (RadioButton)findViewById(R.id.principiante);	
		fontcustom.setTypeface(Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf"));
		fontcustom = (RadioButton)findViewById(R.id.intermedio);	
		fontcustom.setTypeface(Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf"));
		fontcustom = (RadioButton)findViewById(R.id.avanzado);	
		fontcustom.setTypeface(Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf"));
		fontcustom = (RadioButton)findViewById(R.id.usuario);	
		fontcustom.setTypeface(Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		return true;
	}
	
	

}
