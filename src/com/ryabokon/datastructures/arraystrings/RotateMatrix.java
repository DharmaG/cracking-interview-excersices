package com.ryabokon.datastructures.arraystrings;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is
 * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in
 * place? Leetcode “Rotate Image”
 * -----------------------------------------------------------------------------
 * 
 * We can do this in place, by circular rotation of cells.
 * Just like you would swap real-world tiles, knowing all new locations
 * 
 * Start from "outside" circle of matrix
 * First, save one corner cell to a temp, to create a space for moving.
 * Then, swap corner cells, move index counter-clockwise and swap other cells.
 * Do the same thing for all inner circles.
 * 
 * 
 * Has O(n^2) as we need to relocate each cell,
 * and needs 1 additional int to store temporary cell data.
 */
public class RotateMatrix {

	@Test
	public void testSmallMatrix() {
		int[][] matrix = new int[][] { { 1, 2 }, { 3, 4 } };
		int[][] expected = new int[][] { { 3, 1 }, { 4, 2 } };
		int[][] result = rotateMatrixCircular(matrix.clone());

		Assert.assertArrayEquals(expected, result);
	}

	@Test
	public void testMediumMatrix() {
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] expected = new int[][] { { 7, 4, 1 }, { 8, 5, 2 }, { 9, 6, 3 } };
		int[][] result = rotateMatrixCircular(matrix.clone());

		Assert.assertArrayEquals(expected, result);
	}

	@Test
	public void testBigMatrix() {
		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		int[][] expected = new int[][] { { 13, 9, 5, 1 }, { 14, 10, 6, 2 }, { 15, 11, 7, 3 }, { 16, 12, 8, 4 } };
		int[][] result = rotateMatrixCircular(matrix.clone());

		Assert.assertArrayEquals(expected, result);
	}

	public int[][] rotateMatrixCircular(int[][] array) {

		int size = array.length - 1;
		int start = 0;
		int temp = 0;

		int tileAx;
		int tileAy;

		int tileBx;
		int tileBy;

		int tileCx;
		int tileCy;

		int tileDx;
		int tileDy;

		while (start < array.length / 2) {

			for (int i = start; i < size - start; i++) {
				tileAx = i;
				tileAy = start;

				tileBx = start;
				tileBy = size - i;

				tileCx = size - i;
				tileCy = size - start;

				tileDx = size - start;
				tileDy = i;

				temp = array[tileAy][tileAx];
				array[tileAy][tileAx] = array[tileBy][tileBx];
				array[tileBy][tileBx] = array[tileCy][tileCx];
				array[tileCy][tileCx] = array[tileDy][tileDx];
				array[tileDy][tileDx] = temp;
			}
			start++;
		}

		return array;

	}
}
