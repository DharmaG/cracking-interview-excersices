package com.ryabokon.datastructures;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

public class ZerosToRowsAndColumns {

	int[][] testMatrix = getRandomMatrix(10000, 20000);
	int iterations = 1;

	@Test
	public void testSetPerformance() {
		for (int i = 0; i < iterations; i++) {
			int[][] result = nullRowsAndColsWithSet(cloneArray(testMatrix));
		}

	}

	@Test
	public void testDumbPerformance() {
		for (int i = 0; i < iterations; i++) {
			int[][] result = nullRowsAndColsDumb(cloneArray(testMatrix));
		}

	}

	@Test
	public void testSmartPerformance() {
		for (int i = 0; i < iterations; i++) {
			int[][] result = nullRowsAndColsBool(cloneArray(testMatrix));
		}

	}

	@Test
	public void testWithAsserts() {

		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 0, 6, 7 }, { 1, 8, 9, 10 } };
		int[][] expected = { { 1, 0, 3, 4 }, { 0, 0, 0, 0 }, { 1, 0, 9, 10 } };

		int[][] result = nullRowsAndColsWithSet(cloneArray(matrix));
		Assert.assertArrayEquals("Set assert failed", expected, result);

		result = nullRowsAndColsBool(cloneArray(matrix));
		Assert.assertArrayEquals("Smart assert failed", expected, result);

		result = nullRowsAndColsDumb(cloneArray(matrix));
		Assert.assertArrayEquals("Dumb assert failed", expected, result);

	}

	public int[][] nullRowsAndColsBool(int[][] matrix) {

		int rows = matrix.length;
		int cols = matrix[0].length;

		boolean[] zeroRows = new boolean[rows];
		boolean[] zeroCols = new boolean[cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				if (matrix[i][j] == 0) {
					zeroRows[i] = true;
					zeroCols[j] = true;
					break;
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			if (zeroRows[i]) {
				for (int j = 0; j < cols; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		for (int j = 0; j < cols; j++) {
			if (zeroCols[j]) {
				for (int i = 0; i < rows; i++) {
					if (zeroRows[i]) {
						continue;
					}
					matrix[i][j] = 0;
				}
			}
		}

		return matrix;

	}

	public int[][] nullRowsAndColsWithSet(int[][] matrix) {

		int rows = matrix.length;
		int cols = matrix[0].length;

		Set<Integer> zeroRows = new HashSet<Integer>();
		Set<Integer> zeroCols = new HashSet<Integer>();

		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < cols; j++) {

				if (matrix[i][j] == 0) {
					zeroRows.add(i);
					zeroCols.add(j);
					break;
				}
			}
		}

		for (int i : zeroRows) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = 0;
			}
		}

		for (int j : zeroCols) {
			for (int i = 0; i < rows; i++) {
				if (zeroRows.contains(i)) {
					continue;
				}
				matrix[i][j] = 0;
			}

		}

		return matrix;

	}

	public int[][] nullRowsAndColsDumb(int[][] matrix) {

		int rows = matrix.length;
		int cols = matrix[0].length;

		Set<Integer> zeroRows = new HashSet<Integer>();
		Set<Integer> zeroCols = new HashSet<Integer>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				if (matrix[i][j] == 0) {
					zeroRows.add(i);
					zeroCols.add(j);
					break;
				}
			}
		}

		for (int i : zeroRows) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = 0;
			}
		}

		for (int j : zeroCols) {
			for (int i = 0; i < rows; i++) {
				matrix[i][j] = 0;
			}

		}

		return matrix;

	}

	private int[][] getRandomMatrix(int rows, int cols) {
		int[][] matrix = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = RandomUtils.nextInt(0, 1000);
			}
		}

		return matrix;

	}

	private int[][] cloneArray(int[][] src) {
		int length = src.length;
		int[][] target = new int[length][src[0].length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(src[i], 0, target[i], 0, src[i].length);
		}
		return target;
	}
}
