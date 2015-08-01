package my.lawyerapp;



import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyServices extends Service {

	 
	DatabaseAdapter db = new DatabaseAdapter(this);
	Bundle b = new Bundle();
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
		
		sendSmS();
		
		return START_STICKY;
	}
	


	private void sendSmS() {
			
		Cursor c = db.getHearingBefore2Days();
		{
		if(c.moveToFirst())
		{
			do
		{
          String casenum = c.getString(2);
          String casedetail = c.getString(3);
          String casedate = c.getString(4);
          Long clientid =c.getLong(1);
          
           Cursor cu = db.getRecordById(clientid);
           if(cu.moveToNext())
           {
        	   String name = cu.getString(1);
        	    b.putCharSequence("name",name);
           }
           String name =b.getString("name");
           String msg = "Client name :"+name
        		       +"\nCase number :"+casenum
        		       +"\nCase details :"+casedetail
        		       +"\n Your next hearing is on :"+casedate;
           Cursor cus = db.fetchPhone();
        		if(cus.moveToFirst())
        		   {
        			do
        			{
        			String phonenumber = cus.getString(0);
        		    SmsManager sms = SmsManager.getDefault();
        		    sms.sendTextMessage(phonenumber,null,msg,null,null);
        	               		   
        			} while(cus.moveToNext());
        		   
        		   }
        			cus.close();   
           
           
			}while(c.moveToNext());	  						
		}
		}
			
		
	}
	
	
	
	
	
	

}
