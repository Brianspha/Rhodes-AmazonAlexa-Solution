import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class QuestionParser {
	@SuppressWarnings({ "resource"})
	public static String[] ReadQA(String path)
	{
		
		FileReader in=null;
		try {
			in = new FileReader(path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(in);
		try{
		reader = new BufferedReader(new FileReader(path));
		
		String myData = "";
		String line = "";
		while((line = reader.readLine()) != null) myData += line;
		return myData.split(":");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String[]{"Didn't work"};
		
	}
}
