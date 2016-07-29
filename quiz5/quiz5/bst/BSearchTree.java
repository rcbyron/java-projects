package quiz5.bst;

import java.util.Stack;

public class BSearchTree<E extends Comparable<E>> {

	BSTNode<E> overallRoot;
	static int SPACES_PER_LEVEL = 4;

	public BSearchTree() {
		this.overallRoot = null;
	}

	public void print() {
		print(overallRoot, 0);
	}

	public void add(E value) {
		overallRoot = add(overallRoot, value);
	}

	/**
	 * BST insert method
	 * 
	 * @param root
	 *            BST root node
	 * @param value
	 *            the inserted value, allow duplicate values.
	 * @return BST root node
	 */
	private BSTNode<E> add(BSTNode<E> root, E value) {
		BSTNode<E> node = new BSTNode<E>();
		node.setData(value);
		
		if (root == null) { return node; }
		
		int comp = root.getData().compareTo(value);
		//System.out.println(comp);
		if (comp > 0)
			root.setLeft(add(root.getLeft(), value));
		else
			root.setRight(add(root.getRight(), value));
		
		return root;
	}
	
	private void print(BSTNode<E> root, int level) {
		if (root == null)
			return;
		if (root.isLeaf()) {
			for (int i = 0; i < level * SPACES_PER_LEVEL; i++) {
				System.out.print(" ");
			}
			System.out.println(root);
		} else {
			print(root.right, level + 1);
			for (int i = 0; i < level * SPACES_PER_LEVEL; i++) {
				System.out.print(" ");
			}
			System.out.println(root);
			print(root.left, level + 1);
		}
	}

	public boolean find(E value) {
		return find(overallRoot, value);
	}

	/**
	 * Find method in BST
	 * 
	 * @param root
	 *            BST root
	 * @param value
	 *            searched value
	 * @return true if the value is found in the BST
	 */
	private boolean find(BSTNode<E> root, E value) {
		Stack<BSTNode<E>> s = new Stack<BSTNode<E>>();
		s.push(root);
		while (!s.isEmpty()) {
			BSTNode<E> curr = s.pop();
			if (curr.getData().equals(value)) return true;
			if (curr.hasLeftChild()) s.push(curr.getLeft());
			if (curr.getRight() != null) s.push(curr.getRight());
		}
		return false;
	}

	public void remove(E value) {
		overallRoot = remove(overallRoot, value);
	}

	/**
	 * BST remove method
	 * 
	 * @param root
	 *            BST root node
	 * @param value
	 * @return
	 */
	private BSTNode<E> remove(BSTNode<E> root, E value) {
		// You dont need to implement this method now.
//		if (root == null) return null;
//		Stack<BSTNode<E>> s = new Stack<BSTNode<E>>();
//		s.push(root);
//		while (!s.isEmpty()) {
//			BSTNode<E> curr = s.pop();
//			if (curr.getData().equals(value)) {
//				// Find replacement value
//			}
//		}
		return root;
	}
}
