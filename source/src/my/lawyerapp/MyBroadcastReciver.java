package my.lawyerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent it) {
		  Intent mServiceIntent = new Intent();
		  mServiceIntent.setAction("my.lawyerapp.MyServices.class");
		    context.startService(mServiceIntent);
		

	}

}
