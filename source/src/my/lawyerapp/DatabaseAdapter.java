package my.lawyerapp;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat") public class DatabaseAdapter extends SQLiteOpenHelper
{
  public static final int Col_Name = 1;
  public static final int Col_email = 2;
  public static final int Col_id = 0;
  public static final int Col_phone = 3;
  public static final String DATABASE_NAME = "Lawyerdb";
  public static final String DATABASE_TABLE = "clientdetails";
  public static final String ROW_EMAIL = "email";
  public static final String ROW_ID = "_id";
  public static final String ROW_NAME = "name";
  public static final String ROW_PHONENUMBER = "phonenumber";
  private SQLiteDatabase db;
 
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  Calendar c = Calendar.getInstance();
  
  
  
  public DatabaseAdapter(Context Context)
  {
    super(Context, "Lawyerdb", null, 1);
  }

  public void deleteAllRecordsInDb()
  {
    getWritableDatabase().execSQL("DELETE FROM clientdetails");
  }

  public boolean deleteRecordById(long paramLong)
  {
    return getWritableDatabase().delete("clientdetails", "_id=" + paramLong, null) > 0;
  }
  public boolean deleteHearingRecordById(long id)
  {
     return getWritableDatabase().delete("hearing", "Hearingid=" + id, null) > 0;
  }
 
  public Cursor getAllRecords()
  {
    return getReadableDatabase().rawQuery("SELECT _id, name,phonenumber,email FROM clientdetails ORDER BY name", null);
  }

 
  public Cursor getRecordById(Long paramLong)
    throws SQLException
  {
    return getReadableDatabase().query(true, "clientdetails", new String[] { "_id", "name", "email", "phonenumber" }, "_id=" + paramLong, null, null, null, null, null);
  }

  public void insert(String name, String phone, String email)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", name);
    localContentValues.put("phonenumber", phone);
    localContentValues.put("email", email);
    getWritableDatabase().insert("clientdetails", "name", localContentValues);
  }
  public void insertPhoneno(String phone ,int smsvalue)
  {
	  ContentValues cv  = new ContentValues();
	  cv.put("lawyerphno", phone);
	  cv.put("sendSMS", smsvalue);
	  getWritableDatabase().insert("appsetting","name", cv);
	  
  }
 
  public void insertHearing(Long id,String caseno,String casedetails,String date)
  {
	  ContentValues cv = new ContentValues();
	  cv.put("_id", id);
	  cv.put("casenumber", caseno);
	  cv.put("casedetails", casedetails);
	  cv.put("hearingdate", date);
	  getWritableDatabase().insert("hearing", "name", cv);
  }
  public Cursor fetchAllHearings()
  {
	  return getReadableDatabase().rawQuery("SELECT _id,Hearingid,casenumber,casedetails,hearingdate ORDER BY _id", null);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE clientdetails (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phonenumber TEXT,email TEXT);");
    paramSQLiteDatabase.execSQL("CREATE TABLE hearing (Hearingid INTEGER PRIMARY KEY AUTOINCREMENT,_id INTEGER, casenumber VARCHAR,casedetails TEXT,hearingdate TEXT);");
    paramSQLiteDatabase.execSQL("CREATE TABLE appsetting (lawyerphno TEXT,sendSMS INTEGER);"); 
  
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS clientdetails ");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS hearing");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS appsetting");  
  }

  public void updateRecords(long paramLong, String paramString1, String paramString2, String paramString3)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramString1);
    localContentValues.put("phonenumber", paramString2);
    localContentValues.put("email", paramString3);
    localSQLiteDatabase.update("clientdetails", localContentValues, "_id = " + paramLong, null);
    localSQLiteDatabase.close();
  }
  public void updateHeringRecords(long hearingid ,int id,String caseno,String casedetail,String hearingdate )
  {
	  SQLiteDatabase db = getWritableDatabase();
	  ContentValues cv = new ContentValues();
	  cv.put("_id", id);
	  cv.put("casenumber",caseno);
	  cv.put("casedetails", casedetail);
	  cv.put("hearingdate", hearingdate);
	  db.update("hearing", cv,"Hearingid ="+hearingid, null);
	  db.close();
  }

  public Cursor getHearingbyClientId(int id)
  {
	 return  getReadableDatabase().rawQuery("SELECT * FROM hearing WHERE _id ="+id,null);
  }
  public Cursor  getHearingBefore2Days()
  {
	 
	  Calendar d =Calendar.getInstance();
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     d.add(Calendar.DAY_OF_MONTH,2); 
               
	String s =sdf.format(d.getTime());
     
return getReadableDatabase().rawQuery("SELECT * FROM hearing WHERE hearingdate ="+"'"+s+"'",null) ;
	  
  }

  public Cursor fetchPhone()
  {
	  return getReadableDatabase().rawQuery("SELECT lawyerphno FROM appsetting WHERE sendSMS = 1",null);
  }
}