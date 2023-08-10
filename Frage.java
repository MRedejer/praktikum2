package de.gds.Lotto_daten_lesen;

import java.util.ArrayList;



public class Frage
{

	private int frage_id;
	private String frage_text;
	private ArrayList<Antwort> antworten = new ArrayList<Antwort>();
	private String rawAntwort_ID;



	public Frage() {
		}


	public int getFrage_id() {
		return frage_id;
	}

	public void setFrage_id(int frage_id) {
		this.frage_id = frage_id;
	}

	public String getFrage_text() {
		return frage_text;
	}

	public void setFrage_text(String frage_text) {
		this.frage_text = frage_text;
	}

	public ArrayList<Antwort> getAntworten() {
		return antworten;
	}

	public void setAntworten(ArrayList<Antwort> antworten) {
		this.antworten = antworten;
	}


	public void addAntwort(Antwort antwort) {
	antworten.add(antwort);
	}



	@Override
	public String toString() {
		StringBuilder fullText = new StringBuilder(frage_text);
		fullText.append(System.lineSeparator());
		fullText.append("------------------------------------");
		fullText.append(System.lineSeparator());
		for(Antwort antwort: antworten) {
			fullText.append(antwort);

			fullText.append(System.lineSeparator());
		}
		return fullText.toString();
	}


	public String getRawAntwort_ID() {
		return rawAntwort_ID;
	}


	public void setRawAntwort_ID(String rawAntwort_ID) {
		this.rawAntwort_ID = rawAntwort_ID;
	}





}
	// ## alter Code ##
	// ################








