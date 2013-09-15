package com.example.androidmidisonglist;

public class Song {
	private int id;
	private int sortOrder;
	private String name, preset;
	
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
		return id + "\t" + name + "\t" + preset;
	}
	
}
