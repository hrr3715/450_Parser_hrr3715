package edu.louisiana.cacs.csce450GProject;

import java.util.LinkedList;
import java.util.Stack;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Parser{
	
	static Stack<String> initialStack = new Stack<String>();
	static LinkedList<String> inputtokens = new LinkedList<String>();
	static String actionlookup = null;
	static String actionvalue = null;
	static String valueofLHS = null;
	static int lengthofRHS = 0;
	static Stack<String> tempstack = new Stack<String>();
	static String gotolookup = null;
	static String gotovalue = null;
	static String stackaction = "";
	static LinkedList<String> tokens = new LinkedList<String>();
	static String startingvalue = "";
	String [] Grammar = {"","E=E+T","E=T","T=T*F","T=F","F=(E)","F=id"};
	int [] rhslength = {0,3,1,3,1,3,1};	
	static String parseTreeStack = "";
	static scanner scan = new scanner();
	ParseTable pt = new ParseTable();
	public Parser(String fileName){
		System.out.println("File to parse : "+fileName);
		System.out.println("Stack \t\tinputtokens \t\tactionlookup \t\tactionvalue \t\tvalueofLHS \t\tLengthofRHS \t\ttempstack \t\tgotolookup \t\tgotovalue \t\tStackaction \t\tParseTreeStack");
	}
	/*
	* Dummy code
	*/
	public void printParseTree(){
		System.out.println("Hello World from " + getClass().getName());
	}

	/*
	* Dummy code
	*/
	public void parse() throws FileNotFoundException, IOException{
		scan.buildTokenTree(inputtokens);
		/*
		inputtokens.add("id");
		inputtokens.add("+");
		inputtokens.add("id");
		inputtokens.add("*");
		inputtokens.add("id");
		inputtokens.add("$");
		*/
		initialStack.push("0");
		
		System.out.println(inputtokens.toString());
		int a = 0;
		while (!startingvalue.equalsIgnoreCase("a"))
		{
				createInputTokens(initialStack.lastElement(),inputtokens.element());
				actionvalue = pt.createParseTable(inputtokens.element(),Integer.parseInt(initialStack.lastElement()));
				startingvalue = actionvalue.substring(0, 1);
				if (startingvalue.equalsIgnoreCase("s") ){
					stackaction = "push "+inputtokens.element()+actionvalue.substring(1, 2);
					
					
					System.out.println(removeSpecChar(initialStack.toString()) + removeSpecChar(inputtokens.toString()) +actionlookup.toString() + actionvalue.toString() +  stackaction.toString());
					pushStack(inputtokens.element() , actionvalue.substring(1, 2));
					popList();
					if (parseTreeStack.equals("") ||  inputtokens.element().equalsIgnoreCase("id"))
					{
						parseTreeStack  = "id" + parseTreeStack ;
					}
					else if (inputtokens.element().matches("[a-zA-Z]")){
						parseTreeStack  = "["+inputtokens.element() + parseTreeStack+"]" ;
					}
					
				}
				else if (startingvalue.equalsIgnoreCase("R")){
					valueofLHS = Grammar[Integer.parseInt(actionvalue.substring(1, 2))].substring(0, 1);
					lengthofRHS = rhslength[Integer.parseInt(actionvalue.substring(1, 2))];
					tempstack = (Stack<String>) initialStack.clone(); 
					poptempStack(lengthofRHS*2);
					gotolookup = "["+tempstack.lastElement()+","+valueofLHS+"]";
					gotovalue = pt.createParseTable(valueofLHS,Integer.parseInt(tempstack.lastElement()));
					stackaction = "push "+ valueofLHS + gotovalue;
					System.out.println(initialStack.toString() + inputtokens.toString() +actionlookup.toString() + actionvalue.toString() + valueofLHS.toString() + lengthofRHS + tempstack.toString() + gotolookup.toString() + gotovalue.toString() + stackaction.toString());
					popinitialStack(lengthofRHS*2);
					pushStack(valueofLHS , gotovalue);
					if (parseTreeStack.equals("") ||  inputtokens.element().equalsIgnoreCase("id"))
					{
						parseTreeStack  = "id" + parseTreeStack ;
					}
					else if (lengthofRHS == 3){
						parseTreeStack  = "["+inputtokens.element() + parseTreeStack+"]" ;
					}
				}
				else if (startingvalue.equalsIgnoreCase("a")){
					actionvalue = "Accept";
					System.out.println(initialStack.toString() + inputtokens.toString() +actionlookup.toString() + actionvalue.toString());
				}
				a++;
		}
		printParseTree();
	}
	
	public void createInputTokens(String str1,String str2){
		actionlookup = "["+str1+","+str2+"]";
	}
	public void popList(){
		inputtokens.remove();
	}
	public void pushStack(String s1 , String s2){
		initialStack.push(s1);
		initialStack.push(s2);
	}
	public void poptempStack(int i){
		int j =0;
		while (j < i)
		{
			tempstack.pop();
			j++;
		}
	}
	
	public void popinitialStack(int i){
		int j =0;
		while (j < i)
		{
			initialStack.pop();
			j++;
		}
	}
	public String removeSpecChar(String str){
		String a = str;
		String v=a.replaceAll("[^0-9a-zA-Z+*]", "");
		
		
		return v;
	}
	

}