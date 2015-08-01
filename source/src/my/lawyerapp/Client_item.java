package my.lawyerapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Client_item extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_item, menu);
		return true;
	}

}
