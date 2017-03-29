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
		int i = 0,flag = 0,lc = 0;
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
				arr[i]=temp;lc++;
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
				//System.out.println("Entered: "+stemp);
				int temp = dictionary.get(stemp);
				arr[i]=Integer.toBinaryString(temp);
				dictionary.put(st,++count);
				lc++;
				//System.out.println("Out");
			}
		}
		if(flag == 0)
		{
			//System.out.println("In here");
			arr[i] = Integer.toBinaryString(data.charAt(i));lc++;
			//System.out.println(arr[i]);
		}
		int noOfBits = Integer.toBinaryString(count).length();
		int legitBits = noOfBits*(lc)+8;
		//System.out.println(""+lc+legitBits+noOfBits);
		int offsetBytes = 0;
		if(noOfBits%8 != 0)
			offsetBytes = (8-(legitBits%8))%8;
		//System.out.println(""+noOfBits);
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
  		/*try{
			toEncode = bf.readLine();

		}catch(IOException io)
		{
			io.printStackTrace();
		}*/
		//toEncode="thisisthithi";
		LZ77 encoder = new LZ77(toEncode);
		String a=encoder.encode();
		byte[] b = Bytewriter.stringToByte(a);
		try{
			FileOutputStream fos = new FileOutputStream("answer.txt");
			int i;
			for (i=0;i<b.length;i++ ) 
			{
				fos.write((byte)b[i]);
			}
			System.out.println(i);
			if(fos != null)
				fos.close();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		//bytes[0] |= (byte) (1 << 5);
		//bytes[0] &= (byte) ~(1 << 5);
		System.out.println(encoder.encode());
	}
}