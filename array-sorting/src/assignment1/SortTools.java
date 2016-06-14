// SortTools.java 
/*
 * EE422C Project 1 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Summer 2016
 */

package assignment1;

import java.util.Arrays;

public class SortTools {

	/* Returns true if the first n elements of x are sorted
	 * in non-decreasing order, returns false otherwise. */
	public static boolean isSorted(int[] x, int n) {
		if (x == null) {
			System.out.println("Error: x cannot be null, 'isSorted' returning false");
			return false;
		}
		if (n < 0 || n > x.length) {
			System.out.println("Error: n was "+n+" but must be in range 0 to "+x.length+", 'isSorted' returning false");
			return false;
		}
		if (n == 0)
			return false;
		
		int prev = 0;
		for (int i = 1; i < n; i++) {
			// System.out.println("Comparing: "+x[prev]+", "+x[i]);
			if (x[prev] > x[i])
				return false;
			prev = i;
		}
		return true;
	}
	
	/* Finds and returns the index of v in the first n elements of x
	 * or -1 if v is not found
	 */
	public static int find(int[] x, int n, int v) {
		if (x == null) {
			System.out.println("Error: x cannot be null, 'find' returning -1");
			return -1;
		}
		if (n < 0 || n > x.length) {
			System.out.println("Error: n was "+n+" but must be in range 0 to "+x.length+", 'find' returning -1");
			return -1;
		}
		if (n < 1 || v < x[0] || x[n-1] < v)
			return -1;
		
		int low = 0;
		int high = n-1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (x[mid] == v)
				return mid;
			else if (x[mid] < v)
				low = mid;
			else if (x[mid] > v)
				high = mid;
		}
		return -1;
	}
	
	public static int findInsertSpot(int[] x, int n, int v) {
		if (n == 0 || v < x[0])
			return 0;
		if (x[n - 1] < v)
			return n;
		for (int i = 0; i < n; i++) {
			if (x[i] < v && i + 1 < n && v < x[i + 1])
				return i;
		}
		return -1;
	}
	
	public static int[] insertGeneral(int[] x, int n, int v) {
		int vIndex = find(x, n, v);
		if (vIndex != -1)
			return Arrays.copyOf(x, n);
		
		if (x[n - 1] < v) {
			int[] newArr = Arrays.copyOf(x, n + 1);
			newArr[n] = v;
			return newArr;
		}
		int[] newArr = new int[n + 1];
		if (n == 0 || v < x[0]) {
			newArr[0] = v;
			for (int i = 0; i < n; i++)
				newArr[i+1] = v;
			return newArr;
		}
		int parity = 0;
		for (int i = 0; i < n; i++) {
			if (x[i - 1] < v && i + 1 < n && v < x[i + 1]) {
				newArr[i + 1] = v;
				parity = 1;
				continue;
			}
			newArr[i + parity] = x[i];
		}
		return newArr;
	}
	
	public static int insertInPlace(int[] x, int n, int v) {
		return -1;
	}
	
}
