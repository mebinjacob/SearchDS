package edu.ufl.mebin;

/**
 * A Red Black tree to store only key values i.e., the long values of the task
 * given
 * 
 * @author mebin
 *
 */
public class Tree implements DataStructure {

	public Node nil = new Node(0);
	private Node root;

	public class Node {
		private long key;
		private boolean redColor;
		private Node left;
		private Node right;
		private Node parent;

		Node(long val) {
			this.key = val;
			this.redColor = true;
			this.right = nil;
			this.left = nil;
		}
	}

	@Override
	public boolean insert(long value) {

		if (root != null && contains(value))
			return false;
		if (root == null) {
			Node node = new Node(value);
			root = node;
		} else {
			Node y = findNodeOrParent(value);
			insert(value, y);
		}
		return true;
	}

	@Override
	public boolean contains(long value) {
		Node x = findNodeOrParent(value);
		if (x.key == value) {
			return true;
		}
		return false;
	}

	private void insert(long value, Node y) {
		Node z = new Node(value);
		if (root != null) {
			z.parent = y;
		} else {
			root = z;
			root.redColor = false;
			z.parent = null;
		}

		if (y == this.nil) {
			this.root = z;
		} else if (z.key < y.key) {
			y.left = z;
		} else {
			y.right = z;
		}
		z.left = this.nil;
		z.right = this.nil;
		z.redColor = true;
		if (z.parent != null)
			insertFixup(z);
		// return z;
	}

	private void insertFixup(Node z) {
		while (z.parent.redColor && z.parent.parent != null) {
			Node y = null;
			if (z.parent == z.parent.parent.left) {
				y = z.parent.parent.right; // verified
				if (y.redColor) { // Case 1
					z.parent.redColor = false; // verified
					y.redColor = false; // verified
					z.parent.parent.redColor = true; // verified..pushing up the
														// problem
					z = z.parent.parent; // hence pushed z
				} else {
					if (z == z.parent.right) {// case 2
						z = z.parent;// verified
						leftRotate(z);
					}
					z.parent.redColor = false; // case 3
					z.parent.parent.redColor = true; // verified
					rightRotate(z.parent.parent); // verified
				}

			} else {
				y = z.parent.parent.left;
				if (y.redColor) {
					z.parent.redColor = false;
					y.redColor = false;
					z.parent.parent.redColor = true;
					z = z.parent.parent;
				} else {
					if (z == z.parent.left) {
						z = z.parent;
						rightRotate(z);
					}
					z.parent.redColor = false;
					z.parent.parent.redColor = true;
					leftRotate(z.parent.parent);
				}

			}
		}
		this.root.redColor = false;
	}

	private void rightRotate(Node y) {
		Node x = y.left;
		y.left = x.right;
		if (x.right != this.nil) {
			x.right.parent = y;
		}
		x.parent = y.parent;
		if (y.parent == this.nil) {
			this.root = x;
		} else if (y == y.parent.right) {
			y.parent.right = x;
		} else {
			y.parent.left = x;
		}
		x.right = y;
		y.parent = x;
	}

	private void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != this.nil) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == this.nil) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	private Node findNodeOrParent(long val) {
		Node y = this.nil;
		Node x = this.root;
		while (x != this.nil) {
			y = x;
			if (x.key == val) {
				return y; // the node is found
			} else if (val < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		return y;
	}
}
