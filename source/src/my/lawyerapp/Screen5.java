package my.lawyerapp;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Screen5 extends Activity {
RadioButton yesradio ;
RadioButton noradio ;
EditText lawyernametext;
EditText mobilenotext;
int BooleansendSmS ;

Bundle b =new Bundle();

DatabaseAdapter db = new DatabaseAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen5);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen5, menu);
		return true;
	}

	public void setBoolOne(View view )
	
	{     
		Intent myIntent = new Intent(this, MyServices.class);
	    PendingIntent  pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       
       Calendar calendar = Calendar.getInstance();
       calendar.setTimeInMillis(System.currentTimeMillis());
       calendar.set(Calendar.HOUR_OF_DAY, 20);
       
       alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY, pendingIntent);
      
		BooleansendSmS = 1;
		noradio = (RadioButton)findViewById(R.id.noradio);
		if(noradio.isChecked())
		{
		       noradio.setChecked(false);
		       
		}
		
		
		}
	public void setBoolZero(View view )
	{   BooleansendSmS = 0;
		yesradio = (RadioButton)findViewById(R.id.yesradio);
		if(yesradio.isChecked())
		{
		     yesradio.setChecked(false);
		     
		}
		 Intent myIntent = new Intent(this, MyServices.class);
		 stopService(myIntent);
	}
	
	public void saveMobileNum(View view)
	{
      
	     
    	db.getWritableDatabase();
		mobilenotext = (EditText)findViewById(R.id.Mobilenumbertext);
		lawyernametext = (EditText)findViewById(R.id.LawyernameText);
		if(mobilenotext.getText().toString().isEmpty())
		{
	    Toast.makeText(this,"Please enter a valid phone number in the mentioned field", Toast.LENGTH_LONG).show();
		}
		else
		{
		db.insertPhoneno(mobilenotext.getText().toString(),BooleansendSmS);
		db.close();
		mobilenotext.setText("");
		lawyernametext.setText("");
		Toast.makeText(this,"Your settings has been saved", Toast.LENGTH_LONG).show();
		} 
	
	}
	}
