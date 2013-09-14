package com.example.androidmidisonglist;

import java.io.Console;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

public class SongListActivity extends Activity {

	private SongsDataSource datasource;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
       
        
        datasource = new SongsDataSource(this);
        datasource.open();
        
        List<Song> songs = datasource.getAllSongs();
        
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Song> adapter = new ArrayAdapter<Song>(this,android.R.layout.simple_list_item_1, songs);
		ListView lv = (ListView) findViewById(R.id.songListView);
		lv.setAdapter(adapter);
		
		// kliklistener om naar juiste view te gaan (of juiste args door te geven)
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg) {
				
				//Object listItem = list.getItemAtPosition(position);
			}
		});
		
    }
    
    
	public void buttonHandler(View view) {

		switch (view.getId()) {
		case R.id.buttonAdd:
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			LayoutInflater inflater = this.getLayoutInflater();
			final View content = inflater.inflate(R.layout.dialog_new_song, null);
			
			builder	.setTitle("Nieuwe song toevoegen")
					.setCancelable(false)
					.setView(content)
					.setPositiveButton("Opslaan",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {

							EditText inputName = (EditText)content.findViewById(R.id.inputName);
							EditText inputPreset = (EditText)content.findViewById(R.id.inputPreset);
							
							Song song = datasource.createSong(
									inputName.getText().toString(),
									inputPreset.getText().toString()
									);
							
							ListView lv = (ListView) findViewById(R.id.songListView);
							ArrayAdapter<Song> adapter = (ArrayAdapter<Song>) lv.getAdapter();
							adapter.add(song);
							adapter.notifyDataSetChanged();
							
						}
					})
					.setNegativeButton("Annuleren",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// handler
							dialog.cancel();
						}
					});


			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			
			break;
		case R.id.buttonDelete:
			/*if (getListAdapter().getCount() > 0) {
				comment = (Comment) getListAdapter().getItem(0);
				datasource.deleteComment(comment);
				adapter.remove(comment);
			}*/
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
