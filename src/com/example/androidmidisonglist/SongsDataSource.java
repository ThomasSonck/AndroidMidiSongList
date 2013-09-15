package com.example.androidmidisonglist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SongsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_SORTORDER, MySQLiteHelper.COLUMN_NAME,
			MySQLiteHelper.COLUMN_PRESET, };

	public SongsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Song createSong(String name, String preset) {
		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COLUMN_SORTORDER, 0);
		values.put(MySQLiteHelper.COLUMN_NAME, name);
		values.put(MySQLiteHelper.COLUMN_PRESET, preset);

		long insertId = database.insert(MySQLiteHelper.TABLE_SONGS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SONGS, allColumns,
				MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Song newSong = cursorToSong(cursor);
		cursor.close();
		return newSong;
	}

	public void deleteSong(Song song) {
		long id = song.getId();
		System.out.println("Song deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_SONGS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Song> getAllSongs() {
		List<Song> songs = new ArrayList<Song>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_SONGS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Song song = cursorToSong(cursor);
			songs.add(song);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return songs;
	}

	private Song cursorToSong(Cursor cursor) {
		Song song = new Song();

		song.setId(cursor.getInt(0));
		song.setSortOrder(cursor.getInt(1));
		song.setName(cursor.getString(2));
		song.setPreset(cursor.getString(3));

		return song;
	}
}