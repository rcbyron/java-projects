/** Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */
package pMap.phaseB;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * PMap stands for Paired Map. A map is a collection of key value pairs, e.g.,
 * (1, "one") (2, "two") (3, "three") are all pairs with a key that's a integer and a value
 * that's an string.
 */

public class PMap implements Map<Integer,String> {
	private HashSet<Entry<Integer, String>> entries = new HashSet<Entry<Integer, String>>();
	
	public PMap() { }
	
	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		Iterator<Entry<Integer, String>> itr = entries.iterator();
		while (itr.hasNext()) {
			if (itr.next().getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		Iterator<Entry<Integer, String>> itr = entries.iterator();
		while (itr.hasNext())
			if (itr.next().getValue().equals(value))
				return true;
		return false;
	}

	@Override
	public String get(Object key) {
		Iterator<Entry<Integer, String>> itr = entries.iterator();
		while (itr.hasNext()) {
			Entry<Integer, String> e = itr.next();
			if (e.getKey().equals(key))
				return (String) e.getValue();
		}
		return null;
	}

	@Override
	public String put(Integer key, String value) {
		Entry<Integer, String> e = new MyEntry(key, value);
		entries.add(e);
		return value;
	}

	@Override
	public String remove(Object key) {
		Iterator<Entry<Integer, String>> itr = entries.iterator();
		while (itr.hasNext()) {
			Entry<Integer, String> e = itr.next();
			if (e.getKey().equals(key)) {
				itr.remove();
				return (String) e.getValue();
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends String> m) {
		Iterator itr = m.entrySet().iterator();
		while (itr.hasNext()) {
			Entry e = (Entry)itr.next();
			put((Integer)e.getKey(), (String)e.getValue());
		}
	}

	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public Set<Integer> keySet() {
		Set<Integer> keys = new HashSet<Integer>();
		Iterator<Entry<Integer, String>> itr = entries.iterator();
		while (itr.hasNext()) {
			Entry<Integer, String> e = itr.next();
			keys.add((Integer) e.getKey());
		}
		return keys;
	}

	@Override
	public Collection<String> values() {
		Collection<String> values = new HashSet<String>();
		Iterator<Entry<Integer, String>> itr = entries.iterator();
		while (itr.hasNext()) {
			Entry<Integer, String> e = itr.next();
			values.add((String) e.getValue());
		}
		return values;
	}

	@Override
	public Set<Entry<Integer, String>> entrySet() {
		return entries;
	}

	

}
