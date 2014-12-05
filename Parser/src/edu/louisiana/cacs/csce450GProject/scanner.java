package edu.louisiana.cacs.csce450GProject;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class scanner {

	
	static int charClass=0;
    static String lexeme="";
    static char nextChar=0;
    static int nextToken=0;
	static final int LETTER=0;
	static final int DIGIT=1;
	static final int UNKNOWN=99;
	static final int EOF=-1;
	static final int INT_LIT=10;
    static final int IDENT=11;
    static final int ASSIGN_OP=20;
    static final int ADD_OP=21;
    static final int SUB_OP=22;
    static final int MULT_OP=23;
    static final int DIV_OP=24;
    static final int LEFT_PAREN=25;
    static final int RIGHT_PAREN=26;
    static String fileName = System.getProperty("user.dir")+"\\data\\sample.txt";
	static FileReader inputStream=null;
	static LinkedList <String> iptoken = new LinkedList<String>();
	public void buildTokenTree(LinkedList <String> inputtokens) throws IOException,FileNotFoundException
	
	{
		iptoken = inputtokens;
		try {
			inputStream=new FileReader(fileName);
			file(inputStream);    
			 
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

	 public static void file(FileReader f)
		 {
			 inputStream=f;
			 try {
					getChar();
					   do{
						   iptoken.add( lex());                                          
						    }while(nextToken!=EOF);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		 }
	
	public static void addChar()
    {
        
            lexeme=lexeme + nextChar;
        }
    public static void getChar()throws IOException
    {
         nextChar=(char)inputStream.read();  
        if((nextChar)!=EOF) 
     {
      if(Character.isLetter(nextChar))
          charClass=LETTER;
      else if(Character.isDigit(nextChar))
          charClass=DIGIT;
      else charClass=UNKNOWN;
     }
     else charClass=EOF;
    }
    public static void getNonBlank() throws IOException
    {
        while(Character.isSpaceChar(nextChar))
        {
            getChar();
        }
    }

    
    public static int lookup(char ch)
    {
        switch(ch)
        {
                case'(':
                addChar();
                nextToken=LEFT_PAREN;
                break;
                case')':
                addChar();
                nextToken=RIGHT_PAREN;
                break;
                case'+':
                addChar();
                nextToken=ADD_OP;
                break;
                case'-':
                addChar();
                nextToken=SUB_OP;
                break;
                case'*':
                addChar();
                nextToken=MULT_OP;
                break;
                case'/':
                addChar();
                nextToken=DIV_OP;
                break;
                default:
                addChar();
                nextToken=EOF;
                break;
        }
        return nextToken;
    }
	  public static String lex() throws IOException
	    {
	        lexeme="";
	        getNonBlank();
	        switch(charClass)
	        {
	                case LETTER:
	                addChar();
	                getChar();
	                while(charClass==LETTER||charClass==DIGIT){
	                    addChar();
	                    getChar();
	                }
	                nextToken=IDENT;
	                break;
	                case DIGIT:
	                addChar();
	                getChar();
	                while(charClass==DIGIT){
	                    addChar();
	                    getChar();
	                }
	                nextToken=INT_LIT;
	                break;
	                case UNKNOWN:
	                lookup(nextChar);
	                getChar();
	                break;
	                case EOF:
	                nextToken=EOF;
	                lexeme="EOF";    
	        }
	        
	        return lexeme;
	        
	    }
}