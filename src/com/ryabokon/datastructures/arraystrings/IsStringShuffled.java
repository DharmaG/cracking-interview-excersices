package com.ryabokon.datastructures.arraystrings;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.ecyrd.speed4j.StopWatch;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 * ---------------------------------------------------------------------------------
 * 
 * Optimal solution is to count occurrences of chars in both strings
 * +1 for a char from the first string, -1 for another
 * And then to check the "sum"
 * 
 * Another, slower solution is to sort and compare char arrays.
 * 
 * countFaster: 7008 ms
 * count: 11427 ms
 * sort: 78134 ms
 */
public class IsStringShuffled {

	private static int iterations = 100000000;
	String a = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	String b = "QWERTYUIOPASDFGHJKLZXCVBNMXwertyuiopasdfghjklzxcvbnm";

	// -----------------------------------^ this one is extra

	@Test
	public void testCount() {

		for (int i = 0; i < iterations; i++) {
			countCharsInString(a, b);
		}
	}

	@Test
	public void testCountFaster() {

		for (int i = 0; i < iterations; i++) {
			countCharsInStringFaster(a, b);
		}
	}

	@Test
	public void testSort() {

		for (int i = 0; i < iterations; i++) {
			sortChars(a, b);
		}
	}

	@Test
	public void testWithAsserts() {
		String a = "abcd";
		String b = "bcda";
		String c = "bcdb";

		Assert.assertTrue(sortChars(a, b));
		Assert.assertTrue(countCharsInString(a, b));
		Assert.assertTrue(countCharsInStringFaster(a, b));
		Assert.assertFalse(sortChars(a, c));
		Assert.assertFalse(countCharsInString(a, c));
		Assert.assertFalse(countCharsInStringFaster(a, c));
	}

	/**
	 * Have an array with a count of characters for both strings.
	 * If char is in one string, add 1. If in another, subtract 1;
	 * At the end, if array has all 0s, string is mixed.
	 * Has O(4*n+k) complexity, where k depends on amount of unique chars.
	 * Can be optimized, not to wait till all sums are calculated.
	 */
	public boolean countCharsInString(String first, String second) {

		if (first.length() != second.length())
			return false;

		int[] charsCount = new int[58]; // a=97, z=122

		char[] firstChars = first.toCharArray();
		char[] secondChars = second.toCharArray();

		for (int i = 0; i < firstChars.length; i++) {
			int firstChar = firstChars[i] - 65;
			int secondChar = secondChars[i] - 65;
			charsCount[firstChar]++;
			charsCount[secondChar]--;
		}

		for (int i = 0; i < charsCount.length; i++) {
			if (charsCount[i] != 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Works a faster in some cases.
	 * Add 1 on loop through the first array.
	 * Subtract 1 in loop through the second array.
	 * If char count becomes -1 before second loop end, no need to end the loop.
	 * Has O(4*n) complexity
	 */
	public boolean countCharsInStringFaster(String first, String second) {

		if (first.length() != second.length())
			return false;

		int[] charsCount = new int[58]; // a=97, z=122

		char[] firstChars = first.toCharArray();
		char[] secondChars = second.toCharArray();

		for (int i = 0; i < firstChars.length; i++) {
			int c = firstChars[i] - 65;
			charsCount[c]++;
		}

		for (int i = 0; i < secondChars.length; i++) {
			int c = secondChars[i] - 65;
			charsCount[c]--;

			if (charsCount[c] < 0) {
				return false;
			}
		}

		return true;
	}

	public boolean sortChars(String first, String second) {
		char[] firstChars = first.toCharArray();
		char[] secondChars = second.toCharArray();

		Arrays.sort(firstChars);
		Arrays.sort(secondChars);

		return Arrays.equals(firstChars, secondChars);
	}

	/**
	 * "Subtract" two string. Nice try, but no.
	 * "abcd" - "acdb" => [0,1,1,-2] => sum == 0
	 * Buuut "abcd" - "aace" => [0,-1,0,1] => sum == 0
	 * Works only for strings with same chars, that are obviously already shuffled.
	 */
	@Deprecated
	public boolean substractStrings(String firt, String second) {
		char[] firstChar = firt.toCharArray();
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

	@Test
	@Ignore
	public void dataForComplexityGraph() {

		String one = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		String two = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMxwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

		StopWatch swCount = new StopWatch("count");
		StopWatch swFaster = new StopWatch("faster");
		StopWatch swSort = new StopWatch("sort");

		for (int i = 0; i <= one.length(); i++) {

			a = one.substring(0, i);
			b = two.substring(0, i);

			swCount.start();
			testCount();
			swCount.stop(iterations);
			System.out.println(swCount);

			swFaster.start();
			testCountFaster();
			swFaster.stop(iterations);
			System.out.println(swFaster);

			swSort.start();
			testSort();
			swSort.stop(iterations);
			System.out.println(swSort);

		}

	}

}
