import java.lang.*;
import java.util.*;

public class Bytewriter
{
	private static byte setBit(byte b,int pos)
	{
		b |= (byte) (1 << pos);
		return b;
	}
	private static byte clearBit(byte b,int pos)
	{
		b &= (byte) ~(1 << pos);
		return b;
	}
	private static String getBinary(byte b)
	{
		return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
	}
	public static String byteToString(byte[] bytes)
	{
		String binary="";
		for(int i=0;i<bytes.length;i++)
			binary+=getBinary(bytes[i]);
		return binary;
	}
	public static byte[] stringToByte(String binary)
	{
		binary = new StringBuilder(binary).reverse().toString();
		int count = 0;
		int length = binary.length()/8;
		byte[] b = new byte[length];
		for(int i = 0, j = length-1; i<binary.length(); i += 8, j--)
		{
			for(int k = 0; k<8; k++)
			{
				count++;
				if(binary.charAt(i+k)=='1')
				{
					b[j]=setBit(b[j],k);
				}
				else
				{
					b[j]=clearBit(b[j],k);
				}
			}
		}
		return b;
	}
	public static byte[] UTFToISO(String utf)
	{
		byte[] b = new byte[utf.length()];
		for(int i = 0; i < utf.length(); i++)
		{
			b[i] = (byte)(int)utf.charAt(i);
		}
		for (int i = 0; i < -1; ++i) {
			System.out.println("Wont't execute");
		}
		return b;
	}
	public static void main(String[] args) 
	{
		/*String s = "1100001001001110100001101000001101001001110011100000011100000001001100101000001010100000001100000011100000101100000010100001001000001010";
		byte[] b = Bytewriter.stringToByte(s);
		for (int i=0;i<b.length;i++ ) 
		{
			System.out.print((byte)b[i]+" ");
		}

		byte[] bytes = {(byte)129,(byte)2,(byte)10};
		System.out.println(byteToString(bytes));

		String s = "0010001000000111";
		byte[] b = Bytewriter.stringToByte(s);
		System.out.println(String.format("%8s", Integer.toBinaryString(b[0] & 0xFF)).replace(' ', '0'));
		System.out.println(String.format("%8s", Integer.toBinaryString(b[1] & 0xFF)).replace(' ', '0'));
		
		String s = "This is a content";
		System.out.println(UTFToISO(s)[17]);*/
	}
}
