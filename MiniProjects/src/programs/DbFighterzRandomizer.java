package programs;

import java.util.Random;
import java.util.Scanner;

public class DbFighterzRandomizer {
	
	private String[] characterNames = new String[] {
			"Android 16",
			"Android 18",
			"Android 21",
			"Beerus",
			"Broly",
			"Bardock",
			"Captain Ginyu",
			"Cell",
			"Frieza",
			"Gohan (Adult)",
			"Gohan (Teen)",
			"Goku Black",
			"Goku (Super Saiyan)",
			"Goku (SSGSS)",
			"Gotenks",
			"Hit",
			"Kid Buu",
			"Krillin",
			"Majin Buu",
			"Nappa",
			"Picollo",
			"Tien",
			"Trunks",
			"Vegeta (Super Saiyan)",
			"Vegeta (SSGSS)",
			"Vegito (SSGSS)",
			"Yamcha",
			"Zamasu (fused)"
	};
	
	public void run() {
		System.out.println("Press enter to roll for a random character.");
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			String input = scanner.nextLine();
			
			if (input != null && !input.equals("")) {
				//scanner.close();
				return; // stop execution
			}
			
			int randomIndex = new Random().nextInt(characterNames.length - 1);
			String randomCharacterName = characterNames[randomIndex];
			
			System.out.println("Your character is: " + randomCharacterName);
			System.out.println("\n\nType anything to quit, press enter for another one.\n");
		}
	}
}
