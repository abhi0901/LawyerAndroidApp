package my.lawyerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Screen2_2 extends Activity {

	DatabaseAdapter db = new DatabaseAdapter(this);
	EditText name;
	EditText phone;
	EditText email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen2_2);
		name= (EditText)findViewById(R.id.updatename);
		phone = (EditText)findViewById(R.id.updatephone);
		email = (EditText)findViewById(R.id.updateemail);
		String namestring = getIntent().getStringExtra("name");
		String phonestring = getIntent().getStringExtra("phone");
		String emailstring = getIntent().getStringExtra("email");
		
		name.setText(namestring);
		phone.setText(phonestring);
		email.setText(emailstring);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen2_2, menu);
		return true;
	}
    public void updateContact(View view)
    {  
    	name= (EditText)findViewById(R.id.updatename);
    	phone = (EditText)findViewById(R.id.updatephone);
	    email = (EditText)findViewById(R.id.updateemail);
    	Long id = getIntent().getLongExtra("id",0);
    	db.updateRecords(id, name.getText().toString(), phone.getText().toString(), email.getText().toString());
      Toast.makeText(this,"Contact details Updated",Toast.LENGTH_LONG).show();
   name.setText("");
   phone.setText("");
   email.setText("");
    
    
    }
    public void cancelgoBacktoScreen2_1(View view)
    {
    	Intent i = new Intent(this,Screen2_1.class);
    	startActivity(i);
    }
    public void deleteContact(View v)
    {
    	name= (EditText)findViewById(R.id.updatename);
    	phone = (EditText)findViewById(R.id.updatephone);
	    email = (EditText)findViewById(R.id.updateemail);
    	Long id = getIntent().getLongExtra("id",0);
    
    	db.deleteRecordById(id);
    	Toast.makeText(this,"Contact details Updated",Toast.LENGTH_LONG).show();
    	   name.setText("");
    	   phone.setText("");
    	   email.setText("");
    	    
    }
	
}
