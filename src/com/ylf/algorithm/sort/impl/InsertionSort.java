package com.ylf.algorithm.sort.impl;

import com.ylf.algorithm.sort.ComparisonSort;

/**
 * 插入排序, 类似人工排序一副扑克牌
 * 时间复杂度: 最坏情况Θ(N^2)，平均情况Θ(N^2)
 */
public class InsertionSort implements ComparisonSort {
	
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
