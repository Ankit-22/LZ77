import java.lang.*;
import java.util.*;

public class Bytewriter
{
	public static byte setBit(byte b,int pos)
	{
		b |= (byte) (1 << pos);
		return b;
	}
	public static byte clearBit(byte b,int pos)
	{
		b &= (byte) ~(1 << pos);
		return b;
	}
	public static byte[] stringToByte(String binary)
	{
		int count = 0;
		int length = binary.length()/8;
		byte[] b = new byte[length];
		for(int i = 0,j=0;i<binary.length();i+=8,j++)
		{
			for(int k = 0;k<8;k++)
			{
				//System.out.print(binary.charAt(i+k));
				count++;
				if(binary.charAt(i+k)=='1')
				{
					b[j]=setBit(b[j],k+1);
				}
				else
				{
					b[j]=clearBit(b[j],k+1);
				}
			}
			//System.out.println(j);
			//System.out.println((char)b[j]);
		}
		//System.out.println(count);
		return b;
	}
	public static void main(String[] args) 
	{
		String s = "1100001001001110100001101000001101001001110011100000011100000001001100101000001010100000001100000011100000101100000010100001001000001010";
		byte[] b = Bytewriter.stringToByte(s);
		for (int i=0;i<b.length;i++ ) 
		{
			System.out.print(b[i]);
		}
	}
}