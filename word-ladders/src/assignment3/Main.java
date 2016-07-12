/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Zain Modi
 * zam374
 * 76550
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Slip days used: 0
 * Fall 2015
 */

package assignment3;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		
		// Check for command
		while (sc.hasNext()) {
			String token1 = sc.next();
			if (token1.charAt(0) == '/') {
				runCommand(token1);
				continue;
			}
			String token2 = sc.next();
			
			// Find and output word ladder
			showWordLadder(getWordLadderDFS(token1, token2), token1, token2);	
		}

		sc.close();
	}
	
	private static void showWordLadder(ArrayList<String> wordLadder, String w1, String w2) {
		w1 = w1.toLowerCase();
		w2 = w2.toLowerCase();
		if (wordLadder.size() == 0)
			System.out.println("no word ladder can be found between "+w1+" and "+w2+".");
		else {
			System.out.println("a "+wordLadder.size()+"-rung word ladder exists between "+w1+" and "+w2+".");
			for (String word : wordLadder)
				System.out.println("\t"+word.toLowerCase());
		}
		
	}
	
	
	private static void runCommand(String cmd) {
		switch(cmd.toLowerCase()) {
		case "/quit": 	System.exit(0); break;
		default: 		System.out.println("invalid command "+cmd);
		}
	}
	

	private static ArrayList<String> getNeighbors(String word, Set<String> dict) {
		ArrayList<String> neighbors = new ArrayList<String>();
		Iterator<String> iter = dict.iterator();
		while (iter.hasNext()) {
			String dictWord = iter.next();
			for (int i = 0; i < word.length(); i++) {
				if (word.substring(0, i).equalsIgnoreCase(dictWord.substring(0, i))
				    && word.substring(i + 1).equalsIgnoreCase(dictWord.substring(i + 1)))
					neighbors.add(dictWord);
			}
		}
		return neighbors;
	}
	

	private static ArrayList<String> helperDFS(String start, String end, Set<String> dict, Set<String> visited, ArrayList<String> path) {
		visited.add(start);
		if (start.equalsIgnoreCase(end))
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
		start = start.toLowerCase();
		end = end.toLowerCase();
		Set<String> dict = makeDictionary();
		if (start.equalsIgnoreCase(end) || !dict.contains(start) || !dict.contains(end))
			return new ArrayList<String>();
		
		ArrayList<String> path = helperDFS(start, end, dict, new HashSet<String>(), new ArrayList<String>());
		if (path == null)
			return new ArrayList<String>();
		
		path.add(0, start);
		return path;
	}

	public static ArrayList<String> checkDupes(ArrayList<String> matches, ArrayList<String> visited) {
		for (int i = 0; i < matches.size(); i++) {
			if (visited.contains(matches.get(i))) {
				matches.remove(i);
				i--;
			}
		}
		return matches;
	}

	public static ArrayList<String> findWord(String word, Set<String> dict, String end, ArrayList<String> visited) {
		Iterator<String> itr = dict.iterator();
		ArrayList<String> matches = new ArrayList<String>();
		String temp;
		String check_1;
		String check_2;
		int counter = 0;

		while (itr.hasNext()) {
			temp = itr.next();
			for (int i = 0; i < temp.length(); i++) {
				check_1 = temp.substring(i, i + 1);
				check_2 = word.substring(i, i + 1);
				if (check_1.equals(check_2)) {
					counter++;
				}
			}
			if (counter == 4) {
				matches.add(temp);
				counter = 0;
			} else {
				counter = 0;
			}
		}

		return checkDupes(matches, visited);
	}
	public static Queue<String> addMatchesToQueue(Queue<String> nodes, ArrayList<String> matches){
		Iterator<String> itr = matches.iterator();
		while (itr.hasNext())
			nodes.add(itr.next());
		return nodes;
	}

	public static ArrayList<ArrayList<String>> addMatchesToTracker(ArrayList<String> matches, ArrayList<ArrayList<String>> tracker, String head){
		Iterator<String> itr = matches.iterator();
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> tempp = new ArrayList<String>();
		String next;
		
		for(int i = 0; i < tracker.size(); i++){
			if(tracker.get(i).contains(head)){
				temp = (ArrayList<String>)tracker.get(i).clone();
				while(itr.hasNext()){
					next = itr.next();
					tempp = (ArrayList<String>) temp.clone();
					tempp.add(next);
					tracker.add(tempp);
				}
				return tracker;
			}
		}
		return tracker;
	}
	
	public static ArrayList<String> printResults(ArrayList<ArrayList<String>> tracker, String start, String end){
		ArrayList<String> finals = new ArrayList<String>();
		
		for(int i = 0; i < tracker.size(); i++){
			if (tracker.get(i).contains(end))
				finals = (ArrayList<String>) tracker.get(i).clone();
		}
		return finals;
	}
	public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Set<String> dict = makeDictionary();
		Queue<String> nodes = new LinkedList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> matches = new ArrayList<String>();
		ArrayList<ArrayList<String>> tracker = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		Queue<String> traverse = new LinkedList<String>();
		String head;
		
		if(!dict.contains(start.toUpperCase()) || !dict.contains(end.toUpperCase()) || start.equals(end))
			return new ArrayList<String>();
		
		start = start.toUpperCase();
		end = end.toUpperCase();
		nodes.add(start);
		temp.add(start);
		tracker.add(temp);
		while (!nodes.isEmpty()) {
			head = nodes.peek();
			matches = findWord(head, dict, end, visited);
			nodes = addMatchesToQueue(nodes, matches);
			tracker = addMatchesToTracker(matches, tracker, head);
			visited.addAll(matches);
			if (matches.contains(end)) {
				matches = printResults(tracker, start, end);
				return matches; 
			} else {
				traverse.add(nodes.peek());
				nodes.poll();
			}
		}
		return new ArrayList<String>();
	}

	public static Set<String> makeDictionary() {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner(new File("src/assignment3/five_letter_words.txt"));
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
