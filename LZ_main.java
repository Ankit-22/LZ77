import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.lang.*;

class LZ77Main{

	private static void encoderFunction(String filename,String oFile)
	{
		String toEncode = "";
		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
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
			FileOutputStream fos = new FileOutputStream(oFile);
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
	}

	private static void decoderFunction(String filename,String oFile)
	{
		byte[] bFile = {};
		String filePath = filename;
		try
		{
			bFile = Files.readAllBytes(new File(filePath).toPath());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		String bin = Bytewriter.byteToString(bFile);
		LZ77 decoder = new LZ77(bin);
		String decoded = decoder.decode();
		byte[] toWrite = Bytewriter.UTFToISO(decoded);
		if(decoded == null)
		{
			System.out.println("There was an error in decoding..");
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream(oFile);
			fos.write(toWrite);
			fos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		if(args[0].equals("-c"))
		{
			encoderFunction(args[1],args[2]);
		}
		else if(args[0].equals("-d"))
		{
			decoderFunction(args[1],args[2]);
		}
	}
}
