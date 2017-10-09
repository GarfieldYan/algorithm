package com.ylf.algorithm.sort;

import com.ylf.algorithm.sort.impl.BubbleSort;
import com.ylf.algorithm.sort.impl.InsertionSort;
import com.ylf.algorithm.sort.impl.MergeSort;

public class Sorter {

	public static void bubbleSort(int[] A) {
		BubbleSort.bubbleSort(A);
	}

	public static void insertionSort(int[] A) {
		InsertionSort.insertionSort(A);
	}

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