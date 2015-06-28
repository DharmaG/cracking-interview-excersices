package com.ryabokon.datastructures;

import java.util.HashSet;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.ecyrd.speed4j.StopWatch;

/**
 * Implement an algorithm to determine if a string has all unique characters. What
 * if you cannot use additional data structures?
 * -------------------------------------------------------------------------------
 * For a random string,
 * sometimes it's a bit cheaper to use all-to-all compare with O(n^2)
 * than to create an additional array each time and use O(n) check.
 * Creating a Set each time is always more expensive.
 * 
 * array: 1615 ms
 * slow: 1996 ms
 * set: 4124 ms
 * 
 * For a worst case scenario array check is much better
 * array: 2902 ms
 * slow: 19508 ms
 * set: 47759 ms
 */
public class UniqueCharsInString {

	private static int iterations = 100000000;

	@Test
	public void worstCaseTest() {
		System.out.println("Worst case string:");
		String string = "qwertyuiopasdfghjklzxcvbnm";

		for (int i = 1; i <= string.length(); i++) {
			String subString = string.substring(0, i);
			testArray(subString);
			testSlow(subString);
			testSet(subString);
		}
	}

	@Test
	public void randomStringTest() {
		System.out.println("Random string:");

		String string = RandomStringUtils.random(26, "qwertyuiopasdfghjklzxcvbnm");
		testArray(string);
		testSlow(string);
		testSet(string);
	}

	public void testSlow(String string) {
		StopWatch sw = new StopWatch();
		for (int i = 0; i < iterations; i++) {
			areAllCharsUniqueSlow(string);
		}
		sw.stop("slow", iterations);
		System.out.println(sw);
	}

	public void testSet(String string) {
		StopWatch sw = new StopWatch();
		for (int i = 0; i < iterations; i++) {
			areAllCharsUniqueSet(string);
		}
		sw.stop("set", iterations);
		System.out.println(sw);
	}

	public void testArray(String string) {
		StopWatch sw = new StopWatch();
		for (int i = 0; i < iterations; i++) {
			areAllCharsUniqueFast(string);
		}
		sw.stop("array", iterations);
		System.out.println(sw);
	}

	/**
	 * No-brainer, check each char vs. each other chars. O(n^2)
	 */
	public boolean areAllCharsUniqueSlow(String string) {
		char[] chars = string.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			for (int j = i + 1; j < chars.length; j++) {
				if (chars[i] == chars[j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Use power of set to check for unique chars
	 */
	public boolean areAllCharsUniqueSet(String string) {
		char[] chars = string.toCharArray();
		HashSet<Character> occurances = new HashSet<>();
		for (Character c : chars) {
			if (occurances.contains(c)) {
				return false;
			}
			occurances.add(c);
		}
		return true;

	}

	/**
	 * Use an additional boolean array, size of allowed chars amount.
	 * To "count" previous char occurrences
	 * 
	 * should be O(n)
	 */
	public boolean areAllCharsUniqueFast(String string) {

		char[] chars = string.toCharArray();

		boolean[] occurances = new boolean[26]; // A=65, a=97, z=122
		for (int i = 0; i < chars.length; i++) {
			int c = chars[i] - 97;
			if (occurances[c]) {
				return false;
			}
			occurances[c] = true;
		}

		return true;

	}

}
