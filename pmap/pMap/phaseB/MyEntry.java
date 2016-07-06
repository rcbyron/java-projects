/** Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */

package pMap.phaseB;

import java.util.Map;

/**
 * Map.Entry. Assume that key is an integer and value is a string.
 */

public class MyEntry implements Map.Entry<Integer, String> {
	
	private Integer i;
	private String s;
	
	public MyEntry(Integer i, String s) {
		this.i = i;
		this.s = s;
	}
	
	@Override
	public Integer getKey() {
		return i;
	}
	@Override
	public String getValue() {
		return s;
	}
	@Override
	public String setValue(String value) {
		s = value;
		return s;
	}

	public void my_test() { }
}
