package com.ylf.algorithm.sort.impl;

/**
 * 冒泡排序
 * 时间复杂度: Θ(N^2)
 */
public class BubbleSort implements ComparisonSort {
	
	public static void bubbleSort(int[] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = A.length - 1; j > i; j--) {
				if (A[j - 1] > A[j]) {
					int tmp = A[j - 1];
					A[j - 1] = A[j];
					A[j] = tmp;
				}
			}
		}
	}
	
}
