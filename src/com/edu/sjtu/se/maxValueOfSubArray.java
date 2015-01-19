package com.edu.sjtu.se;

public class maxValueOfSubArray 
{	
	//1、穷举法，时间复杂法为（O(n^2)）
	public static int getMaxSum(int[] a, int n)
	{
		int maxValue = Integer.MIN_VALUE;
		int sum;
		for(int i = 0; i < n; i++)
		{
			sum = 0;//每一趟得到该趟的最大的sum值时需要重新归零（important）
			for(int j = i; j < n; j++)
			{
				sum += a[j];
				if(sum > maxValue)
				maxValue = sum;
			}
		}
		return maxValue;
	}
	
	//2、分治法，分为3段，前半段，后半段，包括中间两个值的最大段
	//时间复杂度为O(NlogN）
	public static int getMaxSum(int[] a, int left,int right)
	{
		int maxLeftSum, maxRightSum;                 //左、右部分最大连续子序列值。对应情况a、b   
		int maxLeftBorderSum, maxRightBorderSum;     //从中间分别到左右两侧的最大连续子序列值，对应c
		int leftBorderSum, rightBorderSum;
		int center;
		if(left == right)  //只有一个元素， basic condition
			if(a[left] > 0)
				return a[left];
			else
				return 0;
		
		 center = (left + right)/2;
		 maxLeftSum = getMaxSum(a,left,center);
		 maxRightSum = getMaxSum(a,center + 1,right);
		 
		 //寻找左侧包括边界值的最大连续子序列
		 maxLeftBorderSum = Integer.MIN_VALUE;
		 leftBorderSum = 0;
		 for(int i = center; i >= left; i--)
		 {
			 leftBorderSum += a[i];
			 if(leftBorderSum > maxLeftBorderSum)
				 maxLeftBorderSum = leftBorderSum;
		 }
		 
		 //寻找右侧包括边界值的最大连续子序列
		 maxRightBorderSum = Integer.MIN_VALUE;
		 rightBorderSum = 0;
		 for(int i = center + 1; i <= right; i++)
		 {
			 rightBorderSum += a[i];
			 if(rightBorderSum > maxRightBorderSum)
				 maxRightBorderSum = rightBorderSum;
		 }
		 
		 int max1 = maxLeftSum > maxRightSum ? maxLeftSum : maxRightSum; //情况a,b的最大值
		 int max2 = maxLeftBorderSum + maxRightBorderSum;
		 return max1 > max2 ? max1: max2;
	}
	
	//3.动态规划法
	//时间复杂度为O(n),空间复杂度为O(n)
	//计算出从每一个元素为起始元素的后面的子数组的最大值
	//具体思路：每个元素a[i]开始子数组的和的最大值记为start[i]，必须包括a[i]在内的子数组
	//对于所有的可能的最大连续子序列的值则在All[0]中寻找，代表从0开始的所有元素的子序列的最大值
	//对应的解为：All[i]是max{a[0],a[0]+start[i],All[i+1]},每一个前面的All[i]是基于后面的all[i+1]的值的
	public static int getMaxSum3(int[] a, int n)
	{
		//初始化
		int[] start = new int[n];
		int[] all = new int[n];
		start[n-1] = a[n-1];
		all[n-1] = a[n-1];
		
		for(int i = n-2; i >= 0; i--)
		{
			start[i] = max(a[i], a[i] + start[i+1] );
			all[i] = max(start[i], all[i+1]);
		}
		return all[0];
	}
		
	public static int max(int a , int b)
	{
		return a > b? a : b;
	}
	
	//4.基于动态规划法的升级版
	//基于算法3，因为每一个start[i]都是基于a[i]和start[i+1]两者的值,
	//all[i]也是基于start[i]和all[i+1]两者的值，因此我们要得到最终的结果，
	//只需要保存每一次的nStart和nAll的值，覆盖前面的值,实际上只需要all[0]的值，
	//所以中间的结果并不需要
	//时间复杂度为O(n)，空间复杂度为O(1)
	public static int getMaxSum4(int[] a, int n)
	{
		int nStart = a[n-1];
		int nAll = a[n-1];
		for(int i = n-2; i >= 0; i--)
		{
			nStart = max(a[i], a[i] + nStart);
			nAll = max(nAll,nStart);
		}
		return nAll;
	}
	
	//考虑数组全部为负数的情况，或者最后一个数字为负数的情况，可能会出现变异，比如算法2可能会出小问题
	
	
	
	public static void main(String[] args) {
		int[] array = new int[]{-2, -5, -3, 6, -4, -8, -6,0,8,-9,-7,-6};
		System.out.println(getMaxSum(array, array.length));
		System.out.println(getMaxSum(array,0,array.length - 1));
		System.out.println(getMaxSum3(array,array.length));
		System.out.println(getMaxSum4(array,array.length));
	}

}
