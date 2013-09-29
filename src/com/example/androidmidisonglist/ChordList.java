package com.example.androidmidisonglist;

import java.util.Map;
import java.util.TreeMap;

public class ChordList {

	private Map<String, String> list = new TreeMap<String, String>();

	public Map<String, String> getList() {
		return list;
	}

	public void setList(Map<String, String> list) {
		this.list = list;
	}

	public ChordList() {
		super();
		//http://www.muselive.com/page.php?al=alias6246
		list.put("C", "C E G");
		list.put("Cm", "C D# G");		
		
		list.put("Db", "C# F G#");
		list.put("Dbm", "C# E G#");
		
		list.put("D", "D F# A");
		list.put("Dm", "D F G");
		
		list.put("Eb", "D# G A#");
		list.put("Ebm", "D# F# A#");
		
		list.put("E", "E G# B");
		list.put("Em", "E G B");
		
		list.put("F", "F A C");
		list.put("Fm", "F G# C");
		
		list.put("F#", "F# A# C#");
		list.put("F#m", "F# A C#");
		
		list.put("G", "G B D");
		list.put("Gm", "G A# D");
		
		list.put("Ab", "G# C D#");
		list.put("Abm", "G# B D#");
		
		list.put("A", "A C# E");
		list.put("Am", "A C E");
		
		list.put("Bm", "A# D F");
		list.put("Bbm", "A# C# F");
		
		list.put("B", "B D# F#");
		list.put("Bm", "B D F#");
		
	}
	
	
	
}
