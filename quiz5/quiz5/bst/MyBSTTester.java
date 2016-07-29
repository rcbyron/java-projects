package quiz5.bst;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyBSTTester {
	
	@Test
	public void testAdd() {
		BSearchTree<Integer> intTree = new BSearchTree<Integer>();
		
		intTree.add(4);
		//intTree.print();
		intTree.add(3);
		//intTree.print();
		intTree.add(6);
		//intTree.print();
		intTree.add(1);
		//intTree.print();
		intTree.add(7);
		//intTree.print();
		intTree.add(5);
		//intTree.print();
		
		assertEquals(intTree.overallRoot.getData(), new Integer(4));
		
	}
	
	@Test
	public void testFind() {
		BSearchTree<Integer> intTree = new BSearchTree<Integer>();
		for (int i = -5; i < 6; i++)
			intTree.add(i);
		
		
		assertTrue(intTree.find(3));
		assertTrue(intTree.find(1));
		assertTrue(intTree.find(5));
		assertTrue(intTree.find(-1));
		assertFalse(intTree.find(8));
		assertFalse(intTree.find(-8));
	}
	
	@Test
	public void testDuplicates() {
		BSearchTree<Integer> intTree = new BSearchTree<Integer>();
		for (int i = -5; i < 6; i++)
			intTree.add(i);
		intTree.add(5);
		intTree.add(5);
		//intTree.print();
		assertTrue(intTree.find(5));
	}

}
