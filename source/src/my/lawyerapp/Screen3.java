package my.lawyerapp;


import java.util.HashMap;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("UseSparseArrays") public class Screen3 extends Activity {
	
	HashMap<Integer, String> hashMap1 = new HashMap<Integer, String>();
	HashMap<Integer, String> hashMap2 = new HashMap<Integer, String>();
	HashMap<Integer, String> hashMap3 = new HashMap<Integer, String>();
	HashMap<Integer, String> hashMap4 = new HashMap<Integer, String>();
	HashMap<Integer, String> hashMap5 = new HashMap<Integer, String>();
	Bundle b = new Bundle();
	int nextpostion = 0;
	int prevpostion = hashMap1.size()-1;
	DatabaseAdapter db = new DatabaseAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen3);
		populatelistView();
		registerItemClick();
		
    
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen3, menu);
		return true;
	}

		

	public void moveToLatestDate(View view)
	{  
		            int  count   = hashMap1.size();
		            TextView num = (TextView)findViewById(R.id.casenotext);
					TextView detail = (TextView)findViewById(R.id.casedetailtext);
					TextView date = (TextView)findViewById(R.id.datetext);
			 		date.setText(hashMap5.get(count-1));
					num.setText(hashMap3.get(count-1));
					detail.setText(hashMap4.get(count-1));
					TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
					TextView clientid =(TextView)findViewById(R.id.clientidtext);
					hearingid.setText(hashMap1.get(count-1));
					clientid.setText(hashMap2.get(count-1));
					Toast.makeText(this,"Latest Hearing Date ", Toast.LENGTH_SHORT).show();
	}
   public void moveToLastDate(View view)
   {  
   
	        
	        TextView num = (TextView)findViewById(R.id.casenotext);
			TextView detail = (TextView)findViewById(R.id.casedetailtext);
			TextView date = (TextView)findViewById(R.id.datetext);
	 		date.setText(hashMap5.get(0));
			num.setText(hashMap3.get(0));
			detail.setText(hashMap4.get(0));
			TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
			TextView clientid =(TextView)findViewById(R.id.clientidtext);
			hearingid.setText(hashMap1.get(0));
			clientid.setText(hashMap2.get(0));
			Toast.makeText(this,"Oldest Hearing Date ", Toast.LENGTH_SHORT).show();
		
}
 public void moveToNextDate(View view)
{ 
     if(nextpostion<=hashMap1.size()-1)
     {
    	 TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
    	    TextView clientid =(TextView)findViewById(R.id.clientidtext);
	TextView num = (TextView)findViewById(R.id.casenotext);
	TextView detail = (TextView)findViewById(R.id.casedetailtext);
	TextView date = (TextView)findViewById(R.id.datetext);
	date.setText(hashMap5.get(nextpostion));
	num.setText(hashMap3.get(nextpostion));
    detail.setText(hashMap4.get(nextpostion));
    hearingid.setText(hashMap1.get(nextpostion));
    clientid.setText(hashMap2.get(nextpostion));
    nextpostion++;
     }
     else
    	 
     {  
    	 nextpostion =0;
    	 TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
         TextView clientid =(TextView)findViewById(R.id.clientidtext);
    	 TextView num = (TextView)findViewById(R.id.casenotext);
 	    TextView detail = (TextView)findViewById(R.id.casedetailtext);
     	TextView date = (TextView)findViewById(R.id.datetext);
   	   date.setText(hashMap5.get(nextpostion));
 	   num.setText(hashMap3.get(nextpostion));
       detail.setText(hashMap4.get(nextpostion));
      
       hearingid.setText(hashMap1.get(nextpostion));
       clientid.setText(hashMap2.get(nextpostion));
       
    	
    	
     }
    
}

public void moveToPrevDate(View view)
{
	if(prevpostion>=0)
	{  
		TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
        TextView clientid =(TextView)findViewById(R.id.clientidtext);
		TextView num = (TextView)findViewById(R.id.casenotext);
		TextView detail = (TextView)findViewById(R.id.casedetailtext);
		TextView date = (TextView)findViewById(R.id.datetext);
		date.setText(hashMap5.get(prevpostion));
		num.setText(hashMap3.get(prevpostion));
	    detail.setText(hashMap4.get(prevpostion));
	  	hearingid.setText(hashMap1.get(prevpostion));
	    clientid.setText(hashMap2.get(prevpostion));
	    prevpostion--; 
	}
	else
	{
		prevpostion = hashMap1.size()-1;
		TextView num = (TextView)findViewById(R.id.casenotext);
		TextView detail = (TextView)findViewById(R.id.casedetailtext);
		TextView date = (TextView)findViewById(R.id.datetext);
		date.setText(hashMap5.get(prevpostion));
		num.setText(hashMap3.get(prevpostion));
	    detail.setText(hashMap4.get(prevpostion));
	    TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
	    TextView clientid =(TextView)findViewById(R.id.clientidtext);
	    hearingid.setText(hashMap1.get(prevpostion));
	    clientid.setText(hashMap2.get(prevpostion));
	   
	}
}


