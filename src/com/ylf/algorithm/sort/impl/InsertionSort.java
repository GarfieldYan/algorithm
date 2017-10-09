package com.ylf.algorithm.sort.impl;

public class InsertionSort {
	
	/**
	 * 插入排序, 类似人工排序一副扑克牌
	 * 时间复杂度: 最坏情况O(N^2)，最好情况Ω(N)
	 */
	public static void insertionSort(int[] A) {
		for (int i = 1; i < A.length; i++) {
			int pendingInsertItem = A[i];
			int j = i - 1;
			while (j >= 0 && A[j] > pendingInsertItem) {
				A[j + 1] = A[j];
				j--;
			}
			A[j + 1] = pendingInsertItem;
		}
	}
	
}
