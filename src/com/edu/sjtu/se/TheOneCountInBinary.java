package com.edu.sjtu.se;

public class TheOneCountInBinary {
	
	//����1������������ж�������ֵ������ 
	//ʱ�临�Ӷ�ΪO(v)  v����v�Ķ�����λ��
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
	
	// ����2������λ����������
	//�������ʹ�����Ʋ�����ģ2���Ժ�1�����ж����һλ�����
	//ʱ�临�Ӷȣ� O(logv�� logvΪ�����Ƶ�λ����������ĸ��Ӷ���ͬ
	public static int getOneCount2(byte v)
	{
		int num = 0;
		while(v != 0)
		{
//			if((v & 0x01) != 0)
//				num ++;
			//������д��
			num += v & 0x01;  // �ж�ÿһ�����һλ�Ƿ�Ϊ1����Ϊ1��num��1����Ϊ0��num��0
			v >>= 1;//������һλ
		}
		return num;
	}
	
	//����3�� ʹ��v &= (v-1),��������ʹ�ý�v�еĴ��������Ϊ1λĥƽΪ0��ÿһ�ο���ĥƽһ��1��
	//����0�Ĳ���Ҫĥƽ��ֱ������Ϊ1�ĵط�
	//ʱ�临�Ӷ�ΪO(M),MΪv�е�1�ĸ������˷��ϼѣ�
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
	
	//����4���������256�������е����ȫ���г�һ�ű������������ÿ������Ӧ�Լ��Ķ����Ƹ�����1�ĸ�����
	//int countTable[256]= {0��1��1��2��1��2��2��3������}
	//��Ƶ��ʹ�ô��㷨��Ӧ���п�ȡ��
	
	public static void main(String[] args) {
		int i = 23;
		byte b = (byte) i;
		System.out.println(getOneCount1(b));
		System.out.println(getOneCount2(b));
		System.out.println(getOneCount3(b));
	}

}
