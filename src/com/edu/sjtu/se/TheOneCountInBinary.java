package com.edu.sjtu.se;

public class TheOneCountInBinary {
	
	//方法1：利用相除和判断余数的值来分析 
	//时间复杂度为O(v)  v代表v的二进制位数
	public static int getOneCount1(byte v)
	{
		int num = 0;
		while(v != 0)
		{
			if(v%2 != 0)
			{
				num++;
			}
			v /= 2;
		}
		return num;
	}
	
	// 方法2：利用位操作来分析
	//相除可以使用又移操作，模2可以和1相与判断最后一位的情况
	//时间复杂度： O(logv） logv为二进制的位数，和上面的复杂度相同
	public static int getOneCount2(byte v)
	{
		int num = 0;
		while(v != 0)
		{
//			if((v & 0x01) != 0)
//				num ++;
			//更简洁的写法
			num += v & 0x01;  // 判断每一次最后一位是否为1，若为1，num加1；若为0，num加0
			v >>= 1;//向右移一位
		}
		return num;
	}
	
	//方法3： 使用v &= (v-1),这样可以使得将v中的从右向左的为1位磨平为0，每一次可以磨平一个1，
	//遇到0的不需要磨平，直接跳到为1的地方
	//时间复杂度为O(M),M为v中的1的个数，此法上佳！
	public static int getOneCount3(byte v)
	{
		int num = 0;
		while(v != 0)
		{
			v &= (v-1);
			num++;
		}
		return num;
	}
	
	//方法4：查表法，将256个数所有的情况全部列成一张表，在这个数组中每个数对应自己的二进制个数中1的个数。
	//int countTable[256]= {0，1，1，2，1，2，2，3，……}
	//在频繁使用此算法的应用中可取。
	
	public static void main(String[] args) {
		int i = 23;
		byte b = (byte) i;
		System.out.println(getOneCount1(b));
		System.out.println(getOneCount2(b));
		System.out.println(getOneCount3(b));
	}

}
