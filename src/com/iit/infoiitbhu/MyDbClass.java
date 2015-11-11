package com.iit.infoiitbhu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbClass {
	
	public static final String KEY_ROWID= "_id";
	public static final String KEY_NAME= "person_name";
	public static final String KEY_PASS= "password";
	
	private static final String DATABASE_NAME= " LoginProfiles";
	private static final String DATABASE_TABLE= " People";
	private static final int DATABASE_VERSION= 1;
	
    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
	
	public static class DbHelper extends SQLiteOpenHelper
	{
		public DbHelper(Context context)
		{
			super(context, DATABASE_NAME,null, DATABASE_VERSION );
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE +" (" +
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
			KEY_PASS + " TEXT NOT NULL);"
			);
			//db.execSQL("INSERT INTO " + DATABASE_TABLE +" VALUES('1','2');"
					
					
					
					//);
			
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			
			
		}
	}
	public MyDbClass(Context c)
	{
		ourContext = c;
	}
	public MyDbClass open(){
		ourHelper= new DbHelper(ourContext);
		ourDatabase= ourHelper.getReadableDatabase();
		return this;
	}
	public void close(){
		ourHelper.close();
	}
	public void createEntry(String name, String Password)
	{
		SQLiteDatabase db1 = ourHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_NAME, name);
		cv.put(KEY_PASS, Password);
		db1.insert(DATABASE_TABLE, null, cv);
		db1.close();
		
	}
	public Boolean getData(String check, String Check2){
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PASS};
		SQLiteDatabase db1 = ourHelper.getReadableDatabase();
		Cursor c = db1.query(DATABASE_TABLE, columns, null, null, null, null, null, null);
		
		Boolean result =false;
		int iRow= c.getColumnIndex(KEY_ROWID);
		int iName= c.getColumnIndex(KEY_NAME);
		int iPass= c.getColumnIndex(KEY_PASS);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			if(check.equals(c.getString(iName)) && Check2.equals(c.getString(iPass)))
			{
				result = true;
			}
		}
		return result;
	}
	public String viewData()
	{
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_PASS};
		SQLiteDatabase db1 = ourHelper.getReadableDatabase();
		Cursor c = db1.query(DATABASE_TABLE, columns, null, null, null, null, null, null);
		
		String result ="";
		int iRow= c.getColumnIndex(KEY_ROWID);
		int iName= c.getColumnIndex(KEY_NAME);
		int iPass= c.getColumnIndex(KEY_PASS);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			
				result = result +" "+c.getString(iRow)+" "+c.getString(iName)+ " " + c.getString(iPass)+" \n";
			
		}
		return result;
	}

}
