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
	
	//3.��̬�滮��
	//ʱ�临�Ӷ�ΪO(n),�ռ临�Ӷ�ΪO(n)
	//�������ÿһ��Ԫ��Ϊ��ʼԪ�صĺ��������������ֵ
	//����˼·��ÿ��Ԫ��a[i]��ʼ������ĺ͵����ֵ��Ϊstart[i]���������a[i]���ڵ�������
	//�������еĿ��ܵ�������������е�ֵ����All[0]��Ѱ�ң������0��ʼ������Ԫ�ص������е����ֵ
	//��Ӧ�Ľ�Ϊ��All[i]��max{a[0],a[0]+start[i],All[i+1]},ÿһ��ǰ���All[i]�ǻ��ں����all[i+1]��ֵ��
	public static int getMaxSum3(int[] a, int n)
	{
		//��ʼ��
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
	
	//4.���ڶ�̬�滮����������
	//�����㷨3����Ϊÿһ��start[i]���ǻ���a[i]��start[i+1]���ߵ�ֵ,
	//all[i]Ҳ�ǻ���start[i]��all[i+1]���ߵ�ֵ���������Ҫ�õ����յĽ����
	//ֻ��Ҫ����ÿһ�ε�nStart��nAll��ֵ������ǰ���ֵ,ʵ����ֻ��Ҫall[0]��ֵ��
	//�����м�Ľ��������Ҫ
	//ʱ�临�Ӷ�ΪO(n)���ռ临�Ӷ�ΪO(1)
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
	
	//��������ȫ��Ϊ������������������һ������Ϊ��������������ܻ���ֱ��죬�����㷨2���ܻ��С����
	
	
	
	public static void main(String[] args) {
		int[] array = new int[]{-2, -5, -3, 6, -4, -8, -6,0,8,-9,-7,-6};
		System.out.println(getMaxSum(array, array.length));
		System.out.println(getMaxSum(array,0,array.length - 1));
		System.out.println(getMaxSum3(array,array.length));
		System.out.println(getMaxSum4(array,array.length));
	}

}
