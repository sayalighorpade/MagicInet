package com.deepak.magicinet;

import java.util.ArrayList;

public class ItemsCD {

	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<String> artist = new ArrayList<String>();
	private ArrayList<String> country = new ArrayList<String>();
	private ArrayList<String> fullfare = new ArrayList<String>();
	private ArrayList<String> halffare = new ArrayList<String>();

	public ArrayList<String> getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.add(title);
	}

	public ArrayList<String> getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist.add(artist);
	}

	public ArrayList<String> getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country.add(country);
	}

	public ArrayList<String> getFullfare() {
		return fullfare;
	}

	public void setFullfare(String fullfare) {
		this.fullfare.add(fullfare);
	}

	public ArrayList<String> getHalffare() {
		return halffare;
	}

	public void setHalffare(String halffare) {
		this.halffare.add(halffare);
	}

}
