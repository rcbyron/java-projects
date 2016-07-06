/** Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */
package pMap.phaseA;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PMapTest {

	@Test
	public void test1() {
		PMap pmap = new PMap();
		pmap.put(0, 1);
		pmap.put(1, 2);
		pmap.put(2, 3);
		System.out.println(pmap.size());
		int[] keys = pmap.keys();
		for (int i : keys) {
			System.out.print(i+" ");
		}
		System.out.println();
		int[] values = pmap.values();
		for (int i : values) {
			System.out.print(i+" ");
		}
		System.out.println(pmap.size());
	}

	@Test
	public void test2() {
		System.out.println("Testing more stuff...");
		PMap pmap = new PMap();
		assertEquals(pmap.isEmpty(), true);
		pmap.put(0, 1);
		pmap.put(1, 2);
		pmap.put(2, 3);
		assertEquals(pmap.size(), 3);
		assertEquals(pmap.isEmpty(), false);
		assertEquals(pmap.containsKey(1), true);
		assertEquals(pmap.containsKey(4), false);
		assertEquals(pmap.containsValue(3), true);
		assertEquals(pmap.containsValue(0), false);
		assertEquals(pmap.get(1), 2);
		pmap.remove(2);
		assertEquals(pmap.get(2), -1);
		pmap.clear();
		assertEquals(pmap.isEmpty(), true);
		System.out.println("Passed!");
	}
}
