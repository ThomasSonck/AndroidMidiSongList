package com.example.androidmidisonglist;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ConsoleMessage;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SongListActivity extends Activity {

	private SongsDataSource datasource;
	private int choice = 0;

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.song_list);

		datasource = new SongsDataSource(this);
		datasource.open();

		List<Song> songs = datasource.getAllSongs();

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(
				songs.size());

		for (Song s : songs) {

			HashMap<String, String> item = new HashMap<String, String>();
			item.put("name", s.getName());
			item.put("preset", s.getPreset());

			list.add(item);
		}

		String[] from = new String[] { "name", "preset" };

		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		int nativeLayout = android.R.layout.two_line_list_item;

		ListView lv = (ListView) findViewById(R.id.songListView);

		lv.setAdapter(new SimpleAdapter(this, list, nativeLayout, from, to));

		// kliklistener om naar juiste view te gaan (of juiste args door te
		// geven)
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, final View view,
					int position, long arg) {

				List<Song> songs = datasource.getAllSongs();
				final Song song = songs.get(position);

				AlertDialog.Builder builder = new AlertDialog.Builder(view
						.getContext());

				final CharSequence[] items = { "Spelen", "Bewerken",
						"Verwijder" };
				builder.setTitle("Wadyawannado?")
						.setCancelable(false)
						// .setView(content)
						.setSingleChoiceItems(items, 0,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {

										setChoice(item);

										// mss handig voor debugging?
										/*
										 * Toast.makeText(getApplicationContext()
										 * , items[item], Toast.LENGTH_SHORT)
										 * .show();
										 */
									}
								})

						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										switch (getChoice()) {
										case 0: // spelen
											break;
										case 1: // bewerken
											
											Intent intent = new Intent(view.getContext(), EditSongActivity.class);
											Message m = new Message(song.getId());
											intent.putExtra("message", m);
										    startActivity(intent);
											
											break;
										case 2: // verwijderen
											break;
										default:
											break;
										}
										
										Toast.makeText(getApplicationContext()
												  , "Keuze: " + getChoice(), Toast.LENGTH_SHORT)
												  .show();

									}
								})

						.setNegativeButton("Terug",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// handler
										dialog.cancel();
									}
								});

				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		});

	}

	public void buttonHandler(View view) {

		switch (view.getId()) {
		case R.id.buttonAdd:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			LayoutInflater inflater = this.getLayoutInflater();
			final View content = inflater.inflate(R.layout.dialog_new_song,
					null);

			builder.setTitle("Nieuwe song toevoegen")
					.setCancelable(false)
					.setView(content)
					.setPositiveButton("Opslaan",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									EditText inputName = (EditText) content
											.findViewById(R.id.inputName);
									EditText inputPreset = (EditText) content
											.findViewById(R.id.inputPreset);

									Song song = datasource.createSong(inputName
											.getText().toString(), inputPreset
											.getText().toString());

									ListView lv = (ListView) findViewById(R.id.songListView);
									ArrayAdapter<Song> adapter = (ArrayAdapter<Song>) lv
											.getAdapter();
									adapter.add(song);
									adapter.notifyDataSetChanged();

								}
							})
					.setNegativeButton("Annuleren",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// handler
									dialog.cancel();
								}
							});

			AlertDialog alertDialog = builder.create();
			alertDialog.show();

			break;

		default:
			break;
		}

	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menuItemExit:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
