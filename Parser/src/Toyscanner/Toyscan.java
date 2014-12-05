package Toyscanner;


import java.io.*;

public class Toyscan extends scan{
	 static FileReader inputStream=null;
public static void main(String args[]) throws IOException,FileNotFoundException
{
	try {
		inputStream=new FileReader("C:\\Users\\hrushith\\450 Java\\data\\sample.txt");
		scan.file(inputStream);    
		 
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
	    if(inputStream!=null){
	        try
	        {
	            inputStream.close();
	        }
	        catch(IOException e)
	                {
	                  System.out.println("File close error");
	                }
	    }
	}
                                       

}

}
