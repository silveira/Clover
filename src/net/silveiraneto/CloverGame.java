package net.silveiraneto;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class CloverGame extends Activity {
	CloverView cloverView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* Fullscreen and not title */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        /* Call CloverView */ 
        cloverView = new CloverView(this);
        setContentView(cloverView);
        cloverView.requestFocus();
    }
}