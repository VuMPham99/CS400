//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AVLTest.java
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Assert;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test the rebalancing of the AVL tree operations

//@SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

	AVL<String,String> avl;
	AVL<Integer,String> avl2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = bst = avl = createInstance();
		dataStructureInstance2 = bst2 = avl2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		avl = null;
		avl2 = null;
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected AVL<String, String> createInstance() {
		return new AVL<String,String>();
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected AVL<Integer, String> createInstance2() {
		return new AVL<Integer,String>();
	}

	/** 
	 * Insert three values in sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred.
	 */
	@Test
	void testAVL_001_insert_sorted_order_simple() {
		try {
			avl2.insert(10, "10");
			//System.out.println(avl2.getRoot().key);
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfRightChildOf(10).equals(20)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(30, "30");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values in reverse sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_002_insert_reversed_sorted_order_simple() {
		
		// TODO: implement this test
		try {
			avl2.insert(30, "30");
			//System.out.println(avl2.getRoot().key);
			if (!avl2.getKeyAtRoot().equals(30)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfLeftChildOf(30).equals(20)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(10, "10");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 30 at the root
			// and 20 at its left child and 10 as 20 left child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
		
	}

	/** 
	 * Insert three values so that a right-left rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_003_insert_smallest_largest_middle_order_simple() {
		
		// TODO: implement this test
		try {
			avl2.insert(10, "10");
			//System.out.println(avl2.getRoot().key);
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(30, "30");
			if (!avl2.getKeyOfRightChildOf(10).equals(30)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 30 at the root
			// and 20 at its left child and 10 as 20 left child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
		
	
		
	}

	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_003_insert_largest_smallest_middle_order_simple() {
		
		// TODO: implement this test
		try {
			avl2.insert(30, "30");
			if (!avl2.getKeyAtRoot().equals(30)) 
				fail("avl insert at root does not work");
			
			avl2.insert(10, "10");
			if (!avl2.getKeyOfLeftChildOf(30).equals(10)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 30 at the root
			// and 20 at its left child and 10 as 20 left child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
		
	}
	
	
	// TODO: Add your own tests
	@Test
	void testAVL_004_check_rebalancing_for_larger_tree() {
		try {
			avl2.insert(10, "10");
			avl2.insert(20, "20");
			avl2.insert(30, "30");
			avl2.insert(40, "40");			
			avl2.insert(50, "50");
			avl2.insert(60, "60");
			avl2.insert(70, "70");
			avl2.insert(80, "80");

		
			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(40));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(40),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(40),new Integer(60));
		

		}catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
	}
	@Test
	void testAVL_005_check_rebalancing_for_larger_tree_with_level_order_traversal() {
		try {
			avl2.insert(10, "10");
			avl2.insert(20, "20");
			avl2.insert(30, "30");
			avl2.insert(40, "40");			
			avl2.insert(50, "50");
			avl2.insert(60, "60");
			avl2.insert(70, "70");
			avl2.insert(80, "80");
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(40);
			expectedOrder.add(20);
			expectedOrder.add(60);
			expectedOrder.add(10);
			expectedOrder.add(30);
			expectedOrder.add(50);
			expectedOrder.add(70);
			expectedOrder.add(80);
			List<Integer> actualOrder = avl2.getLevelOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		}catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
	}
	@Test
	void testAVL_006_check_tree_height() {
		try {
			avl2.insert(10, "10");
			avl2.insert(20, "20");
			avl2.insert(30, "30");
			avl2.insert(40, "40");			
			avl2.insert(50, "50");
			avl2.insert(60, "60");
			avl2.insert(70, "70");
			avl2.insert(80, "80");
			if(avl2.getHeight()!=4) {
				fail("tree height should be 4 and not "+avl2.getHeight() );
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
	}
	// Add tests to make sure that rebalancing occurs even if the 
	// tree is larger.   Does it maintain it's balance?
	// Does the height of the tree reflect it's actual height
	// Use the traversal orders to check.
	
	// Can you insert many and still "get" them back out?
	
	// Does delete work?  Does the tree maintain balance when a key is deleted?

}
