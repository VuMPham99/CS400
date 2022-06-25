//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AVL.java
// Files:           UTF-8
// Course:          CS 400, Spring, 2019
//
// Author:          Vu Pham 
// Email:           vmpham2@wisc.edu
// Lecturer's Name: Debra Deppler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;

import java.util.List;

import org.w3c.dom.Node;

// A BST search tree that maintains its balance using AVL rotations.
public class AVL<K extends Comparable<K>,V> extends BST<K, V> {
	protected BSTNode<K, V> root;
	protected List<K> keysList = new ArrayList<K>();
	// must add rebalancing to BST via rotate operations

	public boolean contains(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		BSTNode tmp = getRoot();
		if (root == null) {
			return false;
		}
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		while (tmp != null) {
			if (key.compareTo((K) tmp.key) < 0) {
				tmp = tmp.left;
			} else if (key.compareTo((K) tmp.key) > 0) {
				tmp = tmp.right;
			} else if (key.compareTo((K) tmp.key) == 0) {
				return true;
			}
		}
		return false;
	}
	/*
	 * recursive method helps get the key from the node it's in
	 * return the node which has the key
	 */
	private BSTNode<K,V> helpGet(BSTNode<K,V> root, K key)throws KeyNotFoundException {
		if (root == null) {
			throw new KeyNotFoundException();
		}
		else if (key.compareTo(root.key) < 0) {
				return helpGet(root.left,key);
		}else if (key.compareTo(root.key) > 0) {
			return helpGet(root.right,key);
		}else if(root.key == key) {
			return root;
		}
		return root;
	}
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if (contains(key)) {
			return (V) helpGet(getRoot(), key).value;
		}
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}
		return null;
	}
	/*
	 * recursive method helps find the smallest child
	 * use for remove method
	 */
	private BSTNode<K, V> minChild(BSTNode<K, V> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	/*
	 * recursive method helps remove a node then returns the root
	 * which has already removed the deleted node
	 * also rebalanced the tree in the process
	 */
	private BSTNode<K, V> helpRemove(BSTNode<K, V> root, K key) throws KeyNotFoundException {
		//normal remove
		if (root == null) {
			throw new KeyNotFoundException();
		} else if (key.compareTo(root.key) < 0) {
			root.left = helpRemove(root.left, key);
		} else if (key.compareTo(root.key) > 0) {
			root.right = helpRemove(root.right, key);
		}

		else {
			// node has 1 child or no
			if (root.left == null) {
				numKeys--;
				return root.right;
			} else if (root.right == null) {
				numKeys--;
				return root.left;
			}
			// node has 2 children
			else if (root.left!= null && root.right != null) {
				BSTNode<K,V> tmp = minChild(root.right);
				root.key = tmp.key;
				root.right = helpRemove(root.right,tmp.key);
			}
		}
		//UPDATE HEIGHT OF THE CURRENT NODE 
		root.height =1+ maxHeight(height(root.left),height(root.right));
		// get balance factor to see if the node is unbalanced or not
		int balance = getBalance(root);
		//left-left case
		if(balance > 1 && getBalance(root.left) >=0) {
			return rotateRight(root);
		}else if(balance > 1 && getBalance(root.left) <0) {
			root.left = rotateLeft(root.left);
			return rotateRight(root);
		}//left-right case
		else if(balance < -1 && getBalance(root.right)<=0) {
			return rotateLeft(root); //right-right case
		}else if(balance < -1 && getBalance(root.right)>0) {
			root.right = rotateRight(root.right);
			return rotateLeft(root);
		} // right-left case
		return root;
	}
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (contains(key)) {
			root = helpRemove(root, key);
			return true;
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		return false;
	}
	private int height(BSTNode<K,V> node) {
		if(node==null) {
			return 0;
		}
		return node.height + 1;
	}
	/*
	 * recursive method helps insert node into the tree then returns
	 * the root which has already been modified
	 * also rebalanced the tree in the process
	 */
	private BSTNode<K, V> helpInsert(BSTNode<K, V> root, K key, V value) {
		// normal insert
		if (root == null) {
			root = new BSTNode(key, value);
			return root;
		}
		if (key.compareTo(root.key) < 0) {
			root.left = helpInsert(root.left, key, value);
		} else if (key.compareTo(root.key) > 0) {
			root.right = helpInsert(root.right, key, value);
		}else {
			return root;
		}
		//Update height of this ancestor node 
		root.height =1+ maxHeight(height(root.left), height(root.right));
		// get balance factor to see if it unbalanced or not
		int balance = getBalance(root);
		//left-left case
		if(balance > 1 && key.compareTo(root.left.key)<0) {
			return rotateRight(root);
		}else if(balance > 1 && key.compareTo(root.left.key)>0) {
			root.left = rotateLeft(root.left);	
			return rotateRight(root); 
		} // left-right case
		else if(balance <-1 && key.compareTo(root.right.key)>0) {
			return rotateLeft(root); //right-right case
		}else if(balance <-1 && key.compareTo(root.right.key)<0) {
			root.right = rotateRight(root.right);
			return rotateLeft(root);
		} //right-right case
		return root;
	}

	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (contains(key)) {
			throw new DuplicateKeyException();
		}
		root = helpInsert(root, key, value);
		numKeys++;
	}
	/*
	 * return the root
	 */
	private BSTNode<K,V> getRoot() {
		return root;
	}
	@Override
	public K getKeyAtRoot() {
		// TODO Auto-generated method stub
		if (getRoot().key == null) {
			return null;
		} 
		return getRoot().key;
	}
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
				BSTNode tmp = getRoot();
				if (key == null) {
					throw new IllegalNullKeyException();
				}
				if (contains(key)) {
					while (tmp != null) {
						if (key.compareTo((K) tmp.key) < 0) {
							tmp = tmp.left;
						}
						if (key.compareTo((K) tmp.key) > 0) {
							tmp = tmp.right;
						}
						if (key.compareTo((K) tmp.key) == 0) {
							return (K) tmp.left.key;
						}
					}
				}
				if (!contains(key)) {
					throw new KeyNotFoundException();
				}
				return null;
	}
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		BSTNode tmp = getRoot();
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (contains(key)) {
			while (tmp != null) {
				if (key.compareTo((K) tmp.key) < 0) {
					tmp = tmp.left;
				}
				if (key.compareTo((K) tmp.key) > 0) {
					tmp = tmp.right;
				}
				if (key.compareTo((K) tmp.key) == 0) {
					return (K) tmp.right.key;
				}
			}
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}
		return null;
	}
	
	
	/** Rotates node a to the left, making its right child into its parent.
	 * @param a the former parent
	 * @return the new parent (formerly a's right child)
	 */
	private BSTNode<K,V> rotateLeft(BSTNode<K,V>  a) {
		// Perform rotation 
		BSTNode<K,V> b = a.right;
	    a.right = b.left;
	    b.left = a;
	    // Update heights 
	    a.height = maxHeight(height(a.left),height(a.right))+1;
	    b.height = maxHeight(height(b.left),height(b.right))+1;
	    return b;
	}
	private BSTNode<K,V> rotateRight(BSTNode<K,V>  a) {
		// Perform rotation
		BSTNode<K,V> b = a.left;	
		a.left = b.right;
		b.right = a;
		  // Update heights 
	    a.height = maxHeight(height(a.left),height(a.right))+1;
	    b.height = maxHeight(height(b.left),height(b.right))+1;
	    return b;
	}
	private int maxHeight(int height1, int height2) {
		if(height1 > height2) {
			return height1;
		}else {
			return height2;
		}
	}
	private int getBalance(BSTNode<K,V>node) {
		if(node == null) {
			return 0;
		}
		
		return height(node.left) - height(node.right);
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return avlHeight(getRoot());
	}
	@Override
	public List<K> getLevelOrderTraversal() {
		// TODO Auto-generated method stub
		List<K> levelList = new ArrayList<K>();
		for (int i = 1; i <= getHeight(); i++) {
			levelList = levelOrder(getRoot(), i);
		}
		return levelList;
	}
	/*
	 * recursive method helps putting keys into the list and returns
	 * the key list for level-order traversal
	 */
	private List<K> levelOrder(BSTNode<K, V> node, int height) {
		if (node != null) {
			if (height == 1) {
				keysList.add(node.key);
			} else if (height > 1) {
				levelOrder(node.left, height - 1);
				levelOrder(node.right, height - 1);
			}
		}
		return keysList;
	}
	@Override
	public List<K> getPostOrderTraversal() {
		// TODO Auto-generated method stub
		return postOrder(getRoot());
	}
	@Override
	public List<K> getPreOrderTraversal() {
		// TODO Auto-generated method stub
		return preOrder(getRoot());
	}
	@Override
	public List<K> getInOrderTraversal() {
		// TODO Auto-generated method stud
		return inOrder(getRoot());
	}
	/*
	 * recursive method helps putting keys into the list and returns
	 * the key list for in-order traversal
	 */
	private List<K> inOrder(BSTNode<K, V> node) {
		if (node != null) {
			inOrder(node.left);
			keysList.add(node.key);
			inOrder(node.right);
		}
		return keysList;
	}
	/*
	 * recursive method helps putting keys into the list and returns
	 * the key list for post-order traversal
	 */
	private List<K> postOrder(BSTNode<K, V> node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			keysList.add(node.key);
		}
		return keysList;
	}
	/*
	 * recursive method helps putting keys into the list and returns
	 * the key list for pre-order traversal
	 */
	private List<K> preOrder(BSTNode<K, V> node) {
		if (node != null) {
			keysList.add(node.key);
			preOrder(node.left);
			preOrder(node.right);
		}
		return keysList;
	}
	/*
	 * find the height of the node
	 */
	private int avlHeight(BSTNode<K,V> root) {
		/*
		 * if(root != null && root.left == null & root.right == null) { return 1; }
		 */
		if (root == null) {
			return 0;
		} else {
			int leftHeight = avlHeight(root.left);
			int rightHeight = avlHeight(root.right);
			if (leftHeight > rightHeight) {
				return (leftHeight + 1);
			} else {
				return (rightHeight + 1);
			}
		}
	}
	

}
