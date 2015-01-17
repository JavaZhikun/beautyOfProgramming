package com.edu.sjtu.se;

public class maxValueOfSubArray 
{	
	//1����ٷ���ʱ�临�ӷ�Ϊ��O(n^2)��
	public static int getMaxSum(int[] a, int n)
	{
		int maxValue = Integer.MIN_VALUE;
		int sum;
		for(int i = 0; i < n; i++)
		{
			sum = 0;//ÿһ�˵õ����˵�����sumֵʱ��Ҫ���¹��㣨important��
			for(int j = i; j < n; j++)
			{
				sum += a[j];
				if(sum > maxValue)
				maxValue = sum;
			}
		}
		return maxValue;
	}
	
	//2�����η�����Ϊ3�Σ�ǰ��Σ����Σ������м�����ֵ������
	//ʱ�临�Ӷ�ΪO(NlogN��
	public static int getMaxSum(int[] a, int left,int right)
	{
		int maxLeftSum, maxRightSum;                 //���Ҳ����������������ֵ����Ӧ���a��b   
		int maxLeftBorderSum, maxRightBorderSum;     //���м�ֱ�����������������������ֵ����Ӧc
		int leftBorderSum, rightBorderSum;
		int center;
		if(left == right)  //ֻ��һ��Ԫ�أ� basic condition
			if(a[left] > 0)
				return a[left];
			else
				return 0;
		
		 center = (left + right)/2;
		 maxLeftSum = getMaxSum(a,left,center);
		 maxRightSum = getMaxSum(a,center + 1,right);
		 
		 //Ѱ���������߽�ֵ���������������
		 maxLeftBorderSum = Integer.MIN_VALUE;
		 leftBorderSum = 0;
		 for(int i = center; i >= left; i--)
		 {
			 leftBorderSum += a[i];
			 if(leftBorderSum > maxLeftBorderSum)
				 maxLeftBorderSum = leftBorderSum;
		 }
		 
		 //Ѱ���Ҳ�����߽�ֵ���������������
		 maxRightBorderSum = Integer.MIN_VALUE;
		 rightBorderSum = 0;
		 for(int i = center + 1; i <= right; i++)
		 {
			 rightBorderSum += a[i];
			 if(rightBorderSum > maxRightBorderSum)
				 maxRightBorderSum = rightBorderSum;
		 }
		 
		 int max1 = maxLeftSum > maxRightSum ? maxLeftSum : maxRightSum; //���a,b�����ֵ
		 int max2 = maxLeftBorderSum + maxRightBorderSum;
		 return max1 > max2 ? max1: max2;
	}
	
	public static void main(String[] args) {
		int[] array = new int[]{-2, 5, 3, -6, 4, -8, 6};
		System.out.println(getMaxSum(array, 7));
		System.out.println(getMaxSum(array,0,array.length - 1));
	}

}
