package ec.edu.espol.lenguajes.buscaminas;

import android.content.Context;
import android.content.SharedPreferences;

public class ScoreHandler {

	private ScoreHandler() {
		throw new AssertionError();
	}

	public static Score getScore(DificultadScore dificultad, Context context) {
		String nombre = null;
		int tiempo = 0;
		Score score = new Score(null, 0, dificultad);
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		switch (dificultad) {
		case FACIL:
			nombre = prefs.getString("nameEasy", "anonimo");
			tiempo = prefs.getInt("timeEasy", 999);
			break;
		case MEDIO:
			nombre = prefs.getString("nameNormal", "anonimo");
			tiempo = prefs.getInt("timeNormal", 999);
			break;
		case DIFICIL:
			nombre = prefs.getString("nameHard", "anonimo");
			tiempo = prefs.getInt("timeHard", 999);
			break;
		default:
			return null;
		}
		score.setNombre(nombre);
		score.setTiempo(tiempo);
		return score;
	}

	public static void setScore(Score score, Context context) {
		if (score != null) {
			String nameKey = null, timeKey = null;
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
				editor.putString(nameKey, score.getNombre());
				editor.putInt(timeKey, score.getTiempo());
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

	public static boolean checkCurrentScore(int time, Context context){
		int tiempo;		
		tiempo = (getScore(getTempDificultad(context),context)).getTiempo();
		return time<tiempo;		
	}
	
	public static void setNewScore(String nombre, int time, Context context){
		Score score = new Score(nombre, time, getTempDificultad(context));
		setScore(score, context);
	}
	
	public static void deleteScores(Context context){
		SharedPreferences prefs = context.getSharedPreferences("puntajes",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.remove("nameEasy");
		editor.remove("timeEasy");
		editor.remove("nameNormal");
		editor.remove("timeNormal");
		editor.remove("nameHard");
		editor.remove("timeHard");
		editor.commit();
	}
	
}
