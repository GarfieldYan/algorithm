package com.ylf.algorithm.sort;

import com.ylf.algorithm.sort.impl.BubbleSort;
import com.ylf.algorithm.sort.impl.InsertionSort;
import com.ylf.algorithm.sort.impl.MergeSort;

public class Sorter {

	/**
	 * 时间复杂度: Θ(N^2)
	 */
	public static void bubbleSort(int[] A) {
		BubbleSort.bubbleSort(A);
	}

	/**
	 * 时间复杂度: 最坏情况O(N^2)，最好情况Ω(N)
	 */
	public static void insertionSort(int[] A) {
		InsertionSort.insertionSort(A);
	}

	/**
	 * 时间复杂度: Θ(NlgN)
	 */
	public static void mergeSort(int[] A) {
		MergeSort.mergeSort(A);
	}

	public static void main(String[] args) {
		int[] items = new int[] { 5, 2, 1, 7, 3, 4, 8, 6 };
		Sorter.bubbleSort(items);
		for (int i = 0; i < items.length; i++) {
			System.out.print(items[i] + ((i == (items.length - 1)) ? "" : ", "));
		}
	}

}