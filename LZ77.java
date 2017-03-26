import java.io.*;
import java.util.*;
import java.lang.*;

public class LZ77
{
	private String data;
	LZ77(String data)
	{
		this.data = data;
	}
	public String encode()
	{
		String[] arr = new String[10000];
		int count = 256;
		HashMap<String,String> dictionary = new HashMap<String,String>();
		for(int i = 0; i<data.length(); i++)
		{
			if(dictionary.get(""+data.charAt(i))==null)
			{
				arr[i]=Integer.toBinaryString((int)data.charAt(i));
				
			}
		}
	}
	public static void main(String[] args) 
	{
		
	}
}