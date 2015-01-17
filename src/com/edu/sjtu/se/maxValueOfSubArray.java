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
	
	public static void main(String[] args) {
		int[] array = new int[]{-2, 5, 3, -6, 4, -8, 6};
		System.out.println(getMaxSum(array, 7));
		System.out.println(getMaxSum(array,0,array.length - 1));
	}

}
