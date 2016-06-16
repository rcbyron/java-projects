package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameRunner {

	public static void main(String[] args) {
		try {
			// Print intro text
			Scanner intro = new Scanner(new File("src/assignment2/intro.txt"));
			while (intro.hasNextLine())
				System.out.println(intro.nextLine());
			intro.close();
			
			// Check if user is ready to play
			System.out.print("You have "+GameConfiguration.guessNumber+" to figure out the secret code or you lose the game. Are you ready to play? (Y/N): ");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			sc.close();
			if (s.equalsIgnoreCase("y"))
				// Play the game
				System.out.print("Playing game...");
			else
				System.exit(0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
