/** Phase A <jg55475><rcb2746>
 * Phase B <rcb2746><jg55475>
 */
package pMap.phaseA;

/**
 * PMap stands for Paired Map. A map is a collection of key value pairs, e.g.,
 * (1, 2) (2, 3) (3, 4) are all pairs with a key that's a integer and a value
 * that's an integer
 */
public class PMap {
	private Entry[] entries = new Entry[2]; 
	private int length = 0; 
	
	public PMap() { }

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public boolean containsKey(int key) {
		for(int i = 0; i < length; i++) {
			if(entries[i].getKey() == key) { 
				return true; 
			}
		}
		return false;
	}

	public boolean containsValue(int value) {
		for(int i = 0; i < length; i++)
			if(entries[i].getValue() == value)
				return true; 
		return false;
	}

	public int get(int key) {
		for(int i = 0; i < length; i++)
			if(entries[i].getKey()== key)
				return entries[i].getValue(); 
		return -1;
	}

	public int put(int key, int value) {
		if (length == entries.length) {
			doubleArrSize();
		}
		Entry e = new Entry(key, value);
		entries[length] = e; 
		return length++; 
		
	}
	
	private void doubleArrSize() {
		int new_len = 2 * length;
		Entry[] new_arr = new Entry[new_len];
		for(int i = 0; i < length; i++) {
			new_arr[i] = entries[i];
		}
		entries = new_arr;
	}

	public int remove(int key) {
		if(!containsKey(key)) { return 0; }
		
		int i = 0;
		while(entries[i].getKey() != key) { 
			i++;
		}
		int val = entries[i].getValue();
		while(i < length - 1) { //what if last element
			entries[i] = entries[i + 1]; 
			i++;
		}
		length--; 
		
		return val;
	}

	public void putAll(int[] keys, int[] values) {
		for(int i = 0; i < keys.length; i++) {
			put(keys[i], values[i]);
		}
	}

	public void clear() {
		entries = new Entry[1]; 
		length = 0;
	}

	public int[] keys() {
		int[] key_arr = new int[length];
		for(int i = 0; i < length; i++) {
			key_arr[i] = entries[i].getKey();
		}
		return key_arr;
	}

	public int[] values() {
		int[] val_arr = new int[length];
		for(int i = 0; i < length; i++) {
			val_arr[i] = entries[i].getValue();
		}
		return val_arr;
	}

	public Entry[] entries() {
		return entries;
	}

}
