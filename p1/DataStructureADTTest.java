//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:        DataStructureADTTest.java
// Files:         UTF-8
// Course:         cs 400, 2019
//
// Author:         Vu M Pham
// Email:           vmpham2@wisc.edu
// Lecturer's Name: Debra Deppeler
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

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
	
	private T dataStructureInstance;
	private static int testsPassed; // field to keeps track of tests passed
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// code here will run once before the start of all tests
		testsPassed =0; 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// code here run once after all tests have finished
		System.out.print("You have passed: "+ testsPassed +" tests");
	} // inform how many tests have passed

	@BeforeEach
	void setUp() throws Exception {
		// code here will run before each test
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		// code here will run after each test
		dataStructureInstance = null;
	}

	
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
		fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	// TODO: implement tests 01 - 04
	
	// testsPassed is counted starting from here
	// keeps having "duplicate key" error for TestDS_Sapan
	// takes precaution of the problem above with assert fail

	// test01_after_insert_one_size_is_one
	@Test
	void test01_after_insert_one_size_is_one() {
		String key = ""; // local varibles represent the key and value
		String value = "";
		try {
		dataStructureInstance.insert(key, value);
		if (dataStructureInstance.size() != 1) {
		fail("data structure should have 1 key, with size=1, but size="+dataStructureInstance.size());
		}else { // insert 1 object, so size should only be 1
			testsPassed ++;
		}
		}catch (RuntimeException e) {
			fail("list is empty, shouldn't have any key to duplciate");
		} // error not yet solved
	}
	
	// test02_after_insert_one_remove_one_size_is_0
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		String key = ""; // local varibles represent the key and value
		String value = "";
		try {
		dataStructureInstance.insert(key, value); // insert object
		dataStructureInstance.remove(key);        // remove object
		if (dataStructureInstance.size() != 0) {
			fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}else { // size should be 0 as only 1 object was inserted and removed
		testsPassed ++;
		}
	}catch (RuntimeException e) {
		fail("list is empty, shouldn't have any key to duplciate");
	} // error not yet solved
}
	
	// test03_duplicate_exception_is_thrown
	@Test
	void test03_duplicate_exception_is_thrown() {
		String key1 = ""; // local varibles represent the key and value
		String value1 = ""; // key1 and key3 have matched keys
		String key2 = "asdjhbasd";
		String value2= "jhavsdhk";
		String key3 = "";
		String value3 ="";
		try {
			dataStructureInstance.insert(key1, value1); // insert 3 objects
			dataStructureInstance.insert(key2, value2);
			dataStructureInstance.insert(key3, value3);
			fail("Should have caught an RuntimeException");
		}catch (RuntimeException e){ // Object 1 and 3 have the same key so should have "duplicate key" error
				testsPassed ++;
			System.out.println("successfully threw RuntimeException");
		}catch (Exception e) {
			fail("unexpected exception was caught: " + e);
		}
		
	}
	// test04_remove_returns_false_when_key_not_present
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		String key1 = ""; // // local varibles represent the key and value
		String value1 = "";
		String key2 = "asdjhbasd";
		String value2= "jhavsdhk";
		String key3 = "asdafasd";
		String value3 ="ajksnhdklasd";
		String key4 = "jhabsdhkbdas";
		try {
		dataStructureInstance.insert(key1, value1); // inserts only 3 keys instead of 4
		dataStructureInstance.insert(key2, value2);
		dataStructureInstance.insert(key3, value3);
		if(dataStructureInstance.remove(key4) != false) {
			fail("key4 was never inserted, so should return false if tried to remove key4");
		}else {
			testsPassed ++;
		}
		}catch (RuntimeException e) {
			fail("all keys are different, there shouldn't be any duplicate keys");
		} 
		
	}
	
		
		
	

	
	// TODO: add tests to ensure that you can detect implementation that fail
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes
	@Test
	void test05_returns_size_after_combinations_of_insert_and_remove() {
		String key1 = "";  // local varibles represent the key and value
		String value1 = "";
		String key2 = "asdjhbasd";
		String value2= "jhavsdhk";
		String key3 = "asdafasd";
		String value3 ="ajksnhdklasd";
		String key4 = "jhabsdhkbdas";
		String value4 ="aaaaaaaaaa";
		try {
		dataStructureInstance.insert(key1, value1); // insert 3 keys instead of 4
		dataStructureInstance.insert(key2, value2);
		dataStructureInstance.insert(key3, value3);
		dataStructureInstance.remove(key1);
		dataStructureInstance.remove(key4); // key4 was never inserted
		if(dataStructureInstance.size() != 2) {
			fail("key4 was never inserted, so remove key4 should not affect the size");
		}else { // added 3 keys and removed 1 so size should be 2
			testsPassed ++;
		}
		}catch (RuntimeException e) {
			fail("all keys are different, there shouldn't be any duplicate keys");
		} 
	}
		
	@Test
	void test06_size_should_not_change_after_remove_the_same_key_twice() {
		String key1 = ""; // local varibles represent the key and value
		String value1 = "";
		try {
		dataStructureInstance.insert(key1, value1);
		dataStructureInstance.remove(key1); // remove key1 twice
		dataStructureInstance.remove(key1);
		if(dataStructureInstance.size() != 0) {
			fail("key1 was removed, so if remove again the size should not be affected");
		}else { // size should not be decreased
			testsPassed ++;
		} // list should not be affected after the second remove command
		}catch (RuntimeException e) {
			fail("all keys are different, there shouldn't be any duplicate keys");
		}
		
	}
	@Test
	void test07_return_false_because_key_has_been_removed(){
		String key1 = ""; // local varibles represent the key and value
		String value1 = "";
		String key2 = "asdjhbasd";
		String value2= "jhavsdhk";
		String key3 = "asdafasd";
		String value3 ="ajksnhdklasd";
		String key4 = "jhabsdhkbdas";
		String value4 ="aaaaaaaaaa";
		try {
		dataStructureInstance.insert(key1, value1); // adds 3 keys
		dataStructureInstance.insert(key2, value2);
		dataStructureInstance.insert(key3, value3);
		dataStructureInstance.remove(key1); // removes one of the key that was added
		dataStructureInstance.remove(key4); // removes one of the key that was NOT added
		if(dataStructureInstance.contains(key1) != false) {
			fail("key1 has been removed, so the list shouldn't contains key1");
		}else { // key1 has been removed and key4 should not affected the list
			testsPassed ++; // so the list should not contain key1 anymore
		}
		}catch (RuntimeException e) {
			fail("all keys are different, there shouldn't be any duplicate keys");
		}
	}
		
		
		
		
		
	

}
