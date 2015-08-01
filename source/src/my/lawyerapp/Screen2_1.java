package my.lawyerapp;

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

public class Screen2_1 extends Activity {

	
	DatabaseAdapter db = new DatabaseAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen2_1);
		populateClientList();
		registerItemClick();
	}

	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen2_1, menu);
		return true;
	}

	public void gotoScreen1(View view)
	{
		Intent i = new Intent(this,Screen1.class);
		startActivity(i);
	}
	
	public void addNewClient(View view)
	{
		Intent i = new Intent(this,Screen2.class);
		startActivity(i);
		
	}
	
    @SuppressWarnings("deprecation")
	private void populateClientList() {
    	this.db.getReadableDatabase();
        Cursor cursor = db.getAllRecords();
        startManagingCursor(cursor);
        @SuppressWarnings("deprecation")
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.activity_client_item, cursor, new String[] { "name" }, new int[] {R.id.itemclientname});
        
      ListView mylist =(ListView)findViewById(R.id.ClientList);
      mylist.setAdapter(simpleCursorAdapter);
      
		
	}


	private void registerItemClick() 
	{
	ListView mylist = (ListView)findViewById(R.id.ClientList);
	mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@SuppressWarnings("deprecation")
		public void onItemClick(AdapterView<?> parent, View view, int poistion, long idindb)
		{
			Cursor cursor = db.getRecordById(idindb);
			if(cursor.moveToFirst())
			{
				 final long id = cursor.getLong(0);
		          final String name = cursor.getString(1);
		          final String email = cursor.getString(2);
		          final String phone = cursor.getString(3);
		          String msg = "Client Name:" + name + "\n" + "Phone:" + phone + "\n" + "Email:" + email + "\n";
		          AlertDialog localAlertDialog = new AlertDialog.Builder(Screen2_1.this).create();
		          localAlertDialog.setTitle(name + " details:");
		          localAlertDialog.setMessage(msg);
		          localAlertDialog.setButton("Edit", new DialogInterface.OnClickListener()
		          {
		            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
		            {
		              Intent localIntent = new Intent(Screen2_1.this, Screen2_2.class);
		              localIntent.putExtra("name",name );
		              localIntent.putExtra("id",id);
		              localIntent.putExtra("email",email);
		              localIntent.putExtra("phone",phone);
		              startActivity(localIntent);
		            }
		          });
		          localAlertDialog.setButton2("Cancel", new DialogInterface.OnClickListener()
		          {
		            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
		            {
		            }
		          });
		          localAlertDialog.show();
				
			}
		}
	});
		
	}

}
