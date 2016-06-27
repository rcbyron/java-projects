

import java.awt.Point;
import java.util.Random;

public class Code {

	private static final String PLACEHOLDER = "-";
	
	private String[] code;
	
	public Code(String[] code) {
		this.code = code;
	}

	public Code(String code) {
		this.code = new String[code.length()];
		for (int i = 0; i < code.length(); i++){
		    this.code[i] = Character.toString(code.charAt(i));
		}
	}
	
	public Code(int len) {
	    this.code = new String[len];
	    Random r = new Random();
	    for (int i = 0; i < this.code.length; i++)
	        this.code[i] = GameConfiguration.colors[r.nextInt(this.code.length)];
	}
	
	public String[] getCode() {
		return code;
	}
	
	public Point getPegs(Code guess) {
		// Checks the guess against this Code object
		String[] tempCode = code.clone();
		String[] tempGuess = guess.getCode().clone();
		int blackPegs = 0;
		int whitePegs = 0;
		for (int i = 0; i < tempCode.length; i++) {
			//System.out.println("tempCode: "+Arrays.toString(tempCode));
			//System.out.println("tempGuess: "+Arrays.toString(tempGuess));
			if (!(tempCode[i].equals(PLACEHOLDER)) && tempCode[i].equals(tempGuess[i])) {
				tempCode[i] = PLACEHOLDER;
				tempGuess[i] = PLACEHOLDER;
				blackPegs++;
				//System.out.println("Adding black peg");
			}
		}
		for (int i = 0; i < tempCode.length; i++) {
			for (int j = 0; j < tempGuess.length; j++) {
				if (!(tempCode[i].equals(PLACEHOLDER)) && tempGuess[i].equals(tempCode[j])) {
					tempCode[j] = PLACEHOLDER;
					tempGuess[i] = PLACEHOLDER;
					whitePegs++;
					//System.out.println("Adding white peg");
					break;
				}
			}
		}
		return new Point(blackPegs, whitePegs);
	}
	
	@Override
	public String toString() {
		String s = new String("");
		for (int i = 0; i < code.length; i++)
			s += code[i];
		return s;
	}
	
}
