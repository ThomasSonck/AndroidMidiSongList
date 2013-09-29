package com.example.androidmidisonglist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ChordsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { 
			MySQLiteHelper.COLUMN_CHORDS_ID,
			MySQLiteHelper.COLUMN_CHORDS_SONGS_ID, 
			MySQLiteHelper.COLUMN_CHORDS_NAME,
			MySQLiteHelper.COLUMN_CHORDS_SORTORDER
			};

	public ChordsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Chord createChord(Integer sortOrder, Integer songId, String name) {
		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COLUMN_CHORDS_SORTORDER, sortOrder);
		values.put(MySQLiteHelper.COLUMN_CHORDS_NAME, name);
		values.put(MySQLiteHelper.COLUMN_CHORDS_SONGS_ID, songId);

		long insertId = database.insert(MySQLiteHelper.TABLE_CHORDS, null,values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CHORDS, allColumns,
				MySQLiteHelper.COLUMN_CHORDS_ID + " = " + insertId, null, null, null,null);
		cursor.moveToFirst();
		Chord newChord = cursorToChord(cursor);
		cursor.close();
		return newChord;
	}

	public void deleteChord(Chord chord) {
		long id = chord.getId();
		System.out.println("Chord deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_CHORDS, MySQLiteHelper.COLUMN_CHORDS_ID
				+ " = " + id, null);
	}

	public List<Chord> getAllChords() {
		List<Chord> chords = new ArrayList<Chord>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_CHORDS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Chord chord = cursorToChord(cursor);
			chords.add(chord);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return chords;
	}
	
	public List<Chord> getChordsForSong(int song_id) {
		List<Chord> chords = new ArrayList<Chord>();

		/*
		 * TODO: query schrijven die enkel chords voor deze song ophaalt HIERONDER
		 */
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CHORDS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Chord chord = cursorToChord(cursor);
			chords.add(chord);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return chords;
	}

	private Chord cursorToChord(Cursor cursor) {
		Chord chord = new Chord();

		chord.setId(cursor.getInt(0));
		chord.setSong_id(cursor.getInt(1));
		chord.setSortOrder(cursor.getInt(2));
		chord.setName(cursor.getString(3));
		
		return chord;
	}
}