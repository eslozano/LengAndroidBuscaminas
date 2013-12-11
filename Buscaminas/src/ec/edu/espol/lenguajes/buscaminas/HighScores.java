package ec.edu.espol.lenguajes.buscaminas;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HighScores extends Activity {

	TextView[] tvFacil, tvMedio, tvDificil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		tvFacil = new TextView[3];
		tvMedio = new TextView[3];
		tvDificil = new TextView[3];

		tvFacil[0] = (TextView) this.findViewById(R.id.textViewFacil0);
		tvFacil[1] = (TextView) this.findViewById(R.id.textViewFacil1);
		tvFacil[2] = (TextView) this.findViewById(R.id.textViewFacil2);
		tvMedio[0] = (TextView) this.findViewById(R.id.textViewMedio0);
		tvMedio[1] = (TextView) this.findViewById(R.id.textViewMedio1);
		tvMedio[2] = (TextView) this.findViewById(R.id.textViewMedio2);
		tvDificil[0] = (TextView) this.findViewById(R.id.textViewDificil0);
		tvDificil[1] = (TextView) this.findViewById(R.id.textViewDificil1);
		tvDificil[2] = (TextView) this.findViewById(R.id.textViewDificil2);
		setTVTexts();
	}

	private void setTVTexts() {
		ArrayList<Score> scoresFacil = ScoreHandler.getScores(
				DificultadScore.FACIL, this);
		ArrayList<Score> scoresMedio = ScoreHandler.getScores(
				DificultadScore.MEDIO, this);
		ArrayList<Score> scoresDificil = ScoreHandler.getScores(
				DificultadScore.DIFICIL, this);
		for (int i = 0; i < 3; i++) {
			Score tempFacil = scoresFacil.get(i);
			tvFacil[i].setText(tempFacil.getNombre() + " "
					+ tempFacil.getTiempo());

			Score tempMedio = scoresMedio.get(i);
			tvMedio[i].setText(tempMedio.getNombre() + " "
					+ tempMedio.getTiempo());

			Score tempDificil = scoresDificil.get(i);
			tvDificil[i].setText(tempDificil.getNombre() + " "
					+ tempDificil.getTiempo());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
		return true;
	}

	public void borrarTiempos(View view) {
		ScoreHandler.deleteScores(view.getContext());
		setTVTexts();
	}

}
