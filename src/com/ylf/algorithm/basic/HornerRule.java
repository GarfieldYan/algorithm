package com.ylf.algorithm.basic;

/**
 * 实现霍纳规则的多项式计算
 * 时间复杂度: Θ(N)
 */
public class HornerRule {

	/**
	 * Y=a0+a1*X+a2*X^2+.....+an*X^n
	 *  =a0+X(a1+X(a2+X(a3+X(...+X(an-1+X*(an))))))
	 * 
	 * @param a 多项式的系数数组
	 * @param n 系数数组的长度
	 * @param x 输入
	 * @return y 输出
	 */
	public static double hornerFunction(double[] a, int n, double X) {
		double Y = 0;
		int i = n - 1;
		while (i >= 0) {
			Y = a[i] + X * Y;
			i--;
		}
		return Y;
	}
	
	public static void main(String[] args) {
		double data[] = new double[] { 1.0, 2, 2, 3, 4 };
		double x = 3;
		System.out.println(hornerFunction(data, data.length, x));
	}

}
