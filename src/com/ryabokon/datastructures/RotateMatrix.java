package com.ryabokon.datastructures;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is
 * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in
 * place? Leetcode “Rotate Image”
 * -----------------------------------------------------------------------------
 * 
 * We can do this by circular rotation of cells. Works for 3x3
 * 
 * TODO: generalize for NxN arrays
 *
 */
public class RotateMatrix {

	@Test
	public void testWithAssert() {
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] expected = new int[][] { { 7, 4, 1 }, { 8, 5, 2 }, { 9, 6, 3 } };
		int[][] result = rotateMatrixCircular(matrix.clone());

		Assert.assertArrayEquals(expected, result);

	}

	public int[][] rotateMatrixCircular(int[][] array) {

		int size = array.length - 1;
		int temp = 0;

		int tileAx;
		int tileAy;

		int tileBx;
		int tileBy;

		int tileCx;
		int tileCy;

		int tileDx;
		int tileDy;

		for (int i = 0; i < size; i++) {
			tileAx = i;
			tileAy = 0;

			tileBx = 0;
			tileBy = size - i;

			tileCx = size - i;
			tileCy = size;

			tileDx = size;
			tileDy = i;

			temp = array[tileAy][tileAx];
			array[tileAy][tileAx] = array[tileBy][tileBx];
			array[tileBy][tileBx] = array[tileCy][tileCx];
			array[tileCy][tileCx] = array[tileDy][tileDx];
			array[tileDy][tileDx] = temp;
		}

		return array;

	}
}
