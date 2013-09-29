package com.example.androidmidisonglist;

public class Chord {
	private int id;
	private int song_id;
	private int sortOrder;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int l) {
		this.id = l;
	}
	
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public int getSong_id() {
		return song_id;
	}
	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + "\t" + song_id + "\t" + name;
	}
	
	
}
