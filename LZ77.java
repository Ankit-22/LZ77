import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.lang.*;

public class LZ77
{
	private String data;
	public String decode()
	{
		HashMap<Integer,String> dictionary = new HashMap<Integer,String>();
		int i = 0;
		String output = "";
		int count = 255;
		while(data.charAt(i)=='1')
			i++;
		String wordLengthBinary = data.substring(i,i+8);
		i+=8;
		int wordLength = Integer.parseInt(wordLengthBinary,2);
		while(i<data.length())
		{
			String temp = data.substring(i,i+wordLength);
			Integer key = new Integer(Integer.parseInt(temp,2));
			Integer nextKey;
			String next;
			if(i+wordLength<data.length())
			{
				next = data.substring(i+wordLength,i+2*wordLength);
				nextKey = new Integer(Integer.parseInt(next,2));
			}
			else
			{
				nextKey = -1;
				next = null;
			}
			if(nextKey == -1&&key<256)
			{
				output += (char)(int)key;
			}
			else if (nextKey == -1 && key>255)
			{
				output += dictionary.get(key);
			}
			else if(key<256&&nextKey<256)
			{
				dictionary.put(++count,""+(char)(int)key+(char)(int)nextKey);
				output += (char)(int)key;
			}
			else if(key<256&&nextKey>255)
			{
				dictionary.put(++count,""+(char)(int)key+dictionary.get(nextKey).substring(0,1));
				output += (char)(int)key;
			}
			else if(key>255&&nextKey<256)
			{
				dictionary.put(++count,dictionary.get(key)+(char)(int)nextKey);
				output += dictionary.get(key);
			}
			else
			{
				dictionary.put(++count,dictionary.get(key)+dictionary.get(nextKey).substring(0,1));
				output += dictionary.get(key);
			}
			i+=wordLength;
		}
		return output;
	}
	public String encode()
	{
		String[] arr = new String[1000000];
		int count = 255;
		int i = 0,flag = 0,lc = 0;
		HashMap<String,Integer> dictionary = new HashMap<String,Integer>();
		for(i = 0; i<data.length()-1; i++)
		{
			if(dictionary.get(""+data.charAt(i)+data.charAt(i+1))==null)
			{
				String temp = Integer.toBinaryString((int)data.charAt(i));
				arr[i]=temp;lc++;
				dictionary.put(""+data.charAt(i)+data.charAt(i+1),++count);
			}
			else
			{
				String st = ""+data.charAt(i)+data.charAt(i+1);
				int j=2;
				while(dictionary.get(st)!=null&&(i+j)<data.length())
				{
					st+=data.charAt(i+j);
					j++;
				}
				i+=j-2;
				String stemp;
				if(dictionary.get(st)==null)
				{
					stemp = st.substring(0,st.length()-1);	
				}
				else
				{
					stemp = st;
					flag = 1;
				}
				int temp = dictionary.get(stemp);
				arr[i]=Integer.toBinaryString(temp);
				dictionary.put(st,++count);
				lc++;
			}
		}
		if(flag == 0)
		{
			arr[i] = Integer.toBinaryString(data.charAt(i));lc++;	
		}
		int noOfBits = Integer.toBinaryString(count).length();
		int legitBits = noOfBits*(lc)+8;
		int offsetBytes = 0;
		if(noOfBits%8 != 0)
			offsetBytes = (8-(legitBits%8))%8;
		String ans = "";
		for(int it = 0; it<offsetBytes; it++)
		{
			ans+="1";
		}
		String bits = Integer.toBinaryString(noOfBits);
		int l = 8-bits.length();
		while(l>0)
		{
			bits = "0"+bits;
			l--;
		}
		ans += bits;
		for(int x = 0; x<data.length(); x++)
		{
			if(arr[x]!=null)
			{
				l = noOfBits-arr[x].length();
				while(l>0)
				{
					arr[x] = "0"+arr[x];
					l--;
				}
				if(arr[x].length()>noOfBits)
				{
					return null;
				}
				ans+=arr[x];
			}
		}
		return ans;
	}
	LZ77(String data)
	{
		this.data = data;
	}
}
