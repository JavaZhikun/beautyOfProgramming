package com.edu.sjtu.se;

import java.util.Set;
import java.util.TreeSet;

//数组分割问题
//有一个无序，元素个数为2n的正整数数组。
//如何能把这个数组分割成为元素个数为n的两个数组，并使得两个子数组的和最近


//本质：找和一半的数组情况
public class SplitArray {
	
	public static boolean split(int[] a, int n)
	{
		//定义：TreeSet[i]表示存储从arr中取i个数所能产生的和的集合。
		//初始化：TreeSet最开始只有一个0
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
