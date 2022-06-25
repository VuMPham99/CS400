//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BSTTest.java
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

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//TODO: Add tests to test that binary search tree operations work

public class BSTTest extends DataStructureADTTest {
	 
	BST<String,String> bst;
	BST<Integer,String> bst2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// The setup must initialize this class's instances 
		// and the super class instances.
		// Because of the inheritance between the interfaces and classes,
		// we can do this by calling createInstance() and casting to the desired type
		// and assigning that same object reference to the super-class fields.
		dataStructureInstance = bst = createInstance();
		dataStructureInstance2 = bst2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = bst = null;
		dataStructureInstance2 = bst2 = null;
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected BST<String, String> createInstance() {
		return new BST<String,String>();
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected BST<Integer, String> createInstance2() {
		return new BST<Integer,String>();
	}

	/**
	 * Test that empty trees still produce a valid but empty traversal list
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_001_empty_traversal_orders() {
		try {

			List<String> expectedOrder = new ArrayList<String>();

			// Get the actual traversal order lists for each type		
			List<String> inOrder = bst.getInOrderTraversal();
			List<String> preOrder = bst.getPreOrderTraversal();
			List<String> postOrder = bst.getPostOrderTraversal();
			List<String> levelOrder = bst.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder);

			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			assertEquals(expectedOrder,levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 002: "+e.getMessage());
		}

	}

	/**
	 * Test that trees with one key,value pair produce a valid traversal lists
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_002_check_traversals_after_insert_one() {

		try {

			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			bst2.insert(10,"ten");
			if (bst2.numKeys()!=1) 
				fail("added 10, size should be 1, but was "+bst2.numKeys());
		
			List<Integer> inOrder = bst2.getInOrderTraversal();
			bst2.remove(10);
			
			List<Integer> preOrder = bst2.getPreOrderTraversal();
			
			
		    List<Integer> postOrder = bst2.getPostOrderTraversal();


			List<Integer> levelOrder = bst2.getLevelOrderTraversal();

		
			
		
	
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder); 
			
			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			
			assertEquals(expectedOrder,levelOrder); 

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 003: "+e.getMessage());
		}

	}
	

	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_003_check_inOrder_for_balanced_insert_order() {
		// insert 20-10-30 BALANCED
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	
	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Pre-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_004_check_preOrder_for_balanced_insert_order() {
		
		// TODO implement this test
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);   // L
			expectedOrder.add(10);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPreOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Post-Order traversal order: 10-30-20
	 */
	@Test
	void testBST_005_check_postOrder_for_balanced_insert_order() {
		
		// TODO implement this test
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(30);   // V
			expectedOrder.add(20);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPostOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}

	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Level-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_006_check_levelOrder_for_balanced_insert_order() {
		
		// TODO implement this test
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);   // L
			expectedOrder.add(10);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}

		
	}

	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_007_check_inOrder_for_not_balanced_insert_order() {
		
		// TODO implement this test
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}


	}

	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Pre-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_008_check_preOrder_for_not_balanced_insert_order() {
		
		// TODO implement this test
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPreOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}

	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Post-Order traversal order: 30-20-10
	 */
	@Test
	void testBST_009_check_postOrder_for_not_balanced_insert_order() {
		
		// TODO implement this test
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(30);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(10);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPostOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}

	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Level-Order traversal order: 10-20-30 (FIXED ON 2/14/18)
	 */
	@Test
	void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
		
		// TODO implement this test
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}

		
	}
	
	// TODO: Add your own tests
	@Test
	void testBST_011_check_remove_when_inserted_1_key() {
		try {
		bst2.insert(10,"1st key inserted"); 
		if(bst2.numKeys ==1) {
			bst2.remove(10);
		}else {
			fail("added 10, size should be 1, but was "+bst2.numKeys());
		} if(bst2.numKeys !=0) {
			fail("did not remove key successfully");
		}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test
	void testBST_012_check_remove_when_inserted_many_keys_node_has_1_child() {
		try {
			bst2.insert(40,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(60,"3rd key inserted");
			bst2.insert(10,"4th key inserted");
			bst2.insert(50,"5th key inserted");
			bst2.insert(70,"6th key inserted");
			if(bst2.numKeys==6) {
				bst2.remove(20);
			}else {
				fail("added 10-70, size should be 6, but was "+bst2.numKeys());
			}if(bst2.numKeys!= 5 && bst2.getKeyOfLeftChildOf(40)!= 10) {
				fail("did not remove key successfully");
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test
	void testBST_013_check_remove_when_inserted_many_keys_node_has_2_childs() {
		try {
		bst2.insert(40,"1st key inserted");
		bst2.insert(20,"2nd key inserted");
		bst2.insert(60,"3rd key inserted");
		bst2.insert(10,"4th key inserted");
		bst2.insert(30,"5th key inserted");
		bst2.insert(50,"6th key inserted");
		bst2.insert(70,"7th key inserted");
		bst2.insert(45,"8th key inserted");
		if(bst2.numKeys==8) {
			bst2.remove(60);
		}else {
			fail("added 10-70, size should be 7, but was "+bst2.numKeys());
		}if(bst2.numKeys!= 6 && bst2.getKeyOfRightChildOf(40)!= 70) {
			fail("did not remove key successfully");
		} 
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test
	void testBST_014_check_tree_height_when_inserted_one_node() {
		try {
			bst2.insert(40,"1st key inserted");
			if(bst2.getHeight()!=1) {
				fail("tree height should be 1 since one node was inserted");
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test
	void testBST_015_check_tree_height_when_inseterd_many_nodes() {
		try {
			bst2.insert(40,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(60,"3rd key inserted");
			bst2.insert(10,"4th key inserted");
			bst2.insert(30,"5th key inserted");
			bst2.insert(50,"6th key inserted");
			bst2.insert(70,"7th key inserted");
			bst2.insert(45,"8th key inserted");
			if(bst2.getHeight()!=4) {
				System.out.println(bst2.getHeight());
				fail("tree height should be 4");
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test 
	void testBST_016_check_get_when_inserted_many_nodes() {
		try {
			bst2.insert(40,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(60,"3rd key inserted");
			bst2.insert(10,"4th key inserted");
			bst2.insert(30,"5th key inserted");
			bst2.insert(50,"6th key inserted");
			bst2.insert(70,"7th key inserted");
			bst2.insert(45,"8th key inserted");
			if(bst2.get(45).compareTo("8th key inserted")!=0) {
				fail("fails to get the right key");
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test 
	void testBST_017_check_contains_when_inserted_many_nodes() {
		try {
			bst2.insert(40,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(60,"3rd key inserted");
			bst2.insert(10,"4th key inserted");
			bst2.insert(30,"5th key inserted");
			bst2.insert(50,"6th key inserted");
			bst2.insert(70,"7th key inserted");
			bst2.insert(45,"8th key inserted");
			if(!bst2.contains(50)) {
				fail("50 was inserted as the 6th key, so BST should contain it");
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test 
	void testBST_018_check_getKeyOfLeftChildOf_when_inserted_many_nodes() {
		try {
			bst2.insert(40,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(60,"3rd key inserted");
			bst2.insert(10,"4th key inserted");
			bst2.insert(30,"5th key inserted");
			bst2.insert(50,"6th key inserted");
			bst2.insert(70,"7th key inserted");
			bst2.insert(45,"8th key inserted");
			if(bst2.getKeyOfLeftChildOf(60) != 50) {
				fail("left child of 60 is 50 and not " + bst2.getKeyOfLeftChildOf(60));
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test 
	void testBST_019_check_getKeyOfRightChildOf_when_inserted_many_nodes() {
		try {
			bst2.insert(40,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(60,"3rd key inserted");
			bst2.insert(10,"4th key inserted");
			bst2.insert(30,"5th key inserted");
			bst2.insert(50,"6th key inserted");
			bst2.insert(70,"7th key inserted");
			bst2.insert(45,"8th key inserted");
			if(bst2.getKeyOfRightChildOf(20) != 30) {
				fail("left child of 20 is 30 and not " + bst2.getKeyOfLeftChildOf(20));
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	@Test 
	void testBST_020_check_getKeyAtRoot_when_inserted_many_nodes() {
		try {
			bst2.insert(40,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(60,"3rd key inserted");
			bst2.insert(10,"4th key inserted");
			bst2.insert(30,"5th key inserted");
			bst2.insert(50,"6th key inserted");
			bst2.insert(70,"7th key inserted");
			bst2.insert(45,"8th key inserted");
			if(!bst2.getKeyAtRoot().equals(40)) {
				fail("root's key is 40 and not " + bst2.getKeyAtRoot());
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
		
	
	// Add tests to make sure that get and remove work as expected.
	// Does the height of the tree reflect it's actual and its expected height?
	// Use the traversal orders to check.
	
	// Can you insert many and still "get" them back out?
	
	// Does delete work?  
	// When the key is a leaf?  node with one left child?
	// node with one right child?  node with two children?
	
	// Write replacement value did you choose? 
	// in-order precessor?  in-order successor?
	// How can you test if it is replaced correctly?
	
	@Test
	void teestBST_021_getHeight() {
		try {
				bst2.insert(40,"1st key inserted");
				bst2.insert(20,"2nd key inserted");
				bst2.insert(60,"3rd key inserted");
				bst2.insert(10,"4th key inserted");
				bst2.insert(30,"5th key inserted");
				bst2.insert(50,"6th key inserted");
				bst2.insert(70,"7th key inserted");
				bst2.insert(45,"8th key inserted");
				if(bst2.getHeight()!=4) {
					fail("height should be 4 and not " +bst2.getHeight());
				}
	}catch (Exception e) {
		e.printStackTrace();
		fail("Unexpected exception 004: "+e.getMessage());
		}
	}
}
