package com.ryabokon.datastructures;

import org.junit.Test;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 * ---------------------------------------------------------------------------------
 * 
 */
public class IsStringShuffled {

	@Test
	public void test() {

		String a = "abcd";
		String b = "acdb";
		isStringShuffled(a, b);
	}

	/**
	 * "Subtract" two string. Nice try, but no.
	 * "abcd" - "acdb" => [0,1,1,-2] => sum == 0
	 * Buuut "abcd" - "aace" => [0,-1,0,1] => sum == 0
	 * Works only for strings with same chars, that are obviously already shuffled.
	 */
	public boolean isStringShuffled(String first, String second) {
		char[] firstChar = first.toCharArray();
		char[] secondChar = second.toCharArray();

		int sum = 0;
		for (int i = 0; i < firstChar.length; i++) {
			int diff = firstChar[i] - secondChar[i];
			sum += diff;
			System.out.println(diff);
		}
		System.out.println("Sum: " + sum);

		return false;
	}
}
