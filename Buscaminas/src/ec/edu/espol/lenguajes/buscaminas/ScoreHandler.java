package ec.edu.espol.lenguajes.buscaminas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.Context;
import android.content.SharedPreferences;

public class ScoreHandler {

	private ScoreHandler() {
		throw new AssertionError();
	}

	public static ArrayList<Score> getScores(DificultadScore dificultad,
			Context context) {
		String nombre = null, nameKey, timeKey;
		int tiempo = 0;
		ArrayList<Score> scores = new ArrayList<Score>();
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		switch (dificultad) {
		case FACIL:
			nameKey = "nameEasy";
			timeKey = "timeEasy";
			break;
		case MEDIO:
			nameKey = "nameNormal";
			timeKey = "timeNormal";
			break;
		case DIFICIL:
			nameKey = "nameHard";
			timeKey = "timeHard";
			break;
		default:
			return null;
		}
		if (nameKey != null && timeKey != null) {
			for (int i = 0; i < 3; i++) {
				Score score = new Score(null, 0, dificultad);
				nombre = prefs.getString(nameKey + i, "anonimo");
				tiempo = prefs.getInt(timeKey + i, 999);
				score.setNombre(nombre);
				score.setTiempo(tiempo);
				scores.add(score);
			}
		}
		return scores;
	}

	public static void setScores(ArrayList<Score> scores, Context context) {
		if (scores != null) {
			String nameKey = null, timeKey = null;
			Score score = scores.get(0);
			SharedPreferences prefs = context.getSharedPreferences("puntajes",
					Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			switch (score.getDificultad()) {
			case FACIL:
				nameKey = "nameEasy";
				timeKey = "timeEasy";
				break;
			case MEDIO:
				nameKey = "nameNormal";
				timeKey = "timeNormal";
				break;
			case DIFICIL:
				nameKey = "nameHard";
				timeKey = "timeHard";
				break;
			default:
				break;
			}
			if (nameKey != null && timeKey != null) {
				for (int i = 0; i < 3; i++) {
					score = scores.get(i);
					editor.putString(nameKey + i, score.getNombre());
					editor.putInt(timeKey + i, score.getTiempo());
				}
				editor.commit();
			}
		}
	}

	public static final void setTempTime(int time, Context context) {
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt("timeTemp", time);
		editor.commit();
	}

	public static int getTempTime(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		return prefs.getInt("timeTemp", 999);
	}

	public static void setTempDificultad(DificultadScore dificultad,
			Context context) {
		String dif;
		switch (dificultad) {
		case DIFICIL:
			dif = "dificil";
			break;
		case FACIL:
			dif = "facil";
			break;
		case INVALIDO:
			dif = "invalido";
			break;
		case MEDIO:
			dif = "medio";
			break;
		default:
			dif = null;
			break;
		}
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		if (dif != null) {
			editor.putString("dificultadTemp", dif);
			editor.commit();
		}

	}

	public static DificultadScore getTempDificultad(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		String dif = prefs.getString("dificultadTemp", null);
		if (dif.equalsIgnoreCase("facil")) {
			return DificultadScore.FACIL;
		} else if (dif.equalsIgnoreCase("medio")) {
			return DificultadScore.MEDIO;
		} else if (dif.equalsIgnoreCase("dificil")) {
			return DificultadScore.DIFICIL;
		}
		return DificultadScore.INVALIDO;
	}

	public static boolean checkCurrentScore(int time, Context context) {
		List<Score> scores = getScores(getTempDificultad(context), context);
		for (Score score : scores) {
			if (score.getTiempo() > time) {
				return true;
			}
		}
		return false;
	}

	public static void setNewScore(String nombre, int time, Context context) {
		ArrayList<Score> scores = getScores(getTempDificultad(context), context);
		Score score = new Score(nombre, time, getTempDificultad(context));
		scores.add(score);
		Collections.sort(scores);
		scores.remove(scores.size() - 1);
		setScores(scores, context);
	}

	public static void deleteScores(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		for (int i = 0; i < 2; i++) {
			editor.remove("nameEasy" + i);
			editor.remove("timeEasy" + i);
			editor.remove("nameNormal" + i);
			editor.remove("timeNormal" + i);
			editor.remove("nameHard" + i);
			editor.remove("timeHard" + i);
		}
		editor.commit();
	}

}
