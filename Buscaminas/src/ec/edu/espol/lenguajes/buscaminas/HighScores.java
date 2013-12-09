package ec.edu.espol.lenguajes.buscaminas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HighScores extends Activity {

	TextView tvFacil, tvMedio, tvDificil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		tvFacil = (TextView) this.findViewById(R.id.textViewFacil);
		tvMedio = (TextView) this.findViewById(R.id.textViewMedio);
		tvDificil = (TextView) this.findViewById(R.id.textViewDificil);
		setTVTexts();		
	}

	private void setTVTexts() {
		Score temp = ScoreHandler.getScore(DificultadScore.FACIL, this);
		tvFacil.setText(temp.getNombre() + " " + temp.getTiempo());
		
		temp = ScoreHandler.getScore(DificultadScore.MEDIO, this);
		tvMedio.setText(temp.getNombre() + " " + temp.getTiempo());
		
		temp = ScoreHandler.getScore(DificultadScore.DIFICIL, this);
		tvDificil.setText(temp.getNombre() + " " + temp.getTiempo());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
		return true;
	}

	void borrarTiempos(View view) {
		ScoreHandler.deleteScores(view.getContext());
		setTVTexts();
	}

}
