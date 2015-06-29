package com.ryabokon.datastructures;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become
 * a2blc5a3. If the "compressed" string would not become smaller than the original
 * string, your method should return the original string.
 *
 */
public class StringCompression {

	@Test
	public void testWithAsserts() {
		String string = "aabcccccaaa";
		String expected = "a2b1c5a3";
		String compress = compress(string);
		Assert.assertEquals(expected, compress);

		String expectedStronger = "a2bc5a3";
		String strongerCompress = strongerCompress(string);
		Assert.assertEquals(expectedStronger, strongerCompress);

		System.out.println(string);
		System.out.println(compress);
		System.out.println(strongerCompress);

	}

	public String compress(String string) {
		char[] chars = string.toCharArray();
		char[] result = new char[chars.length * 2];

		char previousChar = 0;
		int resultIndex = -1;
		int count = 1;

		for (int i = 0; i < chars.length; i++) {
			char currentChar = chars[i];
			if (currentChar == previousChar) {
				count++;
			} else {
				count = 1;
				result[resultIndex + 1] = currentChar;
				resultIndex += 2;
				previousChar = currentChar;
			}
			if (count < 10) {
				result[resultIndex] = Character.forDigit(count, 10);
			} else {
				result[resultIndex] = Character.forDigit(count / 10, 10);
				result[resultIndex + 1] = Character.forDigit(count % 10, 10);
			}
		}

		String resultString = String.valueOf(result).trim();

		if (resultString.length() > string.length()) {
			resultString = string;
		}

		return resultString;
	}

	public String strongerCompress(String string) {
		char[] chars = string.toCharArray();
		char[] result = new char[chars.length * 2];

		char previousChar = 0;
		int resultIndex = -1;
		int count = 1;

		for (int i = 0; i < chars.length; i++) {
			char currentChar = chars[i];
			if (currentChar == previousChar) {
				if (count == 1) {
					resultIndex++;
				}

				count++;

				if (count < 10) {
					result[resultIndex] = Character.forDigit(count, 10);
				} else {
					result[resultIndex] = Character.forDigit(count / 10, 10);
					result[resultIndex + 1] = Character.forDigit(count % 10, 10);
				}
			} else {
				count = 1;
				resultIndex++;
				result[resultIndex] = currentChar;
				previousChar = currentChar;
			}

		}

		String resultString = String.valueOf(result).trim();

		if (resultString.length() > string.length()) {
			resultString = string;
		}

		return resultString;
	}
}
