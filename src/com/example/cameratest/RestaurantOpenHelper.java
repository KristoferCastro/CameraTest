package com.example.cameratest;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantOpenHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_NAME = "restaurant";
	
	// table columns
	static String COL_ID = "restaurantId";
	private static String COL_NAME = "name";
	private static String COL_GPS_LAT = "latitude";
	private static String COL_GPS_LON = "longitude";
	
	private static final String TABLE_CREATE = 
			String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"%s TEXT, %s REAL, %s REAL)", TABLE_NAME, COL_ID, COL_NAME, 
					COL_GPS_LAT, COL_GPS_LON);

	RestaurantOpenHelper(Context context){
		// grab database and create it for us
		super(context, DatabaseConfig.DATABASE_NAME , null, DATABASE_VERSION);
	}
	
	@Override 
	public void onCreate(SQLiteDatabase db){
		
		// create table in the database
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public void createRestaurant(HashMap<String, String> queryValues){
		
		// Need to open database for reading/writing
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(COL_NAME, queryValues.get(COL_NAME));
		values.put(COL_GPS_LAT, queryValues.get(COL_GPS_LAT));
		values.put(COL_GPS_LON, queryValues.get(COL_GPS_LON));		
		database.insert(TABLE_NAME, null, values);
	}
	
	public void retrieveRestaurant(){
		
	}
	
	public void deleteRestaurant(){
		
	}
	
	public int updateRestaurant(HashMap<String, String> queryValues){
		// Need to open database for reading/writing
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		return database.update(TABLE_NAME, values, null, null);
	}
	
}
