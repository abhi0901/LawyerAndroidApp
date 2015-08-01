package my.lawyerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Screen2 extends Activity {
DatabaseAdapter db = new DatabaseAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen2, menu);
		return true;
	}
	@SuppressWarnings("deprecation")
	public void savenewContact(View view )
	{
	      AlertDialog ad = new AlertDialog.Builder(Screen2.this).create();
	
		EditText name = (EditText)findViewById(R.id.clientname);
	    EditText email= (EditText)findViewById(R.id.email);
	    EditText phone = (EditText)findViewById(R.id.phone);
	    
	    if(name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || email.getText().toString().isEmpty() )
	      	{
	                
	                	ad.setTitle("EMPTY FIELDS");
	                	ad.setMessage("Please fill data in all fields");
	                			
	                	ad.setButton("OK", new DialogInterface.OnClickListener() {
							@Override
			public void onClick(final DialogInterface dialog, final int id) {
			
			
			}
		});
	                	ad.show();
	               
	    	    	
	    	}
	    else
	    	
	    	{
	    	db.getWritableDatabase();
	  	    db.insert(name.getText().toString(),phone.getText().toString(), email.getText().toString() );
	    name.setText("");
	    email.setText("");
	    phone.setText("");
	    Toast.makeText(this, "Clients Details Added", 1).show();
	    db.close();
	    
	}
	}
	public void cancelBackToScreen2_1(View view )
	{
		Intent i = new Intent(this,Screen2_1.class);
		startActivity(i);
	}
}
