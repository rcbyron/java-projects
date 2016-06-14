package assignment1;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SortToolsTest {

	@Test
	public void test() {
		int[] empty = {}, single = {3}, sorted = {-3, 5, 7, 23};
		int[] unsorted = {4, 3, 6}, repeating = {5, 5, 5, 5,};
		
		//isSorted tests
		assertEquals(false, SortTools.isSorted(empty, 0));
		assertEquals(false, SortTools.isSorted(single, 5));
		assertEquals(true, SortTools.isSorted(single, 1));
		assertEquals(true, SortTools.isSorted(sorted, 4));
		assertEquals(false, SortTools.isSorted(unsorted, 3));
		assertEquals(true, SortTools.isSorted(repeating, 4));
		
		//find tests
		assertEquals(-1, SortTools.find(empty,  0,  0));
		assertEquals(-1, SortTools.find(single,  1,  4));
		assertEquals(0, SortTools.find(single,  1,  3));
		assertEquals(-1, SortTools.find(sorted,  0,  5));
		assertEquals(-1, SortTools.find(sorted,  5,  4));
		assertEquals(-1, SortTools.find(sorted,  4,  4));
		assertEquals(0, SortTools.find(sorted,  4,  -3));
		assertEquals(-1, SortTools.find(sorted,  5,  7));
		assertEquals(2, SortTools.find(sorted,  4,  7));
		assertEquals(0, SortTools.find(sorted,  1,  -3));
		assertEquals(-1, SortTools.find(sorted,  1,  5));
		assertEquals(-1, SortTools.find(sorted,  1,  4));
		assertEquals(1, SortTools.find(repeating,  4,  5));
		/*
		//insertGeneral tests
		assertEquals(empty, SortTools.insertGeneral(empty,  1,  7));
		assertEquals(empty, SortTools.insertGeneral(empty,  -1,  7));
		int[] notEmpty = {7};
		assertArrayEquals(notEmpty, SortTools.insertGeneral(empty, 0, 7));
		int[] arr = {2, 7};
		assertArrayEquals(arr, SortTools.insertGeneral(notEmpty, 1, 2));
		assertArrayEquals(arr, SortTools.insertGeneral(notEmpty, 1, 2));
		assertArrayEquals(sorted, SortTools.insertGeneral(sorted, 4, 23));
		int[] sorted1 = {-3, 2, 5, 7, 23};
		assertArrayEquals(sorted1, SortTools.insertGeneral(sorted, 4, 2));
		assertArrayEquals(sorted, SortTools.insertGeneral(sorted, 4, 7));
		int[] sorted2 = {-9, -3, 5, 7, 23};
		assertArrayEquals(sorted2, SortTools.insertGeneral(sorted, 4, -9));
		int[] sorted3 = {-3, 5, 7, 23, 29};
		assertArrayEquals(sorted3, SortTools.insertGeneral(sorted, 4, 29));
		
		//insertInPlace tests
		int[] empty1 = {1};
		int[] empty2 = empty1;
		assertEquals(1, SortTools.insertInPlace(empty1, 0, 0));
		assertEquals(empty1, empty2);
		assertEquals(0, empty1[0]);
		int[] sorted4 = sorted;
		assertEquals(4, SortTools.insertInPlace(sorted, 3, -6));
		assertEquals(sorted, sorted4);
		assertEquals(-6, sorted[0]);
		
		//insertSort tests
		SortTools.insertSort(unsorted, 3);
		int[] sorted5 = {3, 4, 6};
		assertArrayEquals(sorted5, unsorted);
		int[] unsorted1 = {5, 2, 17, 97, 26, 43, -1};
		int[] sorted6 = {2, 5, 17, 26, 43, 97};
		SortTools.insertSort(unsorted1,  6);
		//assertArrayEquals(sorted6, unsorted1);
		int[] repeating2 = {5, 5, 5, 5};
		SortTools.insertSort(repeating, 4);
		assertArrayEquals(repeating2, repeating);*/
	}

}
