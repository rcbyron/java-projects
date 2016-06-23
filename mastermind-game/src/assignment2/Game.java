package assignment2;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

	private boolean debug;
	
	public Game(boolean debug) {
		this.debug = debug;
		this.debug = false;
	}
	
	private boolean hadError(Code code, Code guess) {
		// Invalid peg length
		if (guess.getCode().length != code.getCode().length)
			return true;
		
		// Invalid color
		List<String> validColors = Arrays.asList(GameConfiguration.colors);
		for (String guessColor : guess.getCode())
			if (!(validColors.contains(guessColor)))
				return true;
		
		return false;
	}
	
	private void runGame() {
		Code code = new Code(GameConfiguration.pegNumber);
		System.out.println("\nGenerating secret code ....\n");
		boolean fail = false;
		
	    for (int i = GameConfiguration.guessNumber; i > 0; i--) {
	    	if (debug)
	    		System.out.println("\nSecret Code (debug mode): "+code+"\n");
	    	if (!fail)
	    		System.out.println("You have "+i+" guesses left.");
	    	System.out.println("What is your next guess?");
	    	System.out.println("Type in the characters for your guess and press enter.");
			System.out.print("Enter guess: ");
		    Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			
			Code guess = new Code(s);

			fail = hadError(code, guess);
			if (fail) {
				System.out.println(guess + " ->  " + "INVALID GUESS\n");
				i++;
				continue;
			}
			
			Point pegs = code.getPegs(guess);
			String blackPegStr = pegs.x > 0 ? pegs.x + " black peg" : "no black pegs";
			String whitePegStr = pegs.y > 0 ? pegs.y + " white peg" : "no white pegs";
			if (pegs.x > 1)
				blackPegStr += "s";
			if (pegs.y > 1)
				whitePegStr += "s";
			
			System.out.println("\n" + guess + " ->  " + "Result: " + blackPegStr + ", " + whitePegStr + "\n");
	    }
	}
	
	public static void main(String[] args) {
		try {
			// Print intro text
			Scanner intro = new Scanner(new File("src/assignment2/intro.txt"));
			while (intro.hasNextLine())
				System.out.println(intro.nextLine());
			intro.close();
			
			// Check if user is ready to play
			System.out.print("\nYou have "+GameConfiguration.guessNumber+" to figure out the secret code or you lose the game.  Are you ready to play? (Y/N): ");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			if (s.equalsIgnoreCase("y")) {
				// Play the game
				Game g = new Game(true);
				g.runGame();
				
				sc.close();
			} else
				System.exit(0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
