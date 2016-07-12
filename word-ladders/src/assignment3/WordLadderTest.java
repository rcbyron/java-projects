/** Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */
package assignment3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class WordLadderTest {

	@Test
	public void testDFS() {
		System.out.println("Starting DFS test...");
		// Wrong word size
		assertEquals(Main.getWordLadderDFS("STONE", "MONEYY").size(), 0);
		
		// Same word
		assertEquals(Main.getWordLadderDFS("STONE", "STONE").size(), 0);
		
		// No ladder exists
		assertEquals(Main.getWordLadderBFS("SNEER", "GANJA").size(), 0);
		
		// 1 Word ladder exists
		assertEquals(Main.getWordLadderDFS("STONE", "ATONE").size(), 2);
		
		// Ignore cases
		assertEquals(Main.getWordLadderDFS("sToNe", "AtOnE").size(), 2);
		
		// Long ladder exists
		ArrayList<String> ladder = Main.getWordLadderDFS("STONE", "MONEY");
		assertEquals(ladder.size() != 0,       true);
		assertEquals(ladder.contains("stone"), true);
		assertEquals(ladder.contains("stony"), true);
		assertEquals(ladder.contains("money"), true);
		assertEquals(ladder.contains(""),      false);
		assertEquals(ladder.contains(null),    false);
		
		System.out.println("DFS test passed!");
	}

	@Test
	public void testBFS() {
		System.out.println("Starting BFS test...");
		// Wrong word size
		assertEquals(Main.getWordLadderBFS("ATONE", "MONEYY").size(), 0);
		
		// Same word
		assertEquals(Main.getWordLadderBFS("ATONE", "ATONE").size(), 0);
		
		// No ladder exists
		assertEquals(Main.getWordLadderBFS("SNEER", "GANJA").size(), 0);
		
		// 1 Word ladder exists
		assertEquals(Main.getWordLadderBFS("ATONE", "STONE").size(), 2);
	
		// Ignore Cases
		assertEquals(Main.getWordLadderBFS("AtOnE", "sToNe").size(), 2);
		
		// Long ladder exists
		ArrayList<String> ladder = Main.getWordLadderDFS("STONE", "MONEY");
		assertEquals(ladder.size() != 0,       true);
		assertEquals(ladder.contains("stone"), true);
		assertEquals(ladder.contains("stony"), true);
		assertEquals(ladder.contains("money"), true);
		assertEquals(ladder.contains(""),      false);
		assertEquals(ladder.contains(null),    false);
		
		System.out.println("BFS test passed!");
	}

}
