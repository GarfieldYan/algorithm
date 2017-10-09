package com.ylf.algorithm.sort.impl;

/**
 * 归并排序，分治法的一种
 * 时间复杂度: 最坏情况Θ(nlgn)，平均情况Θ(nlgn)
 */
public class MergeSort implements ComparisonSort {
	
	public static void mergeSort(int[] A) {
		mergeSort(A, 0, A.length - 1);
	}

	private static void mergeSort(int[] A, int p, int r) {
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

}
