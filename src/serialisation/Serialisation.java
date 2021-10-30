package serialisation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.BasicCard;

public class Serialisation {
	
	//writing of file json
	public static void writeAll(List<BasicCard> listCards) {
		PrintWriter print=null;
		try {
			//creation of file
			FileWriter fichier = new FileWriter("Questions.json");
			//preparation to write in file
			print = new PrintWriter(fichier);
			//we read each question in list and write it in file
			for(BasicCard bc:listCards) {
				print.println(bc.toJson());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (print != null)
				print.close();
		}
	}
	
	//add new basic card to json file
	public static void writeNew(BasicCard bc) {
		PrintWriter print=null;
		try {
			//creation of file / the true tell it be write at the end of the file
			FileWriter fichier = new FileWriter("Questions.json",true);
			//preparation to write in file
			print = new PrintWriter(fichier);
			//write
			print.println(bc.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
		print.close();
	}
	
	//reading file json
	public static List<BasicCard> read() {
		//creation of empty list of questions
		List<BasicCard> listCards=new ArrayList<BasicCard>();
		//reading file json
		try (Scanner scan = new Scanner(new FileReader("Questions.json"))) {
			//we read each line of the file
			while (scan.hasNextLine()) {
				//adding the question of the line to the list
				listCards.add(BasicCard.fromJson(scan.nextLine()));
			}
			//returning list
			return listCards;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
