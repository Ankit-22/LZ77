import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.lang.*;

public class LZ77
{
	private String data;
	public String decode()
	{
		
		return null;
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
	public static void main(String[] args) 
	{
		String toEncode = "";
		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader("contents.txt"));
  			StringBuffer stringBuffer = new StringBuffer();
  			String line = null;
  			while((line =bufferedReader.readLine())!=null)
  			{
	 
   				stringBuffer.append(line).append("\n");
  			}
  			toEncode = stringBuffer.toString();
  			
  		}catch (IOException ioe) {
  			ioe.printStackTrace();
  		}
		LZ77 encoder = new LZ77(toEncode);
		String a=encoder.encode();
		if(a == null)
		{
			System.out.println("Only use ASCII characters.. UTF-8 not supported yet.. Check for \"’\" and such characters not supported in ASCII..");
			return;
		}
		byte[] b = Bytewriter.stringToByte(a);
		try{
			FileOutputStream fos = new FileOutputStream("answer.txt");
			int i;
			for (i=0;i<b.length;i++) 
			{
				fos.write((byte)b[i]);
			}	
			if(fos != null)
				fos.close();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}

		byte[] bFile = {};
		String filePath = "answer.txt";
		try
		{
			bFile = Files.readAllBytes(new File(filePath).toPath());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		String bin = Bytewriter.byteToString(bFile);
		System.out.println(bin);
		LZ77 decoder = new LZ77(bin);

	}
	LZ77(String data)
	{
		this.data = data;	
	}
}