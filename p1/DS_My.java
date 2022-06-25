//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:        DS_My.java
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

import java.util.LinkedList;

public class DS_My implements DataStructureADT {

    // TODO may wish to define an inner class 
    // for storing key and value as a pair
    // such a class and its members should be "private"
	
 	private class storing {
 		private Object [][] pair; // storing key and value as a pair
 		private storing(Comparable key, Object value) { // use constructor to pass in key and value
 			pair = new Object[1][2];
 			pair[0][0] = key; // store key and value at respective indices
 			pair[0][1]= value;
 		}
	}  

    // Private Fields of the class
    // TODO create field(s) here to store data pairs
 	private LinkedList<Object[][]> list; 
    public DS_My() { //constructor
        // TODO Auto-generated method stub
    	list = new LinkedList<Object[][]>(); // create new list of pair
    }

 // Add the key,value pair to the data structure and increases size.
    // If key is null, throws IllegalArgumentException("null key");
    // If key is already in data structure, throws RuntimeException("duplicate key");
    // can accept and insert null values
    @Override
    public void insert(Comparable k, Object v) {
        // TODO Auto-generated method stub
    	if(k == null) {
    		throw new IllegalArgumentException("null key");
    	}
    	for(int i = 0; i< list.size();i++) { // using for loops to go through the list
    		if(list.get(i)[0][0].equals(k)) { // key is stored at the first index of the object stored in list
    			throw new RuntimeException("duplicate key");
    		}
    	}
    	list.add(new DS_My.storing(k, v).pair); // called on inner class to create Object[][]
    }

    
    // If key is found, Removes the key from the data structure and decreases size
    // If key is null, throws IllegalArgumentException("null key") without decreasing size
    // If key is not found, returns false.
    @Override
    public boolean remove(Comparable k) {
        // TODO Auto-generated method stub
    	if(k == null) {
    		throw new IllegalArgumentException("null key");
    	}
    	for(int i = 0; i< list.size();i++) { // uses for loops to go through list to find the key
    		if(list.get(i)[0][0].equals(k)) { // removes key after found matched
    			list.remove(i);
    			return true;
    		}
    	}
        return false;
    }

    // Returns true if the key is in the data structure
    // Returns false if key is null or not present
    @Override
    public boolean contains(Comparable k) {
        // TODO Auto-generated method stub
    	for(int i = 0; i< list.size();i++) { // use for loop to go through list to find matched key
    		if(list.get(i)[0][0].equals(k)) {
    			return true;
    		}
    	}
        return false;
    }

 // Returns the value associated with the specified key
    // get - does not remove key or decrease size
    // If key is null, throws IllegalArgumentException("null key")
    @Override
    public Object get(Comparable k) {
        // TODO Auto-generated method stub
    	if(k == null) {
    		throw new IllegalArgumentException("null key");
    	}
    	for(int i = 0; i< list.size();i++) { // going through the list to find matched key
    		if(list.get(i)[0][0].equals(k)) {
    			return list.get(i); // return matched key
    		}
    	}
        return null; // return null if key not found
    }

    // Returns the number of elements in the data structure
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return list.size();
    }

}
