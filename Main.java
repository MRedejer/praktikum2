package de.gds.Lotto_daten_lesen;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import de.gds.Lotto_daten_lesen.Antwort;
import de.gds.Lotto_daten_lesen.Frage;

/*   To Do
 *  --------------------------------------------------
 *
 * Eclipse
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * 1.    Entantworten lösen
 * 2.    null exception auffangen
 * 3.
 * 4.	 profit !!
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *
 *  To Do in Excel/Notepad++
 *  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 *
 *  akutell läuft !
 *
 *  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 * ---------------------------------------------------
 */

public class Main
{

	public static void main(String[] args) throws FileNotFoundException {

		Frage ersteFrage = init();

		Scanner in = new Scanner(System.in);

		Frage currentFrage = ersteFrage;

		if(currentFrage == null)
		{
				return;
		}
		else {boolean exit = false;
		while (!exit) {
			System.out.println(currentFrage);

			if (in.hasNextInt()) {
				int antwort = in.nextInt();
				Antwort handlung = findMatchingAntwort(currentFrage.getAntworten(), antwort);
				if(handlung == null)
				{
					continue;
				}
				else {currentFrage = handlung.getFrage();}

				} else {
				// keine Zahl
				exit = true;
			}

			// in welchen Fällen muss exit= true gesetzt
			if (currentFrage == null) {
				// antwort gefunden aber ohne FolgeFrage
				exit = true;
			}

			if (currentFrage.getAntworten().isEmpty()) {
				// antwort gefunden mit FolgeFrage, die aber keine Antworten besitzt
				System.out.println(currentFrage);
				exit = true;
			}
		}

		in.close();

		};
	}

	private static  Frage init()
	{

		// ## einlesen der Daten ## */

		List<Frage> alleFragen = new ArrayList<>();
		List<Antwort> alleAntworten = new ArrayList<>();

		String line = " ";
		String delimiter = ";";


		try {
			String fragen_csv = "C:/dev/workspaces/Lotto_daten_lesen/src/de/gds/Lotto_daten_lesen/fragen.csv";
			FileReader fragen_reader = new FileReader(fragen_csv);

			String antworten_csv = "C:/dev/workspaces/Lotto_daten_lesen/src/de/gds/Lotto_daten_lesen/antworten.csv";
			FileReader antworten_reader = new FileReader(antworten_csv);

			BufferedReader f_reader = new BufferedReader(fragen_reader);
			while ((line = f_reader.readLine()) != null) // läuft so lange weiter bis keine Daten mehr vorhanden sind
			{
				String[] token = line.split(delimiter); // Trennzeichenerkennung Semicolon
				// System.out.println(token[0] + " | "+ token[1]+ " | "+ token[2]+" | "+ token[3]);

				Frage currentReadFrage = new Frage();
				currentReadFrage.setFrage_id(Integer.parseInt(token[0]));
				currentReadFrage.setFrage_text(token[2]);
				if (token.length > 3) {
				currentReadFrage.setRawAntwort_ID(token[3]);
				}
				alleFragen.add(currentReadFrage);
			}

			BufferedReader a_reader = new BufferedReader(antworten_reader);
			while ((line = a_reader.readLine()) != null) // läuft so lange weiter bis keine Daten mehr vorhanden sind
			{
				String[] token = line.split(delimiter); // Trennzeichenerkennung Semicolon
				// System.out.println(token[0] + " | "+ token[1]+ " | "+ token[2]+" | "+  token[3]);

				Antwort currentReadAntwort = new Antwort(-1, "", null);
				currentReadAntwort.setAntwort_id(Integer.parseInt(token[0]));
				currentReadAntwort.setFrage_text(token[2]);
				if (token.length > 3) {
				Integer id = token[3].isBlank() ? 0 : Integer.parseInt(token[3]);
				currentReadAntwort.setRawFrage_ID(id);
				}
//				if (token.length > 4) {
//				Integer id = token[4].isBlank() ? 0 : Integer.parseInt(token[4]);
//				currentReadAntwort.
//				}
				alleAntworten.add(currentReadAntwort);
			}
			/*
			 * ## Daten verknüfen ## #####################
			 */

			// ## Alle Fragen den Antworten zu ordnen ##

			for (Antwort antwort : alleAntworten) // abgleich liste mit Anworten
			{
				for (Frage frage : alleFragen) // abgleich Liste mit Fragen
				{
////					if(antwort.)
////					{
//
//						antwort.setLastantwort_ID(Heimgehen heimgehen = new heimgehen);;
//					}
					if (antwort.getRawFrage_ID() == frage.getFrage_id()) {
						antwort.setFrage(frage);
					}

				}
			}

//        	//  ## Alle Antworten den Fragen zu ordnen ##
//
			for (Frage frage : alleFragen) // Alle Antworten den Fragen zu ordnen
			{
				if(frage.getRawAntwort_ID() != null)
				{    // wenn RawAntwort_ID nicht null ist dann ...

					Set<String> allAntwortIds = Arrays.stream(frage.getRawAntwort_ID().split(","))
							.collect(Collectors.toSet());

					for (Antwort antwort : alleAntworten) // abgleich Liste mit Antworten
					{
						if (allAntwortIds.contains(antwort.getAntwort_id().toString()))
						{
							frage.addAntwort(antwort);
						}
					}


				}    // sonst mach einfach weiter ..
			}
			return alleFragen.get(0);





		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*### Folge Antwort script ###*/

	public static Antwort findMatchingAntwort(ArrayList<Antwort> allAntworten, int choice) {
		for (Antwort aktuelleAntwort : allAntworten) {
			if (aktuelleAntwort.getAntwort_id() == choice) {

				return aktuelleAntwort;
			}

		}
		return null;
	}





}
