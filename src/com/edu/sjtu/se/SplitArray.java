package com.edu.sjtu.se;

import java.util.Set;
import java.util.TreeSet;

//����ָ�����
//��һ������Ԫ�ظ���Ϊ2n�����������顣
//����ܰ��������ָ��ΪԪ�ظ���Ϊn���������飬��ʹ������������ĺ����


//���ʣ��Һ�һ����������
public class SplitArray {
	
	public static boolean split(int[] a, int n)
	{
		//���壺TreeSet[i]��ʾ�洢��arr��ȡi�������ܲ����ĺ͵ļ��ϡ�
		//��ʼ����TreeSet�ʼֻ��һ��0
		TreeSet<Integer>[] set = new TreeSet[n];
		set[0].add(0);
		for(int k = 0; k < n; k++)
		{
			int i_max = min(k, n/2-1);
			for(int i = i_max; i >=0; i--)
			{
				for(int j : set[i])
					set[i+1].add(j + a[k]);
			}
		}
		
		for(int i : set[n/2])
			if(getSum(a)/2 ==i)
				return true;
		
		return false;
		
	}
	
	public static int getSum(int[] a)
	{
		int sum = 0;
		for(int i = 0; i < a.length; i++)
		{
			sum += a[i]; 
		}
		return sum;
	}
	public static int min(int a, int b)
	{
		return a < b ? a : b; 
	}

	public static void main(String[] args)
	{
		int[] array = new int[]{1, 2,3,4};
		System.out.println(split(array, array.length));
		
	}
}
