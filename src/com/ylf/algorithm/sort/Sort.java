package com.ylf.algorithm.sort;

public class Sort {

	/**
	 * 冒泡排序, 时间复杂度O(N^2)
	 */
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

	/**
	 * 插入排序, 类似人工排序一副扑克牌, 时间复杂度O(N^2)
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

	/**
	 * 归并排序，分治法的一种, 时间复杂度O(NlogN)
	 */
	public static void mergeSort(int[] A) {
		mergeSort(A, 0, A.length - 1);
	}

	public static void mergeSort(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

	/**
	 * 归并排序的辅助过程，用来合并两个已排序的序列
	 * 
	 * @param	A	待合并数组
	 * @param	pqr	p<=q<r，且 A[p..q]和A[q+1..r]已排序
	 */
	private static void merge(int[] A, int p, int q, int r) {
		int[] rangedA = new int[r - p + 1];
		int currentLeft = p;
		int currentRight = q + 1;
		for (int i = 0; i < rangedA.length; i++) {
			// 两个子序列都已用完
			if (currentLeft > q && currentRight > r) {
				break;
			}
			// 左边子序列已用完，右边没用完
			if (currentLeft > q) {
				rangedA[i] = A[currentRight];
				currentRight++;
				continue;
			}
			// 右边子序列已用完，左边没用完
			if (currentRight > r) {
				rangedA[i] = A[currentLeft];
				currentLeft++;
				continue;
			}
			// 两边都还没用完
			if (A[currentLeft] > A[currentRight]) {
				rangedA[i] = A[currentRight];
				currentRight++;
				continue;
			} else {
				rangedA[i] = A[currentLeft];
				currentLeft++;
				continue;
			}
		}
		// 将排好序的数组复制回原数组
		for (int i = 0; i < rangedA.length; i++) {
			A[p + i] = rangedA[i];
		}
	}

	public static void main(String[] args) {
		int[] items = new int[] { 5, 2, 1, 7, 3, 4, 8, 6 };
		Sort.bubbleSort(items);
		for (int i = 0; i < items.length; i++) {
			System.out.print(items[i] + ((i == (items.length - 1)) ? "" : ", "));
		}
	}

}