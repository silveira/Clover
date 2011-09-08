package net.silveiraneto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

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
    	  final TextView message = new TextView(this);
    	  final SpannableString s = 
    	               new SpannableString(this.getText(R.string.about_text));
    	  Linkify.addLinks(s, Linkify.WEB_URLS);
    	  message.setText(s);
    	  message.setMovementMethod(LinkMovementMethod.getInstance());

    	  AlertDialog about = new AlertDialog.Builder(this)
    	  	.setTitle(R.string.app_name)
    	  	.setIcon(android.R.drawable.ic_dialog_info)
    	  	.setPositiveButton(this.getString(android.R.string.ok), null)
    	  	.setView(message)
    	  	.create();
    	  about.show();
    }
        
}