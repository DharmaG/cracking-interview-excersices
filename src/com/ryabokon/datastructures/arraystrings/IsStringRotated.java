package com.ryabokon.datastructures.arraystrings;

import org.junit.Assert;
import org.junit.Test;

/**
 * Assume you have a method isSubstring which checks if one word is a substring
 * of another. Given two strings, s1 and s2, write code to check If s2 is a rotation
 * of s1 using only one call to isSubstring
 * (e.g., "waterbottle" is a rotation of "erbottlewat").
 * --------------------------------------------------------------------------------------
 * 
 * Concatenate string with itself, to get all possible rotation combinations:
 * "waterbottlewaterbottle"
 * "---erbottlewat--------"
 * 
 * TODO: more effective solution?
 *
 */

public class IsStringRotated {

	public boolean isRotated(String one, String two) {

		if (one.length() != two.length()) {
			return false;
		}

		String doubled = one + one;
		return (doubled.contains(two));

	}

	@Test
	public void testWithAsserts() {
		boolean result = isRotated("waterbottle", "erbottlewat");
		Assert.assertTrue(result);

		result = isRotated("hello", "eeloh");
		Assert.assertFalse(result);
	}
}
