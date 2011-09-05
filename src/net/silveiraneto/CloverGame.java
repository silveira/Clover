package net.silveiraneto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.clear_menu_item:
            cloverView.clear(this);
            return true;
        case R.id.about_menu_item:
            about_dialog();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void about_dialog(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(":*")
        .setPositiveButton("*:", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	dialog.cancel();
            }
        });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
}