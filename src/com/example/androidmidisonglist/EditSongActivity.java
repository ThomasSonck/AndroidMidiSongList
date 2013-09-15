package com.example.androidmidisonglist;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class EditSongActivity extends Activity{

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.edit_song);
        
        Bundle b = getIntent().getExtras();
        Message m = b.getParcelable("message");
        
        int songId = m.getSongId();
        
        // maak de knopjesgrid
        TableLayout table = (TableLayout) findViewById(R.id.tableChordButtons);
        TableRow row = new TableRow(this);
        
        ChordList c = new ChordList();
        
        for(String akkoord : c.getList().keySet()){
        	Button button = new Button(this);
            button.setText(akkoord);
            
            row.addView(button);
            
        }
        
        table.addView(row,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	
}
