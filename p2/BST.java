//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BST.java
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

import java.util.ArrayList; // allowed for creating traversal lists
import java.util.List; // required for returning List<K>

// TODO: Implement a Binary Search Tree class here
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

	// Tip: Use protected fields so that they may be inherited by AVL
	protected BSTNode<K, V> root;
	protected int numKeys; // number of keys in BST
	protected List<K> keysList = new ArrayList<K>(); // list to stor keys in traversal orders

	// Must have a public, default no-arg constructor
	public BST() {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getPreOrderTraversal()
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		// TODO Auto-generated method stub
		return preOrder(root);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getPostOrderTraversal()
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		// TODO Auto-generated method stub
		return postOrder(root);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getLevelOrderTraversal()
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		// TODO Auto-generated method stub
		List<K> levelList = new ArrayList<K>();
		for (int i = 1; i <= getHeight(); i++) {
			levelList = levelOrder(root, i);
		} // loop to go through each level of BST
		return levelList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getInOrderTraversal()
	 */
	@Override
	public List<K> getInOrderTraversal() {
		// TODO Auto-generated method stud
		return inOrder(root);
	}
	/*
	 * recursive method helps insert node into the tree then returns
	 * the root which has already been modified
	 */
	private BSTNode<K, V> helpInsert(BSTNode<K, V> root, K key, V value) {
		if (root == null) {
			root = new BSTNode(key, value);
			return root;
		} // if BST is empty then root is the new node
		if (key.compareTo(root.key) < 0) {
			root.left = helpInsert(root.left, key, value);
		} else if (key.compareTo(root.key) > 0) {
			root.right = helpInsert(root.right, key, value);
		} // recursive method helps insert as the right position
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
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
	 * recursive method helps remove a node then returns the root
	 * which has already removed the deleted node
	 */
	private BSTNode helpRemove(BSTNode<K, V> root, K key) throws KeyNotFoundException {
		if (root == null) {
			throw new KeyNotFoundException();
		} else if (key.compareTo(root.key) < 0) {
			root.left = helpRemove(root.left, key);
		} else if (key.compareTo(root.key) > 0) {
			root.right = helpRemove(root.right, key);
		} //recursive method help find the node to remove

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
				numKeys--;
			}
		}
		return root;
	}
	/*
	 * recursive method helps find the smallest child
	 * use for remove method
	 */
	private BSTNode minChild(BSTNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
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
		} //recursive method to find the node to return
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#get(java.lang.Comparable)
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if (contains(key)) {
			return (V) helpGet(root, key).value;
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
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		BSTNode tmp = root;
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
			} // loop to see if the node is in the tree or not
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#numKeys()
	 */
	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return numKeys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getKeyAtRoot()
	 */
	@Override
	public K getKeyAtRoot() {
		// TODO Auto-generated method stub
		if (root.key == null) {
			return null;
		} 
		return root.key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		BSTNode tmp = root;
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
				} // recursive to find the key needed
			}
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		BSTNode tmp = root;
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
			}// recursive to find the key needed
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}
		return null;
	}
	/*
	 * find the height of the node
	 */
	private int height(BSTNode root) {
		
		if (root == null) {
			return 0;
		} else {
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);
			if (leftHeight > rightHeight) {
				return (leftHeight + 1);
			} else {
				return (rightHeight + 1);
			} 
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getHeight()
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height(root);
	}

}
