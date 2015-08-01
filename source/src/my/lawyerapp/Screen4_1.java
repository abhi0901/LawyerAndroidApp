package my.lawyerapp;



import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen4_1 extends Activity {
	EditText casedate;
	
             
    static final int DATE_DIALOG_ID = 0;
DatabaseAdapter db = new DatabaseAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen4_1);
		EditText caseno = (EditText)findViewById(R.id.updatecaseno);
		EditText casedetail = (EditText)findViewById(R.id.updatecasedetails);
	    casedate = (EditText)findViewById(R.id.updatecasedate);
		TextView name = (TextView)findViewById(R.id.clientnametext);
		String casen = getIntent().getStringExtra("casenum");
		String cased = getIntent().getStringExtra("casedetails");
		String date = getIntent().getStringExtra("hearingdate");
		String clientname =getIntent().getStringExtra("name");
		caseno.setText(casen);
		casedetail.setText(cased);
		casedate.setText(date);
		name.setText("Details of :"+clientname);
		casedate.addTextChangedListener(tw);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen4_1, menu);
		return true;
	}

	public void updateHearing(View view)
	{
		EditText caseno = (EditText)findViewById(R.id.updatecaseno);
		EditText casedetail = (EditText)findViewById(R.id.updatecasedetails);
		EditText casedate = (EditText)findViewById(R.id.updatecasedate);
		String hearingid = getIntent().getStringExtra("hearingid");
		String clientid = getIntent().getStringExtra("clientid");
		int id = Integer.valueOf(clientid);
		
		long hearing_id = Long.valueOf(hearingid);
		db.getWritableDatabase();
		db.updateHeringRecords(hearing_id,id, caseno.getText().toString(), casedetail.getText().toString()
				, casedate.getText().toString());
		db.close();
		Toast.makeText(this,"Hearings Details Updated", Toast.LENGTH_LONG).show();
		caseno.setText("");
		casedetail.setText("");
		casedate.setText("");
		TextView name = (TextView)findViewById(R.id.clientnametext);
		name.setText("");
		
		
				
	}
	
	public void cancelGoBackScreen3(View view)
	{
		Intent i = new Intent(this,Screen3.class);
		startActivity(i);
	}
	public void deleteHearing(View view)
	{
		String hearingid = getIntent().getStringExtra("hearingid");
		long hearing_id = Long.valueOf(hearingid);
		db.getWritableDatabase();
		db.deleteHearingRecordById(hearing_id);
		db.close();
		Toast.makeText(this,"Hearings Details Deleted", Toast.LENGTH_LONG).show();
		EditText caseno = (EditText)findViewById(R.id.updatecaseno);
		EditText casedetail = (EditText)findViewById(R.id.updatecasedetails);
		EditText casedate = (EditText)findViewById(R.id.updatecasedate);
		caseno.setText("");
		casedetail.setText("");
		casedate.setText("");
		TextView name = (TextView)findViewById(R.id.clientnametext);
		name.setText("");
		
	}
	TextWatcher tw = new TextWatcher() {
	    private String current = "";
	    private String ddmmyyyy = "DDMMYYYY";
	    @SuppressLint("DefaultLocale")
		@Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) 
	    {
	        if (!s.toString().equals(current))
	        {
	            String clean = s.toString().replaceAll("[^\\d.]", "");
	            String cleanC = current.replaceAll("[^\\d.]", "");

	            int cl = clean.length();
	            int sel = cl;
	            for (int i = 2; i <= cl && i < 6; i += 2) {
	                sel++;
	            }
	            //Fix for pressing delete next to a forward slash
	            if (clean.equals(cleanC)) sel--;

	            if (clean.length() < 8)
	            {
	               clean = clean + ddmmyyyy.substring(clean.length());
	            }
	            else
	            {
	               //This part makes sure that when we finish entering numbers
	               //the date is correct, fixing it otherways
	               int day  = Integer.parseInt(clean.substring(0,2));
	               int mon  = Integer.parseInt(clean.substring(2,4));
	               int year = Integer.parseInt(clean.substring(4,8));
                   Calendar cal = Calendar.getInstance();
	               if(mon > 12) mon = 12;
	               cal.set(Calendar.MONTH, mon-1);
	               day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
	               year = (year<1900)?1900:(year>2100)?2100:year;
	               clean = String.format("%02d%02d%02d",day, mon, year);
	            }

	            clean = String.format("%s/%s/%s", clean.substring(0, 2),
	                clean.substring(2, 4),
	                clean.substring(4, 8));
	            current = clean;
	            EditText casedate = (EditText)findViewById(R.id.updatecasedate);
	            casedate.setText(current);
	            casedate.setSelection(sel < current.length() ? sel : current.length());
	        }
	    
 
 }


	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	    @Override
	    public void afterTextChanged(Editable s) {}
 };
 
	 
}




