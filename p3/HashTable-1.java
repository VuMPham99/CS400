
/**
 * Filename:   HashTable.java
 * Project:    p3b-201901     
 * Authors:    Vu M Pham 001
 *
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * Due Date:   Thursday 03/28/2019 by 10:00 pm
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */
// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
		private int initialCapacity; 
		private double loadFactorThreshold;
		private int numkeys;
		private Object[]keyList; //array to store keys
		private Object[]valList; //array to store values
	// TODO: comment and complete a default no-arg constructor
		/**
		 * Instantiate your fields
		 */
	public HashTable() {
		this.initialCapacity = 10; // set capacity to 10
		this.loadFactorThreshold = 0.75; // set threshold to 0.75
		this.numkeys = 0; // set number of key to 10
		//creates new lists for keys and values to be stored
		keyList =  new Object[initialCapacity];
		valList =  new Object[initialCapacity];
	}

	// TODO: comment and complete a constructor that accepts 
	// initial capacity and load factor threshold
        // threshold is the load factor that causes a resize and rehash
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		if(initialCapacity <1) { // throws exception if capacity is negative
			throw new  IllegalArgumentException();
		}else {
			this.initialCapacity = initialCapacity;
			this.loadFactorThreshold = loadFactorThreshold;
			this.numkeys = 0;
			keyList =  new Object[initialCapacity];
			valList =  new Object[initialCapacity];
		}
	}
	/**
	 * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException();
	 */

	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalNullKeyException();
		}if(contains(key)) {
			throw new  DuplicateKeyException();
		}
		int tmp = key.hashCode() % getCapacity(); // instant int to hold the index
		int i = tmp;
		do {
			if(getLoadFactor()>= loadFactorThreshold) {
				resize(2*this.initialCapacity+1); // resize when the array is filled
			}
			if(keyList[i]== null) { 
				keyList[i]= key;  //add to keys and values lists
				valList[i]= value;
				numkeys++; // increment numKeys
				return;
			}else {
				i = (i + 1) % getCapacity();  // linear probing
				
			}
		}while(i != tmp);
		
	}
	/**
	 * resize the table if the table is filed
	 * @param capacity
	 * @throws IllegalNullKeyException
	 * @throws DuplicateKeyException
	 */
	private void resize(int capacity) throws IllegalNullKeyException, DuplicateKeyException {
		HashTable tmp = new HashTable(capacity, this.loadFactorThreshold); // creates new object to hold the new hashtable
		for(int i =0; i < initialCapacity; i++) {
			if(keyList[i] != null) {
				tmp.insert((K)keyList[i],(V) valList[i]);
			}
		}
		// instantiates the fields of new table to the ones of the old table 
		keyList =  tmp.keyList;
		valList =  tmp.valList;
		initialCapacity = tmp.initialCapacity;
	}
	/**
	 * find the key needed in the data structure
	 * @param key
	 * @return
	 * @throws IllegalNullKeyException
	 */
	private boolean contains(K key) throws IllegalNullKeyException {
		int i = key.hashCode() % getCapacity();
		while(keyList[i] != null) { // loop go through array to find the key
			if(keyList[i].equals(key)) {
				return  true; // returns true if key is found else if otherwise
			}else {
				i = (i+1)% getCapacity();
			}
		}
		return false; 
	}
	/**
	 * If key is found,
	 * remove the key,value pair from the data structure
	 * decrease number of keys.
	 * return true
	 * If key is null, throw IllegalNullKeyException
	 * If key is not found, return false
	 */
	
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if(!contains(key)) { // no key to be removed
			return false;
		}else {
			int i = key.hashCode() % getCapacity();
			while (!keyList[i].equals(key)) { // loop to find key to remove
				i = (i+1) % getCapacity(); 
			}
			keyList[i] = null; //remove key and value of that key
			valList[i] = null;
			// rehash the table when remove key
			for (i=(i+1)% getCapacity(); keyList[i]!= null; i =(i+1) % getCapacity()) {
				K tmp1 = (K) keyList[i];
				V tmp2 = (V) valList[i];
				keyList[i] = null;
				valList[i] = null;
				numkeys --;
				try {
					insert(tmp1,tmp2);
				} catch (DuplicateKeyException e) {
					
				}
			}
			numkeys--; //decrement number of keys after removed
			return true;
		}

	}

	@Override
	/**
	 * Returns the value associated with the specified key
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
	 */
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalNullKeyException();
		}if (!contains(key)) {
			throw new KeyNotFoundException();
		}
			int i = key.hashCode() % getCapacity();
			while(keyList[i] != null) { //loop through the key list then return the value at index that they key is found in value list
				if(keyList[i].equals(key)) {
					return  (V) valList[i];
				}else {
					i = (i+1)% getCapacity();
				}
			}

		return null;
	}

	@Override
	/**
	 * return number of keys in the table
	 */
	public int numKeys() {
		// TODO Auto-generated method stub
		return numkeys;
	}
	/**
	 * returns the table load factor threshold
	 */
	@Override
	public double getLoadFactorThreshold() {
		// TODO Auto-generated method stub
		return this.loadFactorThreshold;
	}

	@Override
	/**
	 * returns the load factor
	 */
	public double getLoadFactor() {
		// TODO Auto-generated method stub
		return (double)this.numkeys/(double)this.initialCapacity;
	}

	@Override
	/**
	 * returns the capacity
	 */
	public int getCapacity() {
		// TODO Auto-generated method stub
		
		return this.initialCapacity;
	}

	@Override
	/**
	 * returns the resolution for linear probing
	 */
	public int getCollisionResolution() {
		// TODO Auto-generated method stub
		return 1;
	}

	// TODO: add all unimplemented methods so that the class can compile


	

		
}
