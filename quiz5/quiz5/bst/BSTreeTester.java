package quiz5.bst;

public class BSTreeTester {

	public static void main(String[] args) {

		BSearchTree<Integer> intTree = new BSearchTree<Integer>();
		
		intTree.add(4);
		intTree.add(3);
		intTree.add(6);
		intTree.add(1);
		intTree.add(7);
		intTree.add(5);
		intTree.print();
		System.out.println("-------remove 7 below -------------------");
		intTree.remove(7);
		intTree.print();
		System.out.println("-------remove 3 below -------------------");
		intTree.remove(3);
		intTree.print();
		System.out.println("-----------------------------------------");
		intTree = new BSearchTree<Integer>();
		
		intTree.add(4);
		intTree.add(3);
		intTree.add(6);
		intTree.add(1);
		intTree.add(7);
		intTree.add(5);
		intTree.print();
		System.out.println("--------------remove 6--------------------");
		intTree.remove(6);
		intTree.print();
		System.out.println("--------------remove 4-------------------");
		intTree.remove(4);
		intTree.print();
		System.out.println(intTree.find(3));
		System.out.println( intTree.find(1));
		System.out.println( intTree.find(7));
		System.out.println( !intTree.find(8));
		System.out.println( !intTree.find(0));	
	}

}
