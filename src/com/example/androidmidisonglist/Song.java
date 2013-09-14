package com.example.androidmidisonglist;

public class Song {
	private long id;
	private int sortOrder;
	private String name, preset;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreset() {
		return preset;
	}
	public void setPreset(String preset) {
		this.preset = preset;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return name + "  \t \t " + preset;
	}
	
}
