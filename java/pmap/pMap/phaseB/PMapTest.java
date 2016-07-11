/** Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */
package pMap.phaseB;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

public class PMapTest {

	@Test
	public void test1() {
		PMap pmap = new PMap();
		pmap.put(1, "one");
		pmap.put(2, "two");
		pmap.put(3, "three");
		System.out.println(pmap.size());
		Set<Integer> keys = pmap.keySet();
		for (Integer i : keys) {
			System.out.print(i + " ");
		}
		System.out.println();
		Collection<String> values = pmap.values();
		for (String i : values) {
			System.out.print(i + " ");
		}
		System.out.println(pmap.size());
	}

	@Test
	public void test2() {
		System.out.println("Testing more stuff...");
		PMap pmap = new PMap();
		assertEquals(pmap.isEmpty(), true);
		pmap.put(1, "one");
		pmap.put(2, "two");
		pmap.put(3, "three");
		assertEquals(pmap.size(), 3);
		assertEquals(pmap.isEmpty(), false);
		assertEquals(pmap.containsKey(1), true);
		assertEquals(pmap.containsKey(4), false);
		assertEquals(pmap.containsValue("three"), true);
		assertEquals(pmap.containsValue("meeee"), false);
		assertEquals(pmap.get(1), "one");
		pmap.remove(2);
		assertEquals(pmap.get(2), null);
		pmap.clear();
		assertEquals(pmap.isEmpty(), true);
		System.out.println("Passed!");
	}	

}
