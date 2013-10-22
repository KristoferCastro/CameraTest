package com.example.cameratest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MomentOpenHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "moment";
	
	// table columns
	private static final String COL_ID = "momentId";
	private static final String COL_PRICE_RATING = "priceRating";
	private static final String COL_QUALITY_RATING = "qualityRating";
	private static final String COL_RESTAURANT_ID = "restaurantId";
	private static final String COL_MENU_ITEM_ID = "menuItemId";
	
	
	private static final String TABLE_CREATE = 
			String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"%s INTEGER, %s INTEGER,%s INTEGER, %s INTEGER," +
					
					String.format("FOREIGN KEY(%s) REFERENCES %s(%s), ", COL_RESTAURANT_ID, 
							RestaurantOpenHelper.TABLE_NAME, RestaurantOpenHelper.COL_ID ) +
					String.format("FOREIGN KEY(%s) REFERENCES %s(%s)", COL_MENU_ITEM_ID,
							MenuItemOpenHelper.TABLE_NAME, MenuItemOpenHelper.COL_ID) +
					")", TABLE_NAME, COL_ID, 
					COL_PRICE_RATING, COL_QUALITY_RATING, COL_RESTAURANT_ID, COL_MENU_ITEM_ID);

	MomentOpenHelper(Context context){
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
}
