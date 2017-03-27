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
		System.out.println("In");
		String[] arr = new String[100000];
		int count = 256;
		HashMap<String,Integer> dictionary = new HashMap<String,Integer>();
		for(int i = 0; i<data.length(); i++)
		{
			if(dictionary.get(""+data.charAt(i)+data.charAt(i+1))==null)
			{
				System.out.println(dictionary.get(""+data.charAt(i)));
				String temp = Integer.toBinaryString((int)data.charAt(i));
				/*int l = 9-temp.length()
				while(l>0)
				{
					temp = "0"+temp;
					l--;
				}*/
				arr[i]=temp;
				Integer inte = new Integer(count);
				count++;
				System.out.println(inte);
				dictionary.put(""+data.charAt(i)+data.charAt(i+1),inte);
				System.out.println(""+data.charAt(i)+data.charAt(i+1));
				System.out.println(dictionary.get(""+data.charAt(i)+data.charAt(i+1)));
			}
			else
			{
				System.out.println("inside");
				String st = ""+data.charAt(i);
				int j=1;
				while(dictionary.get(st)!=null&&(i+j)<data.length())
				{
					st+=data.charAt(i+j);
					j++;
				}
				String stemp = st.substring(0,st.length()-1);
				int temp = dictionary.get(stemp);
				arr[i]=Integer.toBinaryString(temp);
				dictionary.put(st,++count);
			}
		}
		String ans = "";
		for(int i = 0; i<data.length(); i++)
		{
			ans+=arr[i];
		}
		return ans;
	}
	public static void main(String[] args) 
	{
		LZ77 encoder = new LZ77("thisisthe");
		System.out.println(encoder.encode());
	}
}