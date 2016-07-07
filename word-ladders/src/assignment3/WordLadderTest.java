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
		assertEquals(Main.getWordLadderDFS("STONE", "BBBBB").size(), 0);
		
		// 1 Word ladder exists
		assertEquals(Main.getWordLadderDFS("STONE", "ATONE").size(), 1);
		
		// Long ladder exists
		ArrayList<String> ladder = Main.getWordLadderDFS("STONE", "MONEY");
		assertEquals(ladder.size() != 0,       true);
		assertEquals(ladder.contains("STONE"), true);
		assertEquals(ladder.contains("STONY"), true);
		assertEquals(ladder.contains("MONEY"), true);
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
		assertEquals(Main.getWordLadderBFS("ATONE", "BBBBB").size(), 0);
		
		// 1 Word ladder exists
		assertEquals(Main.getWordLadderBFS("ATONE", "STONE").size(), 1);
		
		// Long ladder exists
		ArrayList<String> ladder = Main.getWordLadderDFS("STONE", "MONEY");
		assertEquals(ladder.size() != 0,       true);
		assertEquals(ladder.contains("STONE"), true);
		assertEquals(ladder.contains("STONY"), true);
		assertEquals(ladder.contains("MONEY"), true);
		assertEquals(ladder.contains(""),      false);
		assertEquals(ladder.contains(null),    false);
		
		System.out.println("BFS test passed!");
	}

}
