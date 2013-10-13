package com.example.androidmidisonglist;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class EditSongActivity extends Activity{
	
	//private int tableChordRowCounter = -1;
	//private int tableChordColCounter = 0;
	//private boolean bEvenSpread = false;
	
	private float fontSize = 100;
	private int margins = 10;
	private int songId = 0;
	private ChordsDataSource datasource;
	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.edit_song);
        
        datasource = new ChordsDataSource(this);
        datasource.open();
        
        
        
        Bundle b = getIntent().getExtras();
        Message m = b.getParcelable("message");
        
        songId = m.getSongId();
        loadChords();
        
        
        // maak de knopjesgrid
        TableLayout table = (TableLayout) findViewById(R.id.tableChordButtons);
        TableRow row = new TableRow(this);
        
        ChordList c = new ChordList();
        
        for(String akkoord : c.getList().keySet()){
        	Button button = new Button(this);
            button.setText(akkoord);
            
            button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					addChord(v);
				}
			});
            
            row.addView(button);
            
        }
        
        table.addView(row,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        addRow(); // add row to currentchordstable
        
    }
	
	public void addChord(View v){
		Button b = (Button) v;
		addTextToTable(b.getText().toString());
	}
	
	public void addTextToTable(String s){
		
		TableLayout table = (TableLayout) findViewById(R.id.tableChords);
		
		TableRow row = null;
		
		if(table.getChildAt(table.getChildCount()-1) != null)
		{
			row = (TableRow) table.getChildAt(table.getChildCount()-1);
		}
		else
		{
			addRow();
			row = (TableRow) table.getChildAt(table.getChildCount()-1);
		}
		
		TextView t = new TextView(this);
		t.setText(s);
		t.setTextSize(fontSize);
		
		
		TableRow.LayoutParams p = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
															TableRow.LayoutParams.WRAP_CONTENT);         
		p.setMargins(margins, 0, margins, 0);
		t.setLayoutParams(p);
		
		row.addView(t);
		
		// scoll naar beneden
		scrollDown();
		
	}

	public void addRow(){
		TableLayout table = (TableLayout) findViewById(R.id.tableChords);
		TableRow row = new TableRow(this);       
        table.addView(row,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
	public void onButtonCancelClick(final View v){
		if(v.getId() == R.id.buttonCancel){
			
			new AlertDialog.Builder(this)
			.setTitle("Terug naar songlijst")
			.setMessage("Wijzigingen niet opslaan?")
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setPositiveButton("Terug naar menu", new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int whichButton) {
			    	Intent intent = new Intent(v.getContext(), SongListActivity.class);
				    startActivity(intent);
			    }})
			    
			 .setNegativeButton("Annuleren", new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int whichButton) {
			    	dialog.cancel();
			    }})
		    .show();
			
		}
	}
	
	public void onButtonDoneClick(View v){
		if(v.getId() == R.id.buttonDone){
			
			datasource.deleteChordsBySongId(songId);
			
			TableLayout table = (TableLayout) findViewById(R.id.tableChords);
			TableRow row = null;

			int sortOrderCounter = 1;
			
			for(int i = 0; i < table.getChildCount(); i++){
				
				if(table.getChildAt(table.getChildCount()-1) != null)
				{
					row = (TableRow) table.getChildAt(i);
					row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 0, fontSize));
				}
				else
				{
					return;
				}
				
				for(int j = 0; j < row.getChildCount(); j++){
				    TextView t = (TextView) row.getChildAt(j);
				    datasource.createChord(sortOrderCounter, songId, t.getText().toString());
				    sortOrderCounter ++;
				}
				
				datasource.createChord(sortOrderCounter, songId, "n"); // nieuwe rij
				sortOrderCounter ++;

			}
			
			
			
			Toast.makeText(getApplicationContext()
					 , "Wijzigingen succesvol opgeslagen! Je bent awesome.", Toast.LENGTH_SHORT)
					  .show();
			Intent intent = new Intent(v.getContext(), SongListActivity.class);
		    startActivity(intent);
			
		}
	}
	
	public void onButtonNewRowClick(View v){
		if(v.getId() == R.id.buttonNewRow){
			addRow();
		}
	}
	
	
	public void onButtonAddLyricsClick(View v){
		if(v.getId() == R.id.buttonAddLyrics){
			
			
			
			
		}
	}
	
	
	
	public void onButtonUndoClick(View v){
		if(v.getId() == R.id.buttonUndo){
			
			
			TableLayout table = (TableLayout) findViewById(R.id.tableChords);
			
			TableRow row;
			
			if(table.getChildAt(table.getChildCount()-1) != null)
			{
				row = (TableRow) table.getChildAt(table.getChildCount()-1);
			}
			else
			{
				/*Toast.makeText(getApplicationContext()
				 , "Fout: rij " + (table.getChildCount()-1) + " bestaat niet.", Toast.LENGTH_SHORT)
				  .show();*/
				
				return;
			}
						
			
			if(row.getChildCount()-1 < 0)
			{
				// verwijder de rij
				if(table.getChildCount()>0)
				{
					table.removeViewAt(table.getChildCount()-1);
				}
			}
			else
			{
				
				if(row.getChildAt(row.getChildCount()-1) != null)
				{
					// normaal gedrag
					row.removeViewAt(row.getChildCount()-1);
				}
				else
				{
					Toast.makeText(getApplicationContext()
							 , "Fout: item is null", Toast.LENGTH_SHORT)
							  .show();
					return;
				}
				

			}

			
		}
		
		scrollDown();
	}
	
	
	public void onButtonZoomInClick(View v){
		
		if(v.getId() == R.id.buttonZoomIn){
		
			if(fontSize <  200)
		    {
		    	fontSize += 10;
		    	margins ++;
		    }
		    else
		    {
		    	return;
		    }
			
			TableLayout table = (TableLayout) findViewById(R.id.tableChords);
			TableRow row = null;
			
			
			for(int i = 0; i < table.getChildCount(); i++){
				
				if(table.getChildAt(table.getChildCount()-1) != null)
				{
					row = (TableRow) table.getChildAt(i);
					row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 0, fontSize));
				}
				else
				{
					return;
				}
				
				for(int j = 0; j < row.getChildCount(); j++){
				    TextView t = (TextView) row.getChildAt(j);
				    t.setTextSize(fontSize);
				}
				
			}
			
			scrollDown();
			
		}
	}
	
	public void onButtonZoomOutClick(View v){
		if(v.getId() == R.id.buttonZoomOut){
		
			if(fontSize >  50)
		    {
		    	fontSize -= 10;
		    	margins --;
		    }
		    else
		    {
		    	return;
		    }
			
			TableLayout table = (TableLayout) findViewById(R.id.tableChords);
			TableRow row = null;
			
			for(int i = 0; i < table.getChildCount(); i++){
				
				if(table.getChildAt(table.getChildCount()-1) != null)
				{
					row = (TableRow) table.getChildAt(i);
					row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 0, fontSize));
				}
				else
				{
					return;
				}
				
				for(int j = 0; j < row.getChildCount(); j++){
				    TextView t = (TextView) row.getChildAt(j);
				    t.setTextSize(fontSize);
				}
				
			}
			
			scrollDown();
		}
	}
	
	public void loadChords(){
		List<Chord> chords = datasource.getChordsForSong(songId);
		
		if(chords.size() > 0)
		{
			for(Chord c : chords)
			{
				if(c.getName().equals("n"))
				{
					addRow();
				}
				else
				{
					addTextToTable(c.getName());
				}
			}
		}

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
    
    public void scrollDown(){
    	ScrollView scroll = (ScrollView) findViewById(R.id.scrollView1);
		scroll.fullScroll(View.FOCUS_DOWN);
    }
    
	
}
