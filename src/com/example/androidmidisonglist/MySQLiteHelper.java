package com.example.androidmidisonglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	  public static final String TABLE_SONGS= "comments";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_SORTORDER = "sortOrder";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_PRESET = "preset";


	  private static final String DATABASE_NAME = "songs.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_SONGS + "(" 
	      + COLUMN_ID + " integer primary key autoincrement, " 
	      + COLUMN_SORTORDER + " integer not null, " 
	      + COLUMN_NAME + " text default '-' not null, "
	      + COLUMN_PRESET + " text default '-' not null);";

	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
	    onCreate(db);
	  }

	} 