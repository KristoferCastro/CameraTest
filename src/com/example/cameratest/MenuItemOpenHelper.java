package com.example.cameratest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MenuItemOpenHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_NAME = "menu_item";
	
	//table columns
	
	static final String COL_ID = "menuItemId";
	private static final String COL_NAME = "name";
	private static final String COL_IMAGE_PATH = "imagePath";
	
	private static final String TABLE_CREATE = 
			String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"%s TEXT, %s TEXT)", TABLE_NAME, COL_ID, COL_NAME, COL_IMAGE_PATH);

	MenuItemOpenHelper(Context context){
		// grab database and create it for us
		super(context, DatabaseConfig.DATABASE_NAME , null, DATABASE_VERSION);
	}
	
	@Override 
	public void onCreate(SQLiteDatabase db){
		// create table in the database
		Log.i("MyCameraApp", "Path: " + db.getPath());
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
}
