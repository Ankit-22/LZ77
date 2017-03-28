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
		//System.out.println("In");
		String[] arr = new String[100000];
		int count = 256;
		int i = 0,flag = 0;
		HashMap<String,Integer> dictionary = new HashMap<String,Integer>();
		for(i = 0; i<data.length()-1; i++)
		{
			if(dictionary.get(""+data.charAt(i)+data.charAt(i+1))==null)
			{
				//System.out.println(dictionary.get(""+data.charAt(i)));
				String temp = Integer.toBinaryString((int)data.charAt(i));
				/*int l = 9-temp.length()
				while(l>0)
				{
					temp = "0"+temp;
					l--;
				}*/
				arr[i]=temp;
				dictionary.put(""+data.charAt(i)+data.charAt(i+1),++count);
				//System.out.println(""+data.charAt(i)+data.charAt(i+1));
				//System.out.println(dictionary.get(""+data.charAt(i)+data.charAt(i+1)));
			}
			else
			{
				//System.out.println("inside");
				String st = ""+data.charAt(i)+data.charAt(i+1);
				//System.out.println(st);
				int j=2;
				//System.out.println(dictionary.get(st));
				//System.out.println((i+j)<data.length());
				while(dictionary.get(st)!=null&&(i+j)<data.length())
				{
					//System.out.println(data.charAt(i+j));
					st+=data.charAt(i+j);
					j++;
				}
				i+=j-2;
				//System.out.println("The string: "+st);
				String stemp;
				//System.out.println(""+(i+2)+(data.length()));
				if(dictionary.get(st)==null)
				{
					stemp = st.substring(0,st.length()-1);
					//System.out.println("i+2<data.length()");
				}
				else
				{
					stemp = st;
					flag = 1;
				}
				System.out.println("Entered: "+stemp);
				int temp = dictionary.get(stemp);
				arr[i]=Integer.toBinaryString(temp);
				dictionary.put(st,++count);
				//System.out.println("Out");
			}
		}
		if(flag == 0)
		{
			//System.out.println("In here");
			arr[i] = Integer.toBinaryString(data.charAt(i));
			//System.out.println(arr[i]);
		}
		int noOfBits = Integer.toBinaryString(count).length();
		//System.out.println(""+noOfBits);
		String ans = "";
		for(int x = 0; x<data.length(); x++)
		{
			if(arr[x]!=null)
			ans+=arr[x]+" ";
		}
		return ans;
	}
	public static void main(String[] args) 
	{
		String toEncode = "";
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try{
			toEncode = bf.readLine();
		}catch(IOException io)
		{
			io.printStackTrace();
		}
		LZ77 encoder = new LZ77(toEncode);
		System.out.println(encoder.encode());
	}
}