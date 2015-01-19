package com.edu.sjtu.se;

//寻找任意一个数阶乘中10的个数
public class TenCountInFactorial 
{
	//思路：首先寻找阶乘中5的个数M，然后寻找阶乘中2的个数N，最后10的个数就是min(M,N)
	public static int getFactorial(int N)
	{
		if(N == 1)
		    return 1;
		else 
			return N * getFactorial(N-1);
	}
	public static int getFiveCount(int N)
	{
	    int num = 0;
	    while(N!=0)
	    {
	    	num += N/5;
	    	N /=5;
	    }
	    return num;
	}
	
	public static int getTwoCount(int N)
	{
		int ret = 0;
		while(N!=0)
		{
			ret += N/2;
			N /= 2;
		}
		return ret;
	}
	
	public static int getTenCount(int N)
	{
		return min(getFiveCount(N), getTwoCount(N));
	}
	
	public static int min(int a, int b)
	{
		return a < b ? a : b;
	}
	
	public static void main(String[] args) {
		int Number = 10;
		System.out.println(getFactorial(Number));
		System.out.println(getTenCount(Number));
	}

}
