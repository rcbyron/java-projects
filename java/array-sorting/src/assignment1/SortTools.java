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

	/* Return true if invalid arguments are given */
	private static boolean invalidArgs(int[] x, int n, String funcName) {
		if (x == null) {
			System.out.println("Error: x cannot be null in '"+funcName+"'");
			return true;
		}
		if (n < 0 || n > x.length) {
			System.out.println("Error: n was "+n+" but must be in range 0 to "+x.length+" in 'isSorted'");
			return true;
		}
		return false;
	}
	
	/* Returns true if the first n elements of x are sorted
	 * in non-decreasing order, returns false otherwise. */
	public static boolean isSorted(int[] x, int n) {
		if (invalidArgs(x, n, new String("isSorted")))
			return false;
		
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
	 * or -1 if v is not found */
	public static int find(int[] x, int n, int v) {
		if (invalidArgs(x, n, new String("find")))
			return -1;
		
		if (n < 1 || v < x[0] || x[n-1] < v)
			return -1;
		
		// Basic binary search
		int low = 0;
		int high = n-1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (x[mid] < v)
				low = mid + 1;
			else if (x[mid] > v)
				high = mid - 1;
			else
				return mid;
		}
		return -1;
	}
	
	/* Returns a newly created sorted array with value v */
	public static int[] insertGeneral(int[] x, int n, int v) {
		if (invalidArgs(x, n, new String("insertGeneral")))
			return x;
		
		// Return a copy of the array x if v is found in x
		int vIndex = find(x, n, v);
		if (vIndex != -1)
			return Arrays.copyOf(x, n);
		
		// Otherwise create a new array with v inserted into x
		int[] newArr = new int[n + 1];
		int i = 0;
		while (i < n && x[i] < v) {
			newArr[i] = x[i];
			i++;
		}
		newArr[i] = v;
		for (i = i + 1; i < n + 1; i++)
			newArr[i] = x[i - 1];
		return newArr;
	}
	
	/* Modifies array x by ensuring value v is in x
	 * Returns n, if v is in x, otherwise n + 1 */
	public static int insertInPlace(int[] x, int n, int v) {
		if (invalidArgs(x, n, new String("insertInPlace")))
			return -1;
		
		int vIndex = find(x, n, v);
		if (vIndex != -1)
			return n;
		
		int i = 0;
		while (i < n && x[i] < v) i++;
		for (int j = n; j > i; j--)
			x[j] = x[j - 1];
		x[i] = v;
		return n + 1;
	}
	
	/* Sorts the first n elements of an array using insertion sort */
	public static void insertSort(int[] x, int n) {
		if (invalidArgs(x, n, new String("insertSort")))
			return;
		
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0; j--) {
				if (x[j] < x[j-1]) {
					int temp = x[j];
					x[j] = x[j-1];
					x[j-1] = temp;
				}
			}
		}
	}
	
}
