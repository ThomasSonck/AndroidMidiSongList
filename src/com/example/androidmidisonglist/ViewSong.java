package com.example.androidmidisonglist;

import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.view.Menu;
import jp.kshoji.driver.midi.activity.AbstractSingleMidiActivity;
import jp.kshoji.driver.midi.device.MidiInputDevice;

public class ViewSong extends AbstractSingleMidiActivity{

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onDeviceDetached(UsbDevice arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDeviceAttached(UsbDevice arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiCableEvents(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiChannelAftertouch(MidiInputDevice arg0, int arg1,
			int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiControlChange(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiMiscellaneousFunctionCodes(MidiInputDevice arg0,
			int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiNoteOff(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiNoteOn(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiPitchWheel(MidiInputDevice arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiPolyphonicAftertouch(MidiInputDevice arg0, int arg1,
			int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiProgramChange(MidiInputDevice arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiSingleByte(MidiInputDevice arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiSystemCommonMessage(MidiInputDevice arg0, int arg1,
			byte[] arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMidiSystemExclusive(MidiInputDevice arg0, int arg1,
			byte[] arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
