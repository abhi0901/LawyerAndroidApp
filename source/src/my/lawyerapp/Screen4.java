package my.lawyerapp;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class Screen4 extends Activity {

	
    
           
    static final int DATE_DIALOG_ID = 0;
	DatabaseAdapter db = new DatabaseAdapter(this);
	Bundle b = new Bundle();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen4);
		populatelistview();
	    registerItemClick();
	    EditText casedate= (EditText)findViewById(R.id.hearingdate);
        casedate.addTextChangedListener(tw);
     
       
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen4, menu);
		return true;
	
		
		
		
	}
		
	@SuppressWarnings("deprecation")
	private void populatelistview() {
		this.db.getReadableDatabase();
        Cursor cursor = db.getAllRecords();
        startManagingCursor(cursor);
        @SuppressWarnings("deprecation")
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.activity_client_item, cursor, new String[] { "name" }, new int[] {R.id.itemclientname});
        
      ListView mylist =(ListView)findViewById(R.id.mylist1);
      mylist.setAdapter(simpleCursorAdapter);
		
	}
	

	public void registerItemClick() {
	
		ListView mylist = (ListView)findViewById(R.id.mylist1);
		mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int poistion, long idindb)
			{
				Cursor cursor = db.getRecordById(idindb);
				if(cursor.moveToFirst())
				{
					 final long id = cursor.getLong(0);
					   

		           		b.putLong("id", id);
				}
			}
		});
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void insertNewHearing(View view)
	{
          Long id = b.getLong("id");
          if(id==0)
          {
        	  
        	  AlertDialog ad = new AlertDialog.Builder(Screen4.this).create();
        	  ad.setTitle("Error");
        	  ad.setMessage("Please Select a Client Name from the list");
        	  ad.setButton("OK", new DialogInterface.OnClickListener() {
								@Override
				public void onClick(final DialogInterface dialog, final int id) {
				
				
				}
			});
        	  ad.show();
          }
           
         
        	  
          
          else
          {
          EditText caseno= (EditText)findViewById(R.id.caseno);
          EditText casedetails= (EditText)findViewById(R.id.casedetails);
          EditText casedate= (EditText)findViewById(R.id.hearingdate);
          
         db.getWritableDatabase();
         db.insertHearing(id, caseno.getText().toString(), casedetails.getText().toString(), casedate.getText().toString());
         caseno.setText("");
         casedate.setText("");
         casedetails.setText("");
         Toast.makeText(this,"Hearings Deatils added",Toast.LENGTH_LONG).show();
          }
	}
	public void goBacktoScreen3(View view)
	{
		Intent i = new Intent(this,Screen3.class);
		startActivity(i);
	}

		 
		 @SuppressLint("DefaultLocale")
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
			            EditText casedate = (EditText)findViewById(R.id.hearingdate);
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

