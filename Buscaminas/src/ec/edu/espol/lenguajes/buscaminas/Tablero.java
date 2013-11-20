package ec.edu.espol.lenguajes.buscaminas;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class Tablero extends Activity implements OnTouchListener {
	private Tableroview Tableroview;
	private Celda[][] celdas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tablero);
		
			
		Tableroview = new Tableroview(this);
		Tableroview.setOnTouchListener(this);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.layoutTablero);        
        layout.addView(Tableroview);
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tablero, menu);
		return true;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	 class Tableroview extends View {

	        public Tableroview(Context context) {
	            super(context);
	        }

	        protected void onDraw(Canvas canvas) {
	            canvas.drawRGB(0, 0, 0);
	            int ancho = 0;
	            if (canvas.getWidth() < canvas.getHeight())
	                ancho = Tableroview.getWidth();
	            else
	                ancho = Tableroview.getHeight();
	            int anchocua = ancho / 8;
	            Paint paint = new Paint();
	            paint.setTextSize(20);
	            Paint paint2 = new Paint();
	            paint2.setTextSize(20);
	            paint2.setTypeface(Typeface.DEFAULT_BOLD);
	            paint2.setARGB(255, 0, 0, 255);
	            Paint paintlinea1 = new Paint();
	            paintlinea1.setARGB(255, 255, 255, 255);
	            int filaact = 0;
	            for (int f = 0; f < 8; f++) {
	                for (int c = 0; c < 8; c++) {
	                    celdas[f][c].fijarxy(c * anchocua, filaact, anchocua);
	                    if (celdas[f][c].destapado == false)
	                        paint.setARGB(153, 204, 204, 204);
	                    else
	                        paint.setARGB(255, 153, 153, 153);
	                    canvas.drawRect(c * anchocua, filaact, c * anchocua
	                            + anchocua - 2, filaact + anchocua - 2, paint);
	                    // linea blanca
	                    canvas.drawLine(c * anchocua, filaact, c * anchocua
	                            + anchocua, filaact, paintlinea1);
	                    canvas.drawLine(c * anchocua + anchocua - 1, filaact, c
	                            * anchocua + anchocua - 1, filaact + anchocua,
	                            paintlinea1);

	                    if (celdas[f][c].contenido >= 1
	                            && celdas[f][c].contenido <= 8
	                            && celdas[f][c].destapado)
	                        canvas.drawText(
	                                String.valueOf(celdas[f][c].contenido), c
	                                        * anchocua + (anchocua / 2) - 8,
	                                filaact + anchocua / 2, paint2);

	                    if (celdas[f][c].contenido == 80
	                            && celdas[f][c].destapado) {
	                        Paint bomba = new Paint();
	                        bomba.setARGB(255, 255, 0, 0);
	                        canvas.drawCircle(c * anchocua + (anchocua / 2),
	                                filaact + (anchocua / 2), 8, bomba);
	                    }

	                }
	                filaact = filaact + anchocua;
	            }
	        }
	    }
}
