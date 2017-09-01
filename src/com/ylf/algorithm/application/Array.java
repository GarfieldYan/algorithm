package com.ylf.algorithm.application;

import com.ylf.algorithm.sort.Sort;

public class Array {

	/**
	 * 寻找和为定值的两个数, 时间复杂度O(NlogN)
	 * 
	 * @return 是否存在和为定值的两个数
	 */
	public static boolean findTwoNumForFixedSum(int[] A, int fixedSum) {
		// 先执行归并排序，时间复杂度O(NlogN)
		Sort.mergeSort(A);
		// 然后用两个指针i，j，各自指向数组的首尾两端，令i=0，j=n-1，然后i++，j--，逐次判断a[i]+a[j]?=sum. 时间复杂度O(N)
		boolean result = false;
		int i = 0;
		int j = A.length - 1;
		while (i < j) {
			// 如果某一刻a[i]+a[j] > sum，则要想办法让sum的值减小，所以此刻i不动，j--
			if (A[i] + A[j] > fixedSum) {
				j--;
				continue;
				// 如果某一刻a[i]+a[j] < sum，则要想办法让sum的值增大，所以此刻i++，j不动
			} else if (A[i] + A[j] < fixedSum) {
				i++;
				continue;
			} else {
				result = true;
				System.out.println("(" + A[i] + "," + A[j] + ")");
				break;
			}
		}
		return result;
	}

	/**
	 * A[1..n]中，若i<j且A[i]>A[j]，则对偶(i,j)称为A的一个逆序对. 此算法是归并排序的增强版本
	 * 
	 * @return 总逆序对数量
	 */
	public static int calcInversion(int[] A) {
		// 存放对数组A进行归并排序后的结果
		int tempA[] = new int[A.length];
		return calcInversion(A, tempA, 0, A.length - 1);
	}

	public static int calcInversion(int[] A, int[] tempA, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			return calcInversion(A, tempA, p, q) 
					+ calcInversion(A, tempA, q + 1, r)
					+ mergeAndCalcInversion(A, tempA, p, q + 1, r);
		} else {
			return 0;
		}
	}

	/**
	 * 合并两个已排序的序列，返回合并过程中发现的逆序对数量(不计两个数只在左数组或只在右数组的逆序对)
	 * 
	 * @param A 待合并数组
	 * @param pqr p<=q<r，且 A[p..q]和A[q+1..r]已排序
	 */
	private static int mergeAndCalcInversion(int[] A, int[] tempA, int p, int q, int r) {
		int currentLeft = p;
		int currentRight = q + 1;
		int count = 0;
		for (int i = p; i < r - p + 1; i++) {
			// 两个子序列都已用完
			if (currentLeft > q && currentRight > r) {
				break;
			}
			// 左边子序列已用完，右边没用完
			if (currentLeft > q) {
				tempA[i] = A[currentRight];
				currentRight++;
				continue;
			}
			// 右边子序列已用完，左边没用完
			if (currentRight > r) {
				tempA[i] = A[currentLeft];
				currentLeft++;
				continue;
			}
			// 两边都还没用完
			if (A[currentLeft] > A[currentRight]) {
				tempA[i] = A[currentRight];
				currentRight++;
				count = count + (q - currentLeft + 1);
				continue;
			} else {
				tempA[i] = A[currentLeft];
				currentLeft++;
				continue;
			}
		}
		// 将排好序的数组复制回原数组
		for (int i = p; i < r - p + 1; i++) {
			A[p + i] = tempA[i];
		}
		return count;
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 20, 6, 4, 5 };
		System.out.println("Number of inversions are " + Array.calcInversion(arr));
	}

}