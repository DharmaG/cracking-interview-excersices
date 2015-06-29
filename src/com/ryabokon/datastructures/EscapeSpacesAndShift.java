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
 * 
 * 
 */
public class EscapeSpacesAndShift {

	public String string = RandomStringUtils.random(200, "he ");

	@Test
	public void testWithAsserts() throws UnsupportedEncodingException {

		String original = " Hello  world . ";
		String expected = URLEncoder.encode(original, "UTF-8").replace("+", "%20");
		String result = useShiftArray(original);

		Assert.assertEquals(expected, result);
	}

	public String useShiftArray(String string) {

		char[] chars = string.toCharArray();
		int originalSize = chars.length;
		int spacesSize = 0; // count a total shift amount
		int[] shifts = new int[originalSize]; // shift amount for each symbol

		for (int i = 0; i < originalSize; i++) {

			if (chars[i] == ' ') {
				spacesSize += 2;
			}

			if (i + 1 < originalSize) {
				shifts[i + 1] = spacesSize;
			}
		}

		// Resize to fit more symbols
		chars = ArrayUtils.addAll(chars, new char[spacesSize]);

		// Go backwards and move symbols
		for (int i = originalSize - 1; i >= 0; i--) {
			int newIndex = i + shifts[i];
			if (chars[i] == ' ') {
				// If it's a space, put special chars.
				chars[newIndex] = '%';
				chars[newIndex + 1] = '2';
				chars[newIndex + 2] = '0';
			} else {
				chars[newIndex] = chars[i];
			}
		}

		return String.valueOf(chars);

	}
}
