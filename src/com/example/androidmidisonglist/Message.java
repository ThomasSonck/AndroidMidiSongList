package com.example.androidmidisonglist;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {

	private int songId;

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public Message(int songId) {
		super();
		this.songId = songId;
	}

	public Message(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(songId);
	}

	private void readFromParcel(Parcel p) {
		songId = p.readInt();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Message createFromParcel(Parcel in) {
			return new Message(in);
		}

		public Message[] newArray(int size) {
			return new Message[size];
		}
	};

}
