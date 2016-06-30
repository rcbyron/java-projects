/*  * EE422C Project 2 (Mastermind) submission by
 * Robert (Connor) Byron
 * rcb2746
 * Haoran Niu
 * hn4582
 * https://github.com/rcbyron/java/tree/master/mastermind-game/src/assignment2
 * Slip days used: 0
 * Summer 2016
 */

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

	private boolean debug;
	private ArrayList<String> history;
	private Scanner sc;
	
	public Game(boolean debug) {
		this.debug = debug;
		sc = new Scanner(System.in);
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

	private void showHistory() {
		System.out.println("\nHistory: ");
		for (String item : history)
			System.out.println(item);
		System.out.println();
	}
	
	private void runGame() {
		System.out.println("\nGenerating secret code ....\n");
		Code code = new Code(GameConfiguration.pegNumber);

		boolean invalidInput = false;
		history = new ArrayList<String>();
		
	    for (int i = GameConfiguration.guessNumber; i > 0; i--) {
	    	if (debug)
	    		System.out.println("Secret Code (debug mode): "+code+"\n");
	    	if (!invalidInput)
	    		System.out.println("You have "+i+" guesses left.");
	    	
	    	System.out.println("What is your next guess?");
	    	System.out.println("Type in the characters for your guess and press enter.");
			System.out.print("Enter guess: ");
			String s = sc.nextLine();
			
			if (s.equalsIgnoreCase("history")) {
				showHistory();
				i++;
				continue;
			}
			
			Code guess = new Code(s);

			invalidInput = hadError(code, guess);
			if (invalidInput) {
				System.out.println(guess + " ->  " + "INVALID GUESS\n");
				i++;
				continue;
			}
			
			if (i <= 1) {
				System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.\n");
				break;
			}
			
			System.out.print("\n" + guess + " ->  " + "Result:  ");
			
			Point pegs = code.getPegs(guess);
			String blackPegStr = pegs.x > 0 ? pegs.x + " black peg" : "no black pegs";
			String whitePegStr = pegs.y > 0 ? pegs.y + " white peg" : "no white pegs";
			if (pegs.x > 1)
				blackPegStr += "s";
			if (pegs.y > 1)
				whitePegStr += "s";
			
			String result = "";
			result = blackPegStr + ", " + whitePegStr;
			
			if (pegs.x == code.getCode().length)
				result = pegs.x +" black pegs -- You win!!";
			
			history.add(guess + "\t\t" + result);
			System.out.println(result + "\n");
			
			if (pegs.x == code.getCode().length)
				break;
	    }

	}
	
	public static void main(String[] args) {
		try {
			// Print intro text
			Scanner intro = new Scanner(new File("src/intro.txt"));
			while (intro.hasNextLine())
				System.out.println(intro.nextLine());
			intro.close();
			
			// Check if user is ready to play
			System.out.print("\nYou have "+GameConfiguration.guessNumber+" to figure out the secret code or you lose the game.  Are you ready to play? (Y/N): ");

			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			if (s.equalsIgnoreCase("y")) {
				// Play the game
				while (s.equalsIgnoreCase("y")) {
					Game g = new Game(true);
					g.runGame();
				    System.out.print("Are you ready for another game (Y/N): ");
				    s = sc.nextLine();
				}
			    	
				sc.close();
			} else
				System.exit(0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
