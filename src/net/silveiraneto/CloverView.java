package net.silveiraneto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

/**
 * @author silveira
 *
 */
public class CloverView extends View implements OnTouchListener {	
	Paint paint = new Paint();
	Clover tree;
	
	public CloverView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        clear(context);
	}

	/* Tell the Clover structure to draw itself (recursively). */
    @Override
    public void onDraw(Canvas canvas) {
    	tree.draw(canvas, paint);
    }
	
    
    /* 
     * OnTouchListener interface method.
     * If user touched the screen, we try to split the ball and force update the screen if necessary.
     * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
     */
    public boolean onTouch(View view, MotionEvent event) {
         if(event.getAction() == MotionEvent.ACTION_UP)
        	 if(this.tree.split(event.getX(), event.getY()))
        		 invalidate();
        return true;
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
    
    public void clear(Context context){
    	tree = new Clover();
    	
    	 /* As this.getWidth() would return zero we get the current display properties and assume the 
         * view is occupying the entire screen. */
        Display dsp =  ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        
        /* Initialize the clover in the middle of screen. */
        this.tree.update(0, (dsp.getHeight()-dsp.getWidth())/2, dsp.getWidth());
        
        /* force repaint */
        invalidate();
    }
}

