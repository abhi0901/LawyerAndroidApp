package my.lawyerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Screen1 extends Activity {
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);
       
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.screen1, menu);
        return true;
    }
 
    

  
    public void gotoScreen2_1(View view)
    {
    	Intent i = new Intent(this,Screen2_1.class);
    	startActivity(i);
    }
    public void gotoScreen3(View view)
    {
    	Intent i = new Intent(this,Screen3.class);
    	startActivity(i);
    }
    public void gotoScreen5(View view)
    {	
    	Intent i = new Intent(this,Screen5.class);
	    startActivity(i);
    	
    }
    
}
