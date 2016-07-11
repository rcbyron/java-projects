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

		Scanner kb = new Scanner(new File("src/short_dict.txt"));
		// System.out.println(getNeighbors("STONE", makeDictionary()));
		// TODO methods to read in words, output ladder
		// while (kb.hasNextLine()) {
		// System.out.println(kb.nextLine());
		// }

		getWordLadderBFS("STONE", "MONEY");
	}

	private static Set<String> getNeighbors(String word, Set<String> dict) {
		Set<String> neighbors = new HashSet<String>();
		Iterator<String> iter = dict.iterator();
		while (iter.hasNext()) {
			String dictWord = iter.next();
			for (int i = 0; i < word.length(); i++) {
				if (word.substring(0, i).equals(dictWord.substring(0, i))
						&& word.substring(i + 1).equals(dictWord.substring(i + 1)))
					neighbors.add(dictWord);
			}
		}
		return neighbors;
	}

	private static ArrayList<String> helperDFS(String start, String end, Set<String> dict, Set<String> visited,
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
		if (start.equals(end))
			return new ArrayList<String>();
		Set<String> dict = makeDictionary();
		ArrayList<String> path = helperDFS(start, end, dict, new HashSet<String>(), new ArrayList<String>());
		if (path == null)
			path = new ArrayList<String>();
		else
			path.add(0, start);
		return path;
	}

	public static boolean checkForMatch(ArrayList<String> matches, String end) {
		Iterator<String> itrr = matches.iterator();
		String temp;

		while (itrr.hasNext()) {
			temp = itrr.next();
			if (temp.equals(end)) {
				return true;
			}
		}
		return false;

	}

	public static ArrayList<String> checkDupes(ArrayList<String> matches, ArrayList<String> visited) {
		Iterator<String> itr = matches.iterator();
		String next;

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
		while(itr.hasNext()){
			nodes.add(itr.next());
		}
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
	
	public static ArrayList<String> printResults(ArrayList<ArrayList<String>> tracker, String end){
		ArrayList<String> finals = new ArrayList<String>();
		
		for(int i = 0; i < tracker.size(); i++){
			if(tracker.get(i).contains(end)){
				finals = (ArrayList<String>) tracker.get(i).clone();
				System.out.println("Ladder contains " + tracker.get(i).size() + " words.");
				for(int j = 0; j < tracker.get(i).size(); j++){
					System.out.println((tracker.get(i).get(j)).toLowerCase());
				}
			}
		}
		return finals;
	}
	public static ArrayList<String> getWordLadderBFS(String start, String end) {

		// TODO some code
		Set<String> dict = makeDictionary();
		Queue<String> nodes = new LinkedList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> matches = new ArrayList<String>();
		ArrayList<ArrayList<String>> tracker = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		Queue<String> traverse = new LinkedList<String>();
		String head;
		
		if(dict.contains(start) && dict.contains(end)){
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
			if (checkForMatch(matches, end)) {
				matches = printResults(tracker, end);
				return matches; 
			}else{
				traverse.add(nodes.peek());
				nodes.poll();
			}
		}
		return null;
		
		}else{
			System.out.println("no word ladder can be found between " + start + " " + end);
			return null;// replace this line later with real return
		}
	}

	public static Set<String> makeDictionary() {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner(new File("src/five_letter_words.txt"));
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
