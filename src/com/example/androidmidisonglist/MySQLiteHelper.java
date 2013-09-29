package com.example.androidmidisonglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "midisonglist.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_SONGS = "songs";
	public static final String COLUMN_SONGS_ID = "_id";
	public static final String COLUMN_SONGS_SORTORDER = "sortOrder";
	public static final String COLUMN_SONGS_NAME = "name";
	public static final String COLUMN_SONGS_PRESET = "preset";

	
	public static final String TABLE_CHORDS = "chords";
	public static final String COLUMN_CHORDS_ID = "_id";
	public static final String COLUMN_CHORDS_SONGS_ID = "song_id";
	public static final String COLUMN_CHORDS_SORTORDER = "sortOrder";
	public static final String COLUMN_CHORDS_NAME = "name";
	

	private static final String CREATE_TABLE_SONGS = "create table "
	  + TABLE_SONGS + "(" 
	  + COLUMN_SONGS_ID + " integer primary key autoincrement, " 
	  + COLUMN_SONGS_SORTORDER + " integer default 0 not null, " 
	  + COLUMN_SONGS_NAME + " text default '-' not null, "
	  + COLUMN_SONGS_PRESET + " text default '-' not null);";
	
	private static final String CREATE_TABLE_CHORDS = "create table "
			  + TABLE_CHORDS + "(" 
			  + COLUMN_CHORDS_ID + " integer primary key autoincrement, " 
			  + COLUMN_CHORDS_SONGS_ID + " integer not null, " 
			  + COLUMN_CHORDS_SORTORDER + " integer default 0 not null, " 
			  + COLUMN_CHORDS_NAME + " text not null);";
	
	
	// maak de databasefile aan
	public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_SONGS);
		database.execSQL(CREATE_TABLE_CHORDS);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
	    onCreate(db);
	}
} 