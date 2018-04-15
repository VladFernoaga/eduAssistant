package ro.unitbv.eduassistant.util;

import lombok.Getter;

/**
 * A simple not thread safe counter
 * @author ferno
 *
 */
public class Counter {

	@Getter
	private int index = 0;

	public int increment() {
		return index++;
	}
	
	
}