@SuppressWarnings("deprecation")
private void populatelistView() {
	db.getReadableDatabase();
    Cursor cursor = db.getAllRecords();
    startManagingCursor(cursor);
    @SuppressWarnings("deprecation")
	SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.activity_client_item, cursor, new String[] { "name" }, new int[] {R.id.itemclientname});
    
  ListView mylist =(ListView)findViewById(R.id.mylist2);
  mylist.setAdapter(simpleCursorAdapter);
	
		
	}
private void registerItemClick() {
	 
	 
	ListView mylist = (ListView)findViewById(R.id.mylist2);
	mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@SuppressWarnings("deprecation")
	
		public void onItemClick(AdapterView<?> parent, View view, int poistion, long idindb)
		{
			Cursor cursor = db.getRecordById(idindb);
			 int count= 0;
			  hashMap1.clear();
		       hashMap2.clear();
		       hashMap3.clear();
		       hashMap4.clear();
		       hashMap5.clear();
			
			if(cursor.moveToFirst())
			{															
			
			   	final long id = cursor.getLong(0);
				   b.putLong("id",id);
				   final String name = cursor.getString(1);
                 		  
				   b.putString("name", name);
    				TextView casenumber = (TextView)findViewById(R.id.casenotext);
					TextView casedetail = (TextView)findViewById(R.id.casedetailtext);
					TextView date = (TextView)findViewById(R.id.datetext);
					casenumber.setText("");
					casedetail.setText("");
					date.setText("");
	        			}	
			 
			cursor.close();
			int  i = (int)b.getLong("id");
			Cursor c = db.getHearingbyClientId(i);
			
			if(c.moveToFirst())
			{
				do {
					final Long id = c.getLong(0);
					String hearingid = id.toString();
					String  client_id = c.getString(1);
					final String  casenum = c.getString(2);
					final String  casedetail = c.getString(3);
					final String  casedate = c.getString(4);
				    hashMap1.put(count, hearingid);
					hashMap2.put(count, client_id);
					hashMap3.put(count, casenum);
					hashMap4.put(count,casedetail);
					hashMap5.put(count, casedate);
					count++;
				        } while (c.moveToNext());
			
				
			}
			 int pos = 0;
			 b.putInt("pos",pos);
			
			setTextView();					
		}
		
	});
	
}

private void setTextView() {
int 	count = hashMap1.size();
String a[]  = b.getStringArray("array");
int i = (int)b.getLong("id");
TextView casenumber = (TextView)findViewById(R.id.casenotext);
TextView casedetail = (TextView)findViewById(R.id.casedetailtext);
TextView date = (TextView)findViewById(R.id.datetext);
TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
TextView clientid =(TextView)findViewById(R.id.clientidtext);
hearingid.setText(hashMap1.get(count-1));
clientid.setText(hashMap2.get(count-1));
casenumber.setText(hashMap3.get(count-1));
casedetail.setText(hashMap4.get(count-1));
date.setText(hashMap5.get(count-1));
	
}


public void gotoScreen4(View view)
	{
	Intent i = new Intent(this,Screen4.class);
	startActivity(i);
	}

	@SuppressWarnings("deprecation")
public void gotoScreen4_1(View view)

	{
		Intent in = new Intent(this,Screen4_1.class);
		TextView casenumber = (TextView)findViewById(R.id.casenotext);
		TextView casedetail = (TextView)findViewById(R.id.casedetailtext);
		TextView date = (TextView)findViewById(R.id.datetext);
		TextView hearingid = (TextView)findViewById(R.id.hearingidtext);
		TextView clientid = (TextView)findViewById(R.id.clientidtext);
		
		if(casenumber.getText().toString().isEmpty()||casedetail.getText().toString().isEmpty()||date.getText().toString().isEmpty())
		{
	   	  AlertDialog ad = new AlertDialog.Builder(Screen3.this).create();
      	  ad.setTitle("Error");
      	  ad.setMessage("Please select a client name from the list or Add new hearings details for the client");
      	  ad.setButton("OK", new DialogInterface.OnClickListener() {
								@Override
				public void onClick(final DialogInterface dialog, final int id) {
				
				}
			});
      	  ad.show();	
		}
		else
		{
			String casenum = casenumber.getText().toString();
			String casedetails = casedetail.getText().toString();
			String hearingdate = date.getText().toString();
			String hearing_id = hearingid.getText().toString();
			String client_id = clientid.getText().toString();
			in.putExtra("casenum", casenum);
			in.putExtra("casedetails", casedetails);
			in.putExtra("hearingdate",hearingdate);
			in.putExtra("hearingid",hearing_id);
			in.putExtra("clientid", client_id);
			String name = b.getString("name");
			in.putExtra("name", name);
			int  i = (int)b.getLong("id");
			startActivity(in);}
	}
		
	
	public void goBacktoMainMenu(View view)
	{
		Intent i = new Intent(this,Screen1.class);
		startActivity(i);
	}
	
	
	
}
