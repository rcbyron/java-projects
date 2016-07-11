/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2015
 */

package assignment3;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner kb = new Scanner(new File("src/assignment3/short_dict.txt"));
		System.out.println(getNeighbors("STONE", makeDictionary()));
		// TODO methods to read in words, output ladder
//		while (kb.hasNextLine()) {
//			System.out.println(kb.nextLine());
//		}
		
		System.out.println(getWordLadderDFS("STONE", "MONEY"));
	}
	
	private static Set<String> getNeighbors(String word, Set<String> dict) {
		Set<String> neighbors = new HashSet<String>();
		Iterator<String> iter = dict.iterator();
		while (iter.hasNext()) {
			String dictWord = iter.next();
			for (int i = 0; i < word.length(); i++) {
				if (word.substring(0, i).equals(dictWord.substring(0, i)) &&
					word.substring(i+1).equals(dictWord.substring(i+1)))
					neighbors.add(dictWord);
			}
		}
		return neighbors;
	}
	
	private static ArrayList<String> helperDFS(String start, String end, 
										Set<String> dict, Set<String> visited, 
										ArrayList<String> path) {
		visited.add(start);
		if (start.equals(end))
			return path;
		for (String s : getNeighbors(start, dict)) {
			if (!visited.contains(s)) {
				path.add(s);
			    ArrayList<String> testPath = helperDFS(s, end, dict, visited, path);
			    if (testPath != null)
			    	return testPath;
			}
		}
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		if (start.equals(end)) return new ArrayList<String>();
		Set<String> dict = makeDictionary();
		ArrayList<String> path = helperDFS(start, end, dict, new HashSet<String>(), new ArrayList<String>());
		if (path == null)
			path = new ArrayList<String>();
		else
			path.add(0, start);
		return path;
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		Set<String> dict = makeDictionary();

//	    for each node n in Graph:            
//	    	 4         n.distance = INFINITY        
//	    	 5         n.parent = NIL
//	    	 6 
//	    	 7     create empty queue Q      
//	    	 8 
//	    	 9     root.distance = 0
//	    	10     Q.enqueue(root)                      
//	    	11 
//	    	12     while Q is not empty:        
//	    	13     
//	    	14         current = Q.dequeue()
//	    	15     
//	    	16         for each node n that is adjacent to current:
//	    	17             if n.distance == INFINITY:
//	    	18                 n.distance = current.distance + 1
//	    	19                 n.parent = current
//	    	20                 Q.enqueue(n)
		
		return null; // replace this line later with real return
	}
    
	public static Set<String> makeDictionary() {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("src/assignment3/five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
