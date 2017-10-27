package com.ylf.algorithm.application;

import java.util.Arrays;

import com.ylf.algorithm.sort.impl.MergeSort;

public class Array {
	
	/**
	 * 寻找最大子数组，采用动态规划
	 * C[i]表示以A[i]结尾的子数组中的最大子数组和. 所以C[i] = Max{ C[i-1]+A[i], A[i] }
	 * 
	 * 时间复杂度: Θ(N)
	 * @return 返回最大子数组信息，元素1是左边界，元素2是右边界，元素3是该最大子数组所有元素的和
	 */
	public static int[] findMaximumSubarray_DP(int[] A) {
		int[] C = new int[A.length];
		C[0] = A[0];
		int start = 0, end = 0;
		int ciStart = 0; //C[i]的起点
		int maximumSum = Integer.MIN_VALUE;
		for (int i = 1; i < A.length; i++) {
			if (C[i-1] < 0) {
				//因为C[i] = Max{ C[i-1]+A[i], A[i] }
				C[i] = A[i];
				ciStart = i;
			} else {
				C[i] = C[i-1] + A[i];
			}
			if (C[i] > maximumSum) {
				start = ciStart;
				end = i;
				maximumSum = C[i];
			}
		}
		return new int[] { start, end, maximumSum };
	}
	
	/**
	 * 寻找最大子数组，动态规划版本的变种
	 * 
	 * 时间复杂度: Θ(N)
	 * @return 返回最大子数组信息，元素1是左边界，元素2是右边界，元素3是该最大子数组所有元素的和
	 */
	public static int[] findMaximumSubarray(int[] A) {
		int start = 0, end = 0;				//保存全局最大子数组的起点和终点
		int maximumSum = Integer.MIN_VALUE;	//保存全局最大子数组的和
		int currentStart = 0, currentEnd = 0;	//保存当前子数组的起点和终点
		int currentSum = 0;						//保存当前子数组的和
		
		//遍历整个数组，查找和为非负数的所有子数组
		for (int i = 0; i < A.length; i++) {
			//若不包含A[i]的当前子数组和为负数，则将当前子数组重置为A[i]
			if (currentSum < 0) {
				currentStart = i;
				currentEnd = i;
				currentSum = A[i];
			//若不包含A[i]的当前子数组和为非负数，则将A[i]纳入当前子数组
			} else {
				currentEnd = i;		//更新当前子数组的终点
				currentSum += A[i];	//更新当前子数组的和
			}
			
			//将当前子数组和与全局最大子数组和进行对比，若大于则更新全局最大子数组
			if (currentSum > maximumSum) {
				start = currentStart;
				end = currentEnd;
				maximumSum = currentSum;
			}
		}
		
		return new int[] {start, end, maximumSum};
	}
	
	/**
	 * 寻找最大子数组，采用分治法(Divide And Conqure)
	 * 
	 * 时间复杂度: Θ(NlgN)
	 * @return 返回最大子数组信息，元素1是左边界，元素2是右边界，元素3是该最大子数组所有元素的和
	 */
	public static int[] findMaximumSubarray_DAC(int[] A) {
		return findMaximumSubarray(A, 0, A.length - 1);
	}
	
	/**
	 * 寻找指定范围内的最大子数组, p<=r
	 *
	 * 时间复杂度: Θ(NlgN)
	 * @return 返回最大子数组信息，元素1是左边界，元素2是右边界，元素3是该最大子数组所有元素的和
	 */
	private static int[] findMaximumSubarray(int[] A, int p, int r) {
		if (p == r) {
			return new int[] {p, r, A[p]};
		} else {
			int mid = (p + r) / 2;
			int[] leftMaximumSubarray = findMaximumSubarray(A, p, mid);
			int[] rightMaximumSubarray = findMaximumSubarray(A, mid+1, r);
			int[] crossMaximumSubarray = findMaxCrossingSubarray(A, p, mid, r);
			if (leftMaximumSubarray[2] >= rightMaximumSubarray[2] && leftMaximumSubarray[2] >= crossMaximumSubarray[2]) {
				return leftMaximumSubarray;
			} else if (rightMaximumSubarray[2] >= leftMaximumSubarray[2] && rightMaximumSubarray[2] >= crossMaximumSubarray[2]) {
				return rightMaximumSubarray;
			} else {
				return crossMaximumSubarray;
			}
		}
	}
	
	/**
	 * 寻找指定范围内跨越中点的最大子数组, p<=mid<r
	 * 目标最大子数组由两部分组成，左部分为[left..mid], 右部分为[mid+1..right]
	 * 
	 * 时间复杂度: Θ(N)
	 * @return 返回最大子数组信息，元素1是左边界，元素2是右边界，元素3是该最大子数组所有元素的和
	 */
	private static int[] findMaxCrossingSubarray(int[] A, int p, int mid, int r) {
		int left = mid;
		int maxLeftSum = 0;	
		for (int i = mid, sum = 0; i >= p; i--) {
			sum += A[i];
			if (sum > maxLeftSum) {
				maxLeftSum = sum;
				left = i;
			}
		}
		
		int right = mid + 1;
		int maxRightSum = 0;
		for (int i = mid + 1, sum = 0; i <= r; i++) {
			sum += A[i];
			if (sum > maxRightSum) {
				maxRightSum = sum;
				right = i;
			}
		}
		
		return new int[] {left, right, maxLeftSum + maxRightSum};
	}

	/**
	 * 寻找和为定值的两个数
	 * 
	 * 时间复杂度: Θ(NlgN)
	 * @return 是否存在和为定值的两个数
	 */
	public static boolean findTwoNumForFixedSum(int[] A, int fixedSum) {
		// 先执行归并排序，时间复杂度Θ(NlogN)
		MergeSort.mergeSort(A);
		// 以下代码时间复杂度O(N), 用两个指针i，j，各自指向数组的首尾两端，令i=0，j=n-1，然后i++，j--，逐次判断a[i]+a[j]?=sum 
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
	 * 时间复杂度: Θ(NlgN)
	 * @return 总逆序对数量
	 */
	public static int calcInversion(int[] A) {
		return calcInversion(A, 0, A.length - 1);
	}

	public static int calcInversion(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			return calcInversion(A, p, q) 					//两个数都在左子序列中的逆序对
					+ calcInversion(A, q + 1, r)			//两个数都在右子序列中的逆序对
					+ mergeAndCalcInversion(A, p, q, r);	//大数在左子序列，小数在右子序列中的逆序对
		} else {
			return 0;
		}
	}

	/**
	 * 合并两个已排序的序列，返回合并过程中发现的逆序对数量(大数在左子序列，小数在右子序列中的逆序对)
	 * 
	 * @param A 待合并数组
	 * @param pqr p<=q<r，且 A[p..q]和A[q+1..r]已排序
	 */
	private static int mergeAndCalcInversion(int[] A, int p, int q, int r) {
		int count = 0;
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
				count = count + (q - currentLeft + 1);
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
		return count;
	}
	
	private static void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			if (i != A.length - 1) {
				System.out.print(A[i] + ",");
			} else {
				System.out.println(A[i]);
			}
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		Array.print(arr);
		System.out.println(Arrays.toString(Array.findMaximumSubarray_DP(arr)));
	}
	
}