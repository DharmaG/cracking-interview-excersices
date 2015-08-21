package com.ryabokon.datastructures.maps;

import org.junit.Assert;
import org.junit.Test;

public class MapTest {

	@Test
	public void test() {

		Map map = new Map();

		for (int i = 0; i < 100; i++) {
			map.put(String.valueOf(i), "VAL_" + i);
		}

		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(map.get(String.valueOf(i)), "VAL_" + i);
		}

		for (int i = 0; i < 100; i++) {
			map.put(String.valueOf(i), "NEW_" + i);
		}

		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(map.get(String.valueOf(i)), "NEW_" + i);
		}

	}

}
