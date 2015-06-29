package com.ryabokon.datastructures;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the
 * string has sufficient space at the end of the string to hold the additional characters,
 * and that you are given the "true" length of the string. (Note: if implementing in Java,
 * please use a character array so that you can perform this operation in place.)
 * ---------------------------------------------------------------------------------------
 * Can be solved in two passes
 * 1) Count spaces in string
 * 2) Go in reverse order,
 * - calculate new positions for chars, using amount of spaces
 * - move chars to new positions
 * - if it's a space char, put special chars
 * 
 * Complexity is O(2n)
 * 
 * Less effective algorithm is to calculate new positions for chars on the first pass
 * And move chars on the second pass. This will use additional array, size of string.
 * 
 * testNewPositionsArray: 74,7 s
 * testSpacesCount: 56 s
 * 
 */
public class EscapeSpacesAndShift {

	public String string = RandomStringUtils.random(2000, "he ");
	private static int iterations = 10000000;

	@Test
	public void testNewPositionsArray() {
		for (int i = 0; i < iterations; i++) {
			useNewPositionsArray(string);
		}
	}

	@Test
	public void testSpacesCount() {
		for (int i = 0; i < iterations; i++) {
			useSpacesCount(string);
		}
	}

	@Test
	public void testWithAsserts() throws UnsupportedEncodingException {

		String original = " Hello  world . ";
		String expected = URLEncoder.encode(original, "UTF-8").replace("+", "%20");
		String resultNewPosArray = useNewPositionsArray(original);
		String resultSpacesCount = useSpacesCount(original);

		Assert.assertEquals(expected, resultNewPosArray);
		Assert.assertEquals(expected, resultSpacesCount);
	}

	/**
	 * On first pass, calculate a new position for each symbol
	 * On the second pass, move symbols
	 */
	public String useNewPositionsArray(String string) {

		char[] chars = string.toCharArray();
		int originalSize = chars.length;
		int spacesSize = 0; // count a total shift amount
		int[] newPositions = new int[originalSize]; // shift amount for each symbol
		for (int i = 0; i < originalSize; i++) {

			if (chars[i] == ' ') {
				spacesSize += 2;
			}

			if (i + 1 < originalSize) {
				newPositions[i + 1] = spacesSize + i + 1;
			}
		}

		// Resize to fit more symbols
		chars = ArrayUtils.addAll(chars, new char[spacesSize]);

		// Go backwards and move symbols
		for (int i = originalSize - 1; i >= 0; i--) {
			int newPosition = newPositions[i];
			if (chars[i] == ' ') {
				// If it's a space, put special chars.
				chars[newPosition] = '%';
				chars[newPosition + 1] = '2';
				chars[newPosition + 2] = '0';
			} else {
				chars[newPosition] = chars[i];
			}
		}

		return String.valueOf(chars);

	}

	/**
	 * Count amount of spaces, and calculate new symbol's position on the fly
	 */
	public String useSpacesCount(String string) {

		char[] chars = string.toCharArray();
		int originalSize = chars.length;
		int spaces = 0;

		for (int i = 0; i < originalSize; i++) {
			if (chars[i] == ' ') {
				spaces++;
			}
		}

		// Resize to fit more symbols
		chars = ArrayUtils.addAll(chars, new char[spaces * 2]);

		// Go backwards and move symbols
		for (int i = originalSize - 1; i >= 0; i--) {
			int newIndex = i + spaces * 2;
			if (chars[i] == ' ') {
				// If it's a space, put special chars.
				chars[newIndex - 2] = '%';
				chars[newIndex - 1] = '2';
				chars[newIndex] = '0';
				spaces--;
			} else {
				chars[newIndex] = chars[i];
			}
		}

		return String.valueOf(chars);

	}
}
