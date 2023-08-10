package de.gds.Lotto_daten_lesen;

import java.util.ArrayList;

public class Antwort extends  Möglichkeiten{

	private String frage_text;
	private Integer antwort_id;
	private Frage frage;
	private Integer rawFrage_ID;




public Antwort(int antwort_id, String frage_text, Frage frage) {

		this.antwort_id = antwort_id;
		this.frage_text = frage_text;
		this.frage = frage;

			    }

public Frage getFrage() {
	return frage;
}

public void setFrage(Frage frage) {
	this.frage = frage;
}

public Integer getAntwort_id() {
		return antwort_id;
	}

public void setAntwort_id(Integer antwort_id) {
		this.antwort_id = antwort_id;
	}

public String getFrage_text() {
		return frage_text;
	}

public void setFrage_text(String frage_text) {
		this.frage_text = frage_text;
	}


public void addFolgeFrage(int antwort_id) {
	this.antwort_id = antwort_id;
}

@Override
public String toString() {
	return antwort_id + " - " + frage_text;
}

public Integer getRawFrage_ID() {
	return rawFrage_ID;
}

public void setRawFrage_ID(Integer rawFrage_ID) {
	this.rawFrage_ID = rawFrage_ID;
}

public void setletzteAnwort(int letzteAntwort) {
	// TODO Auto-generated method stub

}





}





















